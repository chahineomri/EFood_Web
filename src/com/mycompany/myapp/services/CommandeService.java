/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azizm
 */
public class CommandeService {
    public static CommandeService instance = null;
    private ConnectionRequest req;
    public ArrayList<Commande> commande;
    public static boolean resultOK;
    
    public static CommandeService getInstance(){
        if(instance==null)
            instance = new CommandeService();
         return instance;
        
    }
    public CommandeService(){
        req = new ConnectionRequest();
    }
    public boolean AjouterCommande(Commande commande){
        String url = Statics.BASE_URL+"addcostmobile";
        req.setUrl(url);
        req.addArgument("nom", commande.getNom());
        req.addArgument("prenom", commande.getPrenom());
        req.addArgument("email", commande.getEmail());
        req.addArgument("adresse", commande.getAdresse());
        req.addArgument("numtelephone", commande.getNumTelephone()+"");
        req.addArgument("totalcost", commande.getTotalCost()+"");
        req.addArgument("dateCommande", commande.getDate());
        System.out.println(url);

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
    public ArrayList<Commande> parseCours(String jsonText){
        try {
            commande=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Commande u = new Commande();
               
                u.setCommandeId((int) Float.parseFloat(obj.get("id").toString()));
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setEmail(obj.get("email").toString());
                u.setAdresse(obj.get("adresse").toString());
                u.setNumTelephone((int)Double.parseDouble(obj.get("numtelephone").toString()));
                u.setDate(obj.get("dateCommande").toString());
                u.setTotalCost((int)Double.parseDouble(obj.get("totalcost").toString()));
                
               
       
                commande.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return commande;
    }
        
        
    public ArrayList<Commande> getAllCours(){
        String url = Statics.BASE_URL+"/getcommandesmobile";
        req.setUrl(url);
        req.setPost(false);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commande = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commande;
    }
    public boolean deleteCommande(int commandeId){
        String url = Statics.BASE_URL+"deletecommande?commandeId="+commandeId;
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
    
    
}