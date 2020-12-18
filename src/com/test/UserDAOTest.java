package com.test;

import org.junit.jupiter.api.Test;

import static com.controllers.UserController.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDAOTest {

    @Test
    public void isEmailValidTest() {
        String email = "Test@test.com";

        assertTrue(isEmailValid(email));
    }

    @Test
    public void isUsernameValidTest() {
        String usernameT = "Zahz5";
        String usernameF = "a";

        assertTrue(isUsernameValid(usernameT));
        assertFalse(isUsernameValid(usernameF));
    }

    @Test
    public void isPasswordValidTest() {
        String pass = "Az0Er1Ty2";

        assertTrue(isPasswordValid(pass));
    }

}
*/