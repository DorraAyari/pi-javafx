/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entite.Coach;
import Service.CoachService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author dorraayari
 */
public class AddCoachController implements Initializable {


    @FXML
    private TextField descriptionField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField occupationField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField ageField;
@FXML
private ImageView image;

private String imagePath = "";

    /**
     * Initializes the controller class.
     */



@Override
public void initialize(URL url, ResourceBundle rb) {
    image.setOnMouseClicked(event -> {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            
            imagePath = selectedFile.toURI().toString();
            image.setImage(new Image(imagePath));
        }
    });
}

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        CoachService ps = new CoachService();

image.setImage(new Image(imagePath));

    Coach coach = new Coach(nomField.getText(), descriptionField.getText(), occupationField.getText(),
                            weightField.getText(), heightField.getText(), Integer.parseInt(ageField.getText()),
                            imagePath);
    
        ps.insertPst(new Coach(nomField.getText(),
                descriptionField.getText(),
                occupationField.getText(),
                weightField.getText(),
                heightField.getText(),
                Integer.parseInt(ageField.getText()),imagePath));
      FXMLLoader loader=new
         FXMLLoader(getClass().getResource("Detail.fxml"));
      
        Parent root=loader.load();
        nomField.getScene().setRoot(root);
        
        DetailController dc=loader.getController();
        dc.setLabelnom(nomField.getText());
        dc.setImagePath(imagePath);

    }



}
