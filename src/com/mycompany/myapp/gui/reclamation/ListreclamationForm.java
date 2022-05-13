/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.reclamation;

import com.mycompany.myapp.gui.*;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.Service_rec;

/**
 *
 * @author fedi
 */
public class ListreclamationForm extends Form{

    public ListreclamationForm(Form previous) {
        setTitle("List reclamation");

        SpanLabel sp = new SpanLabel();
        System.out.print("leo messi"+Service_rec.getInstance().showreclamations().toString());
        sp.setText(Service_rec.getInstance().showreclamations().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
    

