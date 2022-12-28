FROM node:alpine
WORKDIR /app
RUN chmod 777 .
COPY package.json .
RUN npm config set legacy-peer-deps true
RUN npm install -g npm@9.2.0
RUN npm install
COPY . .
CMD ["npm","start"]