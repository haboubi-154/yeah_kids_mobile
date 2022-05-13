/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.reclamation;

import com.mycompany.myapp.gui.*;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author fedi
 */
public class reclamationForm extends Form{
    Form current;
    public reclamationForm(Form previous) {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAdd = new Button("Add reclamation");
        Button btnList = new Button("List reclamation");
                Button btnmod = new Button("modifier reclamationr");
        Button btnsup = new Button("sup reclamation");

        
        btnAdd.addActionListener(e-> new AddreclamationForm(current).show());
        btnList.addActionListener(e-> new ListreclamationForm(current).show());
                btnmod.addActionListener(e-> new modreclamationForm(current).show());
                                btnsup.addActionListener(e-> new supreclamationForm(current).show());



        addAll(btnAdd,btnList,btnmod,btnsup);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

}
}