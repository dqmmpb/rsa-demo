package com.alphabeta.util;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
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

    // 签名算法
    public static final String SHA1_WITH_RSA = "SHA1withRSA";
    public static final String MD5_WITH_RSA = "MD5withRSA";

    private static final Provider pro = new BouncyCastleProvider();

    // 统一以16进制解析modulus和exponent字符串
    private static final int BIGINTEGER_RADIX = 16;
    // RSA算法
    private static final String ALGORITHM = "RSA";
    // Padding
    private static final String NONE_PADDING = "RSA/None/PKCS1Padding";
    // 随机数算法
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";
    // 编码默认格式
    private static final String UTF8 = "UTF-8";
    // 种子，改变后，生成的密钥对会发生变化
    private static final String SEEDKEY = "seedKey";
    // 密钥默认长度
    private static final int KEYSIZE = 1024;

    // 公钥PEM头部
    private static final String PUBLIC_KEY_PEM_HEADER_PKCS1 = "RSA PUBLIC KEY";
    private static final String PUBLIC_KEY_PEM_HEADER_PKCS8 = "PUBLIC KEY";
    // 私钥PEM头部
    private static final String PRIVATE_KEY_PEM_HEADER_PKCS1 = "RSA PRIVATE KEY";
    private static final String PRIVATE_KEY_PEM_HEADER_PKCS8 = "PRIVATE KEY";


    /**
     * 生成公私钥对
     *
     * @return
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        return generateKeyPair(KEYSIZE);
    }

    /**
     * 生成公私钥对
     *
     * @param keySize 密钥长度
     * @return
     * @throws Exception
     */
    public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM, pro);
        kpg.initialize(keySize);
        KeyPair kp = kpg.generateKeyPair();
        return kp;
    }

    /**
     * 生成公私钥对，使用特定种子生成
     *
     * @param seedKey 种子，当种子相同时，生成的密钥相同
     * @return
     * @throws Exception
     */
    public static KeyPair generateKeyPair(String seedKey) throws NoSuchAlgorithmException {
        return generateKeyPair(seedKey, KEYSIZE);
    }

    /**
     * 生成公私钥对，使用特定种子生成
     *
     * @param seedKey 种子，当种子相同时，生成的密钥相同
     * @param keySize 密钥长度
     * @return
     * @throws Exception
     */
    public static KeyPair generateKeyPair(String seedKey, int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM, pro);
        // windows和linux下SecureRandom的行为不一致
        // 如果使用new SecureRandom(seedKey.getBytes())，在windows会生成相同密钥，在linux会生成不同密钥
        // 因此使用如下方法，确保在相同seedKey下，windows和linux都能生成相同密钥
        byte seedKeyBytes[] = seedKey.getBytes();
        SecureRandom secureRandom = SecureRandom.getInstance(RANDOM_ALGORITHM);
        secureRandom.setSeed(seedKeyBytes);
        // SecureRandom secureRandom = new SecureRandom(seedKey.getBytes());

        kpg.initialize(keySize, secureRandom);
        KeyPair kp = kpg.generateKeyPair();
        return kp;
    }

    /**
     * bytes转公钥
     *
     * @param bytes
     * @return
     * @throws InvalidKeySpecException
     */
    private static PublicKey bytesToPublicKey(byte[] bytes) throws InvalidKeySpecException {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            KeyFactory kf = KeyFactory.getInstance(ALGORITHM, pro);
            return kf.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            // Can't happen
        }
        return null;
    }

    /**
     * bytes转私钥
     *
     * @param bytes
     * @return
     * @throws InvalidKeySpecException
     */
    private static PrivateKey bytesToPrivateKey(byte[] bytes) throws InvalidKeySpecException {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory kf = KeyFactory.getInstance(ALGORITHM, pro);
            return kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            // Can't happen
        }
        return null;
    }

    /**
     * String转公钥
     *
     * @param key
     * @return
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicRSAKey(String key) throws InvalidKeySpecException {
        return bytesToPublicKey(Base64.decode(key));
    }

    /**
     * String转私钥
     *
     * @param key
     * @return
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateRSAKey(String key) throws InvalidKeySpecException {
        return bytesToPrivateKey(Base64.decode(key));
    }

    /**
     * 公私钥转String格式
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String toBase64(Key key) {
        byte[] bytes = key.getEncoded();
        return Base64.toBase64String(bytes);
    }

    /**
     * String转bytes
     *
     * @param text
     * @return
     * @throws Exception
     */
    public static byte[] toBytes(String text) throws UnsupportedEncodingException {
        return toBytes(text, UTF8);
    }

    /**
     * String转bytes
     *
     * @param text
     * @param charSet 编码
     * @return
     * @throws Exception
     */
    public static byte[] toBytes(String text, String charSet) throws UnsupportedEncodingException {
        return text.getBytes(charSet);
    }

    /**
     * 使用密钥加密bytes，返回bytes
     *
     * @param text
     * @param key  密钥（公钥/私钥）
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] text, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(NONE_PADDING, pro);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text);
    }

    /**
     * 使用密钥加密String，返回bytes
     *
     * @param text
     * @param key  密钥（公钥/私钥）
     * @return
     * @throws Exception
     */
    private static byte[] encryptToBytes(String text, Key key) throws UnsupportedEncodingException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return encrypt(toBytes(text), key);
    }


    /**
     * 使用密钥加密String，返回String
     *
     * @param text
     * @param key  密钥（公钥/私钥）
     * @return
     * @throws Exception
     */
    public static String encryptToString(String text, Key key) throws BadPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        byte[] en = encryptToBytes(text, key);
        return Base64.toBase64String(en);
    }

    /**
     * 使用密钥解密bytes，返回bytes
     *
     * @param text
     * @param key  密钥（公钥/私钥）
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] text, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(NONE_PADDING, pro);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(text);
    }

    /**
     * 使用密钥解密bytes，返回String
     *
     * @param text
     * @param key  密钥（公钥/私钥）
     * @return
     * @throws Exception
     */
    private static String decryptToString(byte[] text, Key key) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return new String(decrypt(text, key));
    }

    /**
     * 使用密钥解密String，返回String
     *
     * @param text
     * @param key  私钥
     * @return
     * @throws Exception
     */
    public static String decryptToString(String text, Key key) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        byte[] en = Base64.decode(text);
        return decryptToString(en, key);
    }

    /**
     * 将Pem从String格式转为bytes
     *
     * @param keyPem
     * @return
     * @throws InvalidKeySpecException
     */
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

    /**
     * 从Pem中解析公钥
     *
     * @param keyPem
     * @return
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicRSAKeyFromPem(String keyPem) throws InvalidKeySpecException {
        return bytesToPublicKey(getBytesFromPem(keyPem));
    }

    /**
     * 从Pem中解析私钥
     *
     * @param keyPem
     * @return
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateRSAKeyFromPem(String keyPem) throws InvalidKeySpecException {
        return bytesToPrivateKey(getBytesFromPem(keyPem));
    }

    /**
     * 获取Pem格式的RSAKey
     *
     * @param type
     * @param keyBytes
     * @return
     * @throws InvalidKeySpecException
     */
    private static String generateKeyPem(String type, byte[] keyBytes) throws InvalidKeySpecException {
        try {
            StringWriter stringWriter = new StringWriter();
            PemWriter pemWriter = new PemWriter(stringWriter);
            pemWriter.writeObject(new PemObject(type, keyBytes));
            pemWriter.close();
            String keyPem = stringWriter.toString();
            stringWriter.close();
            return keyPem;
        } catch (IOException e) {
            throw new InvalidKeySpecException("Could not generate key pem");
        }
    }

    /**
     * 获取Pem格式的RSAKey
     *
     * @param type
     * @param key
     * @return
     * @throws InvalidKeySpecException
     */
    private static String generateKeyPem(String type, Key key) throws InvalidKeySpecException {
        return generateKeyPem(type, key.getEncoded());
    }

    /**
     * 获取Pem格式的公钥
     *
     * @param publicKey
     * @return
     * @throws InvalidKeySpecException
     */
    public static String generateKeyPemPKCS8(PublicKey publicKey) throws InvalidKeySpecException {
        return generateKeyPem(PUBLIC_KEY_PEM_HEADER_PKCS8, publicKey);
    }

    /**
     * 获取Pem格式的私钥
     *
     * @param privateKey
     * @return
     * @throws InvalidKeySpecException
     */
    public static String generateKeyPemPKCS8(PrivateKey privateKey) throws InvalidKeySpecException {
        return generateKeyPem(PRIVATE_KEY_PEM_HEADER_PKCS8, privateKey);
    }

    /**
     * PKCS#8转换为PKCS#1
     *
     * @param privateKey
     * @throws IOException
     */
    public static String generateKeyPemPKCS1(PrivateKey privateKey) throws IOException, InvalidKeySpecException {
        byte[] privBytes = privateKey.getEncoded();

        PrivateKeyInfo pkInfo = PrivateKeyInfo.getInstance(privBytes);
        ASN1Encodable encodable = pkInfo.parsePrivateKey();
        ASN1Primitive primitive = encodable.toASN1Primitive();
        byte[] privateKeyPKCS1 = primitive.getEncoded();
        return generateKeyPem(PRIVATE_KEY_PEM_HEADER_PKCS1, privateKeyPKCS1);
    }

    /**
     * X509转换为PKCS#1
     *
     * @param publicKey
     * @throws IOException
     */
    public static String generateKeyPemPKCS1(PublicKey publicKey) throws IOException, InvalidKeySpecException {
        byte[] pubBytes = publicKey.getEncoded();

        SubjectPublicKeyInfo spkInfo = SubjectPublicKeyInfo.getInstance(pubBytes);
        ASN1Primitive primitive = spkInfo.parsePublicKey();
        byte[] publicKeyPKCS1 = primitive.getEncoded();
        return generateKeyPem(PUBLIC_KEY_PEM_HEADER_PKCS1, publicKeyPKCS1);
    }

    /**
     * 签名，使用SHA1摘要
     *
     * @param data
     * @param privateKey
     * @return
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    public static String sign(String data, PrivateKey privateKey) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        return sign(data, privateKey, SHA1_WITH_RSA);
    }

    /**
     * 验签，使用SHA1摘要
     *
     * @param data
     * @param sign
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    public static boolean verify(String data, String sign, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        return verify(data, sign, publicKey, SHA1_WITH_RSA);
    }

    /**
     * 签名
     *
     * @param data
     * @param privateKey
     * @param algorithm  指定摘要算法
     * @return
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    public static String sign(String data, PrivateKey privateKey, String algorithm) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        // 设置签名算法
        Signature signature = Signature.getInstance(algorithm);
        // 设置签名加密方式
        signature.initSign(privateKey);//设置私钥
        // 签名和加密一样 要以字节形式 utf-8字符集得到字节
        signature.update(toBytes(data));
        // 得到base64编码的签名后的字段
        return Base64.toBase64String(signature.sign());
    }

    /**
     * 验签
     *
     * @param data
     * @param sign
     * @param publicKey
     * @param algorithm 指定摘要算法
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    public static boolean verify(String data, String sign, PublicKey publicKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        // 指定签名类型
        Signature signature = Signature.getInstance(algorithm);
        // 放入公钥
        signature.initVerify(publicKey);
        // 放入数据
        signature.update(toBytes(data));
        // 验签结果
        return signature.verify(Base64.decode(sign));
    }
}
