FROM mcr.microsoft.com/playwright:v1.44.0-jammy

WORKDIR /tests

COPY package.json package-lock.json* ./
RUN npm install

COPY . .

CMD ["npm", "test"]
