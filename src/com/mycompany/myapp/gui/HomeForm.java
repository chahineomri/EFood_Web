/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import static javafx.scene.input.KeyCode.U;
//import com.mycompany.myapp.entities.User;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        
        Form f1 = new Form("page 1", BoxLayout.y());
        //getToolbar().addCommandToSideMenu("Logout", null,(e)-> new LoginUserForm().show());
       // getToolbar().addCommandToSideMenu("Edit User Info", null,(e)-> new ModifyUser(current,U).show());
       // getToolbar().addCommandToSideMenu("List Panier", null,(e)-> new ListPanier(current,U,Panier).show());
        show();
        f1.getToolbar().addCommandToRightBar("back", null, (e)->show());
       // User Current_user=U;
        current=this; //Back 
       // setTitle("Home User :"+U.getUsername());
        System.out.println(U);
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
       // Button BtnTournois = new Button("Tournois");
       // Button BtnCoaching = new Button("Coaching");
        
        //Button BtnUser = new Button("Users");
        Button btnProduit=new Button("Produit");
       // Button BtnGames = new Button("Games");
        Button btnCommandes=new Button("Commandes");
      // BtnTournois.addActionListener(e-> new TournamentMenuForm().show());
      // BtnCoaching.addActionListener(e-> new CoachMenuForm(current ).show());
       // btnNews.addActionListener(e-> new NewsMenuForm(current).show());
        btnProduit.addActionListener(e-> new ListLivraison(current).show());
        //BtnGames.addActionListener(e-> new GamesMenuForm(current,Panier).show()); ,,,, BtnGames,
        
       // btnCommandes.addActionListener(e-> new ListCommande(current).show());
          
        addAll(btnProduit,btnCommandes);
       
           // addAll(BtnTournois,BtnCoaching,BtnGames);
        
        
    }
    
    
}
