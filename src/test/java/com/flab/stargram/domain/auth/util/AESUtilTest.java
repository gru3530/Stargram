package com.flab.stargram.domain.auth.util;

import static org.junit.jupiter.api.Assertions.*;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AESUtilTest {
    private SecretKey secretKey;

    @BeforeEach
    void setUp() throws Exception {
        secretKey = AESUtil.generateKey();
    }

    @Test
    void testEncryptionDecryption() {
        String originalText = "테스트 문자열";

        String encryptedText = AESUtil.encrypt(originalText, secretKey);
        assertNotNull(encryptedText, "암호화된 텍스트는 null이 아니어야 합니다.");

        String decryptedText = AESUtil.decrypt(encryptedText, secretKey);
        assertNotNull(decryptedText, "복호화된 텍스트는 null이 아니어야 합니다.");
        assertEquals(originalText, decryptedText, "복호화된 텍스트가 원본 텍스트와 일치해야 합니다.");
    }


}