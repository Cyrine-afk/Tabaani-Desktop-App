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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import tabaani.entities.Themes;
import tabaani.utils.MyConnection;

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
    private TableView<Themes> tblThemes;
    @FXML
    private TableColumn<Themes, String> idTh;
    @FXML
    private TableColumn<Themes, String> nameTh;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    
    ObservableList<Themes> obList = FXCollections.observableArrayList();
    Connection cnx2;
    String query = null;
    @FXML
    private TableColumn<Themes, String> picTh;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cnx2 = MyConnection.getInstance().getCnx();
        
        try {
            ResultSet rs = cnx2.createStatement().executeQuery("SELECT * FROM themes");
            
            while (rs.next()){
                obList.add(new Themes (
                        rs.getInt("id"),
                        rs.getString("themename"),
                        rs.getString("imagetheme")
                ));
                tblThemes.setItems(obList);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        idTh.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTh.setCellValueFactory(new PropertyValueFactory<>("themename"));
        picTh.setCellValueFactory(new PropertyValueFactory<>("imagetheme"));
    
        
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
