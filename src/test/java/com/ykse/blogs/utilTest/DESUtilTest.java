package com.ykse.blogs.utilTest;


import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Administrator on 2017/4/25.
 */
public class DESUtilTest {

    private static Key key;

    @Test
    public void des() throws Exception{
        File file = new File("D:\\graduation\\idea\\blogsystem\\target\\BlogsSystem-0.0.1-SNAPSHOT\\resources\\img\\001.gif");
        InputStream is = new FileInputStream(file);

        //创建目录和文件
        File dir = new File("D:\\graduation\\idea\\blogsystem\\target\\BlogsSystem-0.0.1-SNAPSHOT\\resources\\view");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File serverFile = new File(dir.getAbsolutePath() + File.separator + "001.gif");
        if (serverFile.exists())
        {
            serverFile.delete();
        }

        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(serverFile));

        byte[] sth = encrypt(is);
        decrypt(sth,os);
    }

    public static byte[] encrypt(InputStream is) throws Exception {

        getKey("123456");
        Cipher cipher = null;
        cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        CipherInputStream cis = new CipherInputStream(is, cipher);

        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        while ((cis.read(buf)) != -1) {
            sb.append(new String(buf));
            buf = new byte[1024];
        }
        byte[] cipherByteArray = sb.toString().getBytes();
        cis.close();

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
        objectOutputStream.writeObject(cipherByteArray);
        objectOutputStream.flush();
        byte[] data = arrayOutputStream.toByteArray();

        return data;
    }

    public static void decrypt(byte[] data, BufferedOutputStream stream) throws Exception {

        //解密
        InputStream is4Serialize = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(is4Serialize);
        byte[] deCipherByteArray = (byte[]) ois.readObject();

        InputStream is = new ByteArrayInputStream(deCipherByteArray);

        getKey("123123");
        Cipher cipher = null;
        cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        CipherInputStream cis = new CipherInputStream(is, cipher);

        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = cis.read(buffer, 0, 1024)) != -1) {
            stream.write(buffer, 0, len);
        }
    }

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
}
