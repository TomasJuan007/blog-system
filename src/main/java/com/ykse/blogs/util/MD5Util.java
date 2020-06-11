package com.ykse.blogs.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class MD5Util {

    private static final String HEX_NUMS_STR = "0123456789ABCDEF";
    private static final Integer SALT_LENGTH = 12;

    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR
                    .indexOf(hexChars[pos + 1]));
        }
        return result;
    }

    public static String byteToHexString(byte[] b) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    public static boolean validPassword(String password, String passwordInDb) throws NoSuchAlgorithmException {
        // 将16进制字符串格式口令转换成字节数组
        byte[] pwdInDb = hexStringToByte(passwordInDb);
        // 声明盐变量
        byte[] salt = new byte[SALT_LENGTH];
        // 将盐从数据库中保存的口令字节数组中提取出来
        System.arraycopy(pwdInDb, 0, salt, 0, SALT_LENGTH);
        // 创建消息摘要对象
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 将盐数据传入消息摘要对象
        md.update(salt);
        // 将口令的数据传给消息摘要对象
        md.update(password.getBytes(StandardCharsets.UTF_8));
        // 生成输入口令的消息摘要
        byte[] digest = md.digest();
        // 声明保存数据库中口令消息摘要的变量
        byte[] digestInDb = new byte[pwdInDb.length - SALT_LENGTH];
        // 取得数据库中口令的消息摘要
        System.arraycopy(pwdInDb, SALT_LENGTH, digestInDb, 0,digestInDb.length);
        // 比较根据输入口令生成的消息摘要和数据库中消息摘要是否相同
        return Arrays.equals(digest, digestInDb);
    }

    public static String getEncryptedPwd(String password) throws NoSuchAlgorithmException {
        // 声明加密后的口令数组变量
        byte[] pwd;
        // 随机数生成器
        SecureRandom random = new SecureRandom();
        // 声明盐数组变量
        byte[] salt = new byte[SALT_LENGTH];
        // 将随机数放入盐变量中
        random.nextBytes(salt);

        // 声明消息摘要对象
        MessageDigest md;
        // 创建消息摘要
        md = MessageDigest.getInstance("MD5");
        // 将盐数据传入消息摘要对象
        md.update(salt);
        // 将口令的数据传给消息摘要对象
        md.update(password.getBytes(StandardCharsets.UTF_8));
        // 获得消息摘要的字节数
        byte[] digest = md.digest();

        // 因为要在口令的字节数组中存放盐，再以加上盐的字节长度
        pwd = new byte[digest.length + SALT_LENGTH];
        // 将盐的字节拷贝到生成的加密口令字节数组的第12个字节，以便在验证口令时取出
        System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH);
        // 将消息摘要拷贝到加密口令字节数组从第13个字节开始的字节
        System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);
        // 将字节数组格式加密后的口令转化为16进制字符串格式的口令
        return byteToHexString(pwd);
    }
}
