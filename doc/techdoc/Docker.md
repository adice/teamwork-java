## Docker

### Docker的三大核心概念

	- 镜像（Image）
	- 容器（Container）
	- 仓库（Repository）

### 1.核心概念

  1. 镜像

     一个镜像可以包含一个基本的操作系统环境，镜像是创建Docker容器的基础

     镜像自身是只读的。容器在镜像启动时，会在镜像的最上层创建一个可写层

  2. 容器

     容器就像一个轻量级的沙箱，Docker利用容器来运行和隔离应用

  3. 仓库

     他是Docker集中存放镜像文件的场所
     
     

**镜像是什么**

镜像是一种轻量级、可执行的独立软件包，用来打包软件运行环境和基于运行环境开发的软件，它包含运行某个软件所需的所有内容，包括代码、运行时、库、环境变量和配置文件

所有的应用，直接打包docker镜像，就可以直接跑起来

如何得到镜像

- 从远程仓库下载
- 朋友拷贝
- 自己制作镜像

  

**Docker镜像加载原理**

​		分层

>  Docker镜像加载原理

![20200813123125523](Docker.assets/20200813123125523.png)

- **bootfs(boot file system)** 主要包含bootloader和kernel, bpotloader 主要是引导加载kernel,当我们加载镜像的时候，会通过bootloader加载kernal，Docker镜像最底层是bootfs，当boot加载完成后整个kernal内核都在内存中了，bootfs也就可以卸载，值得注意的是，bootfs是被所有镜像共用的，许多镜像images都是在base image(rootfs)基础上叠加的

- **rootfs (root file system)**，在bootfs之 上.包含的就是典型Linux系统中的/dev, /proc, /bin, /etc等标准目录和文件。rootfs就是 各种不同的操作系统发行版，比如Ubuntu, Centos等等 。

  ![image-20201030084207568](Docker.assets/image-20201030084207568.png)

### 2. Docker安装

```shell
#1、卸载旧版本
yum remove docker \
				docker-client \
				docker-client-latest \
				docker-common \
				docker-latest \
				docker-latest-logrotate \
				docker-logrotate \
				docker-engine

#1、安装需要的安装包
yum install -y yum-utils

#3、设置镜像的仓库
yum-config-manager \ 
	--add-repo \
	https://download.docker.com/linux/centos/docker-ce.repo 
#默认是国外，速度慢，可改成阿里
	https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo 

#更新yum软件包索引
yum makecache fast
	
#4、安装docker依赖 docker-ce社区版 ee企业版
yum install docker-ce docker-ce-cli containerd.io

#5、启动docker
systemctl start docker

#6、使用docker version查看是否安装完成
docker version

#7、docker hello，world
docker run hello-world

#8、查看hello world的镜像
docker images
```
阿里云镜像加速

```shell
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://6fo4x3ke.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

### 3、Docker的常用命令

#### 帮助命令

```shell
docker version      #docker 显示docker的版本信息
docker info         #docker 显示更多的详细信息
docker 命令 --help  #万能命令
```

官方文档：https://docs.docker.com/engine/reference/commandline/docker/

#### 镜像命令

**docker images** 查看本机上的本地镜像

```shell
[root@iZ2zebv278ligs0o43n61xZ ~]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
learn/ping          latest              5e4a24ec3dec        6 days ago          140MB
tomcat              latest              891fcd9c5b3a        9 days ago          647MB
hello-world         latest              bf756fb1ae65        9 months ago        13.3kB
learn/tutorial      latest              a7876479f1aa        7 years ago         128MB

#对应的信息
REPOSITORY  镜像的仓库源
TAG         镜像的标签
IMAGE ID    镜像的id
CREATED     镜像的创建时间
SIZE        镜像的大小

#可选项
-a #all
-q #只显示镜像id
```

**docker search**

```shell
[root@iZ2zebv278ligs0o43n61xZ ~]# docker search mysql

#可选项，通过搜索来过滤
--fliter=STARS=3000  #搜索stars>3000的镜像
```

**docker pull**

```shell
#下载镜像 docker pull 镜像名[:tag]
#不写tag，默认下载最新
[root@iZ2zebv278ligs0o43n61xZ ~]# docker pull mysql:5.7
5.7: Pulling from library/mysql
bb79b6b2107f: Pull complete #分层下载，当其他镜像中拥有时，不再下载
49e22f6fb9f7: Pull complete 
842b1255668c: Pull complete 
9f48d1f43000: Pull complete 
c693f0615bce: Pull complete 
8a621b9dbed2: Pull complete 
0807d32aef13: Pull complete 
f15d42f48bd9: Pull complete 
098ceecc0c8d: Pull complete 
b6fead9737bc: Pull complete 
351d223d3d76: Pull complete 
Digest: sha256:4d2b34e99c14edb99cdd95ddad4d9aa7ea3f2c4405ff0c3509a29dc40bcb10ef
Status: Downloaded newer image for mysql:5.7
docker.io/library/mysql:5.7 #真实地址
```

**docker rmi **

```shell
[root@iZ2zebv278ligs0o43n61xZ ~]# docker rmi -f 891fcd9c5b3a
#删除指定ID镜像
[root@iZ2zebv278ligs0o43n61xZ ~]# docker rmi -f $(docker images -sq)
#删除全部的容镜像
```



#### 容器命令

有了镜像才可以安装容器

1. 下载镜像

```shell
docker pull centos
```

2. 新建容器并启动

```shell
docker run [可选命令] image

#参数说明
--name="Name" 容器名字，用来区分容器
-d            后台运行
-it           使用交互方式运行，进入容器查看命令
-p            指定容器的端口   8080:8080   主机端口:容器端口
	
#启动并进入容器
[root@iZ2zebv278ligs0o43n61xZ ~]# docker run -it centos /bin/bash
[root@bf20eacb6cc1 /]#

#退出容器
[root@bf20eacb6cc1 /]# exit
exit

#查看当前正在运行的容器
[root@iZ2zebv278ligs0o43n61xZ ~]# docker ps

#查看运行过的历史
[root@iZ2zebv278ligs0o43n61xZ ~]# docker ps -a
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS                          PORTS               NAMES
bf20eacb6cc1        centos              "/bin/bash"              3 minutes ago       Exited (0) About a minute ago                       gallant_bassi
cd6d9cba520e        hello-world         "/hello"                 52 minutes ago      Exited (0) 52 minutes ago                           quizzical_chaum

```

**退出容器**

```shell
exit #直接退出并且停止
Ctrl + P + Q # 容器不停止退出
```

**删除容器**

```shell
docker rm 容器id  #不能删除正在运行的容器，强制删除 rm -f
docker rm -f $(docker ps -aq)
docker ps -a -q|xargs docker rm  #删除全部容器
```

**启动和停止容器的操作**

```shell
docker start 容器id
docker restart 容器id
docker stop 容器id
docker kill 容器id  #强制停止当前容器
```



#### 常用其他命令

**后台启动容器**

```shell
[root@iZ2zebv278ligs0o43n61xZ ~]# docker run -d centos

#问题：docker ps 可发现 centos停止了
docker容器后台运行，就必须要一个前台进程，docker发现没有应用，就会自动通知
#nginx，容器启动后， 发现自己没有提供服务，就会立刻停止，就是没有程序了
```

**查看日志**

```shell
docker logs
```

**查看容器中的进程消息 ps**

```shell
docker top 容器id
[root@iZ2zebv278ligs0o43n61xZ ~]# docker top 6ce54578ab18
UID                 PID                 PPID                C                   STIME               TTY                 TIME                CMD
root                2810                2794                0                   08:14               pts/0               00:00:00            /bin/bash

#查看镜像内部信息
[root@iZ2zebv278ligs0o43n61xZ ~]# docker inspect 598ab7c97781


```

**进入当前正在运行的容器**

```shell
#我们通常容器是使用后台方式运行的，需要进入容器，修改一些配置

#命令
docker exec -it 1869fba4ea21 /bin/bash

[root@iZ2zebv278ligs0o43n61xZ ~]# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
[root@iZ2zebv278ligs0o43n61xZ ~]# docker run -it centos /bin/bash
[root@1869fba4ea21 /]# [root@iZ2zebv278ligs0o43n61xZ ~]# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
1869fba4ea21        centos              "/bin/bash"         12 seconds ago      Up 12 seconds                           awesome_hoover
[root@iZ2zebv278ligs0o43n61xZ ~]# docker exec -it 1869fba4ea21 /bin/bash
[root@1869fba4ea21 /]# ls
bin  etc   lib	  lost+found  mnt  proc  run   srv  tmp  var
dev  home  lib64  media       opt  root  sbin  sys  usr

#命令二
[root@iZ2zebv278ligs0o43n61xZ ~]# docker attach 1869fba4ea21
正在执行当前的代码


#exec 进入容器后开启一个新的终端，可以在里面操作
#attach 进入容器正在执行的终端，不会启动新的进程
```

**从容器内主机拷贝到主机上**

```shell
docker cp 容器id：容器内路径 主机内路径
[root@iZ2zebv278ligs0o43n61xZ home]# docker attach 32a0fa36ecd2
[root@32a0fa36ecd2 /]# cd /home
[root@32a0fa36ecd2 home]# ls
[root@32a0fa36ecd2 home]# touch new.java
[root@32a0fa36ecd2 home]# exit
exit
[root@iZ2zebv278ligs0o43n61xZ home]# docker ps 
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
[root@iZ2zebv278ligs0o43n61xZ home]# docker ps -a
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                        PORTS               NAMES
32a0fa36ecd2        centos              "/bin/bash"         3 minutes ago       Exited (0) 13 seconds ago                         elegant_moore
1869fba4ea21        centos              "/bin/bash"         11 minutes ago      Exited (0) 7 minutes ago                          awesome_hoover
598ab7c97781        centos              "/bin/bash"         14 minutes ago      Exited (127) 14 minutes ago                       jovial_joliot
[root@iZ2zebv278ligs0o43n61xZ home]# docker cp 32a0fa36ecd2:/home/new.java /home
[root@iZ2zebv278ligs0o43n61xZ home]# ls
new.java  test.java  www

```

#### 安装Nginx

```shell
#搜索镜像/去docker hub有帮助文档信息
docker search nginx
#下载镜像
docker pull nginx
#运行测试
[root@iZ2zebv278ligs0o43n61xZ ~]# docker run -d --name nginx01 -p 0904：80 nginx
#注意：0904端口需要暴露，安全组进行配置
[root@iZ2zebv278ligs0o43n61xZ ~]# curl localhost:0904
```

### 4、可视化

- portainer

  ```shell
  docker run -d -p 8088:9000 \
  --restart=always -v /var/run/docker.sock:/var/run/docker.sock --privileged=true portainer/portainer
  ```

- Rancher(CI/CD)

**什么是portainer**

- Docker图形化界面管理工具！提供一个后台面板供我们操作！



**commit镜像**

```shell
#和git类似
docker comiit 提交容器成为一个新的副本

docker commit -m="提交的描述信息" -a="作者" 容器id 目标镜像名：[tag]
```

```shell
#1.启动一个默认的tomcat
#2.这个默认的tomcat是没有webapps应用（镜像的原因），官方默认webapps下面是没有文件的
#3.自己拷贝进去基本文件
#4.将修改后的容器提交为一个新的镜像，以后使用修改后的镜像即可
```



### 5、容器数据卷

docker的理念回顾

将应用和环境打包成一个镜像

如果数据都在容器中，那么我们容器删除，数据就会丢失  

**需求：数据可以持久化**

卷技术是一种数据共享的技术,Docker容器中产生的数据，同步到本地

目录的挂载，将我们容器内的目录，挂载到Linux上面

总结：容器的持久化和同步操作，容器间也可以实现数据共享

**使用数据卷**

> 方式一：直接使用命令来挂在 -v

```shell
 docker run -it -v /home/test:/home centos /bin/bash
```

安装MySQL

```shell
#获取MySQl
docker pull mysql:5.7
#数据的挂载，账号密码的配置
docker run -d -p 3310:3306 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql5.7 -e MYSQL_ROOT_PASSWORD=0904Lily --name mysql01 mysql:5.7

#
-d 后台运行
-p 端口映射
-v 卷挂载
-e 环境配置
--name 设置名称

#启动成功后，在本地能够进行连接
#3310和容器内的3306进行了映射
```

**具名和匿名挂载**

```shell
#匿名挂载
-v 容器内路径！
docker run -d -p --name nginx01 -v /ect/nginx nginx

#查看所有的 volume的情况
docker volume ls

#具名挂载
docker run -d -P --name nginx02 -v j-nginx:/etc/nginx nginx

#通过 -v 卷名:容器内路径
#查看一下卷在什么位置
[root@iZ2zebv278ligs0o43n61xZ ~]# docker volume inspect j-nginx
[
    {
        "CreatedAt": "2020-10-28T20:34:45+08:00",
        "Driver": "local",
        "Labels": null,
        "Mountpoint": "/var/lib/docker/volumes/j-nginx/_data",
        "Name": "j-nginx",
        "Options": null,
        "Scope": "local"
    }
]
```

所有卷在不设置绝对路径的情况下，默认都在`/var/lib/docker/volumes/`可以通过具名挂载可以方便找到卷，大多数情况下使用

```shell
-v 容器内路径       #匿名挂载
-v 卷名:容器内路径  #具名挂载
-v /宿主机路径:容器内路径   #指定路径挂载，本质也是匿名挂载
```

拓展：

```shell
#通过 -v 容器内路径：ro  rw  改变读写权限

docker run -d -P --name nginx02 -v j-nginx:/etc/nginx.ro nginx
#read only 只读
docker run -d -P --name nginx02 -v j-nginx:/etc/nginx.rw nginx
#read write 读写

#一旦设置了容器权限，容器对我们挂载出来的内容就有限定了！
#ro ：这个路径只能通过宿主机来操作，容器内是无法操作的
```

**数据卷容器**

命名的容器挂载数据卷，其他容器通过挂载这个父容器实现数据共享，挂载数据卷的容器称为数据卷容器

作用： 使用特定容器维护数据卷

volume（数据卷）

```shell
#启动centos01容器
docker run -it --name centos01 imageName
#centos02继承自centos01
docker run -it --name centos02 --volumes-from centos01 imageName
```



![image-20201028213409431](Docker.assets/image-20201028213409431.png)

容器之间配置信息的传递，数据卷容器的生命周期一直持续到没有容器使用为止

但是一旦持久化到了本地，本地的数据不会删除

### 6、DockerFile

**dockerfile介绍**

DockerFile就是用来构建docker镜像的构建文件，本质是命令脚本

步骤：

1. 编写一个dockerfile文件

2. docker build 构建成为镜像

3. docker run 运行镜像
4. dokcer push发布镜像（dockerHub或者阿里云镜像仓库）

```shell
#创建一个dockerfile
#vim dockerfile1
FORM centos

VOLUME ["volume01","volume2"]  
#匿名挂载

CMD echo "===end===="

CMD /bin/bash
#这里的命令都是镜像的一层
```

```shell
#由dockerfile转换为镜像
docker build -f /home/docker-test-volume/dockerfile1 -t leslie/cnetos:1.0 .
```

**DockerFile的构建过程**

基础知识：

1. 每个保留关键字（指令）都是必须是大写字母

   DockeFile’：构建文件，定义了一切的步骤，源代码

   DockerImage：通过DoclerFile生成的镜像，最后发布和运行的产品（包括jar包和war包）

   Docker容器：容器为镜像运行起来提供服务器

2. Docker指令
	```shell
   FROM            #基础镜像
   MAINTAINER      #指定维护者信息  姓名+邮箱
   RUN             #镜像构建的时候需要运行的命令
   ADD             #步骤：如添加Tomcat等，能自动解压
   WORKDIR         #镜像的工作目录
   VOLUME          #挂载的目录
   EXPOSE          #暴露端口配置
   CMD             #指定这个容器启动的时候要运行的命令，只有最后一个会生效，可被替代
   ENTRYPOINT      #指定这个容器启动的时候要运行的命令，可以追加命令
   ONBUILD         #当构建一个被继承DockerFIle 这个时候就会运行ONBUILD的指令，出发指令
   ENV             #构建的时候设置环境变量
   ```


```shell
#1.编写DockerFile文件
[root@iZ2zebv278ligs0o43n61xZ dockerFile]# cat myDockerFile-images
FROM centos
MAINTAINER tong<2585463717@qq.com>

ENV MYPATH /user/local
WORKDIR $MYPATH

RUN yum -y install vim
RUN yum -y install net-tools

EXPOSE 80

CMD echo $MYPATH
CMD echo "---end----"
CMD /bin/bash

#2.构建镜像
[root@iZ2zebv278ligs0o43n61xZ dockerFile]# docker build -f myDockerFile-images -t mycentos:1.0 .
##注意：-t tag中不能有大写字母

#3.进入测试
```

对比

![image-20201101145219243](Docker.assets/image-20201101145219243.png)

![image-20201101145255827](Docker.assets/image-20201101145255827.png)

**发布自己的镜像**

> DockerHub

```shell
docker login -u username
docker push username/tag:version
#push
```

> 阿里云

1. 登录阿里云，找到容器镜像服务
2. 创建命名空间，创建容器镜像
3. 使用查看官方文档

**小结**

![image-20201101193834014](Docker.assets/image-20201101193834014.png)

![image-20201101194434343](Docker.assets/image-20201101194434343.png)

### 7、Docker网络（容器编排 集群部署）

```shell
#[root@iZ2zebv278ligs0o43n61xZ ~]# docker run -d -P --name tomcat01 tomcat
#查看容器内部的网络地址 ip addr
[root@iZ2zebv278ligs0o43n61xZ ~]# docker exec -it tomcat01 ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
56: eth0@if57: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:12:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 172.18.0.2/16 brd 172.18.255.255 scope global eth0
       valid_lft forever preferred_lft forever
       
#linux 可以ping通容器内部
[root@iZ2zebv278ligs0o43n61xZ ~]# ping 172.18.0.2
PING 172.18.0.2 (172.18.0.2) 56(84) bytes of data.
64 bytes from 172.18.0.2: icmp_seq=1 ttl=64 time=0.073 ms
64 bytes from 172.18.0.2: icmp_seq=2 ttl=64 time=0.060 ms
64 bytes from 172.18.0.2: icmp_seq=3 ttl=64 time=0.058 ms
64 bytes from 172.18.0.2: icmp_seq=4 ttl=64 time=0.057 ms
^C
--- 172.18.0.2 ping statistics ---
#同时tomcat01也可以ping通tomcat02
[root@iZ2zebv278ligs0o43n61xZ ~]# docker exec -it tomcat02 ping 172.18.0.2
PING 172.18.0.2 (172.18.0.2) 56(84) bytes of data.
64 bytes from 172.18.0.2: icmp_seq=1 ttl=64 time=0.104 ms
64 bytes from 172.18.0.2: icmp_seq=2 ttl=64 time=0.069 ms
64 bytes from 172.18.0.2: icmp_seq=3 ttl=64 time=0.071 ms
64 bytes from 172.18.0.2: icmp_seq=4 ttl=64 time=0.068 ms
^C
--- 172.18.0.2 ping statistics ---


```

> 原理

192.168.0.1 路由器

都是同一网段下的

1. 我们每启动一个docker容器，docker就会给docker容器分配一个ip，只要安装了docker，就会有一个网卡docker0，桥接模式，使用的技术是evth-pair技术

2. evth-pair 就是一对虚拟设备接口，他们都是成对出现的，一段连着协议，一段彼此相连

   ![image-20201103085222876](Docker.assets/image-20201103085222876.png)

   结论：tomcat01和tomcat02是公用的一个路由器，docker0

   所有的容器不指定网络的情况下，都是docker0路由的，dokcer会给我们的容器分配一个默认的可用ip

   ![image-20201103085617354](Docker.assets/image-20201103085617354.png)

   docker中的所有网络接口都是虚拟的，虚拟转发效率高

   只要容器删除，对应网桥一对就消失了

   
   
   **容器连接**
   
   同一个宿主机上的多个docker容器之间如果想进行通信，可以通过使用容器的ip地址来通信，也可以通过宿主机的ip加上容器暴露出的端口号来通信，前者会导致ip地址的硬编码，不方便迁移，并且容器重启后ip地址会改变，除非使用固定的ip，后者的通信方式比较单一，只能依靠监听在暴露出的端口的进程来进行有限的通信。通过docker的link机制可以通过一个name来和另一个容器通信，link机制方便了容器去发现其它的容器并且可以安全的传递一些连接信息给其它的容器。
   
   ```shell
   #在创建第二个容器时，将第二个link到第一个容器中
   docker run -d --name test2 --link test1 busybox /bin/sh -c "while true;do sleep 3600 ;done"
   #进入第二个容器内部，发现可以ping通test1，但是test1是ping不通test2的，是单方向的
   ```
   
   
   
   

### 8、Docker Compose

### 9、Docker Swarm

### 10、CI/CD之Jenkins

