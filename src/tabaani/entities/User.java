/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.entities;

import java.time.LocalDate;

/**
 *
 * @author DELL
 */
public class User {
    private int id;
    private String nom_user;
    private String prenom_user;
    private LocalDate date_naiss;
    private String email_user;
    private String login_user;
    private String mdp_user;
    private int num_user;
    private String image_user;

    public User() {
    }

    public User(int id, String nom_user, String prenom_user, LocalDate date_naiss, String email_user, String login_user, String mdp_user, int num_user, String image_user) {
        this.id = id;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.date_naiss = date_naiss;
        this.email_user = email_user;
        this.login_user = login_user;
        this.mdp_user = mdp_user;
        this.num_user = num_user;
        this.image_user = image_user;
    }

    public User(String nom_user, String prenom_user, LocalDate date_naiss, String email_user, String login_user, String mdp_user, int num_user, String image_user) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.date_naiss = date_naiss;
        this.email_user = email_user;
        this.login_user = login_user;
        this.mdp_user = mdp_user;
        this.num_user = num_user;
        this.image_user = image_user;
    }

    public User(String nom_user, String prenom_user, LocalDate date_naiss, String email_user, String login_user, String mdp_user, int num_user) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.date_naiss = date_naiss;
        this.email_user = email_user;
        this.login_user = login_user;
        this.mdp_user = mdp_user;
        this.num_user = num_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public LocalDate getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(LocalDate date_naiss) {
        this.date_naiss = date_naiss;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_user) {
        this.login_user = login_user;
    }

    public String getMdp_user() {
        return mdp_user;
    }

    public void setMdp_user(String mdp_user) {
        this.mdp_user = mdp_user;
    }

    public int getNum_user() {
        return num_user;
    }

    public void setNum_user(int num_user) {
        this.num_user = num_user;
    }

    public String getImage_user() {
        return image_user;
    }

    public void setImage_user(String image_user) {
        this.image_user = image_user;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom_user=" + nom_user + ", prenom_user=" + prenom_user + ", date_naiss=" + date_naiss + ", email_user=" + email_user + ", login_user=" + login_user + ", mdp_user=" + mdp_user + ", num_user=" + num_user + ", image_user=" + image_user + '}';
    }
    
    
}
