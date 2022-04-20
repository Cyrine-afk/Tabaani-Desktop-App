/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import tabaani.entities.Events;
import tabaani.entities.User;
import tabaani.utils.MyConnection;

/**
 *
 * @author DELL
 */
public class ParticipateEventCRUD {
    
    Connection cnx2;
    
    public ParticipateEventCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterParticipant(User p, Events e) {
        String req = "INSERT INTO participate_event (event_id,user_id)" 
                + "VALUES (?,?)";
        try {
            PreparedStatement ste = cnx2.prepareStatement(req);
            ste.setInt(1, p.getId());
            ste.setInt(2, e.getId());
          
            ste.executeUpdate();
            System.out.println("Participant ajoutée");

        } catch (SQLException ex) {
            System.out.println("Error adding a participant !");
            System.out.println(ex.getMessage());

        }
        String requete = "UPDATE events SET nbr_going = nbr_going+1 WHERE id = ?";
        try {
            PreparedStatement ste = cnx2.prepareStatement(requete);
            ste.setInt(1, e.getId());

            ste.executeUpdate();
            System.out.println("Capacity --");

        } catch (SQLException ex) {
            System.out.println("Error adding a participant - Capacity");
            System.out.println(ex.getMessage());

        }

    }

    
}
