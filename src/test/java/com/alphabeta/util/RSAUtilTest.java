package com.alphabeta.util;

import org.junit.Ignore;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;

@Ignore
public class RSAUtilTest {

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsrxY7zwnU8/u9a5KClEjEHDiGyMXYBJUr+il5Pw9KsVqFuhydjV7EiMBTykQ4XuZdaBWqlA7KMOio9xsL8nuMZOSu3fsmuSvMjt6gX/7kd3TQO2Tbjs8sFokJ3LPYqrhWhHCxFe52USMiKEanKbSZch1uPF1+pQHfV4zo8Mu6FQIDAQAB";
    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKyvFjvPCdTz+71rkoKUSMQcOIbIxdgElSv6KXk/D0qxWoW6HJ2NXsSIwFPKRDhe5l1oFaqUDsow6Kj3Gwvye4xk5K7d+ya5K8yO3qBf/uR3dNA7ZNuOzywWiQncs9iquFaEcLEV7nZRIyIoRqcptJlyHW48XX6lAd9XjOjwy7oVAgMBAAECgYEAk2mb506kq//j5R3RolsHizI0Jwt5qSCwXyxc/z4PxcmE5yerievG/Kto056VgjGxIgfahxWBUqVR1/uqQRas1A2j5/de8Y+LcpNrEuwF8YgOWmK3EAty0pgHQ1ezYSaxJ2AMBF427UrzMpGrB77UEzGE07GxbbC/sK/u66h0A/kCQQD89Q3OmWV8Gxie8XkWHeiUhseo3kZ9AYy7tRpsEkTkkWZAK2znphdHl35yDk0Cqu4uCe3usz6TfRlWu+3WK5k3AkEArsLXtUUt1IVeM0Z0Oxz8AWMb4v1lJiS4BhotZs7fyZ6DnMd+LIdfqQCLl9j3hCzdxEqIqmcuL2uGy1OYdfz9EwJAF+lGM9hWOoQJMMUcsBWFrbyL1Q+l1B04Y2n8JGkZsA16f+ha9A7ENpVAc6Gcb/seZqWzoxO4f5KcuZEsK0mVwwJAIp4qCJhZib2ZeWK9Z3BIYyX0wjQbs0CWy26oC7NzFQc3XvkNf1iZlGqtPDkYXrBchaOWCttBhNcx7ljy3HxuzQJAQxcxqCOUmLJah+Mtjb+aJQ2L6Lg3mBA62WNGxXDzpX2pAcJVZ7bNcsBq41rOpQEtQ8bEyj/Nfxxsxy/F57xuCQ==";

    private static final String PUBLIC_KEY_PEM = "-----BEGIN PUBLIC KEY-----\n" +
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsrxY7zwnU8/u9a5KClEjEHDiG\n" +
        "yMXYBJUr+il5Pw9KsVqFuhydjV7EiMBTykQ4XuZdaBWqlA7KMOio9xsL8nuMZOSu\n" +
        "3fsmuSvMjt6gX/7kd3TQO2Tbjs8sFokJ3LPYqrhWhHCxFe52USMiKEanKbSZch1u\n" +
        "PF1+pQHfV4zo8Mu6FQIDAQAB\n" +
        "-----END PUBLIC KEY-----";
    private static final String PRIVATE_KEY_PEM = "-----BEGIN PRIVATE KEY-----\n" +
        "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKyvFjvPCdTz+71r\n" +
        "koKUSMQcOIbIxdgElSv6KXk/D0qxWoW6HJ2NXsSIwFPKRDhe5l1oFaqUDsow6Kj3\n" +
        "Gwvye4xk5K7d+ya5K8yO3qBf/uR3dNA7ZNuOzywWiQncs9iquFaEcLEV7nZRIyIo\n" +
        "RqcptJlyHW48XX6lAd9XjOjwy7oVAgMBAAECgYEAk2mb506kq//j5R3RolsHizI0\n" +
        "Jwt5qSCwXyxc/z4PxcmE5yerievG/Kto056VgjGxIgfahxWBUqVR1/uqQRas1A2j\n" +
        "5/de8Y+LcpNrEuwF8YgOWmK3EAty0pgHQ1ezYSaxJ2AMBF427UrzMpGrB77UEzGE\n" +
        "07GxbbC/sK/u66h0A/kCQQD89Q3OmWV8Gxie8XkWHeiUhseo3kZ9AYy7tRpsEkTk\n" +
        "kWZAK2znphdHl35yDk0Cqu4uCe3usz6TfRlWu+3WK5k3AkEArsLXtUUt1IVeM0Z0\n" +
        "Oxz8AWMb4v1lJiS4BhotZs7fyZ6DnMd+LIdfqQCLl9j3hCzdxEqIqmcuL2uGy1OY\n" +
        "dfz9EwJAF+lGM9hWOoQJMMUcsBWFrbyL1Q+l1B04Y2n8JGkZsA16f+ha9A7ENpVA\n" +
        "c6Gcb/seZqWzoxO4f5KcuZEsK0mVwwJAIp4qCJhZib2ZeWK9Z3BIYyX0wjQbs0CW\n" +
        "y26oC7NzFQc3XvkNf1iZlGqtPDkYXrBchaOWCttBhNcx7ljy3HxuzQJAQxcxqCOU\n" +
        "mLJah+Mtjb+aJQ2L6Lg3mBA62WNGxXDzpX2pAcJVZ7bNcsBq41rOpQEtQ8bEyj/N\n" +
        "fxxsxy/F57xuCQ==\n" +
        "-----END PRIVATE KEY-----";


    private static PublicKey publicKey = null;
    private static PrivateKey privateKey = null;
    private static String publicKeyRem = null;
    private static String privateKeyRem = null;

    static {
        try {
            publicKey = RSAUtil.getPublicRSAKey(PUBLIC_KEY);
            privateKey = RSAUtil.getPrivateRSAKey(PRIVATE_KEY);
            publicKeyRem = PUBLIC_KEY_PEM;
            privateKeyRem = PRIVATE_KEY_PEM;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void testEncryptAndDecrypt(String text, Key encryptKey, Key decryptKey) throws BadPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        String plainText = text;
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);
        System.out.println("明文：" + plainText);
        String encryptedText = RSAUtil.encryptToString(plainText, encryptKey);
        System.out.println("Java加密后：" + encryptedText);
        String decryptedText = RSAUtil.decryptToString(encryptedText, decryptKey);
        System.out.println("Java解密后：" + decryptedText);
    }

    /**
     * 公钥加密，私钥解密
     */
    @Test
    public void testEncryptPubAndDecryptPriv() throws BadPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        String plainText = "Java中文";
        testEncryptAndDecrypt(plainText, publicKey, privateKey);
    }

    /**
     * 私钥加密，公钥解密。认证
     */
    @Test
    public void testEncryptPrivAndDecryptPub() throws BadPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        String plainText = "Java中文";
        testEncryptAndDecrypt(plainText, privateKey, publicKey);
    }

    /**
     * 生成公私钥
     */
    @Test
    public void testGenerate() throws NoSuchAlgorithmException {
        KeyPair kp = RSAUtil.generateKeyPair();
        PrivateKey privateKey = kp.getPrivate();
        PublicKey publicKey = kp.getPublic();

        String privateKeyStr = RSAUtil.toBase64(privateKey);
        String publicKeyStr = RSAUtil.toBase64(publicKey);

        System.out.println(privateKeyStr);
        System.out.println(publicKeyStr);
    }


    /**
     * 生成公私钥 keySize 512
     */
    @Test
    public void testGenerateKeySize512() throws NoSuchAlgorithmException {
        KeyPair kp = RSAUtil.generateKeyPair(512);
        PrivateKey privateKey = kp.getPrivate();
        PublicKey publicKey = kp.getPublic();

        String privateKeyStr = RSAUtil.toBase64(privateKey);
        String publicKeyStr = RSAUtil.toBase64(publicKey);

        System.out.println(privateKeyStr);
        System.out.println(publicKeyStr);
    }


    /**
     * 生成Pem格式的公私钥
     */
    @Ignore
    @Test
    public void testGenerateKeyPem() {
        try {
            System.out.println(privateKey.getFormat());
            System.out.println(privateKey.getAlgorithm());

            System.out.println(publicKey.getFormat());
            System.out.println(publicKey.getAlgorithm());

            String privateKeyPem = RSAUtil.generateKeyPemPKCS8(privateKey);

            System.out.println(privateKeyPem);

            String publicKeyPem = RSAUtil.generateKeyPemPKCS8(publicKey);

            System.out.println(publicKeyPem);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 从Pem中读取公私钥
     */
    @Ignore
    @Test
    public void testGetKeyFromPem() {
        try {

            PrivateKey privateKeyFromPem = RSAUtil.getPrivateRSAKeyFromPem(privateKeyRem);

            System.out.println(privateKeyFromPem);

            PublicKey publicKeyFromPem = RSAUtil.getPublicRSAKeyFromPem(publicKeyRem);

            System.out.println(publicKeyFromPem);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 签名和验签
     */
    @Test
    public void testSignAndVerify() {
        try {

            String decryptedText = "123";
            String messageText = "服务端处理成功，客户端发送过来的内容为：" + decryptedText;
            // 签名
            String sign = RSAUtil.sign(messageText, privateKey);
            System.out.println(messageText);
            System.out.println(sign);

            // 验签
            boolean isPass = RSAUtil.verify(messageText, sign, publicKey);
            System.out.println(isPass);

            // 签名
            String sign2 = RSAUtil.sign(messageText, privateKey, RSAUtil.MD5_WITH_RSA);
            System.out.println(messageText);
            System.out.println(sign2);

            // 验签
            boolean isPass2 = RSAUtil.verify(messageText, sign2, publicKey, RSAUtil.MD5_WITH_RSA);
            System.out.println(isPass2);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 签名和验签
     */
    @Test
    public void testPKCS8ToPKCS1() {
        try {
            // 公钥转换
            System.out.println(publicKeyRem);
            String publicKey_PKCS8 = RSAUtil.generateKeyPemPKCS8(publicKey);
            System.out.println(publicKey_PKCS8);
            String publicKey_PKCS1 = RSAUtil.generateKeyPemPKCS1(publicKey);
            System.out.println(publicKey_PKCS1);

            // 私钥转换
            System.out.println(privateKeyRem);
            String privateKey_PKCS8 = RSAUtil.generateKeyPemPKCS8(privateKey);
            System.out.println(privateKey_PKCS8);
            String privateKey_PKCS1 = RSAUtil.generateKeyPemPKCS1(privateKey);
            System.out.println(privateKey_PKCS1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
