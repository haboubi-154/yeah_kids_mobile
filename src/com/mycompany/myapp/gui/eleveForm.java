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
public class eleveForm extends Form {
    Form current;
    public eleveForm(Resources theme,Form previous) {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAdd = new Button("Add eleve");
        Button btnList = new Button("List eleve");
                Button btnmod = new Button("modifier eleve");
        Button btnsup = new Button("sup eleve");

        
        btnAdd.addActionListener(e-> new AddEleve(theme).show());
        btnList.addActionListener(e-> new ListEleve(theme).show());



        addAll(btnAdd,btnList,btnmod,btnsup);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    
}
}
