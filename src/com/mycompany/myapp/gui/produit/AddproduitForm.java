/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.produit;

/**
 *
 * @author fedi
 */

import com.mycompany.myapp.gui.produit.*;
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
import com.mycompany.myapp.entites.produit;
import com.mycompany.myapp.entites.user;
import com.mycompany.myapp.services.Service_rec;
import com.mycompany.myapp.services.Serviceuser;
import com.mycompany.myapp.services.service_produit;

/**
 *
 * @author bhk
 */
public class AddproduitForm extends Form{

    public AddproduitForm(Form previous) {
           
        setTitle("Add a new produitn");
        setLayout(BoxLayout.y());
        
    
        TextField tfnom = new TextField("","nom");
        TextField tfprix = new TextField("","prix");
        TextField tfquantite = new TextField("","quantite");
        
        Button btnValider = new Button("Add produit");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(tfprix.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {

                        int prix = Integer.parseInt(tfprix.getText().toString());
                         int quantite = Integer.parseInt(tfquantite.getText().toString());

                        produit t = new produit(prix,tfnom.getText().toString(),quantite);
                        if( service_produit.getInstance().ajoutproduit(t))

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

        addAll(tfnom,tfprix,tfquantite,btnValider);

getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
