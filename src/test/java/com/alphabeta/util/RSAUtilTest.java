package com.alphabeta.util;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAUtilTest {

    private static PublicKey publicKey = null;
    private static PrivateKey privateKey = null;

    static {
        try {
            publicKey = RSAUtil.getPublicRSAKey(RSAUtil.PUBLIC_Key);
            privateKey = RSAUtil.getPrivateRSAKey(RSAUtil.PRIVATE_KEY);
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

//    @Test
//    public void testGenerate() {
//        try {
//            KeyPair kp = RSAUtil.generateKeyPair();
//            PrivateKey privateKey = kp.getPrivate();
//            byte[] prk = privateKey.getEncoded();
//            String privateKeyStr = new String(Base64.encode(prk));
//
//            PublicKey publicKey = kp.getPublic();
//            byte[] pbk = publicKey.getEncoded();
//            String publicKeyStr = new String(Base64.encode(pbk));
//
//            System.out.println(privateKeyStr);
//            System.out.println(publicKeyStr);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

}
