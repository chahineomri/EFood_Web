/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.views;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.UserService;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author sarawahada
 */
public class EmailForgotPwdForm extends BaseForm {
    TextField email;
    int randomNum=ThreadLocalRandom.current().nextInt(100000,999999);

    public EmailForgotPwdForm (Resources res) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
         Container emailC = FlowLayout.encloseCenter(
                new Label("Entrer your email!", "WelcomeWhite")
        );
          Container codeC = FlowLayout.encloseCenter(
                new Label("Enter your verification code!", "WelcomeWhite2")
        );
         getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        
        email = new TextField("",TextField.EMAILADDR);
        email.setSingleLineTextArea(false);
        TextField code = new TextField("",TextField.PASSWORD);
        code.setSingleLineTextArea(true);
        Button resend = new Button("Resend");
        Button send = new Button("Send");
        Button haveAnAcount = new Button("Login ?");
        haveAnAcount.addActionListener( e -> previous.show());
        Button validate = new Button("Validate");
        validate.setUIID("LoginButton");
        send.setUIID("LoginButton");
        codeC.setVisible(false);
        validate.setVisible(false);
        code.setVisible(false);
        resend.setVisible(false);
        send.requestFocus();
        
        send.addActionListener(e -> {
             
           User u = UserService.getInstance().getUser(email.getText());
        if (null ==u){
             Dialog.show("Failed", "User not found!", "ok",null);
             System.out.print("user not found");
            
        }
        else{
            sendMail(email.getText());
            System.out.println("Mail sent !");
            Dialog.show("Password reset","We sent you a code to reset your password! Check your Email!",new Command("OK"));
            send.setVisible(false);
            email.setVisible(false);
            validate.setVisible(true);
            code.setVisible(true);
            resend.setVisible(true);
            codeC.setVisible(true);
            emailC.setVisible(false);
            refreshTheme();
        }
        });  
    
        Label codeLabel= new Label("Enter your code","ControlLabel");
        codeLabel.setVisible(false);
        validate.addActionListener(l -> {
         
         
         if(code.getText().equals(""))
         {
                 refreshTheme();
                 codeLabel.setVisible(true);
                
         }
         else if ((int)Float.parseFloat(code.getText()) == randomNum)
         {
             
             System.out.print("yessssssssss");
             Dialog.show("Success", "You can now change your password!", "ok",null);   
             new ForgotPassword(res).show();
             refreshTheme();
         }
         else  {

                  Dialog.show("Failed", "Code entered is incorrect!", "ok",null);
                  System.out.print("nooo");
                  
         }
        
        
                  
        });
            resend.addActionListener(l -> {
            refreshTheme();
            emailC.setVisible(true);
            send.setVisible(true);
            email.setVisible(true);
            validate.setVisible(false);
            code.setVisible(false);
            resend.setVisible(false);
            codeC.setVisible(false);
           });
               Container content = BoxLayout.encloseY(
                emailC,
                BorderLayout.center(email),
                send,
                createLineSeparator(),
                codeC,
                codeLabel,
                createLineSeparator(),
                BorderLayout.center(code),
                createLineSeparator(),
                validate,
                resend,
                haveAnAcount

        );
        
        content.setScrollableY(true);
        
        add(BorderLayout.CENTER,content);
           
    }

      public void sendMail(String address) {

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
                System.out.println("password");
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
         
            System.out.println(randomNum);
                      String htmlCode="<!DOCTYPE html>\n" +
"<html>\n" +
" <head>\n" +
"  <title>\n" +
"  </title>\n" +
"  <meta content=\"summary_large_image\" name=\"twitter:card\">\n" +
"  <meta content=\"website\" property=\"og:type\">\n" +
"  <meta content=\"\" property=\"og:description\">\n" +
"  <meta content=\"https://82y9j95ifn.preview-posted-stuff.com/V2-naiS-FPnm-d6mX-kcQZ/\" property=\"og:url\">\n" +
"  <meta content=\"https://pro-bee-beepro-thumbnails.s3.amazonaws.com/messages/768578/752136/1496341/7283525_large.jpg\" property=\"og:image\">\n" +
"  <meta content=\"\" property=\"og:title\">\n" +
"  <meta content=\"\" name=\"description\">\n" +
"  <meta charset=\"utf-8\">\n" +
"  <meta content=\"width=device-width\" name=\"viewport\">\n" +
"  <link href=\"https://fonts.googleapis.com/css?family=Bitter\" rel=\"stylesheet\" type=\"text/css\">\n" +
"  <link href=\"https://fonts.googleapis.com/css?family=Cormorant+Garamond\" rel=\"stylesheet\" type=\"text/css\">\n" +
"  <link href=\"https://fonts.googleapis.com/css?family=Cabin\" rel=\"stylesheet\" type=\"text/css\">\n" +
"  <link href=\"https://fonts.googleapis.com/css?family=Droid+Serif\" rel=\"stylesheet\" type=\"text/css\">\n" +
"  <link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\">\n" +
"  <link href=\"https://fonts.googleapis.com/css?family=Roboto+Slab\" rel=\"stylesheet\" type=\"text/css\">\n" +
"  <style>\n" +
"   .bee-row,\n" +
"		.bee-row-content {\n" +
"			position: relative\n" +
"		}\n" +
"\n" +
"		.bee-row-1,\n" +
"		.bee-row-2,\n" +
"		.bee-row-3 {\n" +
"			background-repeat: no-repeat\n" +
"		}\n" +
"\n" +
"		body {\n" +
"			background-color: #85a4cd;\n" +
"			color: #000;\n" +
"			font-family: Roboto Slab, Arial, Helvetica Neue, Helvetica, sans-serif\n" +
"		}\n" +
"\n" +
"		a {\n" +
"			color: #0068a5\n" +
"		}\n" +
"\n" +
"		* {\n" +
"			box-sizing: border-box\n" +
"		}\n" +
"\n" +
"		body,\n" +
"		h1,\n" +
"		p {\n" +
"			margin: 0\n" +
"		}\n" +
"\n" +
"		.bee-row-content {\n" +
"			max-width: 650px;\n" +
"			margin: 0 auto;\n" +
"			display: flex\n" +
"		}\n" +
"\n" +
"		.bee-row-content .bee-col-w12 {\n" +
"			flex-basis: 100%\n" +
"		}\n" +
"\n" +
"		.bee-html-block {\n" +
"			text-align: center\n" +
"		}\n" +
"\n" +
"		.bee-divider,\n" +
"		.bee-image {\n" +
"			overflow: auto\n" +
"		}\n" +
"\n" +
"		.bee-divider .center,\n" +
"		.bee-image .bee-center {\n" +
"			margin: 0 auto\n" +
"		}\n" +
"\n" +
"		.bee-row-1 .bee-col-1 .bee-block-3 {\n" +
"			width: 100%\n" +
"		}\n" +
"\n" +
"		.bee-image img {\n" +
"			display: block;\n" +
"			width: 100%\n" +
"		}\n" +
"\n" +
"		.bee-social .icon img {\n" +
"			max-height: 32px\n" +
"		}\n" +
"\n" +
"		.bee-text {\n" +
"			overflow-wrap: anywhere\n" +
"		}\n" +
"\n" +
"		@media (max-width:670px) {\n" +
"			.bee-row-content:not(.no_stack) {\n" +
"				display: block\n" +
"			}\n" +
"		}\n" +
"\n" +
"		.bee-row-1 .bee-row-content,\n" +
"		.bee-row-2 .bee-row-content,\n" +
"		.bee-row-3 .bee-row-content {\n" +
"			background-repeat: no-repeat;\n" +
"			color: #000\n" +
"		}\n" +
"\n" +
"		.bee-row-1 .bee-col-1 {\n" +
"			padding-bottom: 5px;\n" +
"			padding-top: 5px\n" +
"		}\n" +
"\n" +
"		.bee-row-1 .bee-col-1 .bee-block-1 {\n" +
"			padding: 30px\n" +
"		}\n" +
"\n" +
"		.bee-row-1 .bee-col-1 .bee-block-2 {\n" +
"			padding-bottom: 10px;\n" +
"			text-align: center;\n" +
"			width: 100%\n" +
"		}\n" +
"\n" +
"		.bee-row-1 .bee-col-1 .bee-block-10,\n" +
"		.bee-row-1 .bee-col-1 .bee-block-4,\n" +
"		.bee-row-1 .bee-col-1 .bee-block-7,\n" +
"		.bee-row-1 .bee-col-1 .bee-block-9,\n" +
"		.bee-row-2 .bee-col-1 .bee-block-1,\n" +
"		.bee-row-3 .bee-col-1 .bee-block-3 {\n" +
"			padding: 10px\n" +
"		}\n" +
"\n" +
"		.bee-row-1 .bee-col-1 .bee-block-5,\n" +
"		.bee-row-1 .bee-col-1 .bee-block-6 {\n" +
"			padding: 5px 10px\n" +
"		}\n" +
"\n" +
"		.bee-row-1 .bee-col-1 .bee-block-11,\n" +
"		.bee-row-3 .bee-col-1 .bee-block-1 {\n" +
"			padding: 15px\n" +
"		}\n" +
"\n" +
"		.bee-row-2 {\n" +
"			background-color: #c4d6ec\n" +
"		}\n" +
"\n" +
"		.bee-row-2 .bee-col-1 {\n" +
"			padding-bottom: 20px;\n" +
"			padding-top: 20px\n" +
"		}\n" +
"\n" +
"		.bee-row-3 {\n" +
"			background-color: #f3f6fe\n" +
"		}\n" +
"\n" +
"		.bee-row-3 .bee-col-1 .bee-block-2 {\n" +
"			padding: 10px 20px;\n" +
"			text-align: center\n" +
"		}\n" +
"  </style>\n" +
" </head>\n" +
" <body>\n" +
"  <div class=\"bee-page-container\">\n" +
"   <div class=\"bee-row bee-row-1\">\n" +
"    <div class=\"bee-row-content\">\n" +
"     <div class=\"bee-col bee-col-1 bee-col-w12\">\n" +
"      <div class=\"bee-block bee-block-1 bee-divider\">\n" +
"       <div class=\"spacer\" style=\"height:0px;\">\n" +
"       </div>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-2 bee-heading\">\n" +
"       <h1 style=\"color:#ffffff;direction:ltr;font-family:'Roboto Slab', Arial, 'Helvetica Neue', Helvetica, sans-serif;font-size:30px;font-weight:normal;letter-spacing:2px;line-height:120%;text-align:center;margin-top:0;margin-bottom:0;\">\n" +
"        <strong>\n" +
"         FORGOT YOUR PASSWORD?\n" +
"        </strong>\n" +
"       </h1>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-3 bee-image\">\n" +
"       <img alt=\"Wrong Password Animation\" class=\"bee-center bee-autowidth\" src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/3856/GIF_password.gif\" style=\"max-width:500px;\">\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-4 bee-divider\">\n" +
"       <div class=\"spacer\" style=\"height:0px;\">\n" +
"       </div>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-5 bee-text\">\n" +
"       <div class=\"bee-text-content\" style=\"font-size: 14px; line-height: 120%; color: #3f4d75; font-family: inherit;\">\n" +
"        <p style=\"font-size: 14px; line-height: 16px; text-align: center;\">\n" +
"         <span style=\"font-size: 20px; line-height: 24px;\">\n" +
"          Don't worry, be happy!\n" +
"         </span>\n" +
"        </p>\n" +
"       </div>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-6 bee-text\">\n" +
"       <div class=\"bee-text-content\" style=\"font-size: 14px; line-height: 120%; color: #3f4d75; font-family: inherit;\">\n" +
"        <p style=\"font-size: 14px; line-height: 16px; text-align: center;\">\n" +
"         <span style=\"font-size: 22px; line-height: 26px;\">\n" +
"          Let's get you a new password.\n" +
"         </span>\n" +
"        </p>\n" +
"       </div>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-7 bee-divider\">\n" +
"       <div class=\"spacer\" style=\"height:0px;\">\n" +
"       </div>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-8 bee-html-block\">\n" +
"       <div class=\"our-class\">\n" +
"        Your verification code :\n" +
"        <p>\n" +randomNum+
"        </p>\n" +
"       </div>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-9 bee-divider\">\n" +
"       <div class=\"spacer\" style=\"height:0px;\">\n" +
"       </div>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-10 bee-text\">\n" +
"       <div class=\"bee-text-content\" style=\"font-size: 14px; line-height: 120%; color: #3f4d75; font-family: inherit;\">\n" +
"        <p style=\"font-size: 14px; line-height: 16px; text-align: center;\">\n" +
"         <span style=\"font-size: 14px; line-height: 16px;\">\n" +
"          If you didn&rsquo;t request to change your password, simply ignore this email.\n" +
"         </span>\n" +
"        </p>\n" +
"       </div>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-11 bee-divider\">\n" +
"       <div class=\"spacer\" style=\"height:0px;\">\n" +
"       </div>\n" +
"      </div>\n" +
"     </div>\n" +
"    </div>\n" +
"   </div>\n" +
"   <div class=\"bee-row bee-row-2\">\n" +
"    <div class=\"bee-row-content\">\n" +
"     <div class=\"bee-col bee-col-1 bee-col-w12\">\n" +
"      <div class=\"bee-block bee-block-1 bee-text\">\n" +
"       <div class=\"bee-text-content\" style=\"font-size: 14px; line-height: 120%; color: #3f4d75; font-family: inherit;\">\n" +
"        <p style=\"font-size: 14px; line-height: 16px; text-align: center;\">\n" +
"         <span style=\"font-size: 12px; line-height: 14px;\">\n" +
"          This link will&nbsp;expire in 24 hours.&nbsp;If you continue to have problems\n" +
"         </span>\n" +
"         <br style=\"\">\n" +
"         <span style=\"font-size: 12px; line-height: 14px;\">\n" +
"          please feel free to contact us .\n" +
"          <a href=\"https://www.example.com\" rel=\"noopener\" style=\"text-decoration: underline; color: #ffffff;\" target=\"_blank\"></a>\n" +
"         </span>\n" +
"        </p>\n" +
"       </div>\n" +
"      </div>\n" +
"     </div>\n" +
"    </div>\n" +
"   </div>\n" +
"   <div class=\"bee-row bee-row-3\">\n" +
"    <div class=\"bee-row-content\">\n" +
"     <div class=\"bee-col bee-col-1 bee-col-w12\">\n" +
"      <div class=\"bee-block bee-block-1 bee-divider\">\n" +
"       <div class=\"spacer\" style=\"height:10px;\">\n" +
"       </div>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-2 bee-social\">\n" +
"       <div class=\"content\">\n" +
"        <span class=\"icon\" style=\"padding:0 10px 0 10px;\">\n" +
"         <a href=\"https://www.facebook.com/\">\n" +
"          <img alt=\"Facebook\" src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-color/facebook@2x.png\" title=\"facebook\"></a>\n" +
"        </span>\n" +
"        <span class=\"icon\" style=\"padding:0 10px 0 10px;\">\n" +
"         <a href=\"https://www.twitter.com/\">\n" +
"          <img alt=\"Twitter\" src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-color/twitter@2x.png\" title=\"twitter\"></a>\n" +
"        </span>\n" +
"        <span class=\"icon\" style=\"padding:0 10px 0 10px;\">\n" +
"         <a href=\"https://www.linkedin.com/\">\n" +
"          <img alt=\"Linkedin\" src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-color/linkedin@2x.png\" title=\"linkedin\"></a>\n" +
"        </span>\n" +
"        <span class=\"icon\" style=\"padding:0 10px 0 10px;\">\n" +
"         <a href=\"https://www.instagram.com/\">\n" +
"          <img alt=\"Instagram\" src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-color/instagram@2x.png\" title=\"instagram\"></a>\n" +
"        </span>\n" +
"       </div>\n" +
"      </div>\n" +
"      <div class=\"bee-block bee-block-3 bee-divider\">\n" +
"       <div class=\"spacer\" style=\"height:10px;\">\n" +
"       </div>\n" +
"      </div>\n" +
"     </div>\n" +
"    </div>\n" +
"   </div>\n" +
"  </div>\n" +
" </body>\n" +
"</html>";
            textBodyPart.setContent(htmlCode,"text/html");
            emailContent.addBodyPart(textBodyPart);
            msg.setContent(emailContent);
            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
        }
        
        }
    }
     
    