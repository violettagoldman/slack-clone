package com.test;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static com.helpers.PasswordHelper.comparePassAndHashedPassword;
import static com.helpers.PasswordHelper.hashPassword;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordHelperTest {

    @Test
    public void hashPasswordTest() throws NoSuchAlgorithmException {
        String pass = "test";
        String passHashed = "n4bQgYhMfWWaL+qgxVrQFaO/TxsrC4Is0V1sFbDwCgg=";

        assertEquals(passHashed,hashPassword(pass));
    }

    @Test
    public void comparePassAndHashedPasswordTest() throws NoSuchAlgorithmException {
        String pass = "test";
        String passHashed = "n4bQgYhMfWWaL+qgxVrQFaO/TxsrC4Is0V1sFbDwCgg=";

        assertTrue(comparePassAndHashedPassword(pass,passHashed));
    }

}
