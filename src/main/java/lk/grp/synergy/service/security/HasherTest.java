package lk.grp.synergy.service.security;

import static  org.junit.Assert.*;

/**
 * Created by isuru on 1/14/17.
 */
public class HasherTest {

    @org.junit.Test
    public void hash() throws Exception {
        String plainTxt = "isurunix";
        String hashOne = Hasher.hash(plainTxt);
        System.out.println("Hash (1) : "+hashOne);
        String hashTwo = Hasher.hash(plainTxt);
        System.out.println("Hash (2) : "+hashTwo);
        assertEquals(hashOne,hashTwo);
    }

}