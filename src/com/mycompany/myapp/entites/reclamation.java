/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author fedi
 */
public class reclamation {
    private int id_reclamation;
    private String contenu;
    private int id_parent;
    public reclamation(int id,String contenu){
        this.id_parent=id;
        this.contenu=contenu;
        
    }

    public reclamation() {
    }
    public int get_idparent(){
    return this.id_parent;
    
    }
     public void set_idparent(int id){
    this.id_parent=id;
    
    }
   
    public int get_id(){
        return this.id_reclamation;
    }
    public void set_id(int id){
       this.id_reclamation=id;
    }
    public String get_contenu(){
        return this.contenu;
    }
    public void set_contenu(String contenu){
        this.contenu=contenu;
    }
    
    @Override
    public String toString() {
        return "reclamation {"+  "id_parent=" + id_parent + ", contenu=" + contenu + '}' ;
    }
}

