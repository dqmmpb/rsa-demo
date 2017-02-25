package com.alphabeta.util;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.JCERSAPublicKey;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

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

  public static byte[] encrypt(String input, PublicKey publicKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", pro);
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] re = cipher.doFinal(input.getBytes(charSet));
    return re;
  }

  public static byte[] decrypt(byte[] encrypted, PrivateKey privateKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", pro);
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] re = cipher.doFinal(encrypted);
    return re;
  }

  public static void main(String[] args) throws Exception {
    KeyPair kp = generateKeyPair();

    PrivateKey privateKey = kp.getPrivate();
    byte[] prk = privateKey.getEncoded();
    String privateKeyStr = new String(Base64.encode(prk));

    PublicKey publicKey = kp.getPublic();
    byte[] pbk = publicKey.getEncoded();
    String publicKeyStr = new String(Base64.encode(pbk));

    System.out.println("PrivateKey: " + privateKey);
    System.out.println("PublicKey: " + publicKey);

    System.out.println("---------------------------------------");
    System.out.println("PrivateKey String:" + privateKeyStr);
    System.out.println("PublicKey String:" + publicKeyStr);
    System.out.println("---------------------------------------");

    PrivateKey privateKeyD = getPrivateRSAKey(privateKeyStr);
    System.out.println("decodePrivateKey:" + privateKeyD);

    PublicKey publicKeyD = getPublicRSAKey(publicKeyStr);
    System.out.println("decodePublicKey:" + publicKeyD);

    /*     String modulus = ((JCERSAPublicKey)publicKey).getModulus().toString(16);
     String exponent = ((JCERSAPublicKey)publicKey).getPublicExponent().toString(16);
//     modules:
//     a0a36434e33aa15c1ef1335dc2268054323d56411e1e2b13c3986e9cb5b800efed39cd812dd6a1ecc522113084539e30649b83db7b4ef5a510f3081494e3fb5c6c17b09d8e0c49f671f211e09721d1443bfdb2b45654f33138b22b80e716c6494128e8d149ebc028c1658dfdbc86bb049b4d7faab7260c75d82670cc4cc2ffd9
     System.out.println("modulus: " + modulus);
     System.out.println("exponent: " + exponent);
     PublicKey pb = getPublicRSAKey(modulus, exponent);
     System.out.println("PublicKey: " + pb);*/

  }

}
