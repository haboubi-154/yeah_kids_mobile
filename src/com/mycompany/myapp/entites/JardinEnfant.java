/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

import java.util.Date;



/**
 *
 * @author lotfi
 */
public class JardinEnfant {

    public static void add(Eleve Pubs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int idj;
    private String nom;
    private String description;
    private String logo;
    private int nb_employes;
    private String adresse;
    public Date date_creation;
    private float prix;
    private int telephone;

    public JardinEnfant() {
    }

    public JardinEnfant(int idj, String nom, String description, String logo, int nb_employes, String adresse, Date date_creation, float prix, int telephone) {
        this.idj = idj;
        this.nom = nom;
        this.description = description;
        this.logo = logo;
        this.nb_employes = nb_employes;
        this.adresse = adresse;
        this.date_creation = date_creation;
        this.prix = prix;
        this.telephone = telephone;
    }

    public JardinEnfant(String nom, String description, String logo, int nb_employes, String adresse, Date date_creation, float prix, int telephone) {
        this.nom = nom;
        this.description = description;
        this.logo = logo;
        this.nb_employes = nb_employes;
        this.adresse = adresse;
        this.date_creation = date_creation;
        this.prix = prix;
        this.telephone = telephone;
    }

    public int getIdj() {
        return idj;
    }

    public void setIdj(int idj) {
        this.idj = idj;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getNb_employes() {
        return nb_employes;
    }

    public void setNb_employes(int nb_employes) {
        this.nb_employes = nb_employes;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "JardinEnfant{" + "idj=" + idj + ", nom=" + nom + ", description=" + description + ", logo=" + logo + ", nb_employes=" + nb_employes + ", adresse=" + adresse + ", date_creation=" + date_creation + ", prix=" + prix + ", telephone=" + telephone + '}';
    }

    public java.util.Date getDate_Pub() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Date date_creation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
}
