/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tabaani.entities.Themes;
import tabaani.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class UPDATEthemeController implements Initializable {

    @FXML
    private TextField tfNameTheme;
    @FXML
    private TextField tfPictureEvent;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    
    Connection cnx2 = MyConnection.getInstance().getCnx();
    String query = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Themes theme = null;
    private boolean update;
    int themeId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private void save(MouseEvent event) {
        
        String themename = tfNameTheme.getText();
        String imagetheme = tfPictureEvent.getText();
        
        if (themename.isEmpty() || imagetheme.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Make sure to fill all the fields", ButtonType.OK);
                a.show();
        } else {
            getQuery();
            insert();
            clean();

        }
    }

    @FXML
    private void clean() {
        
        tfNameTheme.setText(null);
        tfPictureEvent.setText(null);
        
    }
    
    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO themes (name, birth) VALUES (?,?)";

        }else{
            query = "UPDATE themes SET themename = ?, imagetheme = ? WHERE id = '"+themeId+"'";
        }

    }
    
    private void insert() {

        try {

            preparedStatement = cnx2.prepareStatement(query);
            preparedStatement.setString(1, tfNameTheme.getText());
            preparedStatement.setString(2, tfPictureEvent.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    void setTextField(int id, String themename, String imagetheme) {

       themeId = id;
       tfNameTheme.setText(themename);
       tfPictureEvent.setText(imagetheme);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    
}
