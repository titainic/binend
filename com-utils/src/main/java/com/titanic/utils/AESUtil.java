package com.titanic.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;

/**
 * AES 加密解密工具类
 */
public class AESUtil {
    private static final Logger log = LoggerFactory.getLogger(AESUtil.class);
    private static final String SHA1PRNG = "SHA1PRNG";
    private static final String AES = "AES";

    private static String serverKey = "13399fhhxy^%#{}|";                    //测试用key，非正式
    private static String clientKey = "08322^%&_+RFKUIO";                    //正式用，不能修改，老密钥

    private static Cipher serverEncryptCipher;                               //服务端加密密码器
    private static Cipher serverDecryptCipher;                               //服务端解密密码器

    private static Cipher clientEncryptCipher;                               //客户端加密密码器
    private static Cipher clientDecryptCipher;                               //客户端解密密码器

    private static SecretKeySpec serverKeySpec;
    private static SecretKeySpec clientKeySpec;

    static {
        KeyGenerator serverkgen;
        KeyGenerator clientkgen;
        try {
            /**
             * 服务端密钥初始化
             */
            serverkgen = KeyGenerator.getInstance(AES);
            SecureRandom serverSecureRandom = SecureRandom.getInstance(SHA1PRNG);
            serverSecureRandom.setSeed(serverKey.getBytes());
            serverkgen.init(128, serverSecureRandom);
            SecretKey serverSecretKey = serverkgen.generateKey();
            byte[] serverEnCodeFormat = serverSecretKey.getEncoded();
            serverKeySpec = new SecretKeySpec(serverEnCodeFormat, AES);
            serverDecryptCipher = Cipher.getInstance(AES);
            serverDecryptCipher.init(Cipher.DECRYPT_MODE, serverKeySpec);
            serverEncryptCipher = Cipher.getInstance(AES);
            serverEncryptCipher.init(Cipher.ENCRYPT_MODE, serverKeySpec);

            /**
             * 客户端密钥初始化
             */
            clientkgen = KeyGenerator.getInstance(AES);
            SecureRandom clientSecureRandom = SecureRandom.getInstance(SHA1PRNG);
            clientSecureRandom.setSeed(clientKey.getBytes());
            clientkgen.init(128, clientSecureRandom);

            SecretKey clientSecretKey = clientkgen.generateKey();
            byte[] clientEnCodeFormat = clientSecretKey.getEncoded();
            clientKeySpec = new SecretKeySpec(clientEnCodeFormat, AES);
            clientDecryptCipher = Cipher.getInstance(AES);
            clientDecryptCipher.init(Cipher.DECRYPT_MODE, clientKeySpec);
            clientEncryptCipher = Cipher.getInstance(AES);
            clientEncryptCipher.init(Cipher.ENCRYPT_MODE, clientKeySpec);
        } catch (Exception e) {
            log.error("happens error when initialize AesUtil", e);
        }
    }

    /**
     * 获取密码器
     *
     * @param seed
     * @param isEncrypt
     * @return
     */
    public static Cipher getCipher(String seed, boolean isEncrypt) {
        Cipher cipher = null;
        try {
            KeyGenerator clientkgen = KeyGenerator.getInstance(AES);
            SecureRandom clientSecureRandom = SecureRandom.getInstance(SHA1PRNG);
            clientSecureRandom.setSeed(seed.getBytes());
            clientkgen.init(128, clientSecureRandom);

            SecretKey clientSecretKey = clientkgen.generateKey();
            byte[] clientEnCodeFormat = clientSecretKey.getEncoded();
            SecretKeySpec clientKeySpec = new SecretKeySpec(clientEnCodeFormat, AES);
            cipher = Cipher.getInstance(AES);
            if (isEncrypt)
                cipher.init(Cipher.ENCRYPT_MODE, clientKeySpec);
            else
                cipher.init(Cipher.DECRYPT_MODE, clientKeySpec);
        } catch (Exception e) {
            log.error("happens error when getCipher", e);
        }
        return cipher;
    }

    /**
     * 服务端解密
     *
     * @param cipherText
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String decodeServerText(String cipherText) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return decodeText(serverDecryptCipher, cipherText);
    }

    public static String decodeServerText(String cipherText, String charset)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException {
        return decodeText(serverDecryptCipher, cipherText, charset);
    }

    /**
     * 服务端加密
     *
     * @param text
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String encodeServerText(String text) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return encodeText(serverEncryptCipher, text);
    }

    public static String encodeServerText(String text, String charset) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return encodeText(serverEncryptCipher, text, charset);
    }

    /**
     * 客户端解密
     *
     * @param cipherText
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String decodeClientText(String cipherText) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return decodeText(clientDecryptCipher, cipherText);
    }

    public static String decodeClientText(String cipherText, String charset)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException {
        return decodeText(clientDecryptCipher, cipherText, charset);
    }

    public static String decodeClientTextWithSeed(String cipherText, String clientKey)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException {
        Cipher cipher = getCipher(clientKey, false);
        return decodeText(cipher, cipherText);
    }

    /**
     * 客户端加密
     *
     * @param text
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String encodeClientText(String text) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return encodeText(clientEncryptCipher, text);
    }

    public static String encodeClientText(String text, String charset) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return encodeText(clientEncryptCipher, text, charset);
    }

    public static String encodeClientTextWithSeed(String text, String clientKey)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException {
        Cipher cipher = getCipher(clientKey, true);
        return encodeText(cipher, text);
    }

    /**
     * 解密文本
     *
     * @param cipher
     * @param cipherText
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String decodeText(Cipher cipher, String cipherText) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return decodeText(cipher, cipherText, "UTF-8");
    }

    private static String decodeText(Cipher cipher, String cipherText, String charset)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException {
        if (cipherText == null) {
            return null;
        }
        if ("".equals(cipherText)) {
            return "";
        }
        byte[] resultBytes = null;
        byte[] byteContent = hex2Bytes(cipherText);
        resultBytes = cipher.doFinal(byteContent);
        if (StringUtils.isBlank(charset)) {
            return new String(resultBytes);
        } else
            return new String(resultBytes, charset);
    }

    /**
     * 加密文本
     *
     * @param cipher
     * @param text
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String encodeText(Cipher cipher, String text) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return encodeText(cipher, text, "UTF-8");
    }

    private static String encodeText(Cipher cipher, String text, String charset)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException {
        if (text == null) {
            return null;
        }
        if ("".equals(text)) {
            return "";
        }

        byte[] resultBytes = null;
        String result = null;
        byte[] byteContent = null;
        if (StringUtils.isBlank(charset)) {
            byteContent = text.getBytes();
        } else
            byteContent = text.getBytes(charset);
        resultBytes = cipher.doFinal(byteContent);
        result = bytes2Hex(resultBytes);
        return result;
    }

    /**
     * 字符转换成16进制
     *
     * @param bytes
     * @return
     */
    public static String bytes2Hex(byte[] bytes) {
        if (bytes == null)
            return null;
        StringBuilder result = new StringBuilder();
        String tmp = "";
        for (int n = 0; n < bytes.length; n++) {
            tmp = (Integer.toHexString(bytes[n] & 0XFF));
            if (tmp.length() == 1)
                result.append("0").append(tmp);
            else
                result.append(tmp);
        }
        return result.toString().toUpperCase();
    }

    /**
     * 16进制转换成字符
     *
     * @param hexStr
     * @return
     */
    public static byte[] hex2Bytes(String hexStr) {
        byte[] b = hexStr.getBytes();
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("length need to be even");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
}
