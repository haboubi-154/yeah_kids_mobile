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
import com.mycompany.myapp.entites.produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fedi
 */
public class service_produit {

    
   public static service_produit instance = null;
    String json;
    private boolean resultat;
    public ArrayList<produit> produits;
    private MultipartRequest request;
        private final ConnectionRequest req;


    private service_produit() {
req = new ConnectionRequest();
        request = new MultipartRequest();    }

    public static service_produit getInstance() {
        if (instance == null) {
            instance = new service_produit();
        }
        return instance;
    }

    
   

     public ArrayList<produit> parse(String jsonTxt) {
        try {
            produits = new ArrayList<>();

            JSONParser parser = new JSONParser();

            Map<String, Object> CategoriesJSON;
            CategoriesJSON = parser.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));
            List<Map<String, Object>> listeRec;
            listeRec = (List<Map<String, Object>>) CategoriesJSON.get("root");

            for (Map<String, Object> item : listeRec) {
                produit u = new produit();

                float id = Float.parseFloat(item.get("id_produit").toString());
                u.setPrix(Integer. parseInt(item.get("prix").toString()));
                u.setNom(item.get("nom").toString());
                
                
                System.out.print(produits.add(u));
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("aaa");
        }
        return produits;

    }
    
   public ArrayList<produit> showproduits() {
        request = new MultipartRequest();
        String url = Statics.BASE_URL + "/produit/affiche";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String dataaa = new String(request.getResponseData(), "utf-8");
                    System.out.println("our dataaa " + dataaa);
                    produits = parse(dataaa);
                    request.removeResponseListener(this);
                } catch (Exception ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        return produits;
    }
     

    public boolean ajoutproduit(produit t) {
        try {
            String url = Statics.BASE_URL + "/produit/ajouter";
                req.setPost(false);

       req.setUrl(url);
       
       req.addArgument("prix",Integer.toString(t.getPrix()));
              req.addArgument("quantite",Integer.toString(t.getQuantite()));

       req.addArgument("nom", t.getNom()+"");

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

    public boolean modifier(int id, produit t) {
        String url = Statics.BASE_URL + "/produit/modifier/"+id;
                    req.setPost(false);

       req.setUrl(url);
       
       req.addArgument("prix",Integer.toString(t.getPrix()));
              req.addArgument("quantite",Integer.toString(t.getQuantite()));

       req.addArgument("nom", t.getNom()+"");

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
        String url = Statics.BASE_URL + "/produit/delete/" + id;
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
