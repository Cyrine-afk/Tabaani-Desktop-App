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
import java.util.logging.Level;
import java.util.logging.Logger;
import tabaani.entities.Themes;
import tabaani.utils.MyConnection;

/**
 *
 * @author DELL
 */
public class ThemesCRUD {
    
    Connection cnx2;
    
    public ThemesCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterTheme() {
        try {
            String requete = "INSERT INTO themes (themename,imagetheme) "
                    + "       VALUES ('theme Java','image Java')"; 
            Statement st = cnx2.createStatement(); //pour les requetes statiques
            st.executeUpdate(requete); //update bata base only request (update, delete or add functions)
            System.out.println("Theme successfully added !");        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void ajouterTheme2(Themes Th) {
        try {
            String requete2 = "INSERT INTO themes (themename,imagetheme) "
                    + "VALUES (?,?)"; //requete pré-compilée 
            PreparedStatement pst = cnx2.prepareStatement(requete2); //pour les requetes dynamiiques + plus rapide que statement (temps d'execution)
            pst.setString(1, Th.getThemename());
            pst.setString(2, Th.getImagetheme());
            pst.executeUpdate(); 
            System.out.println("Your theme has been successfully added !");            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public List<Themes> afficherThemes() {
            List<Themes> themesList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM themes";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3); 
            while (rs.next()) {
                Themes t = new Themes();
                t.setId(rs.getInt(1));
                t.setThemename(rs.getString("themename"));
                t.setImagetheme(rs.getString("imagetheme"));
                themesList.add(t);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return themesList;
    }
    
    
    
}
