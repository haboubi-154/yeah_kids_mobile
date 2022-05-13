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
public  class user {
    protected String prenom;
    protected String nom;
    protected String email;
    protected String login;
    protected String mdp;
    protected String image;
   protected byte[] img;
   protected String salt;

      protected String question;
   protected String responde;
   protected String role;


    public user(){};
    public user(String prenom, String nom, String email,String login,String mdp,String image,String salt,String question,String responde,String role) {
        this.prenom = prenom;
        this.nom = nom;
        this.login = login;
        this.mdp = mdp;
        this.image = image;
        this.email = email;
        this.salt=salt;
        this.question = question;
        this.responde=responde;
        
        
    }

    public user(String prenom, String nom, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.login = login;
        this.mdp = mdp;
        this.image = image;
        this.email = email;    }
        
   
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    
    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }
     public void setEmail(String email) {
        this.email=email;
    }

    public String getimage() {
        return this.image;
    }
    public void setimage(String image) {
        this.image = image;
    }
    public void setlogin(String login) {
        this.login = login;
    }
    public String getlogin() {
        return this.login;
    }
    public void setmdp(String mdp) {
        this.mdp = mdp;
    }
    public String getmdp() {
        return this.mdp;
    }
    public void setimg(byte[] img) {
        this.img = img;
    }
    public byte[] getimg() {
        return this.img;
    }
    public void setsalt(String salt){
        this.salt=salt;
    }
    public String getsalt(){
        return this.salt;
    }
public void setresponde(String responde){
        this.responde=responde;
    }
    public String getresponde(){
        return this.responde;
    }
   public void setquestion(String question){
        this.question=question;
    }
    public String getquestion(){
        return this.question;
    }
    public void setrole(String role){
        this.role=role;
    }
    public String getrole(){
        return this.role;
    }
     public String toString() {
        return "user {"+"role="+role +  "prenom=" + prenom + ", nom=" + nom + ", email=" + email + ", login=" + login + ", mdp=" + mdp + ", image=" + image + '}' ;
    }
}
