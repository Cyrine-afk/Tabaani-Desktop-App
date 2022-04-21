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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import tabaani.entities.Themes;
import tabaani.services.ThemesCRUD;
import org.controlsfx.control.Notifications;

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
    @FXML
    private Label Control;
    private URL urll;
    private ResourceBundle rbb;
    ThemesCRUD evt = new ThemesCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Control.setVisible(false);
    }    

    @FXML
    private void saveTheme(ActionEvent event) {
        
        try {
            String control ="";
            //controle de saisie    
            if (tfNameTheme.getText() == null || tfNameTheme.getText().trim().isEmpty()) {
                control = "Make sure to fill all the fields";
                Control.setText(control);  
                //notificationShow("Alert!",control);  
                System.out.println("Alert!"+control);
            } else if (evt.CheckThemeByName(tfNameTheme.getText())) {
                control += "\n Theme name already exists !";
                Control.setText(control);
                //notificationShow("Alert!",control); 
                System.out.println("Alert!"+control);
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
        }
        
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
    
    
    public void notificationShow(String title,String message) {
        Notifications notificationBuilder = Notifications.create()
               .title(title).text(message).graphic(null).hideAfter(javafx.util.Duration.seconds(20))
               .position(Pos.CENTER)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {    
                       
                       System.out.println("clicked ON ");
               }});
        notificationBuilder.show();
    }
    
}
