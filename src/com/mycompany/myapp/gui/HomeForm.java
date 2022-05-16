/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;

import com.codename1.ui.Button;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author LENOVO
 */
public class HomeForm extends Form{

    public HomeForm() {
      
                setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnoffer = new Button("Offers");
        Button btncoupoun = new Button("Coupouns");
        Button btnpromo = new Button("Promotions");
        btnoffer.addActionListener(e-> new OffreForm().show());
        btncoupoun.addActionListener(e-> new CoupounForm().show());
        btnpromo.addActionListener(e-> new PromoForm().show());
        addAll( btnoffer,btncoupoun,btnpromo);
        
  
        
    }
    
    
    
}
