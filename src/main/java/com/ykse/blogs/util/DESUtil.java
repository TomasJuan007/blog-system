package com.ykse.blogs.util;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Administrator on 2017/3/28.
 */
public class DESUtil {

    private static Key key;

    /**
     * 根据参数生成KEY
     */
    private static void getKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            _generator.init(new SecureRandom(strKey.getBytes()));
            key = _generator.generateKey();
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }

    public static void encrypt(InputStream is, BufferedOutputStream stream) throws InvalidKeyException {

        getKey("123123");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try {
            CipherInputStream cis = new CipherInputStream(is, cipher);
            int i;
            while ((i = cis.read()) != -1) {
                stream.write(i);
            }
            stream.flush();
            cis.close();
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }
    }

    public static void decrypt(InputStream is, BufferedOutputStream stream) throws InvalidKeyException {

        getKey("123123");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        cipher.init(Cipher.DECRYPT_MODE, key);

        try {
            CipherInputStream cis = new CipherInputStream(is, cipher);
            int i;
            while ((i = cis.read()) != -1) {
                stream.write(i);
            }
            stream.flush();
            cis.close();
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }
    }
}
