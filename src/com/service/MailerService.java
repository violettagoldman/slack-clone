package com.service;

import javax.mail.*;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
public class MailerService{

    public static final String email = "ppijako@yahoo.com";
    public static final String password = "Pijakosupport123";
    public static void sendResetEmail(String emailString){
      try{
        Email email = new SimpleEmail();
        email.setHostName("smtp.mail.yahoo.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(MailerService.email, password));
        email.setSSLOnConnect(true);
        email.setFrom("ppijako@yahoo.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo(emailString);
        email.send();

    }catch (Exception e){
      }
    }
    public static void sendWelcomeEmail(){

    }


}
