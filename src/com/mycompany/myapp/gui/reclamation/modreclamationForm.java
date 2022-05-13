/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.reclamation;

import com.mycompany.myapp.gui.*;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entites.reclamation;
import com.mycompany.myapp.entites.user;
import com.mycompany.myapp.services.Service_rec;
import com.mycompany.myapp.services.Serviceuser;

/**
 *
 * @author fedi
 */
public class modreclamationForm extends Form{
    public modreclamationForm(Form previous){
            setTitle("mod a reclamation");
        setLayout(BoxLayout.y());
        
            TextField tfid_rec = new TextField("","id_rec");

        TextField tfid = new TextField("","id_parent");
        TextField tfreclamation = new TextField("","reclamation");
        
        Button btnValider = new Button("mod reclamation");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfreclamation.getText().length()==0)||(tfid.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        int id_rec= Integer.parseInt(tfid_rec.getText().toString()) ;
                        int id_parent = Integer.parseInt(tfid.getText().toString());
                        reclamation t = new reclamation(id_parent,tfreclamation.getText().toString());
                        if( Service_rec.getInstance().modifier(id_rec,t))

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

        addAll(tfid,tfreclamation,btnValider);

getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
                
    }

