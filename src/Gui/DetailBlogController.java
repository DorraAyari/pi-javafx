/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.sql.SQLException;
import Entite.Blog;
import Service.BlogService;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author EMNA
 */
public class DetailBlogController implements Initializable {

    @FXML
    private Label labelnom;
    @FXML
    private ListView<Blog> listview;
    @FXML
    private ImageView imageView;
    
    private String imagePath;
    
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
          BlogService ps = new BlogService();
    List<Blog> listp = ps.readAll();

    ObservableList<Blog> obs = FXCollections.observableArrayList(listp);
    
    listview.setItems(obs);

    listview.setCellFactory(lv -> new BlogListCell());
    // Set the image
        if (imagePath != null) {
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
     
    }    
        
private class BlogListCell extends ListCell<Blog> {
    private final Label nomLabel = new Label();
    private final Label descriptionLabel = new Label();
    private final Label moredescreptionLabel = new Label();
    private final Label sloganLabel = new Label();
    private final ImageView imageView = new ImageView();
    private final Button editButton = new Button();
    private final Button deleteButton = new Button();
    private final HBox hbox = new HBox(imageView, new VBox(nomLabel, descriptionLabel, moredescreptionLabel, sloganLabel), deleteButton, editButton);

    public BlogListCell() {
        super();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(10);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
editButton.setTooltip(new Tooltip("Modifier"));
Image editImage = new Image("file:/C:/Users/EMNA/Downloads/image/kisspng-computer-icons-download-clip-art-update-icon-5b4d1fcee4d926.5261803715317810709374-removebg-preview.png");
ImageView editImageView = new ImageView(editImage);
editImageView.setFitHeight(30);
editImageView.setFitWidth(30);
editButton.setGraphic(editImageView);



        deleteButton.setTooltip(new Tooltip("Supprimer"));
        Image supprimerImage = new Image("file:/C:/Users/EMNA/Downloads/image/delete-dust-bin-erase-eraser-remove-icon-1.png");
ImageView supprimerImageView = new ImageView(supprimerImage);
supprimerImageView.setFitHeight(30);
supprimerImageView.setFitWidth(30);
deleteButton.setGraphic(supprimerImageView);



        editButton.setOnAction(event -> {
            Blog selectedBlog = getItem();
            if (selectedBlog != null) {
                System.out.println("Selected blog ID: " + selectedBlog.getId());

                // Open a new window or dialog to edit the blog's information
                EditBlogController dialog = new EditBlogController(selectedBlog);
                Optional<Blog> result = dialog.showAndWait();
                if (result.isPresent()) {
                    // Update the blog's information in the database and refresh the list view
                    BlogService service = new BlogService();
                    service.update(result.get());

                    // Refresh the list view with updated data from the database
                    List<Blog> listp = service.readAll();
                    ObservableList<Blog> obs = FXCollections.observableArrayList(listp);
                    listview.setItems(obs);
                }
            }
        });

        deleteButton.setOnAction(event -> {
            Blog selectedBlog = getItem();
            if (selectedBlog != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce blog ?");
                alert.setContentText(selectedBlog.getNom());

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    BlogService service = new BlogService();
                    service.delete(selectedBlog.getId());
                    listview.getItems().remove(selectedBlog);
                }
            }
        });
    }

    @Override
    protected void updateItem(Blog blog, boolean empty) {
        setText(null);
        setGraphic(null);
        super.updateItem(blog, empty);

        if (empty || blog == null) {
            setText(null);
            setGraphic(null);
        } else {
            nomLabel.setText("Nom: " + blog.getNom());
            descriptionLabel.setText("Description: " + blog.getDescription());
            moredescreptionLabel.setText("More Description: " + blog.getMoredescription());
            sloganLabel.setText("Slogan: " + blog.getSlogan());
            deleteButton.setTooltip(new Tooltip("Supprimer"));

            // Set the image of the ImageView
            String imagePath = blog.getImage();
            
            if (imagePath != null) {
                Image image = new Image("file:/C:/Users/EMNA/" + new File(imagePath).getName());
                imageView.setImage(image);
            }

            setGraphic(hbox);
        }
    }
}
    
}
