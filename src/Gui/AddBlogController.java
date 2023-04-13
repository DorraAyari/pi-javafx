/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entite.Blog;
import Service.BlogService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author EMNA
 */
public class AddBlogController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextField sloganField;
    @FXML
    private Button browseField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextArea moredescreptionField;
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
    private void ajouterBlog(ActionEvent event) throws IOException {
         BlogService pss = new BlogService();

       image.setImage(new Image(imagePath));

       Blog blog = new Blog(nomField.getText(),descriptionField.getText(), moredescreptionField.getText(),
                            sloganField.getText(), imagePath);
    
        pss.insertPst(new Blog(nomField.getText(),
                descriptionField.getText(),
                moredescreptionField.getText(),
                sloganField.getText(),
                imagePath));
      FXMLLoader loader=new
         FXMLLoader(getClass().getResource("DetailBlog.fxml"));
      
        Parent root=loader.load();
        nomField.getScene().setRoot(root);
        
        DetailBlogController dc=loader.getController();
        dc.setLabelnom(nomField.getText());
        dc.setImagePath(imagePath);
    }

    
}
