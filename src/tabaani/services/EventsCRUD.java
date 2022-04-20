/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tabaani.entities.Events;
import tabaani.utils.MyConnection;

/**
 *
 * @author DELL
 */
public class EventsCRUD {
    private Connection cnx;
    
    public void ajouterEvent() {
        try {
            String requete = "INSERT INTO events (eventname,nbrmaxpart,imageevent,description,eventdate,eventaddress,eventtheme_id,org_id,nbr_going) "
                    + "       VALUES ('eventname',10,'imageevent','description','2022-03-20','eventaddress',1,1,3)"; 
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete); //update bata base only request (update, delete or add functions)
            System.out.println("Event successfully added !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void ajouterEvent2(Events E) {
        
    }
    
    public List<Events> afficherEvents() {
        return null;
    }
    
    
    
}
