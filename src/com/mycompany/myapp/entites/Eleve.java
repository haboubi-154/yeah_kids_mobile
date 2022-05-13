/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author lotfi
 */
public class Eleve {
    private int ide;
    private String nom;
    private String prenom;
    private int age;
    private int idp;
    private int idj;

    public Eleve() {
    }

    public Eleve(int ide, String nom, String prenom, int age, int idp, int idj) {
        this.ide = ide;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.idp = idp;
        this.idj = idj;
    }

    public Eleve(String nom, String prenom, int age, int idp, int idj) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.idp = idp;
        this.idj = idj;
    }


    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdp() {
        return idp;
    }

    public void setidp(int idp) {
        this.idp = idp;
    }

   public int getIdj() {
        return idj;
    }

    public void setidj(int idj) {
        this.idj = idj;
    }

    @Override
    public String toString() {
        return "Eleve{" + "ide=" + ide + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", idp=" + idp + ", idj=" + idj + '}';
    }
    
    
}
    

    
    

