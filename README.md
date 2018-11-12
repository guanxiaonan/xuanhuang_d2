### 目录结构
```
├── dmx-config-server				//配置中心
├── dmx-eureka-server				//服务注册和发现
├── dmx-one-service					//统一API网关
├── dmx-inventory-service			//库存服务
├── dmx-material-service			//物料服务
├── dmx-produce-task-service		//生产任务服务
└── dmx-user-service				//用户服务
```

### 项目说明
该项目是物料管理平台的后台服务部分,只提供API接口。
使用到的相关技术：SpringBoot/SpringCloud/Mybatis/MySQL/Quartz
SpringCloud 使用到的组件包括：Eureka、Feign、Config

> ps: dmx-produce-task-service用到了远程配置和Quartz

### 前端界面

