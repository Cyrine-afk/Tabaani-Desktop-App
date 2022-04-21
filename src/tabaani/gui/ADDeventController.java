/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tabaani.entities.Events;
import tabaani.entities.Themes;
import tabaani.services.EventsCRUD;
import tabaani.services.ThemesCRUD;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ADDeventController implements Initializable {

    @FXML
    private TextField tfNameEvent;
    @FXML
    private Button btnSave;
    @FXML
    private ImageView logoImg;
    @FXML
    private Button btnBack;
    @FXML
    private TextField tfImgEvent;
    @FXML
    private TextField nfMaxPEvent;
    @FXML
    private TextArea tfDescEvent;
    @FXML
    private DatePicker dateEvent;
    @FXML
    private TextField tfAdrEvent;
    @FXML
    private ComboBox<String> tfThemeEvent = new ComboBox<>();
    @FXML
    private TextField nfNbrGoingEvent;
    @FXML
    private ComboBox<String> tfOrgEvent = new ComboBox<>();
    @FXML
    private AnchorPane anchor;
    List<Themes> myLst;
    ThemesCRUD CC= new ThemesCRUD();
    int i=0;
    @FXML
    private Label Control;
    private URL urll;
    private ResourceBundle rbb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Control.setVisible(false);
        tfOrgEvent.getItems().addAll("En ligne", "Exposé", "Festival", "Formation", "Autres");
        myLst = CC.afficherThemes();
        for (i=0;i<myLst.size();i++) { 
            tfThemeEvent.getItems().add(myLst.get(i).getThemename());
        }
        
    }    
    
    @FXML
    private void saveEvent(ActionEvent event) {
        try {
            
            if (i>0) {
                initialize(urll, rbb);
            }
            i++;
            System.out.println(i);
            Control.setVisible(false);
            String control = "";
            
            //Controle de saisie    
            
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
    
    @FXML
    private void back(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventsMenu.fxml"));
        
        try {
            Parent root = loader.load();
            
            btnBack.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }

    
}
