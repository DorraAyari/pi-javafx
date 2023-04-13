/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entite.Produit;
import Service.ProduitServise;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AA
 */
public class DetailPController implements Initializable {
 
 
    @FXML
    private Label labelnom;
    @FXML
    private ListView<Produit> listview;
    @FXML
    private ImageView image;
    private String imagePath;
    @FXML
    private Button supbtn;
    @FXML
    private Button modbtn;

     public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
}

    public void setLabelnom(String labelnom){
        this.labelnom.setText(labelnom);
    }
    
     

    
    
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ProduitServise pt = new ProduitServise();
         List<Produit> listp = pt.readAll();

    ObservableList<Produit> obs = FXCollections.observableArrayList(listp);

    listview.setItems(obs);

    //listview.setCellFactory(lv -> new PtoduitList());
    // Set the image
        if (imagePath != null) {
            Image image = new Image(imagePath);
          //  image.setImage(image);
        }
            
                }    

    @FXML
    private void supprimer(ActionEvent event) {
      // Get the selected item from the listview
    Produit selectedProduit = listview.getSelectionModel().getSelectedItem();
    
    // Remove the selected item from the ObservableList
    listview.getItems().remove(selectedProduit);
    
    // Delete the selected item from the database
    ProduitServise pt = new ProduitServise();
    pt.delete(selectedProduit.getId());
    
    // Update the listview
    listview.setItems(listview.getItems());
}

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        
        
        // Get the selected item from the listview
    Produit selectedProduit = listview.getSelectionModel().getSelectedItem();
    
    // Open the modification form in a new window or dialog
    FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProduit.fxml"));
    Parent root = loader.load();
    UpdateProduitController controller = loader.getController();
    
    // Prefill the fields with the existing details of the selected item
    controller.setFields(selectedProduit.getNom(), selectedProduit.getDescription(), selectedProduit.getPrix(),selectedProduit.getImage());
    
    // Show the modification form
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.showAndWait();
    
    // When the modification form is closed, retrieve the modified values from the form fields
    String nom = controller.getNom();
    String Description = controller.getDescription();
    int prix = controller.getPrix();
    String Image = controller.getImage();
    
    // Update the selected item with the modified values and save the changes to the database
    selectedProduit.setNom(nom);
    selectedProduit.setDescription(Description);
    selectedProduit.setPrix(prix);
    selectedProduit.setImage(Image);
    ProduitServise pt = new ProduitServise();
    pt.update(selectedProduit);
    
    // Update the listview with the modified item
    ObservableList<Produit> items = listview.getItems();
    int index = items.indexOf(selectedProduit);
    items.set(index, selectedProduit);
    listview.setItems(items);
    }
    
}
