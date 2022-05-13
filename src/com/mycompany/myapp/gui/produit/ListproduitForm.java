/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.produit;

import com.mycompany.myapp.gui.produit.*;
import com.mycompany.myapp.gui.*;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.Service_rec;
import com.mycompany.myapp.services.service_produit;

/**
 *
 * @author fedi
 */
public class ListproduitForm extends Form{

    public ListproduitForm(Form previous) {
        setTitle("List produit");

        SpanLabel sp = new SpanLabel();
        System.out.print(service_produit.getInstance().showproduits().toString());
        sp.setText(service_produit.getInstance().showproduits().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
    

