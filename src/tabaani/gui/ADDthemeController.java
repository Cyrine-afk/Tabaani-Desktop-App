/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    
}
