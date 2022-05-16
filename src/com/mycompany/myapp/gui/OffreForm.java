/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.ListoffresForm;

/**
 *
 * @author LENOVO
 */
public class OffreForm extends Form {
Form current;
    public OffreForm() {
        current=this; //Back 
          setTitle("Offers");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnadd = new Button("Add offer");
        Button btnlist = new Button("List All the offers");
        
       // btnadd.addActionListener(e-> new AddoffreForm(current).show());
        btnlist.addActionListener(e-> new ListoffresForm( ).show());
       
        addAll( btnadd,btnlist);
    }
    
    
}
