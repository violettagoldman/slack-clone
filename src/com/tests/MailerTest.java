package com.tests;

import com.service.MailerService;
import org.junit.Test;

public class MailerTest {
    @Test
    public void SendEmail(){
        MailerService.sendEmail("pijako@yopmail.com");
    }
    //test@yopmail.com
}
