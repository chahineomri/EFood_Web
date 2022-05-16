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
public class ProduitService {

    public ArrayList<Produit> produit;

    public static ProduitService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ProduitService() {
        req = new ConnectionRequest();
    }

    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }
        return instance;
    }

    public ArrayList<Produit> parseCat(String jsonText) {
        try {
            produit = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> clubListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) clubListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données

                Produit e = new Produit ();

                try {
                    float id = Float.parseFloat(obj.get("id").toString());

                    e.setId((int) id);
                } catch (Exception e1) {
                    System.out.println("id");
                }

                try {
                    e.setName(obj.get("name").toString());
                } catch (Exception e2) {
                    System.out.println("name");
                }

                try {
                    e.setImage(obj.get("image").toString());
                } catch (Exception e2) {
                    System.out.println("image");
                }

                try {
                    e.setPrice(obj.get("price").toString());
                } catch (Exception e4) {
                    System.out.println("price");
                }
                try {
                    e.setCategory(obj.get("Category").toString());
                } catch (Exception e5) {
                    System.out.println("category");
                }

                try {
                    produit.add(e);
                } catch (Exception e6) {
                    System.out.println("produit");
                }
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return produit;
    }

    public ArrayList<Produit> getAllproduit() {
        String url = Statics.BASE_URL + "/viewP";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produit = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produit;
    }

    public boolean Deleteproduit(int id) {
        String url = Statics.BASE_URL + "/deleteproduit?id=" + id;

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

    public void addproduit(Produit c) {
        String url = Statics.BASE_URL + "/addProduit?name=" + c.getName() + "&image=" + c.getImage() + "&price="
                + c.getPrice() + "&category=" + c.getCategory();

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean modifierproduit(Produit event) {
        String url = Statics.BASE_URL + "/updateProduit/" + event.getId() + "?name=" + event.getName() + "&price=" + event.getPrice()
                + "&logo=" + event.getImage() + "&president=" + event.getCategory();
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
    
public boolean AddPanierProduit(Produit p) {
        String url = Statics.BASE_URL + "/panier/add_jason/" + p.getId();
               
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 
         public ArrayList<Produit> getProduitsPanierMobile(){
       
         
         String url = Statics.BASE_URL+"/panierf_jason";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produit = parseCat(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
        return produit ;
    }
  
}
