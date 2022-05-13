
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entites.JardinEnfant;
import java.io.IOException;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceJardin_enfant;

/**
 *
 * @author ffsga
 */
public class ListeJardinEnfant extends BaseForm{
    
     Form current;

    public ListeJardinEnfant(Resources res) {
        super("Gestion Jardin:", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        tb.addSearchCommand(e -> {

        });

        Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();

       // addTab(swipe, s1, res.getImage("imagenotfound.png"), "", "", res);
        // deb 
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

       // rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("List", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("", barGroup);
        liste.setUIID("SelectBar");
        
         Label lAjout = new Label("Ajouter");//test
        lAjout.setUIID("NewsTopLine");
        Style AjoutStyle = new Style(lAjout.getUnselectedStyle());
        AjoutStyle.setFgColor(0x001eff);
         FontImage AjoutImage = FontImage.createMaterial(FontImage.MATERIAL_ADD, AjoutStyle);
        lAjout.setIcon(AjoutImage); //test button
        
        Label lpieChart = new Label("Pie Chart");//test
        lpieChart.setUIID("NewsTopLine");
        Style lpieChartStyle = new Style(lpieChart.getUnselectedStyle());
        lpieChartStyle.setFgColor(0x760299);
         FontImage AjoutImagePieChart = FontImage.createMaterial(FontImage.MATERIAL_PIE_CHART, lpieChartStyle);
        lpieChart.setIcon(AjoutImagePieChart); //test button
        
        
        RadioButton Ajouter = RadioButton.createToggle("Ajouter", barGroup);
        Ajouter.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        lAjout.addPointerPressedListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           AddEleve a = new AddEleve(res);
            a.show();
            refreshTheme();
        });
        
        
        
            mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           ListEleve a = new ListEleve(res);
            a.show();
            refreshTheme();
        });

                   liste.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           AddEleve a = new AddEleve(res);
            a.show();
            refreshTheme();
        });
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, lAjout,lpieChart),
                FlowLayout.encloseBottom(arrow)
        ));

        Ajouter.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(Ajouter, arrow);
        });
        mesListes.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(mesListes, arrow);
        });
        bindButtonselection(mesListes, arrow);
        bindButtonselection(liste, arrow);
        bindButtonselection(Ajouter, arrow);
        //   special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        ArrayList<JardinEnfant> ListJE = ServiceJardin_enfant.getInstance().getAllJardinEnfant();
        for (JardinEnfant Pub : ListJE) {
            String urlImage = "imagenotfound.png";
            Image PlaceHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(PlaceHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            addButton(Pub,res);

            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container contImage = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
 this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e ->new HomeForm(res).show() ); // Revenir vers l'interface précédente

    
        }

    }


    private void addTab(Tabs swipe, Label s1, Image image, String string, String string0, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }

        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);

        }
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("", "ImageOverlay");
        Container page1
                = LayeredLayout.encloseIn(
                        imageScale,
                        overLay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(string0, "LargeWhiteText"),
                                        s1
                                )
                        )
                );
        swipe.addTab("", res.getImage("imagenotfound.png"), page1);
    }

    private void bindButtonselection(Button mesListes, Label arrow) {
        mesListes.addActionListener(e -> {
            if (mesListes.isSelected()) {
                updateArrowPosition(mesListes, arrow);
            }

        });
    }

    private void updateArrowPosition(Button mesListes, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, mesListes.getX() + mesListes.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }

    private void addButton( JardinEnfant Pub,Resources res) {

         int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
       Container cont = new Container();
 Label Nomtxt = new Label("" + Pub.getNom(), "NewsTopLine2");
        Label Descriptiontxt = new Label("" + Pub.getDescription(), "NewsTopLine2");
        Label Nb_employestxt = new Label("" + Pub.getNb_employes(), "NewsTopLine");
        Label Adressetxt = new Label("" + Pub.getAdresse(), "NewsTopLine");
         Label Date_creationtxt = new Label("" + Pub.getDate_creation(), "NewsTopLine2");
        Label Prixtxt = new Label("" + Pub.getPrix(), "NewsTopLine");
        Label Telephonetxt = new Label("" + Pub.getTelephone(), "NewsTopLine");
       
         
        //supprimer
        Label lsup = new Label("");
        lsup.setUIID("NewsTopLine");
        Style supStyle = new Style(lsup.getUnselectedStyle());
        supStyle.setFgColor(0xf21f1f);

        FontImage suppImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supStyle);
        lsup.setIcon(suppImage);
        lsup.setTextPosition(RIGHT);
        lsup.addPointerPressedListener((ActionEvent l) -> { 
              
        Dialog dig = new Dialog("Supprimer"); 

        if (Dialog.show("Suppression", "Vous Voulez Supprimer cet Publication ? ", "Non", "Oui")) {
            dig.dispose();
        } else {
            dig.dispose();
            if(ServiceJardin_enfant.getInstance().deleteJardinEnfant(Pub.getIdj())){
                System.out.println("done");
            new ListeJardinEnfant(res).show();
        }
        }
        });
        //Modifier
        Label lModifier = new Label("");
        lModifier.setUIID("NewsTopLine");
        Style ModifierStyle = new Style(lModifier.getUnselectedStyle());
        ModifierStyle.setFgColor(0x0bc152);

        FontImage ModifierImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, ModifierStyle);
        lModifier.setIcon(ModifierImage);
        lModifier.setTextPosition(LEFT);
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new ModifierJardinEnfant(res,Pub).show();
        });
    
        cont.add( BoxLayout.encloseY(
                BoxLayout.encloseX(Nomtxt),
                BoxLayout.encloseX(Descriptiontxt),
                BoxLayout.encloseX(Nb_employestxt),
                BoxLayout.encloseX(Date_creationtxt),
                BoxLayout.encloseX(Prixtxt),
                BoxLayout.encloseX(Telephonetxt,lModifier, lsup)));

        add(cont);

    }
    
}


 