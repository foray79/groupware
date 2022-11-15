package com.foray.gw.Service;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
//encrypt
public class Encrypt {
    public String sha256(String text) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());

            return bytesToHex(md.digest());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public String md5(String text) throws  NoSuchAlgorithmException{
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());

            return bytesToHex( md.digest());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
