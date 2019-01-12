#!/bin/bash

# mvn clean package -Dmaven.test.skip=true -U

# docker build -t hub.c.163.com/springcloud/eureka .

# docker push hub.c.163.com/springcloud/eureka

echo "------启动eureka服务---";



echo "启动redis"
docker start d3334b8df3c7
echo "启动redis完成"

echo "启动rabbitmq"
docker start dfeb6dc182e8
echo "启动rabbitmq完成"


echo "启动注册中心"
cd /Users/codingprh/Documents/javaSoftware/spring_cloud_action/spring_cloud_eureka/sh
sh springBoot.sh start ../target/eureka_server.jar
echo "启动注册中心完成"

echo "启动配置中心"
cd /Users/codingprh/Documents/javaSoftware/spring_cloud_action/spring_cloud_config/sh
sh springBoot.sh start ../target/config_server.jar
echo "启动配置中心完成"

