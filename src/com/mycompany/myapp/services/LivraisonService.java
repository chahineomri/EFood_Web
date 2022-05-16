/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

  
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.entities.Produit;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author oumayma cherif
 */
public class LivraisonService {

    public ArrayList<Livraison> livraison;

    public static LivraisonService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public LivraisonService() {
        req = new ConnectionRequest();
    }

    public static LivraisonService getInstance() {
        if (instance == null) {
            instance = new LivraisonService();
        }
        return instance;
    }

    public ArrayList<Livraison> parseCat(String jsonText) {
        try {
            livraison = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> clubListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) clubListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données

                Livraison e = new Livraison ();

                try {
                    float id = Float.parseFloat(obj.get("id").toString());

                    e.setId((int) id);
                } catch (Exception e1) {
                    System.out.println("id");
                }

                try {
                    e.setNom(obj.get("nom").toString());
                } catch (Exception e2) {
                    System.out.println("nom");
                }

                try {
                    e.setPrenom(obj.get("prenom").toString());
                } catch (Exception e2) {
                    System.out.println("prenom");
                }

                try {
                    e.setAdresse(obj.get("adresse").toString());
                } catch (Exception e4) {
                    System.out.println("adresse");
                }
                try {
                    e.setEmail(obj.get("email").toString());
                } catch (Exception e5) {
                    System.out.println("email");
                }
                

                try {
                    livraison.add(e);
                } catch (Exception e6) {
                    System.out.println("livraison");
                }
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return livraison;
    }

    public ArrayList<Livraison> getAllLivraison() {
        String url = Statics.BASE_URL + "/viewLivraison";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livraison = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livraison;
    }

    public boolean DeleteLivraison(int id) {
        String url = Statics.BASE_URL + "/deleteLivraison?id=" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

    public void addLivraison(Livraison c) {
        String url = Statics.BASE_URL + "/addLivraison?nom=" + c.getNom() + "&prenom=" + c.getPrenom() + "&adresse="
                + c.getAdresse() + "&email=" + c.getEmail();

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean modifierLivraison(Livraison event) {
        String url = Statics.BASE_URL + "/updateLivraison/" + event.getId() + "?nom=" + event.getNom() + "&prenom=" + event.getPrenom()
                + "&adresse=" + event.getAdresse() + "&email=" + event.getEmail();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
   
}
