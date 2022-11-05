#!/bin/bash

# description: CentOS7安装Docker
# author: 莫提

# 卸载旧版本

if [ -n "$1" ]; then
   # 校验传入参数 
    if [ "$1" = "-r" ]
    then
	echo "开始卸载已有Docker服务..."
	systemctl stop docker
	yum -y remove docker-ce
	rm -rf /var/lib/docker
	yum -y remove docker docker-common docker-selinux docker-engine
    else
       	echo " "
	echo "=================================="
	echo "=============安装失败============="
	echo "=================================="
	echo "非法参数：$1"
	echo "=================================="
	echo "首次安装：./docker-install.sh"
	echo "重新安装：./docker-install.sh -r"
	echo "=================================="
	echo " "
    fi
fi

echo "开始安装Docker服务..."

# 安装需要的软件包
yum install -y yum-utils device-mapper-persistent-data lvm2
# 设置stable镜像仓库
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
# 更新yum软件索引
yum makecache fast
# 安装Docker-ce
yum -y install docker-ce
# 启动Docker
systemctl start docker
# 开机自启
systemctl enable docker
# 配置Docker镜像加速器
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://c2yf9ia3.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker

echo " "
echo "=================================="
echo "=============安装成功============="
echo "=================================="
echo " "

docker ps
