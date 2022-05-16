/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.NetworkManager;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ProduitService;
import java.util.ArrayList;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.utils.Statics;
//import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author oumaymacherif
 */
public class AddLivraison extends Form{

    Form previous;

    private String fileNameInServer = "";

    public AddLivraison() {

        setTitle("Add a new produit ");
        setLayout(BoxLayout.y());

        TextField Name = new TextField("", "name");

        TextField Price = new TextField("", "price");
        TextField Category = new TextField("", "category");
        Button btnValider = new Button("Add produit");
        // addStringValue("", btnAjouter);

        Font materialFontAjout = FontImage.getMaterialDesignFont();
        int size = Display.getInstance().convertToPixels(5, true);
        materialFontAjout = materialFontAjout.derive(size, Font.STYLE_PLAIN);
        Button Delete = new Button("Supprimer");
        Delete.setIcon(FontImage.create("\uea4c", Delete.getUnselectedStyle(), materialFontAjout));
        //MyApplication

        Button imgBtn = new Button("parcourir");
        //addStringValue("", imgBtn);

        imgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    // FileUploader fu = new FileUploader(Statics.URL_UPLOAD);

                    //Upload
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent v) {
                            if (v == null || v.getSource() == null) {
                                System.out.println("choisir image fail !");
                                return;
                            }

                            String filePath = ((String) v.getSource()).substring(7);
                            System.out.println(filePath);

                            //try {
                            //   fileNameInServer = fu.upload(filePath);
                            //   } catch (Exception ex) {
                            //   ex.printStackTrace();
                            // }
                        }
                    }, Display.GALLERY_IMAGE);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        btnValider.addActionListener((e) -> {
            //ProduitService c = new ProduitService();
            Produit r = new Produit(Name.getText().toString(), fileNameInServer, Price.getText(), Category.getText().toString());
            System.out.println("data evenement ==" + r);
            ProduitService.getInstance().addproduit(r);

        });
        addAll(Name, imgBtn, Price, Category, btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListLivraison(this).show());

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
    }

}
