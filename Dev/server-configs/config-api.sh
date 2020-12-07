yum install docker -y
service docker start
systemctl enable docker
chmod 777 /var/run/docker.sock
chmod -R 777 /home/ec2-user/Zenite/.git

cd /home/ec2-user/Zenite/Dev/backend

yum install java-1.8.0-openjdk.x86_64 -y
cp /etc/profile /etc/profile_backupcp /etc/profile /etc/profile_backup
echo 'export JAVA_HOME=/usr/lib/jvm/jre-1.8.0-openjdk' | sudo tee -a /etc/profile
echo 'export JRE_HOME=/usr/lib/jvm/jre' | sudo tee -a /etc/profile
source /etc/profile
./mvnw package && java -jar target/*-0.1.0.jar

docker build -t api-server .
docker run -p 8081:8080 -d back-server
docker run -p 8082:8080 -d back-server
cd /home/ec2-user/Zenite/Dev/server-configs
docker build -t load-balance .
docker run -p 8080:80 -d load-balance