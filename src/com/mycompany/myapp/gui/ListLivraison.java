/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.services.LivraisonService;
import java.util.ArrayList;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author oumaymacherif
 */
public class ListLivraison extends Form {

    public ListLivraison(Form previous) {

        setTitle("Liste des livraison");

        LivraisonService es = new LivraisonService();
        ArrayList<Livraison> list = es.getAllLivraison();

        Button ajoutLivraison = new Button("Ajouter");

        ajoutLivraison.addPointerPressedListener(l -> {
            new AddLivraison().show();
        });

        Container chercherContianer = new Container();
        chercherContianer.setLayout(BoxLayout.y());
        chercherContianer.addAll(ajoutLivraison);
        this.add(chercherContianer);

        for (Livraison r : list) {

            Container c3 = new Container(BoxLayout.y());
          //  String url = Statics.BASE_URL + "/" + "uploads/" + r.getImage();

            SpanLabel cat = new SpanLabel("Nom   :" + r.getNom());
            SpanLabel cat0 = new SpanLabel("prenom :" + r.getPrenom());
            SpanLabel cat1 = new SpanLabel("adresse  :" + r.getAdresse());
            SpanLabel cat2 = new SpanLabel("email :" + r.getEmail());
            Image placeholder = Image.createImage(200, 200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
           // URLImage urlim = URLImage.createToStorage(enc, r.getImage(), url + "/" + r.getImage());
           // ImageViewer imgV = new ImageViewer();
           //imgV.setImage(urlim);
            c3.add(cat);
            c3.add(cat0);
            c3.add(cat1);
            c3.add(cat2);
            //c3.add(imgV);

            Button Delete = new Button("Delete");
            c3.add(Delete);

            Button Modifier = new Button("Modifier");
            Modifier.addPointerPressedListener(l -> {
                new UpdateLivraison (r).show();
            });
            c3.add(Modifier);

            Delete.getAllStyles().setBgColor(0xF36B08);
            Delete.addActionListener(e -> {
                Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer ce club ?\nCette action est irréversible!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        es.DeleteLivraison(r.getId());

                        alert.dispose();
                        refreshTheme();
                    }

                }
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();

                new ListLivraison(previous).show();

            });
            ajoutLivraison.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {

                    refreshTheme();
                }

            });
            System.out.println("add Livraison");

            addAll(c3);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
