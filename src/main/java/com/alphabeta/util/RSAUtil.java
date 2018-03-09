package com.alphabeta.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * ==================================================================
 * RSAUtil.java created by alphabeta
 *
 * @author alphabeta
 * @version 1.0
 * ==================================================================
 */

public class RSAUtil {

    public static final String PUBLIC_Key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsrxY7zwnU8/u9a5KClEjEHDiGyMXYBJUr+il5Pw9KsVqFuhydjV7EiMBTykQ4XuZdaBWqlA7KMOio9xsL8nuMZOSu3fsmuSvMjt6gX/7kd3TQO2Tbjs8sFokJ3LPYqrhWhHCxFe52USMiKEanKbSZch1uPF1+pQHfV4zo8Mu6FQIDAQAB";
    public static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKyvFjvPCdTz+71rkoKUSMQcOIbIxdgElSv6KXk/D0qxWoW6HJ2NXsSIwFPKRDhe5l1oFaqUDsow6Kj3Gwvye4xk5K7d+ya5K8yO3qBf/uR3dNA7ZNuOzywWiQncs9iquFaEcLEV7nZRIyIoRqcptJlyHW48XX6lAd9XjOjwy7oVAgMBAAECgYEAk2mb506kq//j5R3RolsHizI0Jwt5qSCwXyxc/z4PxcmE5yerievG/Kto056VgjGxIgfahxWBUqVR1/uqQRas1A2j5/de8Y+LcpNrEuwF8YgOWmK3EAty0pgHQ1ezYSaxJ2AMBF427UrzMpGrB77UEzGE07GxbbC/sK/u66h0A/kCQQD89Q3OmWV8Gxie8XkWHeiUhseo3kZ9AYy7tRpsEkTkkWZAK2znphdHl35yDk0Cqu4uCe3usz6TfRlWu+3WK5k3AkEArsLXtUUt1IVeM0Z0Oxz8AWMb4v1lJiS4BhotZs7fyZ6DnMd+LIdfqQCLl9j3hCzdxEqIqmcuL2uGy1OYdfz9EwJAF+lGM9hWOoQJMMUcsBWFrbyL1Q+l1B04Y2n8JGkZsA16f+ha9A7ENpVAc6Gcb/seZqWzoxO4f5KcuZEsK0mVwwJAIp4qCJhZib2ZeWK9Z3BIYyX0wjQbs0CWy26oC7NzFQc3XvkNf1iZlGqtPDkYXrBchaOWCttBhNcx7ljy3HxuzQJAQxcxqCOUmLJah+Mtjb+aJQ2L6Lg3mBA62WNGxXDzpX2pAcJVZ7bNcsBq41rOpQEtQ8bEyj/Nfxxsxy/F57xuCQ==";

    public static final String PUBLIC_KEY_PEM = "-----BEGIN PUBLIC KEY-----\n" +
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsrxY7zwnU8/u9a5KClEjEHDiG\n" +
        "yMXYBJUr+il5Pw9KsVqFuhydjV7EiMBTykQ4XuZdaBWqlA7KMOio9xsL8nuMZOSu\n" +
        "3fsmuSvMjt6gX/7kd3TQO2Tbjs8sFokJ3LPYqrhWhHCxFe52USMiKEanKbSZch1u\n" +
        "PF1+pQHfV4zo8Mu6FQIDAQAB\n" +
        "-----END PUBLIC KEY-----";
    public static final String PRIVATE_KEY_PEM = "-----BEGIN PRIVATE KEY-----\n" +
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

    private static PublicKey bytesToPublicKey(byte[] bytes) throws InvalidKeySpecException {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            KeyFactory kf = KeyFactory.getInstance("RSA", pro);
            return kf.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            // Can't happen
        }
        return null;
    }

    private static PrivateKey bytesToPrivateKey(byte[] bytes) throws InvalidKeySpecException {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory kf = KeyFactory.getInstance("RSA", pro);
            return kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            // Can't happen
        }
        return null;
    }

    public static PublicKey getPublicRSAKey(String key) throws InvalidKeySpecException {
        return bytesToPublicKey(Base64.decode(key));
    }

    public static PrivateKey getPrivateRSAKey(String key) throws InvalidKeySpecException {
        return bytesToPrivateKey(Base64.decode(key));
    }

    public static String toString(Key key) throws Exception {
        byte[] bytes = key.getEncoded();
        return toString(bytes);
    }

    public static String toString(byte[] bytes) throws Exception {
        return new String(Base64.encode(bytes));
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


    private static byte[] getBytesFromPem(String keyPem) throws InvalidKeySpecException {
        try {
            StringReader stringReader = new StringReader(keyPem);
            PemReader pemReader = new PemReader(stringReader);
            PemObject pemObject = pemReader.readPemObject();
            pemReader.close();
            return pemObject.getContent();
        } catch (IOException e) {
            throw new InvalidKeySpecException("Could not get key pem");
        }
    }

    public static PublicKey getPublicRSAKeyFromPem(String keyPem) throws InvalidKeySpecException {
        return bytesToPublicKey(getBytesFromPem(keyPem));
    }

    public static PrivateKey getPrivateRSAKeyFromPem(String keyPem) throws InvalidKeySpecException {
        return bytesToPrivateKey(getBytesFromPem(keyPem));
    }

    private static String generateKeyPem(String type, Key key) throws InvalidKeySpecException {
        try {
            StringWriter stringWriter = new StringWriter();
            PemWriter pemWriter = new PemWriter(stringWriter);
            pemWriter.writeObject(new PemObject(type, key.getEncoded()));
            pemWriter.close();
            String keyPem = stringWriter.toString();
            stringWriter.close();
            return keyPem;
        } catch (IOException e) {
            throw new InvalidKeySpecException("Could not generate key pem");
        }
    }

    public static String generatePublicRSAKeyPem(Key key) throws InvalidKeySpecException {
        return generateKeyPem("PUBLIC KEY", key);
    }

    public static String generatePrivateRSAKeyPem(Key key) throws InvalidKeySpecException {
        return generateKeyPem("PRIVATE KEY", key);
    }

}
