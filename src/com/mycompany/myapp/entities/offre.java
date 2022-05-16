/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author LENOVO
 */

import java.util.Date;
/**
 *
 * @author LENOVO
 */
public class offre {

  
   private int id_offre;
   private String text_offre;
   private String  date_offre;
   private String type_offre;
   private String img_offre;

    public offre() {
    }

    public offre(int id_offre, String text_offre, String date_offre, String type_offre, String img_offre) {
        this.id_offre = id_offre;
        this.text_offre = text_offre;
        this.date_offre = date_offre;
        this.type_offre = type_offre;
        this.img_offre = img_offre;
    }

    public offre(String text_offre, String date_offre, String type_offre, String img_offre) {
        this.text_offre = text_offre;
        this.date_offre = date_offre;
        this.type_offre = type_offre;
        this.img_offre = img_offre;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public String getText_offre() {
        return text_offre;
    }

    public void setText_offre(String text_offre) {
        this.text_offre = text_offre;
    }

    public String getDate_offre() {
        return date_offre;
    }

    public void setDate_offre(String date_offre) {
        this.date_offre = date_offre;
    }

    public String getType_offre() {
        return type_offre;
    }

    public void setType_offre(String type_offre) {
        this.type_offre = type_offre;
    }

    public String getImg_offre() {
        return img_offre;
    }

    public void setImg_offre(String img_offre) {
        this.img_offre = img_offre;
    }

    @Override
    public String toString() {
        return "offre{" + "id_offre=" + id_offre + ", text_offre=" + text_offre + ", date_offre=" + date_offre + ", type_offre=" + type_offre + ", img_offre=" + img_offre + '}';
    }
   


            
}

