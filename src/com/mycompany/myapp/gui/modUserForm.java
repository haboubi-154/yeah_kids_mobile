/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
import com.mycompany.myapp.entites.user;
import com.mycompany.myapp.services.Serviceuser;

/**
 *
 * @author fedi
 */
public class modUserForm extends Form{
    public modUserForm(Form previous){
    String[] role = { "manager", "parent", "admin"
    
};
         String[] question = {"Dans quelle ville êtes-vous né ?","Quel est votre film favori?","Quelle est la marque de votre première voiture ?","Quelle est votre couleur favorite?","Quelle est la personnalité historique que vous préférez ?"
    
};
         Picker p_role = new Picker();
        Picker p_question = new Picker();
        setTitle("modifer  user");
        setLayout(BoxLayout.y());
        
            TextField tfid = new TextField("","id");

        TextField tfnom = new TextField("","nom");
        TextField tfprenom = new TextField("","prenom");
        TextField tflogin = new TextField("","login");
        TextField tfemail = new TextField("","email");

        TextField tfpassword = new TextField("","password");
        
        TextField tfreponse = new TextField("","reponse");
        

p_role.setStrings(role);
p_question.setStrings(question);

p_role.setSelectedString(role[0]);
p_question.setSelectedString(question[0]);

p_role.addActionListener(e -> ToastBar.showMessage("You picked " + p_role.getSelectedString(), FontImage.MATERIAL_INFO));
  p_question.addActionListener(e -> ToastBar.showMessage("You picked " + p_question.getSelectedString(), FontImage.MATERIAL_INFO));
      

        Button btnValider = new Button("mod user");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfid.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        int id = Integer.parseInt(tfid.getText().toString());
                        String password = tfpassword.getText().toString();
                        user t = new user(tfprenom.getText().toString(),tfnom.getText().toString(),tfemail.getText().toString(),tflogin.getText().toString(),password,"image","10",p_question.getSelectedString().toString(),tfreponse.getText().toString(),p_role.getSelectedString().toString());
                        if( Serviceuser.getInstance().modifier(id,t) )

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

        addAll(tfid,tfnom,tfprenom,tflogin,tfemail,tfpassword,p_question,tfreponse,p_role,btnValider);

getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
