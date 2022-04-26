/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import tabaani.entities.Events;
import tabaani.entities.Themes;
import tabaani.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LISTeventsController implements Initializable {

    @FXML
    private ImageView logoImg;
    @FXML
    private Button btnBack;
    @FXML
    private TableView<Events> tblThemes;
    @FXML
    private TableColumn<Events, String> nameE;
    @FXML
    private TableColumn<Events, String> maxE;
    @FXML
    private TableColumn<Events, String> picE;
    @FXML
    private TableColumn<Events, String> descE;
    @FXML
    private TableColumn<Events, String> adrE;
    @FXML
    private TableColumn<Events, String> nbrGoingE;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    
    ObservableList<Events> obList = FXCollections.observableArrayList();
    Connection cnx2;
    String query = null;
    
    @FXML
    private TableColumn<Events, String> dateE = new TableColumn<>("eventdate");;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx2 = MyConnection.getInstance().getCnx();
        
        try {
            ResultSet rs = cnx2.createStatement().executeQuery("SELECT * FROM events");
            
            
           /* while (rs.next()){
        
                obList.add(new Events(
                        rs.getInt("id"),
                        rs.getInt("nbrmaxpart"),
                        rs.getString("imageevent"),
                        rs.getString("eventname"),
                        rs.getString("description"),
                        rs.getDate("eventdate"),
                        rs.getString("eventaddress"),
                        //rs.getObject("eventtheme_id", "themes"),
                        rs.getInt("nbr_going")));
                tblThemes.setItems(obList);
            }
            */
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        nameE.setCellValueFactory(new PropertyValueFactory<>("eventname"));
        maxE.setCellValueFactory(new PropertyValueFactory<>("nbrmaxpart"));
        picE.setCellValueFactory(new PropertyValueFactory<>("imageevent"));
        descE.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateE.setCellValueFactory(new PropertyValueFactory<>("eventdate"));
        adrE.setCellValueFactory(new PropertyValueFactory<>("eventaddress"));
        nbrGoingE.setCellValueFactory(new PropertyValueFactory<>("nbr_going"));
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

    @FXML
    private void go_editT(ActionEvent event) {
    }

    @FXML
    private void go_deleteT(ActionEvent event) {
        
        
    }
    
}
