FROM node:12

WORKDIR .

COPY package*.json ./frontend

RUN npm install
# If you are building your code for production
# RUN npm ci --only=production

COPY . .

EXPOSE 3000

CMD [ "npm", "start" ]