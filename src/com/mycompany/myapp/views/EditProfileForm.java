/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.views;

/**
 *
 * @author sarawahada
 */
import com.codename1.components.FloatingActionButton;
import com.codename1.io.Log;
import com.codename1.sms.ActivationForm;
import com.codename1.sms.TwilioSMS;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;

import static com.codename1.ui.Component.LEFT;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
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




public class EditProfileForm extends SideMenuBaseForm {
       String accountSID = "ACed511353381614dcc72e1c248160e5e6";
String authToken = "da30048051a082731b641544ad707042";
String fromPhone = "+19705288371";
    public EditProfileForm(Resources res) {
         super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(hi.getWidth(), hi.getWidth() / 5, 0xffff0000), true);
         URLImage profile = URLImage.createToStorage(placeholder, "400px-AGameOfThrones.jpg",
        "http://awoiaf.westeros.org/images/thumb/9/93/AGameOfThrones.jpg/400px-AGameOfThrones.jpg");
        profile.fetch();
       Style stitle = hi.getToolbar().getTitleComponent().getUnselectedStyle();
       stitle.setBgImage(profile);
        //Image profilePic = res.getImage("SessionManager");
        Image mask = res.getImage("round-mask.png");
       // profile = profile.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profile, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
       

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Hi,"+SessionManager.getNameUser(), "Title"),
                                    new Label(SessionManager.getEmailUser(), "SubTitle")
                                )
                            ).add(BorderLayout.WEST, profilePicLabel)
                        
                );
              System.out.print(SessionManager.getUserStatus());
        if("true".equals(SessionManager.getUserStatus())) {
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_LOCK);
        fab.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setPaddingTop(10);
        fab.setVisible(false);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        
        }
        else{
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_LOCK);
        fab.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setPaddingTop(10);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        fab.addActionListener((ActionEvent e) -> {
            
        TwilioSMS smsAPI = TwilioSMS.create(accountSID, authToken, fromPhone);
        Label confirm = new Label("Verify your account");
        confirm.getAllStyles().setFgColor(000000);
        ActivationForm.create(confirm.toString())
                .show(s -> Log.p(s), smsAPI);
        refreshTheme();
        fab.setVisible(false);
        UserService.getInstance().verify(SessionManager.getEmailUser());
        });
      
            
        }       
        add(new Label("Edit Account", "TodayTitle"));
        
    
       
        
        Form previous = Display.getInstance().getCurrent();

        TextField name = new TextField("", "Name", 20, TextField.ANY);
       
        TextField lastname = new TextField("", "Last Name", 20, TextField.ANY);
        TextField email = new TextField(SessionManager.getEmailUser(), SessionManager.getEmailUser(), 20, TextField.EMAILADDR);
        email.setEditable(false);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
     
        name.getAllStyles().setMargin(LEFT, 0);
        name.getAllStyles().setFgColor(000000);
        lastname.getAllStyles().setMargin(LEFT, 0);
        lastname.getAllStyles().setFgColor(000000);
        email.getAllStyles().setMargin(LEFT, 0);
        email.getAllStyles().setFgColor(000000);
        password.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setFgColor(000000);
  
        Label nameLabel= new Label("Enter your name","ControlLabel");
        nameLabel.setVisible(false);
        Label lastnameLabel= new Label("Enter your last name","ControlLabel");
        lastnameLabel.setVisible(false);
        Label passwordLabel= new Label("Enter your old password","ControlLabel");
        passwordLabel.setVisible(false);
         add(email);
         add(nameLabel);
         add(name);
         add(lastnameLabel);
         add(lastname);
         add(passwordLabel);
         add(password);
        Button img = new Button("Select a profile picture");

        Button next = new Button("Update");
        next.setUIID("LoginButton");
        add(next);
        
        next.addActionListener((ActionEvent e) -> {
        if(name.getText().equals("")){
         refreshTheme();
         nameLabel.setVisible(true);
         lastnameLabel.setVisible(false);
          passwordLabel.setVisible(false);
        }
        else if(lastname.getText().equals("")){
         refreshTheme();
         lastnameLabel.setVisible(true);
         nameLabel.setVisible(false);
         passwordLabel.setVisible(false);
        }
         else if(password.getText().equals("")){
         refreshTheme();
         passwordLabel.setVisible(true);
         nameLabel.setVisible(false);
         lastnameLabel.setVisible(false);
        }
    

         else {
            UserService.getInstance().update(name.getText(),lastname.getText(),email.getText(),password.getText());
            sendMailUpdated(email.getText());
            Dialog.show("Success", "Account updated!", "ok",null);
            System.out.print("user updated");
            
        }

        });

        // We remove the extra space for low resolution devices so things fit better
         Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

         setupSideMenu(res);
    }

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void sendMailUpdated(String address) {

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
            msg.setSubject("Account created");
            //msg.setText("Email Body Text");
            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
             String htmlCode="<!DOCTYPE html>\n" +
"<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
"\n" +
"<head>\n" +
"	<title></title>\n" +
"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"	<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n" +
"	<!--[if !mso]><!-->\n" +
"	<link href=\"https://fonts.googleapis.com/css?family=Chivo\" rel=\"stylesheet\" type=\"text/css\">\n" +
"	<link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\" type=\"text/css\">\n" +
"	<!--<![endif]-->\n" +
"	<style>\n" +
"		* {\n" +
"			box-sizing: border-box;\n" +
"		}\n" +
"\n" +
"		body {\n" +
"			margin: 0;\n" +
"			padding: 0;\n" +
"		}\n" +
"\n" +
"		a[x-apple-data-detectors] {\n" +
"			color: inherit !important;\n" +
"			text-decoration: inherit !important;\n" +
"		}\n" +
"\n" +
"		#MessageViewBody a {\n" +
"			color: inherit;\n" +
"			text-decoration: none;\n" +
"		}\n" +
"\n" +
"		p {\n" +
"			line-height: inherit\n" +
"		}\n" +
"\n" +
"		@media (max-width:720px) {\n" +
"			.row-content {\n" +
"				width: 100% !important;\n" +
"			}\n" +
"\n" +
"			.column .border {\n" +
"				display: none;\n" +
"			}\n" +
"\n" +
"			.stack .column {\n" +
"				width: 100%;\n" +
"				display: block;\n" +
"			}\n" +
"		}\n" +
"	</style>\n" +
"</head>\n" +
"\n" +
"<body style=\"background-color: #FFFFFF; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
"	<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #FFFFFF;\">\n" +
"		<tbody>\n" +
"			<tr>\n" +
"				<td>\n" +
"					<table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/96/bg_hero_illo.jpg'); background-repeat: repeat;\">\n" +
"						<tbody>\n" +
"							<tr>\n" +
"								<td>\n" +
"									<table class=\"row-content\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 700px;\" width=\"700\">\n" +
"										<tbody>\n" +
"											<tr>\n" +
"												<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 0px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
"													<table class=\"image_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
"																<div align=\"center\" style=\"line-height:10px\"><img src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/96/logo.png\" style=\"display: block; height: auto; border: 0; width: 140px; max-width: 100%;\" width=\"140\" alt=\"Image\" title=\"Image\"></div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
"														<tr>\n" +
"															<td style=\"padding-bottom:35px;padding-left:10px;padding-right:10px;padding-top:10px;\">\n" +
"																<div style=\"font-family: Arial, sans-serif\">\n" +
"																	<div style=\"font-family: 'Chivo', Arial, Helvetica, sans-serif; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #5beda6; line-height: 1.2;\">\n" +
"																		<p style=\"margin: 0; text-align: center; font-size: 12px;\"><span style=\"color:#ffcc00;\"><span style=\"font-size:38px;\"><em>Your profile was updated successfully!</em></span></span></p>\n" +
"																	</div>\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"image_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
"																<div align=\"center\" style=\"line-height:10px\"><img src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/96/illo_hero_transparent.png\" style=\"display: block; height: auto; border: 0; width: 587px; max-width: 100%;\" width=\"587\" alt=\"Image\" title=\"Image\"></div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"												</td>\n" +
"											</tr>\n" +
"										</tbody>\n" +
"									</table>\n" +
"								</td>\n" +
"							</tr>\n" +
"						</tbody>\n" +
"					</table>\n" +
"					<table class=\"row row-2\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"						<tbody>\n" +
"							<tr>\n" +
"								<td>\n" +
"									<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 700px;\" width=\"700\">\n" +
"										<tbody>\n" +
"											<tr>\n" +
"												<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-left: 25px; padding-right: 25px; padding-top: 25px; padding-bottom: 25px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
"													<table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
"														<tr>\n" +
"															<td style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:10px;\">\n" +
"																<div style=\"font-family: Tahoma, Verdana, sans-serif\">\n" +
"																	<div style=\"font-size: 12px; font-family: 'Lato', Tahoma, Verdana, Segoe, sans-serif; mso-line-height-alt: 18px; color: #555555; line-height: 1.5;\">\n" +
"																		<p style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 27px;\"><span style=\"font-size:18px;\">Hi there!</span></p>\n" +
"																		<p style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 27px;\"><span style=\"font-size:18px;\">We want to let you know that your profile has been updated successfully.</span></p>\n" +
"																	</div>\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"divider_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td>\n" +
"																<div align=\"center\">\n" +
"																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"																		<tr>\n" +
"																			<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;\"><span>&#8202;</span></td>\n" +
"																		</tr>\n" +
"																	</table>\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
"														<tr>\n" +
"															<td>\n" +
"																<div style=\"font-family: sans-serif\">\n" +
"																	<div style=\"font-size: 14px; mso-line-height-alt: 21px; color: #393d47; line-height: 1.5; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif;\">\n" +
"																		<p style=\"margin: 0; font-size: 14px; text-align: center;\"><strong><span style=\"font-size:16px;\">If it wasn't you who updated the profile, Let us know.</span></strong></p>\n" +
"																	</div>\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"												</td>\n" +
"											</tr>\n" +
"										</tbody>\n" +
"									</table>\n" +
"								</td>\n" +
"							</tr>\n" +
"						</tbody>\n" +
"					</table>\n" +
"					<table class=\"row row-3\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #9ef2c3;\">\n" +
"						<tbody>\n" +
"							<tr>\n" +
"								<td>\n" +
"									<table class=\"row-content\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #333; width: 700px;\" width=\"700\">\n" +
"										<tbody>\n" +
"											<tr>\n" +
"												<td class=\"column column-1\" width=\"50%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
"													<table class=\"image_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td style=\"padding-right:20px;width:100%;padding-left:0px;padding-top:5px;padding-bottom:45px;\">\n" +
"																<div align=\"right\" style=\"line-height:10px\"><img src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/96/gplay.gif\" style=\"display: block; height: auto; border: 0; width: 122px; max-width: 100%;\" width=\"122\" alt=\"Image\" title=\"Image\"></div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"												</td>\n" +
"												<td class=\"column column-2\" width=\"50%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
"													<table class=\"image_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td style=\"padding-left:20px;width:100%;padding-right:0px;padding-top:5px;padding-bottom:5px;\">\n" +
"																<div style=\"line-height:10px\"><img src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/96/appstore.png\" style=\"display: block; height: auto; border: 0; width: 122px; max-width: 100%;\" width=\"122\" alt=\"Image\" title=\"Image\"></div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"												</td>\n" +
"											</tr>\n" +
"										</tbody>\n" +
"									</table>\n" +
"								</td>\n" +
"							</tr>\n" +
"						</tbody>\n" +
"					</table>\n" +
"					<table class=\"row row-4\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #FFFFFF;\">\n" +
"						<tbody>\n" +
"							<tr>\n" +
"								<td>\n" +
"									<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 700px;\" width=\"700\">\n" +
"										<tbody>\n" +
"											<tr>\n" +
"												<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 40px; padding-bottom: 40px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
"													<table class=\"image_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td style=\"padding-bottom:15px;width:100%;padding-right:0px;padding-left:0px;\">\n" +
"																<div align=\"center\" style=\"line-height:10px\"><img src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/96/logo.png\" style=\"display: block; height: auto; border: 0; width: 148px; max-width: 100%;\" width=\"148\" alt=\"Image\" title=\"Image\"></div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
"														<tr>\n" +
"															<td>\n" +
"																<div style=\"font-family: sans-serif\">\n" +
"																	<div style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #5beda6; line-height: 1.2; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif;\">\n" +
"																		<p style=\"margin: 0; font-size: 14px; text-align: center;\"><span style=\"font-size:16px;\"><strong>EFood Delivery</strong></span></p>\n" +
"																		<p style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 14.399999999999999px;\">&nbsp;</p>\n" +
"																	</div>\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"social_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td>\n" +
"																<table class=\"social-table\" width=\"148px\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" align=\"center\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"																	<tr>\n" +
"																		<td style=\"padding:0 5px 0 0px;\"><a href=\"https://twitter.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-gray/twitter@2x.png\" width=\"32\" height=\"32\" alt=\"Twitter\" title=\"Twitter\" style=\"display: block; height: auto; border: 0;\"></a></td>\n" +
"																		<td style=\"padding:0 5px 0 0px;\"><a href=\"https://plus.google.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-gray/googleplus@2x.png\" width=\"32\" height=\"32\" alt=\"Google+\" title=\"Google+\" style=\"display: block; height: auto; border: 0;\"></a></td>\n" +
"																		<td style=\"padding:0 5px 0 0px;\"><a href=\"https://www.youtube.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-gray/youtube@2x.png\" width=\"32\" height=\"32\" alt=\"YouTube\" title=\"YouTube\" style=\"display: block; height: auto; border: 0;\"></a></td>\n" +
"																		<td style=\"padding:0 5px 0 0px;\"><a href=\"https://www.facebook.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-gray/facebook@2x.png\" width=\"32\" height=\"32\" alt=\"Facebook\" title=\"Facebook\" style=\"display: block; height: auto; border: 0;\"></a></td>\n" +
"																	</tr>\n" +
"																</table>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"												</td>\n" +
"											</tr>\n" +
"										</tbody>\n" +
"									</table>\n" +
"								</td>\n" +
"							</tr>\n" +
"						</tbody>\n" +
"					</table>\n" +
"					<table class=\"row row-5\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #F4F4F4;\">\n" +
"						<tbody>\n" +
"							<tr>\n" +
"								<td>\n" +
"									<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 700px;\" width=\"700\">\n" +
"										<tbody>\n" +
"											<tr>\n" +
"												<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 25px; padding-bottom: 25px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
"													<table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
"														<tr>\n" +
"															<td>\n" +
"																<div style=\"font-family: sans-serif\">\n" +
"																	<div style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2; font-family: Lato, Tahoma, Verdana, Segoe, sans-serif;\">\n" +
"																		<p style=\"margin: 0; font-size: 12px; text-align: center;\">All rights reserved © 2022 /&nbsp; Efood</p>\n" +
"																	</div>\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"												</td>\n" +
"											</tr>\n" +
"										</tbody>\n" +
"									</table>\n" +
"								</td>\n" +
"							</tr>\n" +
"						</tbody>\n" +
"					</table>\n" +
"				</td>\n" +
"			</tr>\n" +
"		</tbody>\n" +
"	</table><!-- End -->\n" +
"</body>\n" +
"\n" +
"</html>" ;
            textBodyPart.setContent(htmlCode,"text/html");
            emailContent.addBodyPart(textBodyPart);
            msg.setContent(emailContent);
            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
        }
        
        }
        
        
}
