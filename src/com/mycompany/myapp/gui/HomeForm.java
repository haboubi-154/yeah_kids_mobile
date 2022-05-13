/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.produit.produitForm;
import com.mycompany.myapp.gui.reclamation.*;
/**
 *
 * @author fedi
 */
public class HomeForm extends Form{
   Form current;

    public HomeForm(Resources theme){
        current=this;


        Toolbar tb = this.getToolbar();
Image icon = theme.getImage("icon.png"); 
Container topBar = BorderLayout.east(new Label(icon));
topBar.add(BorderLayout.SOUTH, new Label("Cool App Tagline...", "SidemenuTagline")); 
topBar.setUIID("SideCommand");
tb.addComponentToSideMenu(topBar);

tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {}); 
tb.addMaterialCommandToSideMenu("USERS", FontImage.MATERIAL_WEB, e -> { new UserForm(current).show(); });
tb.addMaterialCommandToSideMenu("ELEVE", FontImage.MATERIAL_SETTINGS, e -> {new eleveForm(theme,current).show();});
tb.addMaterialCommandToSideMenu("JARDIN", FontImage.MATERIAL_INFO, e -> {new jardinForm(theme,current).show();});
tb.addMaterialCommandToSideMenu("PRODUIT", FontImage.MATERIAL_INFO, e -> {new produitForm(current).show();});
tb.addMaterialCommandToSideMenu("EVENT", FontImage.MATERIAL_INFO, e -> {});
tb.addMaterialCommandToSideMenu("RECLAMATION", FontImage.MATERIAL_INFO, e -> {new reclamationForm(current).show();});




addComponent(new Label("Hi Admin"));
    
    }
    
    
}
