/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author test
 */
public class produit {
    private int id_produit;
    private int prix;
    private String nom ;
    private int quantite;
    private String image ;
    private byte img[];

    public produit(int id_produit, int prix, String nom, int quantite, String image) {
        this.id_produit = id_produit;
        this.prix = prix;
        this.nom = nom;
        this.quantite = quantite;
        this.image = image;
    }

    public produit(int prix, String nom, int quantite) {
        this.prix = prix;
        this.nom = nom;
        this.quantite = quantite;
       
    }

    public produit() {
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getImage() {
        return image;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setImage(String image) {
        this.image = image;
    }

     public void setimg(byte[] img) {
        this.img = img;
    }
    public byte[] getimg() {
        return this.img;
    }

    @Override
    public String toString() {
        return "produit{" + "id_produit=" + id_produit + ", prix=" + prix + ", nom=" + nom + ", quantite=" + quantite + ", image=" + image + '}';
    }

    
    
    
        
}
