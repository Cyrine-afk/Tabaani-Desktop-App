/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tabaani.entities.User;
import tabaani.utils.MyConnection;

/**
 *
 * @author DELL
 */
public class UserCRUD {
    Connection cnx2;
    
    public UserCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterUser() {
        try {
            String requete = "INSERT INTO themes (nom_user,prenom_user,date_naiss,email_user,login_user,mdp_user,num_user,image_user) "
                    + "       VALUES ('java username','prenom user java',1992-04-09,'email@gmail.com','samurai','123',98514752,'img java')"; 
            Statement st = cnx2.createStatement(); //pour les requetes statiques
            st.executeUpdate(requete); //update bata base only request (update, delete or add functions)
            System.out.println("User successfully added !");        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    public List<User> afficherThemes() {
            List<User> usersList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM themes ORDER BY themename ASC";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3); 
            while (rs.next()) {
                User t = new User();
                
                t.setId(rs.getInt(1));
                t.setNom_user(rs.getString("nom_user"));
                t.setPrenom_user(rs.getString("prenom_user"));
        //(rs.getString("imagetheme"));
                
                usersList.add(t);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return usersList;
    }
    
    
}
