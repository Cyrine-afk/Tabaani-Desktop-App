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
            String requete2 = "INSERT INTO themes (themename,imagetheme) "
                    + "VALUES (?,?)"; //requete pré-compilée 
        try {
            
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
            String requete3 = "SELECT * FROM themes ORDER BY themename ASC";
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
    
    public void supprimerTheme(Themes T) {
        try {
            String requete = "DELETE FROM themes WHERE themename=? ";

            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1, T.getId());
            
            pst.executeUpdate();
            System.out.println("Theme successfully deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); //System.out.println(ex.getMessage());
        }
    }
    
    public void modifierTheme(int id, String object, Object obj) {
        try {
            
            String requete = "UPDATE themes SET ? = ? WHERE id = ?";
        
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, object);
            pst.setObject(2, obj);
            pst.setInt(3, id);
            String ch = pst.toString().replaceFirst("\'","");
            String ch2 = ch.replaceFirst("\'", "");
            int pos = ch2.indexOf("UPDATE");
            String ch3 = ch2.substring(pos, ch2.length());
            System.out.println(ch3);
            pst = cnx2.prepareStatement(ch3);
            pst.executeUpdate();
            System.out.println("Theme successfully updated !");
            
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    public boolean CheckThemeByName(String name) {

        boolean p = false;
        try {

            Statement pst = cnx2.createStatement();

            ResultSet rs = pst.executeQuery("SELECT * FROM themes WHERE themename='" + name + "'");

            while (rs.next()) {
                 p=true;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;

    }
    
}
