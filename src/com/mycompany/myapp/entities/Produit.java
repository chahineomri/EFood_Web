/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author oumaymacherif
 */
public class Produit {

    private int id;
    public String name;
    public String image;
    private String price;
    private String category;

    public Produit(int id, String name, String image, String price, String category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.category = category;
    }

    public Produit() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produit(String name, String image, String price, String category) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "serviceproduit {" + "id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", category=" + category + '}';
    }

}
