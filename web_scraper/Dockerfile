FROM mcr.microsoft.com/playwright:v1.52.0-jammy

WORKDIR /app

ENV PLAYWRIGHT_BROWSERS_PATH=/data/browsers

RUN npm init -y
RUN npm install playwright express
RUN npm install eureka-js-client


# Böngészők telepítése ide: /data/browsers
RUN npx playwright install --with-deps

COPY . .

ENV EXPRESS_SERVER_PORT=4000

CMD ["node", "server.js"]
