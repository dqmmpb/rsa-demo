# RSA Demo

> **RSA算法**的相关知识可参看：  
百度百科[RSA算法][1]  
知乎[RSA算法的加密原理是什么？][2]  
阮一峰的网络日志[RSA算法原理（一）][3]  
阮一峰的网络日志[RSA算法原理（二）][4]

## 基本流程
+ Client生成Client端的公私钥(cPub,cPriv)
+ Server生成Server端的公私钥(sPub,sPriv)
+ Client使用Server的sPub将message加密，提交给Server
+ Server使用Server端的sPriv将message解密
+ Server使用Client端的cPub将response的message加密，返回给Client
+ Client使用Client端的cPriv将返回的message解密

## 运行

```
 mvn tomcat7:run
```

## 访问

http://localhost:8080/rsa/web/v1/index

[1]: http://baike.baidu.com/link?url=xmptW-i-04zzpBeSEhxRdUu33Xp_osV7i0e2MGTNbPzh3LME6S_ERt4ViHGp0D_RInCP4PSfNpNlMeSdsCzmTGbqGOrj3GQX0tdo8v3-16V59rN4Xng8FgSLJdh1L1va "RSA算法——百度百科"
[2]: https://www.zhihu.com/question/25038691 "RSA算法的加密原理是什么？——知乎"
[3]: http://www.ruanyifeng.com/blog/2013/06/rsa_algorithm_part_one.html "RSA算法原理（一）——阮一峰的网络日志"
[4]: http://www.ruanyifeng.com/blog/2013/07/rsa_algorithm_part_two.html "RSA算法原理（二）——阮一峰的网络日志"
