package com.ykse.blogs.service;

import java.io.File;

public interface FileEncryptionService {
    void encrypt(File fileIn, String fileName, String sKey);
    void decrypt(File fileIn, String sKey);
    String md5s(String plainText);
}
