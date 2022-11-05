#!/bin/bash

# description: 使用Docker安装Mysql5.7
# author: 莫提

version=5.7
password=123456
port=3306

if [[ $1 == -v* ]];then
	version=${1:2}
elif [[ $1 == -P* ]];then
	password=${1:2}
elif [[ $1 == -p* ]];then
	port=${1:2}
fi

if [[ $2 == -v* ]];then
	version=${2:2}
elif [[ $2 == -P* ]];then
	password=${2:2}
elif [[ $2 == -p* ]];then
	port=${2:2}
fi

if [[ $3 == -v* ]];then
	version=${3:2}
elif [[ $3 == -P* ]];then
	password=${3:2}
elif [[ $3 == -p* ]];then
	port=${3:2}
fi


echo " "
echo "============================"
echo "=======开始安装MySQL========"
echo "============================"
echo "Version：$version"
echo "Password：$password"
echo "Port：$port"
echo "============================"
echo " "

# 拉去镜像
docker pull mysql:$version

# 运行镜像
docker run --restart=always -p $port:3306 --name mysql$version -e MYSQL_ROOT_PASSWORD=$password -d mysql:$version


echo "============================"
echo "=======安装MySQL成功========"
echo "============================"

docker ps
