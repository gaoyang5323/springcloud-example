# springcloud-example

### 版本
| 依赖            | 版本号                                 |
|---------------|-------------------------------------|
| spring-cloud-alibaba       | 2021.1                                |
| spring-cloud          | 2020.0.1                                |
| spring-boot         | 2.4.2                                |


### 服务
| 服务名                  | 端口或地址 |
|----------------------|-------|
| nacos 服务注册发现及配置中心    | 8848     |
| sentinel 限流及熔断降级     | 9080     |
| gateway 网关           | 8080     |
| user 用户中心            | 8081     |
| order 订单中心           | 8082     |
| xxl-job-admin 调度管理中心 |  http://localhost:8888/xxl-job-admin|
| xxl-job 调度执行         | 8889     |
| zipkin 分布式追踪系统        |  9411     |


### 碰到的问题
1.碰到idea打包问题,idea右侧maven点击父工程,进行clean install,然后可以正常子项目package等操作;  
2.idea中运行正常,打包jar运行报错parse data from Nacos error;  
配置内容编码不一致导致。  
配置文件中有中文，而nacos读取配置文件时默认编码为utf-8，而通过cmd启动项目默认使用gbk; 
启动时指定编码格式:  
```java -jar -Dfile.encoding=utf-8  xxx.jar```