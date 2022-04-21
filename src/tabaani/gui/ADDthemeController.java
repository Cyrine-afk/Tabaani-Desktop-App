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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import tabaani.entities.Themes;
import tabaani.services.ThemesCRUD;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ADDthemeController implements Initializable {

    @FXML
    private TextField tfNameTheme;
    @FXML
    private TextField tfPictureEvent;
    @FXML
    private Button btnSave;
    @FXML
    private ImageView logoImg;
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
    private void saveTheme(ActionEvent event) {
        String name = tfNameTheme.getText();
        String pic = tfPictureEvent.getText();
        
        Themes t = new Themes(name, pic);
        ThemesCRUD tcr = new ThemesCRUD();
        tcr.ajouterTheme2(t);
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
    
}
