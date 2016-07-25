package com.alphabeta.util;

import static org.junit.Assert.fail;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Assert;
import org.junit.Test;

public class RSAUtilTest {

  private static final String PUBLIC_Key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzwYuXTPEtWV49OS/Cb5QIbX2LtZBHhCi9hiR2hrJEcWA4vUmC8GsMMOKw933VxAurjw1Llhj4QXpKZi9hfOlc6bn7GoyAZpVgl+JAzwQFuTOSJyRacgGDef0BY0zW/kQZjILI7ovqXwAcSaOGhFQgy6OWAkNDeKqGQFXeMwr9owIDAQAB";

  private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALPBi5dM8S1ZXj05L8JvlAhtfYu1kEeEKL2GJHaGskRxYDi9SYLwawww4rD3fdXEC6uPDUuWGPhBekpmL2F86VzpufsajIBmlWCX4kDPBAW5M5InJFpyAYN5/QFjTNb+RBmMgsjui+pfABxJo4aEVCDLo5YCQ0N4qoZAVd4zCv2jAgMBAAECgYEAjlxHMEFodFDluLkUoPl7FJ2aI05dALajCU42jIQqpNfhq64FjSTYsqP4tMydJPIJiApYLjemeN5qeoepGJ0ztuU/QM56Q59c6S4nBxDhc619dvt9jABsompCl6yrPYzi3FG1TmJa4ugBOln+KS/Pw/aSaEu4E1CUOYgYuIljT1kCQQDWo9fwnEpdH2WoW4E1ARwuV++SfsV7szJtSIBEKuhIzRDLMEB97yoEffWVYgncou+XYXdMn5Eb0dkRP4u07ZUvAkEA1mThgvhz0gyM08GxaXIT/+9JskCuCvvcDTVJVhVebeK/y0rZGBsvIMuPux+6wy4S2qqj+BwjSfP+71JLrPopzQJAFx2aGe2bDKBfAFyqc5zk/hC2Wl6QwhuwaJiQR8cfMQf0sQ1HRMjHC6jNFAN08HATwYfbo0LkC8zzxanET/3uPQJBAKYaPJmzElC/xm/dVi2C47nbU3aWJAGAhkl5alsWbTWngr7nO3Ewxn+bFr18ZL8JODRQFn+IlVKbhn02fkkC/FUCQDD7cPsWeab+iJlam4gooS/suDryNqWQFFsuZDAuseDzQKpa7Qvd/5TH5eiiWpUoKCySjQapO+JXHjIb1PQm4Rw=";

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
/*
  @Test
  public void testGenerateKeyPair() {

    try {
      KeyPair kp = RSAUtil.generateKeyPair();

      PrivateKey privateKey = kp.getPrivate();
      byte[] prk = privateKey.getEncoded();
      String privateKeyStr = new String(Base64.encode(prk));

      PublicKey publicKey = kp.getPublic();
      byte[] pbk = publicKey.getEncoded();
      String publicKeyStr = new String(Base64.encode(pbk));

      System.out.println("PrivateKey: " + privateKey);
      System.out.println(" PublicKey: " + publicKey);

      System.out.println("PrivateKey Base64: " + privateKeyStr);
      System.out.println(" PublicKey Base64: " + publicKeyStr);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }*/

 /* @Test
  public void testGetPrivateRSAKey() {

    try {
      PublicKey publicKey = RSAUtil.getPublicRSAKey(PUBLIC_Key);
      PrivateKey privateKey = RSAUtil.getPrivateRSAKey(PRIVATE_KEY);
      System.out.println("PrivateKey: " + privateKey);
      System.out.println("PublicKey: " + publicKey);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }*/

  /**
   * java加密， java解密
   */
  @Test
  public void testEncryptJavaAndDecryptJava() {
    try {
      String plainText = "Java中文";
      System.out.println("加密明文：" + plainText);
      byte[] en = RSAUtil.encrypt(plainText, publicKey);
      String encryptedText = new String(Base64.encode(en));
      System.out.println("Java加密后：" + encryptedText);
      byte[] re = RSAUtil.decrypt(en, privateKey);
      String decryptedText = new String(re);
      System.out.println("Java解密后：" + decryptedText);
      Assert.assertEquals(plainText, decryptedText);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * java加密， java解密
   */
  @Test
  public void testEncryptJavaAndDecryptJava2() {
    try {
      String plainText = "Java中文2";
      String encryptedText = "Hj3MnvCmFw6o+ggqLadnoMca2AJCedicY5CSoF0Q1CPZn6EIidqjDuBD8++2IXEFD+RTWutvhiSSce8Ot09n7JifwWFc3g5hcorHDM/e9MsC0PMxG32AMyZnqMxX+1T0xXnlHI8lr+0q4rNtGZ1f0uaE/ZO3MDzqClmamQO5I/0=";
      System.out.println("加密明文：" + plainText);
      System.out.println("Java加密后：" + encryptedText);
      byte[] en = Base64.decode(encryptedText);
      byte[] re = RSAUtil.decrypt(en, privateKey);
      String decryptedText = new String(re);
      System.out.println("Java解密后：" + decryptedText);
      Assert.assertEquals(plainText, decryptedText);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  
  /**
   * js加密, java解密
   */
  @Test
  public void testEncryptJsAndDecryptJava() {
    try {
      String plainText = "Javascript中文";
      String encryptedText = "LpsvBoKDHlpRsnAsC5lqU198lr8G9t2P0mHI1DAY7oNk6mJ+qd8JYq2X3yyutZL7K2dERBJSATqmYaRgnQgWVlMjlOi+j/Ob5kcaqrPVXLFJpgtqLYgJ2Qq7ecw3LrPTAoHNqsReZ7ZzYkWeMkqQzaPuAAT9UmJI1KG7iyOBNFY=";
      System.out.println("加密明文：" + plainText);
      System.out.println("Js加密后：" + encryptedText);
      byte[] en = Base64.decode(encryptedText);
      byte[] re = RSAUtil.decrypt(en, privateKey);
      String decryptedText = new String(re);
      System.out.println("Java解密后：" + decryptedText);
      Assert.assertEquals(plainText, decryptedText);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
