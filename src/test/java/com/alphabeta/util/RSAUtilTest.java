package com.alphabeta.util;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAUtilTest {

    private static final String PUBLIC_Key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsrxY7zwnU8/u9a5KClEjEHDiGyMXYBJUr+il5Pw9KsVqFuhydjV7EiMBTykQ4XuZdaBWqlA7KMOio9xsL8nuMZOSu3fsmuSvMjt6gX/7kd3TQO2Tbjs8sFokJ3LPYqrhWhHCxFe52USMiKEanKbSZch1uPF1+pQHfV4zo8Mu6FQIDAQAB";

    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKyvFjvPCdTz+71rkoKUSMQcOIbIxdgElSv6KXk/D0qxWoW6HJ2NXsSIwFPKRDhe5l1oFaqUDsow6Kj3Gwvye4xk5K7d+ya5K8yO3qBf/uR3dNA7ZNuOzywWiQncs9iquFaEcLEV7nZRIyIoRqcptJlyHW48XX6lAd9XjOjwy7oVAgMBAAECgYEAk2mb506kq//j5R3RolsHizI0Jwt5qSCwXyxc/z4PxcmE5yerievG/Kto056VgjGxIgfahxWBUqVR1/uqQRas1A2j5/de8Y+LcpNrEuwF8YgOWmK3EAty0pgHQ1ezYSaxJ2AMBF427UrzMpGrB77UEzGE07GxbbC/sK/u66h0A/kCQQD89Q3OmWV8Gxie8XkWHeiUhseo3kZ9AYy7tRpsEkTkkWZAK2znphdHl35yDk0Cqu4uCe3usz6TfRlWu+3WK5k3AkEArsLXtUUt1IVeM0Z0Oxz8AWMb4v1lJiS4BhotZs7fyZ6DnMd+LIdfqQCLl9j3hCzdxEqIqmcuL2uGy1OYdfz9EwJAF+lGM9hWOoQJMMUcsBWFrbyL1Q+l1B04Y2n8JGkZsA16f+ha9A7ENpVAc6Gcb/seZqWzoxO4f5KcuZEsK0mVwwJAIp4qCJhZib2ZeWK9Z3BIYyX0wjQbs0CWy26oC7NzFQc3XvkNf1iZlGqtPDkYXrBchaOWCttBhNcx7ljy3HxuzQJAQxcxqCOUmLJah+Mtjb+aJQ2L6Lg3mBA62WNGxXDzpX2pAcJVZ7bNcsBq41rOpQEtQ8bEyj/Nfxxsxy/F57xuCQ==";

    private static PublicKey publicKey = null;
    private static PrivateKey privateKey = null;

    static {
        try {
            publicKey = RSAUtil.getPublicRSAKey(PUBLIC_Key);
            privateKey = RSAUtil.getPrivateRSAKey(PRIVATE_KEY);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * java加密， java解密
     */
    @Test
    public void testEncryptJavaAndDecryptJava() {
        try {
            String plainText = "Java中文";
            System.out.println("明文：" + plainText);
            String encryptedText = RSAUtil.encryptToString(plainText, publicKey);
            System.out.println("Java加密后：" + encryptedText);
            String decryptedText = RSAUtil.decryptToString(encryptedText, privateKey);
            System.out.println("Java解密后：" + decryptedText);
            Assert.assertEquals(plainText, decryptedText);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
