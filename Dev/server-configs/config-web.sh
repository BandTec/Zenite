#!/bin/bash

yum install docker -y
service docker start
systemctl enable docker
chmod 777 /var/run/docker.sock
chmod -R 777 /home/ec2-user/Zenite/.git
cd /home/ec2-user/Zenite/Dev/frontend

docker build -t web-server .
docker run -p 8081:3000 -d web-server
docker run -p 8082:3000 -d web-server

cd /home/ec2-user/Zenite/Dev/server-configs
docker build -t load-balance .
docker run -p 80:80 -d load-balance
