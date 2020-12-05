docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi web-server
docker rmi load-balance
cd /home/ec2-user/Zenite/Dev/frontend
git pull

docker build -t web-server .
docker run -p 8081:3000 -d web-server
docker run -p 8082:3000 -d web-server

cd /home/ec2-user/Zenite/Dev/server-configs
docker build -t load-balance .
docker run -p 80:80 -d load-balance