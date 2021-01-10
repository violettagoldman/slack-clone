package com.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import com.controllers.UserController;

public class MailerService{
/*
    public static final String email = "ppijako@yahoo.com";
    public static final String username ="ppijako";
    //public static final String password = "Pijakosupport123";
    public static final String password = "qqetodazgvanqaad";

 */
    public static final String email = "spijako@gmail.com";
    public static final String password = "Pijako123";

    public static void sendEmail(String emailString,String destination, String subject) throws Exception{
        Email email = new SimpleEmail();
        email.setCharset("UTF-8");
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(MailerService.email, MailerService.password));
        email.setSSLOnConnect(true);
        email.setFrom(MailerService.email);
        email.setSubject(subject);
        email.setMsg(emailString);
        email.addTo(destination);
         System.out.println(email.send());
    }

    public static void sendResetMail(String dest)throws Exception{
        String emailString = "Bonjour,\n\n votre mot de pass est ré-initialisé à \"Password12345\".\n\n Vous devez le changer le plus rapidement possible.\n\nTrès bonne journée\n\nPijako Support";
        sendEmail(emailString,dest,"Mot de passe Pijako");
    }
    public static void sendWelcomeEmail(String dest)throws Exception{
        String email="Bonjour,\n\nmerci d'avoir choisi Pijako.\n\nTrès bonne journée, gros bisou.\n\n\n\nPijako Support";
        sendEmail(email,dest,"Welcome to Pijako!");
    }


}
