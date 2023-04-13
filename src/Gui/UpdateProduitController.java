/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Service.ProduitServise;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AA
 */
public class UpdateProduitController implements Initializable {

    @FXML
    private TextField tt1;
    @FXML
    private TextField tt2;
    @FXML
    private TextField tt3;
    @FXML
    private TextField tt4;
@FXML
    private Button btnmodp;
    public String getNom() {
        return tt1.getText();
    }

    public String getDescription() {
        return tt2.getText();
    }

    public int getPrix() {
        String prixStr = tt3.getText();
        return Integer.parseInt(prixStr);
    }
    public String getImage() {
        return tt4.getText();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void setFields(String nom, String description, int prix, String imagePath) {
    tt1.setText(nom);
    tt2.setText(description);
    tt3.setText(Integer.toString(prix));
    if (imagePath != null) {
        Image image = new Image(imagePath);
       // image.setImage(image);
    }
}
    @FXML
    private void save(ActionEvent event) {
    }
  
}
