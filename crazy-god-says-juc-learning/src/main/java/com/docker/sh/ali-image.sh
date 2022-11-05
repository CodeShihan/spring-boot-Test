#!/bin/bash

# description: 更换Centos7默认镜像源为阿里源
# author: 莫提

cd /etc/yum.repos.d/

# 备份原配置
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup


# 下载阿里镜像配置
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo


echo " "
echo "=================="
echo "更换阿里镜像源成功"
echo "=================="
echo " "

# 重新生成缓存
yum makecache
