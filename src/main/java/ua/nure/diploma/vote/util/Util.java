package ua.nure.diploma.vote.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {
    public String encryptString(String inputPassword) {
        return DigestUtils.sha256Hex(inputPassword);
    }
}
