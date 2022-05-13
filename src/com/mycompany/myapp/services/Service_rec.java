/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fedi
 */
public class Service_rec {

    
   public static Service_rec instance = null;
    String json;
    private boolean resultat;
    public ArrayList<reclamation> reclamations;
    private MultipartRequest request;
        private final ConnectionRequest req;


    private Service_rec() {
req = new ConnectionRequest();
        request = new MultipartRequest();    }

    public static Service_rec getInstance() {
        if (instance == null) {
            instance = new Service_rec();
        }
        return instance;
    }

    public boolean addreclamation(reclamation t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/reclamation/ajouter";
    
       req.setUrl(url);
       
       req.addArgument("id_parent",Integer.toString(t.get_idparent()));
       req.addArgument("text", t.get_contenu()+"");
       

       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultat = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultat;
    }

     public ArrayList<reclamation> parse(String jsonTxt) {
        try {
            reclamations = new ArrayList<>();

            JSONParser parser = new JSONParser();

            Map<String, Object> CategoriesJSON;
            CategoriesJSON = parser.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));
            List<Map<String, Object>> listeRec;
            listeRec = (List<Map<String, Object>>) CategoriesJSON.get("root");

            for (Map<String, Object> item : listeRec) {
                reclamation u = new reclamation();

                float id = Float.parseFloat(item.get("id_reclamation").toString());
                u.set_idparent(Integer. parseInt(item.get("id_Parent").toString()));
                u.set_contenu(item.get("text").toString());
                
                System.out.print(u);

                reclamations.add(u);
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("aaa");
        }
        return reclamations;

    }
    
   public ArrayList<reclamation> showreclamations() {
        request = new MultipartRequest();
        String url = Statics.BASE_URL + "/reclamation/affiche";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String dataaa = new String(request.getResponseData(), "utf-8");
                    System.out.println("our dataaa " + dataaa);
                    reclamations = parse(dataaa);
                    request.removeResponseListener(this);
                } catch (Exception ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        return reclamations;
    }
     

    public boolean ajoutreclamation(reclamation t) {
        try {
            String url = Statics.BASE_URL + "/reclamation/ajouter";
                req.setPost(false);

       req.setUrl(url);
       
       req.addArgument("id_parent",Integer.toString(t.get_idparent()));
       req.addArgument("text", t.get_contenu()+"");

            req.setUrl(url);
            req.setFailSilently(true);

            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    System.out.println("action performed");

                    resultat = req.getResponseCode() == 200;
                    System.out.println(req.getResponseCode());
                    System.out.println(req.getRequestBody());
                    req.removeResponseListener(this);

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultat;

    }

    public boolean modifier(int id, reclamation t) {
        String url = Statics.BASE_URL + "/reclamation/modifier/"+id;
                    req.setPost(false);

       req.setUrl(url);
       
       req.addArgument("id_parent",Integer.toString(t.get_idparent()));
       req.addArgument("text", t.get_contenu()+"");

        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    System.out.println("action performed");

                    resultat = req.getResponseCode() == 200;
                    System.out.println(req.getResponseCode());
                    System.out.println(req.getRequestBody());
                    req.removeResponseListener(this);

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
        

        return resultat;
    
    }

    public boolean delete(int id) {
        String url = Statics.BASE_URL + "/reclamation/delete/" + id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultat = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultat;
    }

}
