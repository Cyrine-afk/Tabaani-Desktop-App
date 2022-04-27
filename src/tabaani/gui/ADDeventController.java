/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.xml.bind.DatatypeConverter;
import tabaani.entities.Events;
import tabaani.entities.Themes;
import tabaani.entities.User;
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
    EventsCRUD evt = new EventsCRUD();
    ObservableList<Events> obList = FXCollections.observableArrayList();
    Connection cnx2 = MyConnection.getInstance().getCnx();
    String query = null;
    EventsCRUD th ;
    Events event = null;
    PreparedStatement preparedStatement = null ;
    private boolean update;
    
    List<Themes> myLst;
    ThemesCRUD CC= new ThemesCRUD();
    
    //Themes ComboBox
    final ObservableList options = FXCollections.observableArrayList();
    final ObservableList<Themes> data = FXCollections.observableArrayList();
    TableView<Themes> table;
    ResultSet rs = null;
    
    //Users ComboBox
    final ObservableList options2 = FXCollections.observableArrayList();
    final ObservableList<User> data2 = FXCollections.observableArrayList();
    TableView<User> table2;
    ResultSet rs2 = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        myLst = CC.afficherThemes();
        
        //ComboBox Themes
        options.clear();
        try {
            String query = "SELECT themename FROM themes ";
            preparedStatement = cnx2.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                options.add(rs.getString("themename"));
            }
            
            preparedStatement.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
        tfThemeEvent.getItems().addAll(options);
        
        //ComboBox Users
        options2.clear();
        try {
            String query = "SELECT login_user FROM user ";
            preparedStatement = cnx2.prepareStatement(query);
            rs2 = preparedStatement.executeQuery();
            
            while(rs2.next()){
                options2.add(rs2.getString("login_user"));
            }
            
            preparedStatement.close();
            rs2.close();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
        tfOrgEvent.getItems().addAll(options2);
               
        
        
    }    
    
    @FXML
    private void saveEvent(ActionEvent event) {
        
        try {
            String control ="";
            //controle de saisie    
            if (tfNameEvent.getText() == null || 
                tfImgEvent.getText().trim().isEmpty() ||
                nfMaxPEvent.getText().trim().isEmpty() ||
                tfDescEvent.getText().trim().isEmpty() ||
                dateEvent.getValue() == null ||
                tfAdrEvent.getText().trim().isEmpty() ||
                tfThemeEvent.getValue().isEmpty() ||
                tfOrgEvent.getValue().isEmpty() ||
                nfNbrGoingEvent.getText().trim().isEmpty() ) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Make sure to fill all the fields", ButtonType.OK);
                a.show();
            } else if (evt.CheckEventByName(tfNameEvent.getText())) {
                 Alert a1 = new Alert(Alert.AlertType.ERROR, "Event name already exists !", ButtonType.OK);
                a1.show();
                tfNameEvent.setStyle("background-color: rgba(255,0,0,0.2);");
            } else {
                String name = tfNameEvent.getText();
                String pic = tfImgEvent.getText();
                
                String Scapacite = nfMaxPEvent.getText();
                int maxPart = DatatypeConverter.parseInt(Scapacite);
                
                String descr = tfDescEvent.getText();
                LocalDate dateE = dateEvent.getValue();
                String adrE  = tfAdrEvent.getText();  
                
                Themes C =new Themes();
                String Categorie = tfThemeEvent.getValue();
                C.setThemename(Categorie);
                
                User C2 =new User();
                String OrgE = tfOrgEvent.getValue();
                C2.setLogin_user(OrgE);                
                
                String SnbrCPart = nfNbrGoingEvent.getText();
                int nbrCPart = DatatypeConverter.parseInt(SnbrCPart);
        
                Events t = new Events(
                        maxPart, 
                        pic,
                        name,
                        descr,
                        dateE,
                        adrE,
                        C,
                        C2,
                        nbrCPart
                );
                EventsCRUD tcr = new EventsCRUD();
                tcr.ajouterEvent2(t);
            }
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
    }
    
    /*public void fillComboBox(){
        options.clear();
        try {
            String query = "SELECT themename FROM themes ";
            preparedStatement = cnx2.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                options.add(rs.getString("themename"));
            }
            
            preparedStatement.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }        
    }*/
    
    
    
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
