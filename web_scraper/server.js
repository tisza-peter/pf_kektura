const express = require('express');
const { chromium } = require('playwright');
const Eureka = require('eureka-js-client').Eureka;

const app = express();
const port = parseInt(process.env.EXPRESS_SERVER_PORT || '4000', 10);
if (isNaN(port)) {
  console.error('Hibás port beállítás!');
  process.exit(1);
}

// Eureka kliens beállítása
const client = new Eureka({
  eureka: {
    host: process.env.EUREKA_HOST || 'kektura-eureka-server', // Eureka szerver hostja
    port: process.env.EUREKA_PORT || 8080, // Eureka portja
    servicePath: '/eureka/apps/',
  },
  instance: {
    app: 'webscraper', // Szolgáltatás neve, amit regisztrálni szeretnél Eureka-ban
    hostName: 'web-scraper', // Hostnév, ahol az alkalmazás fut
    ipAddr: 'web-scraper', // IP cím, ami elérhető lesz Eureka számára
    port: {
      $: port, // Az Express alkalmazás portja
      '@enabled': true, // Engedélyezett port
    },
    vipAddress: 'webscraper', // Virtuális IP cím
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn', // A data center neve
    },
  },
});

// Retry mechanizmus, ha a Eureka szerver nem elérhető
const startClientWithRetry = (retries = 50, delay = 2000) => {
  client.start(err => {
    if (err) {
      console.error('Hiba a Eureka kliens indításakor:', err);
      if (retries > 0) {
        console.log(`Újrapróbálkozás ${retries} alkalommal, ${delay / 1000} másodperc múlva...`);
        setTimeout(() => startClientWithRetry(retries - 1, delay), delay);
      } else {
        console.error('Még mindig nem sikerült csatlakozni az Eureka szerverhez!');
        process.exit(1); // Ha több próbálkozás után sem sikerül, kilépünk
      }
    } else {
      console.log('Szolgáltatás sikeresen regisztrálva Eureka-ban');
    }
  });
};

// Elindítjuk a kliens próbálkozást
startClientWithRetry();

// Az alkalmazás leállításakor
process.on('SIGINT', () => {
  client.stop(err => {
    if (err) {
      console.error('Hiba a Eureka kliens leállításakor:', err);
    } else {
      console.log('Szolgáltatás sikeresen leállítva Eureka-ban');
    }
    process.exit();
  });
});

app.use(express.json());

// POST /query endpoint: megadott URL-ről, adott CSS szelektorral adatokat nyer ki
app.post('/query', async (req, res) => {
  const { url, selector } = req.body;

  if (!url || !selector) {
    return res.status(400).json({ error: "Missing 'url' or 'selector' in request body" });
  }

  const browser = await chromium.launch({ headless: true });
  const context = await browser.newContext();
  const page = await context.newPage();

  try {
    await page.goto(url, { waitUntil: 'domcontentloaded' });
    await page.waitForSelector(selector, { timeout: 5000 });

    const results = await page.$$eval(selector, elements =>
      elements.map(e => e.href || e.innerText || e.getAttribute('src') || e.outerHTML)
    );

    res.setHeader('Content-Type', 'application/json');
    res.json({ results });
  } catch (err) {
    console.error('Hiba történt a lekérés során:', err);
    res.status(500).json({ error: err.message });
  } finally {
    await browser.close();
  }
});

// Express szerver indítása
app.listen(port, () => {
  console.log(`Playwright kereső API fut a következő címen: http://localhost:${port}/query`);
});
