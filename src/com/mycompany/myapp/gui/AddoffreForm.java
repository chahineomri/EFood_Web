/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.mycompany.myapp.entities.offre;
import com.mycompany.myapp.services.serviceoffre;

/**
 *
 * @author LENOVO
 */
public class AddoffreForm extends Form{

   /* public AddoffreForm(Form previous) {
                setTitle("Add a new task");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","TaskName");
        TextField tfdate = new TextField("","Taskdate");
         TextField tftype = new TextField("","Tasktype");
          TextField tfimg = new TextField("","Taskimg");
        Button btnValider = new Button("Add task");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfdate.getText().length()==0)||(tftype.getText().length()==0)||(tfimg.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        offre o = new offre(tfName.getText().toString(),tfdate.getText().toString(),tftype.getText().toString(),tfimg.getText().toString());
                        if( serviceoffre.getInstance().addoffre(o))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfdate,tftype,tfimg,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
*/

    }
    

