

package com.mycompany.myapp.views;


import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.sms.ActivationForm;
import com.codename1.sms.TwilioSMS;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.UserService;
import java.io.IOException;
import java.io.OutputStream;



public class ProfileForm extends SideMenuBaseForm {
   

  String accountSID = "ACed511353381614dcc72e1c248160e5e6";
String authToken = "da30048051a082731b641544ad707042";
String fromPhone = "+19705288371";
    public ProfileForm(Resources res) throws IOException {

           super(BoxLayout.y());
                   Form f;
           f = createProfileForm(res);
    }
    
       public Form createProfileForm(Resources res) throws IOException {
       
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        Image profile = Image.createImage(FileSystemStorage.getInstance().openInputStream(SessionManager.getProfilePicUser()));

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
              Button editLabel = new Button("");
        editLabel.setUIID("LoginButton");
        FontImage.setMaterialIcon(editLabel, FontImage.MATERIAL_ADD_A_PHOTO, 4);
        add(editLabel); 
        editLabel.setVisible(false);
        if("true".equals(SessionManager.getUserStatus())) {
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_LOCK);
        fab.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setPaddingTop(10);
        fab.setVisible(false);
        editLabel.setVisible(true);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
         refreshTheme();
           editLabel.addActionListener((ActionEvent e) -> {
             String filePath = Capture.capturePhoto();
        
        if (filePath != null) {
            try { 
                String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() +  ".jpg";
                Image img = Image.createImage(filePath);
   
                img = img.fill(mask.getWidth(), mask.getHeight());  
                profilePicLabel.setMask(mask.createMask());
                profilePicLabel.setIcon(img);
                try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored )) 
                {
               ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_JPEG, 0.9f);
               UserService.getInstance().UpdateProfilePic(SessionManager.getEmailUser(), pathToBeStored);
               Dialog.show("Success", "Profile picture updated!", "ok",null);
               System.out.print("pdp updated");
               SessionManager.setProfilePicUser(pathToBeStored);
                } catch (IOException ex) {
                }
            } catch (IOException ex) {
            }
           
        } });
        
        }
        else{
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_LOCK);
        fab.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setPaddingTop(10);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        fab.addActionListener((ActionEvent e) -> {
            
        TwilioSMS smsAPI = TwilioSMS.create(accountSID, authToken, fromPhone);
        
        ActivationForm.create("Verify your account")
                .show(s -> Log.p(s), smsAPI);
        
        fab.setVisible(false);
        editLabel.setVisible(false);
        UserService.getInstance().verify(SessionManager.getEmailUser());
        SessionManager.setUserStatus("true");
        });
      
            
        }
        
      
        add(new Label("Your Prefrences", "TodayTitle"));
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        FontImage restau = FontImage.createMaterial(FontImage.MATERIAL_RESTAURANT, "Label", 3);
        FontImage dish = FontImage.createMaterial(FontImage.MATERIAL_FASTFOOD, "Label", 3);
        FontImage wish = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, "Label", 3);
        FontImage bag = FontImage.createMaterial(FontImage.MATERIAL_LOCAL_MALL, "Label", 3);

        addButtonBottom(arrowDown, "Restaurants", restau, true);
        addButtonBottom(arrowDown, "Dishes", dish, false);
        addButtonBottom(arrowDown, "Wishlist ", wish, false);
        addButtonBottom(arrowDown, "Bag", bag, false);
        setupSideMenu(res);
      return null;
    }
    
    private void addButtonBottom(Image arrowDown, String text, Image icon, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(icon);
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
       
    }

    @Override
    protected void showOtherForm(Resources res) {
        
    }
    
}




