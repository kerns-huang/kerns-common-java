### 
基于spring cloud，把swagger 硬编码的代码做成可配置的代码，不用重复的去配置这部分的代码。

### 在nacos上的配置demo
swagger:
  title:  用户服务 
  description: >
     本地测试，请求header需要添加uid，appid，platform.
  basePackage: com.xd.user.controller
  excludePath:
  -  /error
  - /actuator/**