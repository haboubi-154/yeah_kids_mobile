/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.produit;

import com.mycompany.myapp.gui.produit.*;
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
public class produitForm extends Form{
    Form current;
    public produitForm(Form previous) {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAdd = new Button("Add produit");
        Button btnList = new Button("List produit");
                Button btnmod = new Button("modifier produit");
        Button btnsup = new Button("sup produit");

        
        btnAdd.addActionListener(e-> new AddproduitForm(current).show());
        btnList.addActionListener(e-> new ListproduitForm(current).show());
                btnmod.addActionListener(e-> new modproduitForm(current).show());
                                btnsup.addActionListener(e-> new supproduitForm(current).show());



        addAll(btnAdd,btnList,btnmod,btnsup);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

}
}