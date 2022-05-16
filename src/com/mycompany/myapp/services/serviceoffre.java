/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.offre;
import java.util.ArrayList;
import com.mycompany.myapp.utils.statics;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *
 * @author LENOVO
 */
public class serviceoffre {
    //singleton 
    public static serviceoffre instance = null ;
    
    public static boolean resultOk = true;
      //initilisation connection request 
    private ConnectionRequest req;
      public static serviceoffre getInstance() {
        if(instance == null )
            instance = new serviceoffre();
        return instance ;
    }
         public serviceoffre() {
        req = new ConnectionRequest();
        
    }
        public ArrayList<offre>affichageoffre() {
        
        ArrayList<offre> result = new ArrayList<>();
        String url = statics.BASE_URL+"/offre/get";
        req.setUrl(url);
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            try {
                Map<String,Object>mapOffres = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapOffres.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    
                    offre o = new offre();
                     while (mapOffres.values().remove(null));
                    //dima id fi codename one float 5outhouha
                    float id_offre = Float.parseFloat(obj.get("id_offre").toString());
                    String text_offre = obj.get("text_offre").toString();
                    String type_offre = obj.get("type_offre").toString();
                   String img_offre = obj.get("img_offre").toString();
                   
                   
                   o.setId_offre((int)id_offre);
                    o.setText_offre(text_offre);
                   o.setType_offre(type_offre);
                    o.setImg_offre(img_offre);
                    
                    String DateConverter =  obj.get("date_offre").toString().substring(obj.get("date_offre").toString().indexOf("timestamp") + 10 , obj.get("date_offre").toString().lastIndexOf("}"));
                    Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = formatter.format(currentTime);
                    o.setDate_offre(dateString);
                    //insert data into ArrayList result
                    result.add(o);
                    
                }
            } catch(Exception ex) {            
                
                ex.printStackTrace();
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
                return result;

        }   
                }



