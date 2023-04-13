/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entite.Produit;
import Service.ProduitServise;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author AA
 */
public class AddProduitController implements Initializable {

    @FXML
    private TextField t2;
    @FXML
    private TextField t1;
    @FXML
    private TextField t3;
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

    /*@FXML
    private void Ajouter(ActionEvent event)  throws IOException {
        ProduitServise pt = new ProduitServise();
         image.setImage(new Image(imagePath));
    Produit produit = new Produit(t1.getText(), t2.getText(),
                         Integer.parseInt(t3.getText()),
                            imagePath);
    
        pt.insertPst(new Produit(t1.getText(),
                t2.getText(),
                Integer.parseInt(t3.getText())
                ,imagePath));
      FXMLLoader loader=new
         FXMLLoader(getClass().getResource("DetailP.fxml"));
      
        Parent root=loader.load();
        t1.getScene().setRoot(root);
        
      

    }*/
     
   @FXML
private void Ajouter(ActionEvent event) throws IOException {
    ProduitServise pt = new ProduitServise();

    // Check that all text fields are filled in
    if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR, "Please fill in all fields");
        alert.showAndWait();
        return;
    }

    // Check that the price field contains a valid integer value
    int prix;
    try {
        prix = Integer.parseInt(t3.getText());
    } catch (NumberFormatException e) {
        Alert alert = new Alert(AlertType.ERROR, "Price must be an integer value");
        alert.showAndWait();
        return;
    }

    // Add the new product
    pt.insertPst(new Produit(t1.getText(), t2.getText(), prix, imagePath));
    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailP.fxml"));
    Parent root = loader.load();
    t1.getScene().setRoot(root);
}

    }

   
    

