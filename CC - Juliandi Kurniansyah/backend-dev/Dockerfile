FROM node:20

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY . .

ENV PORT=3000

ENV MODEL_URL=https://storage.googleapis.com/athena-dev-bucket/pytorch_model.bin.index.json


CMD ["npm", "start"]
