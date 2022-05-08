/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.views.ProfileForm;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.statics;
import com.mycompany.myapp.views.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author sarawahada
 */
public class UserService {
    
        public ArrayList<User> users;

  //singleton 
    public static UserService instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static UserService getInstance() {
        if(instance == null )
            instance = new UserService();
        return instance ;
    }
    public UserService() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField name,TextField lastname,TextField email,TextField password,TextField confirmPassword, Resources res ) {

        String url = statics.BASE_URL+"/user/mobile/signup?name="+name.getText().toString()+"&lastname="+lastname.getText().toString()+
                "&email="+email.getText().toString()+"&password="+password.getText().toString();
        
        req.setUrl(url);
        
        //Control saisie
        if(name.getText().equals(" ") &&lastname.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Error","Fill required fields","OK",null);
            
        }
       
        req.addResponseListener((e)-> {
         
           
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    //SignIn
    
    public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = statics.BASE_URL+"/user/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) ;
            System.out.println(json);

            if(json.equals("Incorrect Email")) {
                Dialog.show("Authentication failed","User not found","OK",null);
            }
            else if(json.equals("Incorrect pwd")) {
                Dialog.show("Authentication failed","Password is incorrect","OK",null);
            }
            else {
                try {
                    System.out.println("data =="+json);
                    Map<String,Object> User = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    //Session
                    float IdUser = Float.parseFloat(User.get("iduser").toString());
                    SessionManager.setIdUser((int)IdUser);
                    SessionManager.setPasswordUser(User.get("passworduser").toString());
                    SessionManager.setNameUser(User.get("nameuser").toString());
                    SessionManager.setLastNameUser(User.get("lastnameuser").toString());
                    SessionManager.setEmailUser(User.get("emailuser").toString());
                    SessionManager.setUserStatus(User.get("userstatus").toString());
                    //photo
                    if(User.get("profilepicuser") != null)
                        SessionManager.setProfilePicUser(User.get("profilepicuser").toString());
                    if(!User.isEmpty() )
                        new ProfileForm(rs).show();
                } catch (IOException ex) {
                   
                }
                    }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
       public ArrayList<User> parseTasks(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)tasksListJson.get("root");
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User t = new User();
               float id = Float.parseFloat(obj.get("iduser").toString());
                t.setIdUser((int)id);
                t.setNameUser(obj.get("nameuser").toString());
                t.setLastNameUser(obj.get("lastnameuser").toString());
                t.setEmailUser(obj.get("emailuser").toString());
                t.setProfilePicUser(obj.get("profilepicuser").toString());
                System.out.println(t.getNameUser());
                //Ajouter la tâche extraite de la réponse Json à la liste
                users.add(t);
            }
        } catch (IOException ex) {}
         /*
        A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        */
        return users;
    }
       
      public ArrayList<User> getAllUsers(){
        String url = statics.BASE_URL+"/user/getAllUsers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(req.getResponseData())); 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
      
     public User getUser(String text) {
        ArrayList<User>  l = getAllUsers();
        int i;
        for (i = 0; i < l.size(); i++) {
            System.err.println(l.get(i).getIdUser());
            if (l.get(i).getEmailUser().compareTo(text) == 0) {
                return l.get(i);
            }
        }
        return null;
    }
     
     
     
      public void updatePassword(String email, String password) {
        String url = statics.BASE_URL + "/user/mobile/updatePassword?email="+email+"&password="+password ;
        System.out.print(url);
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
        public void verify(String email) {
        String url = statics.BASE_URL + "/user/mobile/verify?email="+email ;
        System.out.print(url);
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
         public void update(String name,String lastname,String email, String password) {
        String url = statics.BASE_URL + "/user/mobile/update?name="+name+"&lastname="+lastname+"&email="+email+"&password="+password ;
        req.setUrl(url);
        
         req.addResponseListener((e)-> {    JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) ;
            System.out.println(json);
if(json.equals("Incorrect")) {
                 Dialog.show("Update failed","Old password is incorrect","OK",null);
            }
else{
            
                byte[]data = (byte[]) e.getMetaData();
                String responseData = new String(data);
                System.out.println("data ===>"+responseData);         
         }
         }

        );
        NetworkManager.getInstance().addToQueueAndWait(req);
   
                }
     
}
