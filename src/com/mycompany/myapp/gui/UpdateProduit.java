/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ProduitService;
/**
 *
 * @author oumaymacherif
 */
public class UpdateProduit extends Form{
  


    Form previous;

    public UpdateProduit(Produit ev) {
        super("Newsfeed", BoxLayout.y());

        setTitle("Modifier Votre club");

        TextField Description = new TextField(ev.getName(), "name", 20, TextField.ANY);
        Description.setUIID("TextFieldBlack");

        TextField Image = new TextField(ev.getPrice(), "price", 20, TextField.ANY);
        Image.setUIID("TextFieldBlack");

        TextField DateAvis = new TextField(ev.getImage(), "Image ", 20, TextField.ANY);
        DateAvis.setUIID("TextFieldBlack");

        TextField Nom = new TextField(ev.getCategory(), "category", 20, TextField.ANY);
        Nom.setUIID("TextFieldBlack");

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //OnClick Button
        btnModifier.addPointerPressedListener(l -> {

            ev.setName(Description.getText());
            ev.setPrice(Nom.getText());
            ev.setImage(Image.getText());
            ev.setCategory(Image.getText());

            //Appel a la methode UPDATE
            if (ProduitService.getInstance().modifierproduit(ev)) {
                //If True
                // new Listclub(res).show();

            }
        });

        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
             
                new FloatingHint(Description),
                new FloatingHint(Image),
                new FloatingHint(DateAvis),
                new FloatingHint(Nom),
                btnModifier
             
        );

        add(content);
        show();

    }

}
    

