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
import com.codename1.ui.util.Resources;

/**
 *
 * @author fedi
 */
public class jardinForm extends Form {
    Form current;
    jardinForm(Resources theme, Form previous) {
    
current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAdd = new Button("Add jardin");
        Button btnList = new Button("List jardin");
                

        
        btnAdd.addActionListener(e-> new AddJardinEnfant(theme).show());
        btnList.addActionListener(e-> new ListeJardinEnfant(theme).show());



        addAll(btnAdd,btnList);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    
    
    }
    
}
