package com.tests;

import com.service.MailerService;
import org.junit.Test;

public class MailerTest {
    @Test
    public void SendEmail(){
//        MailerService.sendEmail("pijako@yopmail.com");
    }
    //test@yopmail.com
    @Test
    public void sendWelcome(){
        try{
            MailerService.sendWelcomeEmail("yoann12345@yopmail.com");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Test
    public void sendReset(){
        try{
            MailerService.sendResetMail("yoann12345@yopmail.com");
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
