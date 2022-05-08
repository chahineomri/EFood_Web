/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.views;

/**
 *
 * @author sarawahada
 */

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;

import com.codename1.ui.Label;
import com.codename1.ui.TextField;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.UserService;
import java.util.Properties;


import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class ForgotPassword extends BaseForm {
        public static String mailUpdate="a";

    public ForgotPassword(Resources res) {
       super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
         Container newPassC = FlowLayout.encloseCenter(
                new Label("Let's get you a new password!", "WelcomeWhite2")
        );

        Button change = new Button("Change");
        change.setUIID("LoginButton");
        TextField pass = new TextField("","Enter your password",20,TextField.PASSWORD);
        Label passLabel= new Label("Enter your new password","ControlLabel");
        passLabel.setVisible(false);
        Label passLabelLength= new Label("Password is too short","ControlLabel");
        passLabelLength.setVisible(false);
            change.addActionListener(e -> {
                if (pass.getText().equals("")){
                 refreshTheme();
                 passLabel.setVisible(true);
                 passLabelLength.setVisible(false);
                }
                else if (pass.getText().length()<8){
                 refreshTheme();
                 passLabelLength.setVisible(true);
                 passLabel.setVisible(false);
                }
                else{
            EmailForgotPwdForm ef = new EmailForgotPwdForm(res);
            mailUpdate = ef.email.getText().toString();
            System.out.println(mailUpdate);
            User u = UserService.getInstance().getUser(mailUpdate);
            Dialog.show("Password reset","You password was updated successfully!",new Command("OK"));
            new LoginForm(res).show();
            refreshTheme();
                }
                
                 });
        Container content = BoxLayout.encloseY(
                newPassC,
                createLineSeparator(),
                passLabel,
                passLabelLength,
                createLineSeparator(),
                BorderLayout.center(pass),
                change
              
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);

    }
   
    public void sendMailUpdate(String address) {

       //authentification info
        String username = "efoodappproject@gmail.com";
        String password = "EfoodAppPiDEV";
        String fromEmail = "efoodappproject@gmail.com";
    
        String toEmail =address;
        System.out.println(toEmail);

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("password changed");
                return new PasswordAuthentication(username, password);
            }
        });

        //start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("Password Reset");
            //msg.setText("Email Body Text");
            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
            System.out.println("Success");
            textBodyPart.setText("Password changed ");
            emailContent.addBodyPart(textBodyPart);
            msg.setContent(emailContent);
            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
        } 
    }
}