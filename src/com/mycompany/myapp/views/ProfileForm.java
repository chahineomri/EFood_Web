

package com.mycompany.myapp.views;


import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.io.Log;
import com.codename1.sms.ActivationForm;
import com.codename1.sms.TwilioSMS;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;

import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.UserService;



public class ProfileForm extends SideMenuBaseForm {
  String accountSID = "ACed511353381614dcc72e1c248160e5e6";
String authToken = "da30048051a082731b641544ad707042";
String fromPhone = "+19705288371";
    public ProfileForm(Resources res) {
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
        new StatsForm(res).show();
    }
}




