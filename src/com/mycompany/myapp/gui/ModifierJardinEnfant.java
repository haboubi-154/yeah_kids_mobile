



package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Eleve;
import com.mycompany.myapp.entites.JardinEnfant;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.services.ServiceEleve;

/**
 *
 * @author ffsga
 */
public class ModifierJardinEnfant extends BaseForm{
    
    Form current;
    public ModifierJardinEnfant(Resources res , JardinEnfant Pub) {
         super("Modifier jardin",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        tb.addSearchCommand(e -> {
        });
        
        
//        super.addSideMenu(res);
        
Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();

       // addTab(swipe, s1, res.getImage("event.png"), "", "", res);
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
        RadioButton liste = RadioButton.createToggle("Categorie", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        partage.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           AddJardinEnfant a = new AddJardinEnfant(res);
            a.show();
            refreshTheme();
        });
        
        
            mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           ListeJardinEnfant a = new ListeJardinEnfant(res);
            a.show();
            refreshTheme();
        });

                   liste.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           AddJardinEnfant a = new AddJardinEnfant(res);
            a.show();
            refreshTheme();
        });
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonselection(mesListes, arrow);
        bindButtonselection(liste, arrow);
        bindButtonselection(partage, arrow);
        //   special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        
        
        TextField nom = new TextField(Pub.getNom() , "nom" , 20 , TextField.ANY);
        TextField description = new TextField(Pub.getDescription() , "description" , 20 , TextField.ANY);
        TextField nb_employes = new TextField(String.valueOf(Pub.getNb_employes()) , "nb_employes" , 20 , TextField.ANY);
         TextField adresse = new TextField(String.valueOf(Pub.getAdresse()) , "adresse" , 20 , TextField.ANY);
         Picker date_creation = new Picker();
        date_creation.setDate(Pub.getDate_Pub());
        date_creation.setType(Display.PICKER_TYPE_DATE);
          TextField prix = new TextField(String.valueOf(Pub.getPrix()) , "prix" , 20 , TextField.ANY);
          TextField telephone = new TextField(String.valueOf(Pub.getTelephone()) , "telephone" , 20 , TextField.ANY);
        
        
        
        
            
        nom.setUIID("TextFieldBlack");       
        description.setUIID("NewsTopLine");
        nb_employes.setUIID("NewsTopLine");
        adresse.setUIID("NewsTopLine");
         date_creation.setUIID("NewsTopLine");
         prix.setUIID("NewsTopLine");
        telephone.setUIID("TextFieldBlack");
        
        
        
    
       
        nom.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        nb_employes.setSingleLineTextArea(true);
        
        
        
         addStringValue("nom : ", nom);
         addStringValue("description : ", description);
         addStringValue("nb_employes : ", nb_employes);
         addStringValue("adresse : ", adresse);
         addStringValue("date_creation : ", date_creation);
         addStringValue("prix : ", prix);
         addStringValue("telephone : ", telephone);
         
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
        addStringValue("", btnModifier);
       
       //Event onclick btnModifer
       
            btnModifier.addPointerPressedListener((e) -> {
            try {
                if (nom.getText().equals("") || description.getText().equals("") || nb_employes.getText().equals("")|| adresse.getText().equals("")|| date_creation.getText().equals("")|| prix.getText().equals("")|| telephone.getText().equals(""))  {
                    Dialog.show("Erreur", "Veuillez vérifier les données ", "Annuler", "OK");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    float id = Float.parseFloat(nb_employes.getText());
                    float id1 = Float.parseFloat(prix.getText());
                     float id2 = Float.parseFloat(telephone.getText());
                    Pub.setNom(nom.getText());
                    Pub.setDescription(description.getText());
                    Pub.setNb_employes((int)id);
                      Pub.setAdresse(adresse.getText());
                       Pub.setDate_creation(Pub.date_creation());
                        Pub.setPrix((int)id1);
                         Pub.setTelephone((int)id2);
                     
                    
                    
                    

                   
//                    
//               Sms SMS=new Sms();
//     SMS.SendSMS("un nouvel Evénement a été ajouté, veuillez consulter notre application pour plus de détails");
//                   

                    ServiceEleve.getInstance().ModifierJardinEnfant(Pub);
                    iDialog.dispose();
                    new ListEleve(res).show();
                }
            } catch (Exception ex) {
            }
        });

        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> current.showBack()); // Revenir vers l'interface précédente
        
        
    }
    
    private void addStringValue(String s, Component V) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).add(BorderLayout.CENTER, V));
        add(createLineSeparator(0xeeeeee));

    }
    
    private void bindButtonselection(Button mesListes, Label arrow) {
        mesListes.addActionListener(e -> {
            if (mesListes.isSelected()) {
                updateArrowPosition(mesListes, arrow);
            }

        });
    }

    private void updateArrowPosition(Button partage, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, partage.getX() + partage.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }
}
