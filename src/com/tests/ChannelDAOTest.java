package com.tests;

import org.junit.jupiter.api.Test;

import static com.dao.concret.ChannelDAO.*;
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
