/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author LENOVO
 */
public class PromoForm extends Form {
    Form current;
    public PromoForm() {
        current=this; //Back 
          setTitle("promotions");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnadd = new Button("Add promotions");
        Button btnlist = new Button("List All the promotions");
        
      //  btnadd.addActionListener(e-> new AddoffreForm(current).show());
      //  btnlist.addActionListener(e-> new ListoffresForm(current).show());
       
        addAll( btnadd,btnlist);
    }
    
    
}
