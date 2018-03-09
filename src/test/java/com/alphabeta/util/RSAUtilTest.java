package com.alphabeta.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAUtilTest {

    private static PublicKey publicKey = null;
    private static PrivateKey privateKey = null;
    private static String publicKeyRem = null;
    private static String privateKeyRem = null;
    static {
        try {
            publicKey = RSAUtil.getPublicRSAKey(RSAUtil.PUBLIC_Key);
            privateKey = RSAUtil.getPrivateRSAKey(RSAUtil.PRIVATE_KEY);
            publicKeyRem = RSAUtil.PUBLIC_KEY_PEM;
            privateKeyRem = RSAUtil.PRIVATE_KEY_PEM;
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

    @Test
    public void testGenerate() {
        try {
            KeyPair kp = RSAUtil.generateKeyPair();
            PrivateKey privateKey = kp.getPrivate();
            PublicKey publicKey = kp.getPublic();

            String privateKeyStr = RSAUtil.toString(privateKey);
            String publicKeyStr = RSAUtil.toString(publicKey);

            System.out.println(privateKeyStr);
            System.out.println(publicKeyStr);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 生成Pem格式的Key
     */
    @Test
    public void testGenerateKeyPem() {
        try {
            System.out.println(privateKey.getFormat());
            System.out.println(privateKey.getAlgorithm());

            System.out.println(publicKey.getFormat());
            System.out.println(publicKey.getAlgorithm());

            String privateKeyPem = RSAUtil.generatePublicRSAKeyPem(privateKey);

            System.out.println(privateKeyPem);

            String publicKeyPem = RSAUtil.generatePublicRSAKeyPem(publicKey);

            System.out.println(publicKeyPem);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 从Pem中读取Key
     */
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
}
