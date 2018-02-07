package com.alphabeta.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * ==================================================================
 * RSAUtil.java created by alphabeta
 *
 * @author alphabeta
 * @version 1.0
 *          ==================================================================
 */

public class RSAUtil {

    public static final String PUBLIC_Key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsrxY7zwnU8/u9a5KClEjEHDiGyMXYBJUr+il5Pw9KsVqFuhydjV7EiMBTykQ4XuZdaBWqlA7KMOio9xsL8nuMZOSu3fsmuSvMjt6gX/7kd3TQO2Tbjs8sFokJ3LPYqrhWhHCxFe52USMiKEanKbSZch1uPF1+pQHfV4zo8Mu6FQIDAQAB";
    public static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKyvFjvPCdTz+71rkoKUSMQcOIbIxdgElSv6KXk/D0qxWoW6HJ2NXsSIwFPKRDhe5l1oFaqUDsow6Kj3Gwvye4xk5K7d+ya5K8yO3qBf/uR3dNA7ZNuOzywWiQncs9iquFaEcLEV7nZRIyIoRqcptJlyHW48XX6lAd9XjOjwy7oVAgMBAAECgYEAk2mb506kq//j5R3RolsHizI0Jwt5qSCwXyxc/z4PxcmE5yerievG/Kto056VgjGxIgfahxWBUqVR1/uqQRas1A2j5/de8Y+LcpNrEuwF8YgOWmK3EAty0pgHQ1ezYSaxJ2AMBF427UrzMpGrB77UEzGE07GxbbC/sK/u66h0A/kCQQD89Q3OmWV8Gxie8XkWHeiUhseo3kZ9AYy7tRpsEkTkkWZAK2znphdHl35yDk0Cqu4uCe3usz6TfRlWu+3WK5k3AkEArsLXtUUt1IVeM0Z0Oxz8AWMb4v1lJiS4BhotZs7fyZ6DnMd+LIdfqQCLl9j3hCzdxEqIqmcuL2uGy1OYdfz9EwJAF+lGM9hWOoQJMMUcsBWFrbyL1Q+l1B04Y2n8JGkZsA16f+ha9A7ENpVAc6Gcb/seZqWzoxO4f5KcuZEsK0mVwwJAIp4qCJhZib2ZeWK9Z3BIYyX0wjQbs0CWy26oC7NzFQc3XvkNf1iZlGqtPDkYXrBchaOWCttBhNcx7ljy3HxuzQJAQxcxqCOUmLJah+Mtjb+aJQ2L6Lg3mBA62WNGxXDzpX2pAcJVZ7bNcsBq41rOpQEtQ8bEyj/Nfxxsxy/F57xuCQ==";

    public static final Provider pro = new BouncyCastleProvider();

    private static final String charSet = "UTF-8";

    // 种子,改变后,生成的密钥对会发生变化
    private static final String seedKey = "seedKey";

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", pro);
        kpg.initialize(1024, new SecureRandom(seedKey.getBytes()));
        KeyPair kp = kpg.generateKeyPair();

        return kp;
    }

    public static PublicKey getPublicRSAKey(String modulus, String exponent) throws Exception {
        RSAPublicKeySpec spec = new RSAPublicKeySpec(new BigInteger(modulus, 16), new BigInteger(exponent, 16));
        KeyFactory kf = KeyFactory.getInstance("RSA", pro);
        return kf.generatePublic(spec);
    }

    public static PublicKey getPublicRSAKey(String key) throws Exception {
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(Base64.decode(key));
        KeyFactory kf = KeyFactory.getInstance("RSA", pro);
        return kf.generatePublic(x509);
    }

    public static PrivateKey getPrivateRSAKey(String key) throws Exception {
        PKCS8EncodedKeySpec pkgs8 = new PKCS8EncodedKeySpec(Base64.decode(key));
        KeyFactory kf = KeyFactory.getInstance("RSA", pro);
        return kf.generatePrivate(pkgs8);
    }

    public static byte[] toBytes(String text) throws Exception {
       return text.getBytes(charSet);
    }

    private static byte[] encrypt(byte[] text, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", pro);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(text);
    }

    private static byte[] encryptToBytes(String text, PublicKey publicKey) throws Exception {
        return RSAUtil.encrypt(RSAUtil.toBytes(text), publicKey);
    }

    public static String encryptToString(String text, PublicKey publicKey) throws Exception {
        byte[] en = RSAUtil.encryptToBytes(text, publicKey);
        return new String(Base64.encode(en));
    }

    private static byte[] decrypt(byte[] encrypted, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", pro);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encrypted);
    }

    private static String decryptToString(byte[] encrypted, PrivateKey privateKey) throws Exception {
        return new String(RSAUtil.decrypt(encrypted, privateKey));
    }

    public static String decryptToString(String encrypted, PrivateKey privateKey) throws Exception {
        byte[] en = Base64.decode(encrypted);
        return decryptToString(en, privateKey);
    }

}
