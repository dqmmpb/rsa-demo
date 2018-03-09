package com.alphabeta.util;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class RSAUtilTest {

    private static PublicKey publicKey = null;
    private static PrivateKey privateKey = null;
    private static String publicKeyRem = null;
    private static String privateKeyRem = null;
    static {
        try {
            publicKey = RSAUtil.getPublicRSAKey(RSAUtil.PUBLIC_KEY);
            privateKey = RSAUtil.getPrivateRSAKey(RSAUtil.PRIVATE_KEY);
            publicKeyRem = RSAUtil.PUBLIC_KEY_PEM;
            privateKeyRem = RSAUtil.PRIVATE_KEY_PEM;
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

        String privateKeyStr = RSAUtil.toString(privateKey);
        String publicKeyStr = RSAUtil.toString(publicKey);

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

        String privateKeyStr = RSAUtil.toString(privateKey);
        String publicKeyStr = RSAUtil.toString(publicKey);

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
}
