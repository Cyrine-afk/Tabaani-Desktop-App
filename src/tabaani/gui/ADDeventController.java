/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.xml.bind.DatatypeConverter;
import tabaani.entities.Events;
import tabaani.entities.Themes;
import tabaani.entities.User;
import tabaani.services.EventsCRUD;
import tabaani.services.ThemesCRUD;
import tabaani.utils.MyConnection;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import tabaani.utils.JavaMailUtil;

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
    @FXML
    private TableView<Events> tblThemes;
    @FXML
    private TableColumn<Events, String> nameE;
    @FXML
    private TableColumn<Events, String> maxE;
    @FXML
    private TableColumn<Events, String> descE;
    @FXML
    private TableColumn<Events, String> adrE;
    @FXML
    private TableColumn<Events, String> nbrGoingE;
    @FXML
    private TableColumn<Events, String> themeT;
    @FXML
    private TableColumn<Events, String> hostE;
    @FXML
    private TableColumn<Events, String> editCol;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        myLst = CC.afficherThemes();
        
        loadData();
        
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
               
        //List Events
        //loadData();
        
    } 
    
    @FXML
    private void refreshTable() {
        
        try {
            obList.clear();
            
            ResultSet rs = cnx2.createStatement().executeQuery("SELECT * FROM events");
            
            /*DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            dateE.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Events, String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Events, String> param) {
                return new SimpleStringProperty(param.getValue().getEventdate().format(formatters));
            }
        });*/
            
            while (rs.next()){
                obList.add(new Events (
                        rs.getInt("id"),
                        rs.getInt("nbrmaxpart"),
                        rs.getString("imageevent"),
                        rs.getString("eventname"),
                        rs.getString("description"),
                        //2022-03-20,
                        //rs.getDate(query),
                        rs.getString("eventaddress"),
                        rs.getInt("eventtheme_id"),
                        rs.getInt("org_id"),
                        rs.getInt("nbr_going")
                ));
                
                tblThemes.setItems(obList);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ADDthemeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
                
                
                System.out.println(C.getThemename());
                
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
                        C.getId()/*.getId()*/,
                        C2.getId()/*.getId()*/,
                        nbrCPart
                );
                
                
                
                EventsCRUD tcr = new EventsCRUD();
                tcr.ajouterEvent2(t);
            }
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
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
    
    private void loadData() {
        
        refreshTable();
           
        nameE.setCellValueFactory(new PropertyValueFactory<>("eventname"));
        maxE.setCellValueFactory(new PropertyValueFactory<>("nbrmaxpart"));
        descE.setCellValueFactory(new PropertyValueFactory<>("description"));
        adrE.setCellValueFactory(new PropertyValueFactory<>("eventaddress"));
        nbrGoingE.setCellValueFactory(new PropertyValueFactory<>("nbr_going"));
        themeT.setCellValueFactory(new PropertyValueFactory<>("eventtheme_id"));
        hostE.setCellValueFactory(new PropertyValueFactory<>("org_id"));
        
        
        //add cell of button edit 
         Callback<TableColumn<Events, String>, TableCell<Events, String>> cellFoctory = (TableColumn<Events, String> param) -> {
            // make cell containing buttons
            final TableCell<Events, String> cell = new TableCell<Events, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView emailIcon = new FontAwesomeIconView(FontAwesomeIcon.SEND);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        emailIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#33a8ff;"
                        );
                        
                        deleteIcon.setOnMouseClicked((MouseEvent mouseEvent) -> {
                            
                            try {
                                event = tblThemes.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM events WHERE id  ="+event.getId();
                                preparedStatement = cnx2.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(ADDthemeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        });
                                                
                        editIcon.setOnMouseClicked((MouseEvent mouseEvent) -> {
                            
                            event = tblThemes.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/tabaani/gui/UPDATEtheme.fxml"));
                            try {
                                loader.load();    
                            } catch (IOException ex) {
                                Logger.getLogger(ADDthemeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            UPDATEthemeController updateThController = loader.getController();
                            updateThController.setUpdate(true);
                            /*updateThController.setTextField(
                                    event.getId(),
                                    event.getNbrmaxpart(),
                                    event.getDescription(),
                                    event.getEventdate().toString(),
                                    event.getEventaddress(),
                                    event.getNbr_going(),
                                    event.getEventtheme_id(),
                                    event.getOrg_id()
                            );*/
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            
                        });
                        
                        emailIcon.setOnMouseClicked((MouseEvent mouseEvent) -> {
                            
                            try {
                                
                                JavaMailUtil.sendMail("cyrine1409@gmail.com");
                                
                            } catch (Exception ex) {
                                Logger.getLogger(ADDthemeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        });
                        

                        HBox managebtn = new HBox(editIcon, deleteIcon, emailIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(editIcon, new Insets(2, 4, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         
        editCol.setCellFactory(cellFoctory);
        tblThemes.setItems(obList);
        
        
    }

    @FXML
    private void print(MouseEvent event) {
        
        /*String path="";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //j.showSaveDialog(this);
        int x=j.showSaveDialog(this);
        
        if (x==JFileChooser.)*/
    }

    
}
