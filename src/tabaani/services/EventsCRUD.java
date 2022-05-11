/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import tabaani.entities.Events;
import tabaani.entities.Themes;
import tabaani.entities.User;
import tabaani.utils.MyConnection;

/**
 *
 * @author DELL 
 */
public class EventsCRUD {
    
    Connection cnx2;
    
    public EventsCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterEvent() {
        try {
            String requete = "INSERT INTO events (eventname,nbrmaxpart,imageevent,description,eventdate,eventaddress,eventtheme_id,org_id,nbr_going) "
                    + "       VALUES ('eventname',10,'imageevent','description','2022-03-20','eventaddress',4,2,3)"; 
            
            Statement st = cnx2.createStatement();
            
            st.executeUpdate(requete); //update bata base only request (update, delete or add functions)
            System.out.println("Event successfully added !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void ajouterEvent2(Events E) {
        System.out.println(E.getEventtheme_id());
        
        String requete2 = "INSERT INTO events (eventname,nbrmaxpart,imageevent,description,eventdate,eventaddress,eventtheme_id,org_id,nbr_going) "
            + "VALUES (?,?,?,?,?,?,?,?,?)"; //requete pré-compilée 
            
        try {
            Date Date_event = Date.valueOf(E.getEventdate());
            
            PreparedStatement pst = cnx2.prepareStatement(requete2); //pour les requetes dynamiiques + plus rapide que statement (temps d'execution)
            pst.setString(1, E.getEventname());
            pst.setInt(2, E.getNbrmaxpart());
            pst.setString(3, E.getImageevent());
            pst.setString(4, E.getDescription());
            pst.setDate(5, Date_event);
            pst.setString(6, E.getEventaddress());
            pst.setInt(7, 3);
            pst.setInt(8, 2);
            pst.setInt(9, E.getNbr_going());
            
            
            pst.executeUpdate(); 
            System.out.println("Your event has been successfully added !");            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public List<Events> afficherEvents() {
        List<Events> eventsList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM events ORDER BY nbrmaxpart ASC";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3); 
            
            /*while (rs.next()) {
                
                if (rs.getInt("id") != 0 && FindEvent(rs.getInt("id")).getEventdate().isAfter(LocalDate.now()) ) {
                  Events p = FindEvent(rs.getInt("id"));
                  eventsList.add(p);
                }              
                
            }*/
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return eventsList;
    }
    
    /*public Events FindEvent(int id) {

        Events p = new Events();
        try {

            Statement pst = cnx2.createStatement();

            ResultSet rs = pst.executeQuery("SELECT * FROM events WHERE id=" + id + "");

            while (rs.next()) {
                Themes categorie = new Themes();
                categorie.setId(rs.getInt("eventtheme_id"));
                User id_user = new User();
                id_user.setId(rs.getInt("org_id"));
                Date dateaux = rs.getDate("eventdate");
                LocalDate date = dateaux.toLocalDate();
                String nom_event = rs.getString("eventname");
                
                String description = rs.getString("description");
                int capacite_event = rs.getInt("nbr_going");
                int nb_max = rs.getInt("nbrmaxpart");
                String image_event = rs.getString("image_event");
                String location_event = rs.getString("eventaddress");
                
                p.setId(id);
                p.setOrg_id(id_user);
               // p.setEventdate(date);
                p.setEventtheme_id(categorie);
                p.setNbr_going(capacite_event);
                p.setDescription(description);
                p.setImageevent("C:\\xampp\\php\\www\\ArtBox-CrashTest-WEB\\public\\imagesEvent\\"+image_event);
                p.setNbrmaxpart(nb_max);
                p.setEventname(nom_event);
                p.setEventaddress(location_event);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return p;
    }*/
    
    public void supprimerEvent(Events p) {
        try {
            String requete = "DELETE FROM events WHERE id=?";

            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Event successfully deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void modifierEvent(int id, String object, Object obj) {
        try {
            String requete = "UPDATE events SET ? = ? WHERE id = ?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, object);
            pst.setObject(2, obj);
            pst.setInt(3, id);
            String ch = pst.toString().replaceFirst("\'", "");
            String ch2 = ch.replaceFirst("\'", "");
            int pos = ch2.indexOf("UPDATE");
            String ch3 = ch2.substring(pos, ch2.length());
            System.out.println(ch3);
            pst = cnx2.prepareStatement(ch3);
            pst.executeUpdate();
            System.out.println("Event successfully updated !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean CheckEventByName(String name) {

        boolean p = false;
        try {

            Statement pst = cnx2.createStatement();

            ResultSet rs = pst.executeQuery("SELECT * FROM events WHERE eventname='" + name + "'");

            while (rs.next()) {
                 p=true;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;

    }

    
}
