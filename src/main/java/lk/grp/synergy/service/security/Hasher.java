package lk.grp.synergy.service.security;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by isuru on 1/14/17.
 */
public class Hasher {

    private static final int SECRET = "i_hat3_windows".hashCode();
    private static BASE64Encoder base64Encoder = new BASE64Encoder();

    public static  String hash(String plainText){
        String hash = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(plainText.getBytes());
            hash = base64Encoder.encode(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

}
