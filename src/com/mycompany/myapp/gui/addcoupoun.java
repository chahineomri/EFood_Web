/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.coupouns;
import com.mycompany.myapp.services.serviceoffre;

/**
 *
 * @author LENOVO
 */
public class addcoupoun extends Form {
     public addcoupoun(Form previous) {
          setTitle("Add a new task");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","TaskName");
        TextField tfdate = new TextField("","Taskdate");
         TextField tftype = new TextField("","Tasktype");
          TextField tfimg = new TextField("","Taskimg");
        Button btnValider = new Button("Add task");
        
        
    
    
}
}