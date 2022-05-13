
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.JardinEnfant;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import java.util.*;
import java.util.Map;




/**
 *
 * @author ffsga
 */
public class ServiceJardin_enfant {
     public ArrayList<JardinEnfant> lista;

    public static ServiceJardin_enfant instance = null;
    public boolean resultOK = true;
    private ConnectionRequest req;

    public ServiceJardin_enfant() {
        req = new ConnectionRequest();
    }

    public static ServiceJardin_enfant getInstance() {
        if (instance == null) {
            instance = new ServiceJardin_enfant();
        }
        return instance;
    }

    public boolean AddJardinEnfant(JardinEnfant Pub) {
        String url = Statics.BASE_URL + "/jardinenfant/addJSON?nom="+ Pub.getNom() + "&Description=" + Pub.getDescription() + "&Nb_employes=" + Pub.getNb_employes() + "&Adresse=" + Pub.getAdresse() + "&Date_creation=" + Pub.getDate_creation() +"&Prix=" + Pub.getPrix() +"&telephone=" + Pub.getTelephone();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("Jardin Enfant == " + str);

        }
        );

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

     public ArrayList<JardinEnfant> parsePub(String jsonText) throws ParseException  {

        try {

            lista = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                JardinEnfant jardin = new JardinEnfant();
                         


                 
                
                //LocalDate datepub1 = LocalDate.parse(str2);
               
                float id = Float.parseFloat(obj.get("idj").toString());                        
                jardin.setIdj((int)id); 
                jardin.setNom(obj.get("nom").toString());
                jardin.setDescription(obj.get("description").toString());

                                 float id1 = Float.parseFloat(obj.get("nbEmployes").toString());  

                jardin.setNb_employes((int)id1);
                jardin.setAdresse(obj.get("adresse").toString());
                
                 String DatePB = obj.get("dateCreation").toString();

                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                String str2 = formater.format(DatePB);
                
                          Date date_creation = (Date) formater.parse(str2);
                jardin.setDate_creation(date_creation);
                                float prix = Float.parseFloat(obj.get("prix").toString());                        

                jardin.setPrix(prix);
                 float id2 = Float.parseFloat(obj.get("telephone").toString());  
                                                          

                jardin.setTelephone((int)id2);

                lista.add(jardin);
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return lista;
    }


    
    public ArrayList<JardinEnfant> getAllJardinEnfant() {
        String url = Statics.BASE_URL + "/jardinenfant/json/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

               
                try {
                    lista = parsePub(new String(req.getResponseData()));
                } catch (ParseException ex) {
                   
                }
                

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lista;
    }

    //delete
    public boolean deleteJardinEnfant(int idj) {
        String url = Statics.BASE_URL + "/jardinenfant/deleteJSON/"+idj;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public void modifierJardinEnfant(JardinEnfant Pub) {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL + "/JardinEnfant/updateJSON/"+ Pub.getIdj() + "?nom=" + Pub.getNom() + "&Description=" + Pub.getDescription() + "&Nb_employes=" + Pub.getNb_employes() + "&Adresse=" + Pub.getAdresse() + "&Date_creation=" + Pub.getDate_creation() +"&Prix=" + Pub.getPrix() +"&telephone=" + Pub.getTelephone();
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
    

