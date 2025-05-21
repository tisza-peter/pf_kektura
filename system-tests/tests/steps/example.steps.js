const { Given, Then } = require('@cucumber/cucumber');
const { chromium } = require('@playwright/test');
const assert = require('assert');

let browser, page;

Given('a felhasználó megnyitja a {string} oldalt', async function (url) {
  browser = await chromium.launch();
  const context = await browser.newContext();
  page = await context.newPage();
  await page.goto(url);
});

Then('a cím tartalmazza a {string} szót', async function (expected) {
  const title = await page.title();
  assert(title.includes(expected));
  await browser.close();
});
