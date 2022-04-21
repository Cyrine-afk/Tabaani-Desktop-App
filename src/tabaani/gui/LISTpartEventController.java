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
import tabaani.entities.ParticipateEvent;
import tabaani.entities.Themes;
import tabaani.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LISTpartEventController implements Initializable {

    @FXML
    private ImageView logoImg;
    @FXML
    private Button btnBack;
    @FXML
    private TableColumn<ParticipateEvent, String> eventP;
    @FXML
    private TableColumn<ParticipateEvent, String> UserP;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<ParticipateEvent, String> idP;
    @FXML
    private TableView<ParticipateEvent> tblPartE;
    
    ObservableList<ParticipateEvent> obList = FXCollections.observableArrayList();
    Connection cnx2;
    String query = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx2 = MyConnection.getInstance().getCnx();
        
        try {
            ResultSet rs = cnx2.createStatement().executeQuery("SELECT * FROM participate_event");
            
            while (rs.next()){
                obList.add(new ParticipateEvent(
                        rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getInt("user_id")
                ));
                tblPartE.setItems(obList);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        idP.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventP.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        UserP.setCellValueFactory(new PropertyValueFactory<>("user_id"));
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

    @FXML
    private void go_editT(ActionEvent event) {
    }

    @FXML
    private void go_deleteT(ActionEvent event) {
    }
    
}
