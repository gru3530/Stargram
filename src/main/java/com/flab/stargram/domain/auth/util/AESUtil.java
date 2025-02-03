package com.flab.stargram.domain.auth.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {

    private static final String AES = "AES";
    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256;
    private static final int IV_SIZE = 12;
    private static final int TAG_BIT_LENGTH = 128;

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES);
        keyGen.init(KEY_SIZE);
        return keyGen.generateKey();
    }

    public static String encrypt(String data, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);

            byte[] iv = new byte[IV_SIZE];
            new SecureRandom().nextBytes(iv);

            GCMParameterSpec spec = new GCMParameterSpec(TAG_BIT_LENGTH, iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);

            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            byte[] encryptedWithIv = new byte[IV_SIZE + encryptedData.length];
            System.arraycopy(iv, 0, encryptedWithIv, 0, IV_SIZE);
            System.arraycopy(encryptedData, 0, encryptedWithIv, IV_SIZE, encryptedData.length);

            return Base64.getEncoder().encodeToString(encryptedWithIv);
        } catch (Exception e) {
            throw new RuntimeException("Error during encryption: " + e.getMessage(), e);
        }
    }



    public static String decrypt(String encryptedData, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
            byte[] decodedData = Base64.getDecoder().decode(encryptedData);

            byte[] iv = new byte[IV_SIZE];
            System.arraycopy(decodedData, 0, iv, 0, IV_SIZE);

            byte[] encryptedBytes = new byte[decodedData.length - IV_SIZE];
            System.arraycopy(decodedData, IV_SIZE, encryptedBytes, 0, encryptedBytes.length);

            GCMParameterSpec spec = new GCMParameterSpec(TAG_BIT_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, key, spec);

            byte[] decryptedData = cipher.doFinal(encryptedBytes);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error during decryption: " + e.getMessage(), e);
        }
    }
}