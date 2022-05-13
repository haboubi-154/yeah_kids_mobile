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

/**
 *
 * @author fedi
 */
public class UserForm extends Form{
    Form current;
    public UserForm(Form previous) {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAdd = new Button("Add USer");
        Button btnList = new Button("List USer");
                Button btnmod = new Button("modifier USer");
        Button btnsup = new Button("sup User");

        
        btnAdd.addActionListener(e-> new AddUserForm(current).show());
        btnList.addActionListener(e-> new ListuserForm(current).show());
                btnmod.addActionListener(e-> new modUserForm(current).show());
                                btnsup.addActionListener(e-> new supUserForm(current).show());



        addAll(btnAdd,btnList,btnmod,btnsup);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

}
}