/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entites.Eleve;
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
import java.util.Date;
import java.util.Map;




/**
 *
 * @author ffsga
 */
public class ServiceEleve {
     public ArrayList<Eleve> Publications;

    public static ServiceEleve instance = null;
    public boolean resultOK = true;
    private ConnectionRequest req;

    public ServiceEleve() {
        req = new ConnectionRequest();
    }

    public static ServiceEleve getInstance() {
        if (instance == null) {
            instance = new ServiceEleve();
        }
        return instance;
    }

    public boolean AddPublication(Eleve Pub) {
        String url = Statics.BASE_URL + "/eleve/addJSON?nom=" + Pub.getNom() + "&prenom=" + Pub.getPrenom() + "&age=" + Pub.getAge() + "&idp=" + Pub.getIdp() + "&idj=" + Pub.getIdj() ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("eleve == " + str);

        }
        );

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Eleve> parsePub(String jsonText) throws ParseException  {

        try {

            Publications = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Eleve Pubs = new Eleve();
                         

               
                float id = Float.parseFloat(obj.get("ide").toString());  
                float id1 = Float.parseFloat(obj.get("age").toString());   
                 float id2 = Float.parseFloat(obj.get("idp").toString());   
                  float id3 = Float.parseFloat(obj.get("idj").toString());   
                Pubs.setIde((int)id); 
                Pubs.setNom(obj.get("nom").toString());
                Pubs.setPrenom(obj.get("prenom").toString());
                Pubs.setAge((int)id1);
                Pubs.setidp((int)id2);
                Pubs.setidj((int)id3);


                Publications.add(Pubs);
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return Publications;
    }

    
    public ArrayList<Eleve> getAllPublication() {
        String url = Statics.BASE_URL + "/eleve/json/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

               
                try {
                    Publications = parsePub(new String(req.getResponseData()));
                } catch (ParseException ex) {
                   
                }
                

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Publications;
    }

    //delete
    public boolean deleteEleve(int ide) {
        String url = Statics.BASE_URL + "/eleve/deleteJSON/"+ide;
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
    
    public void modifierEleve(Eleve Pub) {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL + "/eleve/updateJSON/"+ Pub.getIde() + "?nom=" + Pub.getNom() + "&prenom=" + Pub.getPrenom() + "&age=" + Pub.getAge() + "&idp=" + Pub.getIdp() + "&idj=" + Pub.getIdj();
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    } 

    public void ModifierJardinEnfant(JardinEnfant Pub) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void AddJardinEnfant(JardinEnfant J) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteJardinEnfant(int idj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
