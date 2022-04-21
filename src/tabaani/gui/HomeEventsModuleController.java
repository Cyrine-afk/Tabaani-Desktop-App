/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class HomeEventsModuleController implements Initializable {

    @FXML
    private ImageView tabaaniImg;  
    @FXML
    private ImageView apolloImg;
    @FXML
    private Button btnEvents;
    @FXML
    private Button btnThemes;
    @FXML
    private Button btnPart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void go_events_menu(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventsMenu.fxml"));
        
        try {
            Parent root = loader.load();
            
            btnEvents.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }

    @FXML
    private void go_themes_menu(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ThemesMenu.fxml"));
        
        try {
            Parent root = loader.load();
            
            btnThemes.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }

    @FXML
    private void go_part_menu(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LISTpartEvent.fxml"));
        
        try {
            Parent root = loader.load();
            
            btnThemes.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
    
}
