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
import com.mycompany.myapp.entites.user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fedi
 */
public class Serviceuser {

    
   public static Serviceuser instance = null;
    String json;
    private boolean resultat;
    public ArrayList<user> users;
    private MultipartRequest request;
        private final ConnectionRequest req;


    private Serviceuser() {
req = new ConnectionRequest();
        request = new MultipartRequest();    }

    public static Serviceuser getInstance() {
        if (instance == null) {
            instance = new Serviceuser();
        }
        return instance;
    }

    public boolean adduser(user t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/user/ajouter";
    
       req.setUrl(url);
       
       req.addArgument("name", t.getNom());
       req.addArgument("prenom", t.getPrenom()+"");
       req.addArgument("salt", t.getsalt()+"");
       req.addArgument("password", t.getmdp()+"");
       req.addArgument("image", t.getimage()+"");
       req.addArgument("role", t.getrole()+"");
       req.addArgument("question", t.getquestion()+"");
        req.addArgument("responde", t.getresponde()+"");

       
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

     public ArrayList<user> parse(String jsonTxt) {
        try {
            users = new ArrayList<>();

            JSONParser parser = new JSONParser();

            Map<String, Object> CategoriesJSON;
            CategoriesJSON = parser.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));
            List<Map<String, Object>> listeRec;
            listeRec = (List<Map<String, Object>>) CategoriesJSON.get("root");

            for (Map<String, Object> item : listeRec) {
                user u = new user();

                float id = Float.parseFloat(item.get("id").toString());
                u.setNom(item.get("nom").toString());
                u.setPrenom(item.get("prenom").toString());
                u.setrole(item.get("role").toString());
                u.setEmail(item.get("email").toString());
                u.setlogin(item.get("login").toString());
                
                users.add(u);
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("aaa");
        }
        return users;

    }
    
   public ArrayList<user> showUsers() {
        request = new MultipartRequest();
        String url = Statics.BASE_URL + "/user/affiche";
        request.setUrl(url);

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String dataaa = new String(request.getResponseData(), "utf-8");
                    System.out.println("our dataaa " + dataaa);
                    users = parse(dataaa);
                    request.removeResponseListener(this);
                } catch (Exception ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        return users;
    }
     

    public boolean ajoutuser(user t) {
        try {
            String url = Statics.BASE_URL + "/user/ajouter";
                req.setPost(false);

       req.setUrl(url);
       
       req.addArgument("nom", t.getNom());
       req.addArgument("prenom", t.getPrenom()+"");
       req.addArgument("salt", t.getsalt()+"");
       req.addArgument("password", t.getmdp()+"");
       req.addArgument("email", t.getEmail()+"");
       req.addArgument("login", t.getlogin()+"");

       req.addArgument("image", t.getimage()+"");
       req.addArgument("role", t.getrole()+"");
       req.addArgument("question", t.getquestion()+"");
        req.addArgument("responde", t.getresponde()+"");

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

    public boolean modifier(int id, user t) {
        String url = Statics.BASE_URL + "/user/modifier/"+id;
                    req.setPost(false);

       req.setUrl(url);
       
       req.addArgument("nom", t.getNom());
       req.addArgument("prenom", t.getPrenom()+"");
       req.addArgument("salt", t.getsalt()+"");
       req.addArgument("password", t.getmdp()+"");
       req.addArgument("email", t.getEmail()+"");
       req.addArgument("login", t.getlogin()+"");

       req.addArgument("image", t.getimage()+"");
       req.addArgument("role", t.getrole()+"");
       req.addArgument("question", t.getquestion()+"");
        req.addArgument("responde", t.getresponde()+"");

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
        String url = Statics.BASE_URL + "/user/delete/" + id;
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
