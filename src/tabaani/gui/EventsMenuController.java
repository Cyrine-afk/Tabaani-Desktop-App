/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class EventsMenuController implements Initializable {

    @FXML
    private ImageView logoImg;
    @FXML
    private Button btnAddE;
    @FXML
    private Button btnListE;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void go_addE(ActionEvent event) {
    }

    @FXML
    private void go_listE(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeEventsModule.fxml"));
        
        try {
            Parent root = loader.load();
            
            btnBack.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
    
}
