

package com.mycompany.myapp.views;


import com.codename1.io.FileSystemStorage;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import java.io.IOException;


public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) throws IOException {
         Image profilePic;
       
        profilePic = Image.createImage(FileSystemStorage.getInstance().openInputStream(SessionManager.getProfilePicUser()));

        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label( SessionManager.getNameUser() +" "+ SessionManager.getLastNameUser(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());
        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Profile", FontImage.MATERIAL_PERM_IDENTITY,  e -> {
            try {
              
                new ProfileForm(res).show();
            } catch (IOException ex) {
            }
        });
        getToolbar().addMaterialCommandToSideMenu("  Bag", FontImage.MATERIAL_SHOPPING_CART,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Delete Account", FontImage.MATERIAL_REMOVE_CIRCLE_OUTLINE,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Edit Account", FontImage.MATERIAL_EDIT,  e -> {
            try {
                new EditProfileForm(res).show();
            } catch (IOException ex) {
            }
        });
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
    }
    
    protected abstract void showOtherForm(Resources res);
}
