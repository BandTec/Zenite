docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi api-server
docker rmi load-balance
cd /home/ec2-user/Zenite/Dev/backend
git pull

./mvnw package && java -jar target/*-0.1.0.jar
docker build -t api-server .
docker run -p 8081:8080 -d api-server
docker run -p 8082:8080 -d api-server

cd /home/ec2-user/Zenite/Dev/server-configs
docker build -t load-balance .
docker run -p 8080:80 -d load-balance