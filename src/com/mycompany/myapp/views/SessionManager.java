/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.views;
import com.codename1.io.Preferences;

/**
 *
 * @author sarawahada
 */
public class SessionManager {
    
    
    public static Preferences pref ; 


    private static int IdUser ; 
    private static String NameUser ; 
    private static String LastNameUser ; 
    private static String EmailUser; 
    private static String PasswordUser ;
    private static String ProfilePicUser;
    private static String UserStatus;


    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getIdUser() {
        return IdUser;
    }

    public static String getNameUser() {
        return NameUser;
    }

    public static String getLastNameUser() {
        return LastNameUser;
    }

    public static String getEmailUser() {
        return EmailUser;
    }

    public static String getPasswordUser() {
        return PasswordUser;
    }

    public static String getProfilePicUser() {
        return ProfilePicUser;
    }

 

    public static String getUserStatus() {
        return UserStatus;
    }

    public static void setIdUser(int IdUser) {
        SessionManager.IdUser = IdUser;
    }

    public static void setNameUser(String NameUser) {
        SessionManager.NameUser = NameUser;
    }

    public static void setLastNameUser(String LastNameUser) {
        SessionManager.LastNameUser = LastNameUser;
    }

    public static void setEmailUser(String EmailUser) {
        SessionManager.EmailUser = EmailUser;
    }

    public static void setPasswordUser(String PasswordUser) {
        SessionManager.PasswordUser = PasswordUser;
    }

    public static void setProfilePicUser(String ProfilePicUser) {
        SessionManager.ProfilePicUser = ProfilePicUser;
    }


    public static void setUserStatus(String UserStatus) {
        SessionManager.UserStatus = UserStatus;
    }

 
    
}