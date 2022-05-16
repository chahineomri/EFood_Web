/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.myapp.entities.User;

/**
 *
 * @author bhk
 */
public class CommandeMenuForm extends Form{
Form current;
    public CommandeMenuForm() {
        
        //User Current_user=U;
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
       add(new Label("Choisir une option"));
        //Button btnAjCommande = new Button("Passer une Commande");
       //Button btnlivraison = new Button("Gestion des Commandes");
        Button btnlivraison = new Button("livraison");
        Button btnProduit = new Button(" produit");
        //btnListTasks.addActionListener(e -> new ListCommande(current).show());
        // BtnTournois.addActionListener(e-> new TournamentMenuForm().show());
      // BtnCoaching.addActionListener(e-> new CoachMenuForm(current ).show());
        //btnAddProduit.addActionListener(e-> new AddProduit(current).show());
        btnProduit.addActionListener(e-> new ListProduit(current).show());
        btnlivraison.addActionListener(e-> new ListLivraison(current).show());
        //btnCommandes.addActionListener(e-> new ListCommande(current).show());
         // btnAjCommande.addActionListener(e-> new AjouterCommande(current ).show());

          
        addAll(btnlivraison,btnProduit);
       
        //addAll(btnAddTask, btnProduit,btnpanier);
        //btnListTasks,
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
    
    
}
