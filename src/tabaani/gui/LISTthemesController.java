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
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LISTthemesController implements Initializable {

    @FXML
    private ImageView logoImg;
    @FXML
    private Button btnBack;
    @FXML
    private TableView<?> tblThemes;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ThemesMenu.fxml"));
        
        try {
            Parent root = loader.load();
            
            btnBack.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }

    @FXML
    private void go_editT(ActionEvent event) {
    }

    @FXML
    private void go_deleteT(ActionEvent event) {
    }
    
}
