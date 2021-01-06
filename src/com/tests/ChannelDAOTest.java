package com.tests;

<<<<<<< HEAD
/*
package com.test;
>>>>>>> yoann:src/com/test/ChannelDAOTest.java

import org.junit.jupiter.api.Test;

import statica com.controller.ChannelController.isChannelNameValid;
import static com.dao.concret.ChannelDAO.*;
=======
import org.junit.jupiter.api.Test;

import static com.helpers.RegexHelper.isChannelNameValid;
>>>>>>> yoann
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChannelDAOTest {

    @Test
    public void isChannelNameValidTest() {
        String channelName = "testChannel";

        assertTrue(isChannelNameValid(channelName), "erreur");
    }

    @Test
    public void isChannelNameInvalidTest() {
        String channelName = "testChannellllllllllll";

        assertFalse(isChannelNameValid(channelName), "erreur");
    }

}
<<<<<<< HEAD
*/
=======

>>>>>>> yoann
