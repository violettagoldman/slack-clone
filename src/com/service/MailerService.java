package com.service;

<<<<<<< HEAD
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
public class MailerService{
/*
    public static final String email = "ppijako@yahoo.com";
    public static final String username ="ppijako";
    //public static final String password = "Pijakosupport123";
    public static final String password = "qqetodazgvanqaad";

 */
    public static final String email = "spijako@gmail.com";
    public static final String password = "Pijako123";

    public static void sendEmail(String emailString){
      try{
        Email email = new SimpleEmail();
/*
        email.setHostName("smtp.mail.yahoo.com");
        email.setSmtpPort(465);
        //email.setAuthenticator(new DefaultAuthenticator(MailerService.username, password));
        email.setAuthentication(MailerService.email, MailerService.password );
        email.setSSLOnConnect(true);
 */
         email.setHostName("smtp.googlemail.com");
         email.setSmtpPort(465);
         email.setAuthenticator(new DefaultAuthenticator(MailerService.email, MailerService.password));
         email.setSSLOnConnect(true);
         email.setFrom(MailerService.email);
         email.setSubject("TestMail");
         email.setMsg("This is a test mail ... :-)");
         email.addTo(emailString);
         System.out.println(email.send());

    }catch (Exception e){
          System.err.println(e);
          System.err.println("errr");
=======
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
>>>>>>> yoann
      }
    }
    public static void sendWelcomeEmail(){

    }


}