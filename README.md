# Rsa Demo

> **RSA算法**的相关知识可参看：  
百度百科[RSA算法][1]  
知乎[RSA算法的加密原理是什么？][2]  
阮一峰的网络日志[RSA算法原理（一）][3]
阮一峰的网络日志[RSA算法原理（二）][4]

## 公私钥
> 公私钥（java和js相同）
### 公私钥的javascript代码
```javascript
  var RSA_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzwYuXTPEtWV49OS/Cb5QIbX2LtZBHhCi9hiR2hrJEcWA4vUmC8GsMMOKw933VxAurjw1Llhj4QXpKZi9hfOlc6bn7GoyAZpVgl+JAzwQFuTOSJyRacgGDef0BY0zW/kQZjILI7ovqXwAcSaOGhFQgy6OWAkNDeKqGQFXeMwr9owIDAQAB";
  var PRI_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALPBi5dM8S1ZXj05L8JvlAhtfYu1kEeEKL2GJHaGskRxYDi9SYLwawww4rD3fdXEC6uPDUuWGPhBekpmL2F86VzpufsajIBmlWCX4kDPBAW5M5InJFpyAYN5/QFjTNb+RBmMgsjui+pfABxJo4aEVCDLo5YCQ0N4qoZAVd4zCv2jAgMBAAECgYEAjlxHMEFodFDluLkUoPl7FJ2aI05dALajCU42jIQqpNfhq64FjSTYsqP4tMydJPIJiApYLjemeN5qeoepGJ0ztuU/QM56Q59c6S4nBxDhc619dvt9jABsompCl6yrPYzi3FG1TmJa4ugBOln+KS/Pw/aSaEu4E1CUOYgYuIljT1kCQQDWo9fwnEpdH2WoW4E1ARwuV++SfsV7szJtSIBEKuhIzRDLMEB97yoEffWVYgncou+XYXdMn5Eb0dkRP4u07ZUvAkEA1mThgvhz0gyM08GxaXIT/+9JskCuCvvcDTVJVhVebeK/y0rZGBsvIMuPux+6wy4S2qqj+BwjSfP+71JLrPopzQJAFx2aGe2bDKBfAFyqc5zk/hC2Wl6QwhuwaJiQR8cfMQf0sQ1HRMjHC6jNFAN08HATwYfbo0LkC8zzxanET/3uPQJBAKYaPJmzElC/xm/dVi2C47nbU3aWJAGAhkl5alsWbTWngr7nO3Ewxn+bFr18ZL8JODRQFn+IlVKbhn02fkkC/FUCQDD7cPsWeab+iJlam4gooS/suDryNqWQFFsuZDAuseDzQKpa7Qvd/5TH5eiiWpUoKCySjQapO+JXHjIb1PQm4Rw=";
```

## js加密，js解密

> 使用js加密数据，使用js解密数据（每次加解密，密文都不一样）
### 加密明文
Javascript中文
### Js加密后
```
ooZvZoyRzOFvHPEMidBuhE7vl8PxbgZYtJmH+2bqb7E9vPoGVpeFdiIb65EJ5W7fweWX8YZ4775B64/wg68s3FlowG2utpFRpu+tiulMKeeF2fYnr28IvzIJcn7nJJ9tO9T+8fAYIRL2JRNnjGi6orZkETPkiugsXAm/ac6bpPQ=
```
### Js解密后
Javascript中文

## java加密，js解密

> 使用java加密数据，使用js解密数据（密文由java程序提供）
### 加密明文
Java中文2
### Js加密后
```
Hj3MnvCmFw6o+ggqLadnoMca2AJCedicY5CSoF0Q1CPZn6EIidqjDuBD8++2IXEFD+RTWutvhiSSce8Ot09n7JifwWFc3g5hcorHDM/e9MsC0PMxG32AMyZnqMxX+1T0xXnlHI8lr+0q4rNtGZ1f0uaE/ZO3MDzqClmamQO5I/0=
```
### Js解密后
Java中文2

## js加密，js解密

> 使用js加密，使用js解密（密文由js程序提供）
### 加密明文
Javascript中文
### Js加密后
```
LpsvBoKDHlpRsnAsC5lqU198lr8G9t2P0mHI1DAY7oNk6mJ+qd8JYq2X3yyutZL7K2dERBJSATqmYaRgnQgWVlMjlOi+j/Ob5kcaqrPVXLFJpgtqLYgJ2Qq7ecw3LrPTAoHNqsReZ7ZzYkWeMkqQzaPuAAT9UmJI1KG7iyOBNFY=
```
### Js解密后
Javascript中文


[1]: http://baike.baidu.com/link?url=xmptW-i-04zzpBeSEhxRdUu33Xp_osV7i0e2MGTNbPzh3LME6S_ERt4ViHGp0D_RInCP4PSfNpNlMeSdsCzmTGbqGOrj3GQX0tdo8v3-16V59rN4Xng8FgSLJdh1L1va "RSA算法——百度百科"
[2]: https://www.zhihu.com/question/25038691 "RSA算法的加密原理是什么？——知乎"
[3]: http://www.ruanyifeng.com/blog/2013/06/rsa_algorithm_part_one.html "RSA算法原理（一）——阮一峰的网络日志"
[4]: http://www.ruanyifeng.com/blog/2013/07/rsa_algorithm_part_two.html "RSA算法原理（二）——阮一峰的网络日志"
