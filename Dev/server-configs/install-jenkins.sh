#!/bin/bash

rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
yum -y install java-1.8.0-openjdk* wget ant ant-junit
cd /etc/yum.repos.d/
curl -O https://pkg.jenkins.io/redhat-stable/jenkins.repo
yum -y install jenkins
systemctl start jenkins
systemctl enable jenkins