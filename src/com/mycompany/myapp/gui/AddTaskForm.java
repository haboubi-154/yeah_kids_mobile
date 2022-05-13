/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author bhk
 */
public class AddTaskForm extends Form{

    public AddTaskForm(Form previous) {
        setTitle("Add a new task");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","TaskName");
        TextField tfStatus= new TextField("", "Status: 0 - 1");
                TextField tfemail = new TextField("","email");
        TextField tfemail2 = new TextField("","email");
        TextField tfemail3 = new TextField("","email");
        TextField tfemail4 = new TextField("","email");

        Button btnValider = new Button("Add task");
        
           
        
        addAll(tfName,tfStatus,tfemail2,tfemail4,tfemail3,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
