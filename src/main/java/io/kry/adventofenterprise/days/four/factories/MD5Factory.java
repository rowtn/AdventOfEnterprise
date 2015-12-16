package io.kry.adventofenterprise.days.four.factories;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Factory {

    public static byte[] hash(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(message.getBytes());
    }

}
