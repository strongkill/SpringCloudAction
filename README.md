# springCloud实战

本次开发环境为：

```java
spring-boot:2.1.1.RELEASE
spring-cloud.version:Greenwich.RC1
```

## springCloud netflix-eureka

eureka担任的角色为服务注册中心

### 搭建高可用的netflix-eureka-server

1. 引入相关依赖

   ```java
           <!--eureka-server端服务器配置-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
           </dependency>
   ```

2. 配置@EnableEurekaServer注解

   ```java
   @SpringBootApplication
   @EnableEurekaServer
   public class SpringCloudDemoApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(SpringCloudDemoApplication.class, args);
       }
   
   }
   ```

3. 配置相关yml文件

   ```java
   #*****eureka的配置*******
   eureka:
     client:
       serviceUrl:
   #      defaultZone: http://${cluster.node1.hostname}:${cluster.node1.port}/eureka/
         defaultZone: http://127.0.0.1:8762/eureka/
   #    禁止eureka-server注册成客户端,搭建集群不能开启该功能。
   #    register-with-eureka: false
     server:
   #  eureka的自我保护机制：开发环境开启，生产环境关闭
       enable-self-preservation: false
     instance:
       hostname: 127.0.0.1
   #*****项目常用配置********
   spring:
     application:
       name: eureka1
   #*****项目常用配置********
   server:
     port: 8761
   #*****自定义配置：用于配置eureka集群搭建的注册中心**********
   cluster:
     node1:
       hostname: 127.0.0.1
       port: 8761
     node2:
       hostname: 127.0.0.1
       port: 8762
     node3:
       hostname: 127.0.0.1
       port: 8763
   ```

4. 启动检查

   ![image-20181224101541222](https://ws4.sinaimg.cn/large/006tNbRwgy1fyhm8dimnqj310c03y3yy.jpg)

   通过在idea启动多个application，但是采用不同的配置文件。搭建集群完成，最重要是上面的yml文件，并且两两注册。

![image-20181224101923213](https://ws1.sinaimg.cn/large/006tNbRwgy1fyhmc5hyrfj30ji0bcdi5.jpg)

**注：在生产环境上，至少要搭建两台eureka服务器。互相注册。**

### 搭建netflix-eureka总结

![image-20181224102021684](https://ws1.sinaimg.cn/large/006tNbRwgy1fyhmd6288aj30j00ba416.jpg)

## springCloud Ribbon

ribbon担任的角色是负载均衡

## springCloud Config

如何正确的读取配置文件：

```shell
#规则
/{name}-{profiles}.yml
/{label}/{name}-{profiles}.yml
#解释
name 服务名
profiles 环境
label 分支(branch)
```

利用mq实现不用部署，更新配置文件

![image-20181230103136368](https://ws3.sinaimg.cn/large/006tNbRwly1fyokeq7sowj318u0nwqbr.jpg)

**如何区分SpringCloud版本：**

GA:general availability 面向大众的可用版本

M：miltone 里程碑版本

SNAPSHOT：快照，代码可变。

## springCloud Feign和RestTemplate

feign实现服务之间的通信

### 实现进程通信方式

1. 直接RestTemplate方式，写死url。

   ```java
   //服务进程通信：方式一：直接new RestTemplate()的方式
   RestTemplate restTemplate = new RestTemplate();
   String respone = restTemplate.getForObject("http://localhost:8081/productDemo/msg", String.class);
   ```

   注：url写死

2. 使用RestTemplate和LoadBalancerClient方式

   ```java
   //服务进程通信：方式2：使用loadBalanceClient，来获取服务名
   ServiceInstance serviceInstance = loadBalancerClient.choose("product");
   String url = String.format("http://%s:%s/productDemo/msg", serviceInstance.getHost(), serviceInstance.getPort());
   String respone = restTemplate.getForObject(url, String.class);
   ```

3. 使用feign方式

   ```java
   #第一步：先引入依赖
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
           </dependency>
   #第二步：使用注解
   @EnableFeignClients(basePackages = "com.codingprh.springcloud.order_client.client")
   #定义提供服务的接口
   @FeignClient(name = "order", fallback = OrderClient.OrderClientFallback.class)
   ```

## SpringZuul网关简介：

## 项目的架构图

![image-20181231114246305](https://ws2.sinaimg.cn/large/006tNbRwly1fyps32ulq5j31020s512b.jpg)

## 项目结构介绍

### 同步流程

商品下单流程：order视角

1. 查询商品信息（调用商品服务）
2. 计算总价（生成订单详情）
3. 商品服务扣库存（调用商品服务）
4. 订单入库（生成订单）

### 异步扣库存（异步下单）

商品下单流程：order视角

1. 查询商品信息（商品信息保存在redis中）
2. 订单服务创建订单写入数据库，并发送信息

# 声明

注：**文章内容仅用作学习和分享; 如有雷同, 纯属拷贝;** 

