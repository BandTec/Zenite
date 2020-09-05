FROM node:12

WORKDIR .

COPY package*.json ./

# RUN npm install
# If you are building your code for production
RUN npm ci --only=production

COPY . .

EXPOSE 3000

CMD [ "npm", "start" ]

# sudo docker build -t zenite-frontend
# sudo docker run -p 4661:3000 -d zenite-frontend
# sudo docker ps -a
# sudo docker logs <ID>