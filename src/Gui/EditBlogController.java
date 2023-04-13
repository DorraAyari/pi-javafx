/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entite.Blog;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 *
 * @author EMNA
 */
public class EditBlogController extends Dialog<Blog>{
    private final TextField nomField = new TextField();
    private final TextField descriptionField = new TextField();
    private final TextField moredescreptionField = new TextField();
    private final TextField sloganField = new TextField();
    
    private final TextField imageField = new TextField();
    

    private final Button selectImageButton = new Button("Sélectionner une image");
    private final ImageView imageView = new ImageView();
    private final FileChooser fileChooser = new FileChooser();
    
    
    
      public EditBlogController(Blog blog) {

        setTitle("Modifier un blog");
        setHeaderText("Modifiez les informations du blog et cliquez sur Enregistrer");

        // Set the initial values of the fields to the blog's information
        nomField.setText(blog.getNom());
        descriptionField.setText(blog.getDescription());
        moredescreptionField.setText(blog.getMoredescription());
        sloganField.setText(blog.getSlogan());
        imageView.setImage(new Image(blog.getImage()));

        // Add the fields to the dialog
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
grid.setPrefSize(500, 500);

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        
        grid.add(new Label("moredescreption:"), 0, 2);
        grid.add(moredescreptionField, 1, 2);
        
        grid.add(new Label("slogan:"), 0, 3);
        grid.add(sloganField, 1, 3);
        
        grid.add(new Label("Image:"), 0, 4);
        grid.add(selectImageButton, 1, 4);
      
     grid.add(imageView, 2, 4, 1, 2);
       

        selectImageButton.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(getOwner());
            if (file != null) {
                String imagePath = file.toURI().toString();
                imageField.setText(imagePath);
                imageView.setImage(new Image(imagePath));
            }
        });

        getDialogPane().setContent(grid);

        // Add buttons to the dialog
        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);

       
        setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Blog editedBlog = new Blog(
                        nomField.getText(),
                        descriptionField.getText(),
                        moredescreptionField.getText(),
                        sloganField.getText(),
                        
                        blog.getId(),
                        imageField.getText()
                );
                return editedBlog;
            }
            return null;
        });
    }
}
