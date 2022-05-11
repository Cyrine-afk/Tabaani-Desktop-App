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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import static jdk.nashorn.tools.ShellFunctions.input;
import nl.captcha.Captcha;
import tabaani.entities.Themes;
import tabaani.services.ThemesCRUD;
import org.controlsfx.control.Notifications;
import tabaani.entities.Events;
import tabaani.utils.MyConnection;

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
    @FXML
    private TableView<Themes> tbThemes;
    @FXML
    private TableColumn<Themes, String> nameTh;
    @FXML
    private TableColumn<Themes, String> picTh;
    @FXML
    private TableColumn<Themes, String> editCol;
    @FXML
    private TextField filterField;
    
    private URL urll;
    private ResourceBundle rbb;
    ThemesCRUD evt = new ThemesCRUD();
    ObservableList<Themes> obList = FXCollections.observableArrayList();
    Connection cnx2 = MyConnection.getInstance().getCnx();
    String query = null;
    ThemesCRUD th ;
    Themes theme = null;
    PreparedStatement preparedStatement = null ;
    private boolean update;
    
    //cnx2 = MyConnection.getInstance().getCnx();

       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Control.setVisible(false);
        
        loadData();
        
        /*Captcha captcha = new Captcha.Builder(200, 50)
           .addText()
           .addBackground()
           .addNoise()
           .gimp()
           .addBorder()
           .build(); */
        
        
        /*input.setLayoutX(100D);
        input.setLayoutY(410D);
        input.setPromptText("Rechercher ..");
        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               view.setPredicate(new Predicate<Item<Themes>>() {
                    @Override
                    public boolean test(Item<Themes> t) {

                        boolean flag = t.getValue().getThemename().toLowerCase().contains(newValue.toLowerCase());
                        return flag;
                    }
                });
            }
        });*/
        
    }   
    
    public void notificationShow() {
        Image img = new Image("images/reverifier.png");
                Notifications notificationBuilder = Notifications.create()
                        .title("Theme Added")
                        .text("Theme successfully added to database")
                        .graphic(new ImageView(img)/*null*/)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.CENTER)
                        .onAction((ActionEvent event1) -> {System.out.println("Clicked on notification");});
                notificationBuilder.darkStyle();
                notificationBuilder.show();
    }

    @FXML
    private void refreshTable() {
                
        try {
            obList.clear();
            
            ResultSet rs = cnx2.createStatement().executeQuery("SELECT * FROM themes");
            
            while (rs.next()){
                obList.add(new Themes (
                        rs.getInt("id"),
                        rs.getString("themename"),
                        rs.getString("imagetheme")
                ));
                tbThemes.setItems(obList);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ADDthemeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void saveTheme(ActionEvent event) {
        
        try {
            String control ="";
            //controle de saisie    
            if (tfNameTheme.getText() == null || tfNameTheme.getText().trim().isEmpty()) {
                control = "Make sure to fill all the fields";
                Control.setText(control);  
                Alert a = new Alert(Alert.AlertType.ERROR, "Make sure to fill all the fields", ButtonType.OK);
                a.show();
                //notificationShow("Alert!",control);  
                System.out.println("Alert!"+control);
            } else if (evt.CheckThemeByName(tfNameTheme.getText())) {
                control += "\n Theme name already exists !";
                Control.setText(control);
                 Alert a1 = new Alert(Alert.AlertType.ERROR, "Theme name already exists !", ButtonType.OK);
                a1.show();
                //notificationShow("Alert!",control); 
                System.out.println("Alert!"+control);
                //tfNameTheme.setStyle("background-color: rgba(255,0,0,0.2);");
            } else {
                String name = tfNameTheme.getText();
                String pic = tfPictureEvent.getText();
        
                Themes t = new Themes(name, pic);
                ThemesCRUD tcr = new ThemesCRUD();
                tcr.ajouterTheme2(t);
                
                notificationShow();
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
        
        nameTh.setCellValueFactory(new PropertyValueFactory<>("themename"));
        picTh.setCellValueFactory(new PropertyValueFactory<>("imagetheme"));
        
        
        //add cell of button edit 
         Callback<TableColumn<Themes, String>, TableCell<Themes, String>> cellFoctory = (TableColumn<Themes, String> param) -> {
            // make cell containing buttons
            final TableCell<Themes, String> cell = new TableCell<Themes, String>() {
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
                        
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                theme = tbThemes.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM themes WHERE id  ="+theme.getId();
                                preparedStatement = cnx2.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(ADDthemeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        });
                               
                                                
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            theme = tbThemes.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/tabaani/gui/UPDATEtheme.fxml"));
                            try {
                                loader.load();    
                            } catch (IOException ex) {
                                Logger.getLogger(ADDthemeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            UPDATEthemeController updateThController = loader.getController();
                            updateThController.setUpdate(true);
                            updateThController.setTextField(
                                    theme.getId(),
                                    theme.getThemename(),
                                    theme.getImagetheme()
                            );
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            
                        });
                        

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         
        editCol.setCellFactory(cellFoctory);
        tbThemes.setItems(obList);
        
        //Research 
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Themes> filteredData = new FilteredList<>(obList, b -> true);
        // Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(theme -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (theme.getThemename().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches theme name.
                                }
				else  
				    	 return false; // Does not match.
			});
		});
        // Wrap the FilteredList in a SortedList. 
	SortedList<Themes> sortedData = new SortedList<>(filteredData);
		
	// Bind the SortedList comparator to the TableView comparator, otherwise, sorting the TableView would have no effect.
	sortedData.comparatorProperty().bind(tbThemes.comparatorProperty());
		
	// Add sorted (and filtered) data to the table.
	tbThemes.setItems(sortedData);
        
        
    }

    
    
}