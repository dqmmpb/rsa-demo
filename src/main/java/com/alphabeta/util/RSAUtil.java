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
