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
public class CoupounForm extends Form {
    Form current;
    public CoupounForm() {
        current=this; //Back 
          setTitle("coupouns");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnadd = new Button("Add coupoun");
        Button btnlist = new Button("List All the coupouns");
        
        btnadd.addActionListener(e-> new addcoupoun(current).show());
        btnlist.addActionListener(e-> new listcoupoun(current).show());
       
        addAll( btnadd,btnlist);
}
}
