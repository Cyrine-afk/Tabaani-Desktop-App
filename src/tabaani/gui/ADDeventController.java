/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import tabaani.utils.MyConnection;

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
    
    private URL urll;
    private ResourceBundle rbb;
    ThemesCRUD evt = new ThemesCRUD();
    ObservableList<Events> obList = FXCollections.observableArrayList();
    Connection cnx2 = MyConnection.getInstance().getCnx();
    String query = null;
    EventsCRUD th ;
    Events event = null;
    PreparedStatement preparedStatement = null ;
    private boolean update;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //loadData();
        
    }    
    
    @FXML
    private void saveEvent(ActionEvent event) {
        
        /*try {
            String control ="";
            //controle de saisie    
            if (tfNameEvent.getText() == null || 
                tfImgEvent.getText().trim().isEmpty() ||
                nfMaxPEvent.getInt().trim().isEmpty() ||
                tfImgEvent.getText().trim().isEmpty() ||
                tfImgEvent.getText().trim().isEmpty() ||
                tfImgEvent.getText().trim().isEmpty() ||
                tfImgEvent.getText().trim().isEmpty() ||
                tfImgEvent.getText().trim().isEmpty() ||
                
                ) {
                control = "Make sure to fill all the fields";
                Control.setText(control);  
                Alert a = new Alert(Alert.AlertType.ERROR, "Make sure to fill all the fields", ButtonType.OK);
                a.show();
            } else if (evt.CheckThemeByName(tfNameTheme.getText())) {
                control += "\n Theme name already exists !";
                Control.setText(control);
                 Alert a1 = new Alert(Alert.AlertType.ERROR, "Theme name already exists !", ButtonType.OK);
                a1.show();
                tfNameTheme.setStyle("background-color: rgba(255,0,0,0.2);");
            } else {
                String name = tfNameTheme.getText();
                String pic = tfPictureEvent.getText();
        
                Themes t = new Themes(name, pic);
                ThemesCRUD tcr = new ThemesCRUD();
                tcr.ajouterTheme2(t);
            }
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
        }*/
        
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
