/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import java.sql.SQLException;
import Entite.Coach;
import Service.CoachService;
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
 * @author dorraayari
 */
public class DetailController implements Initializable {
    
@FXML
    private ImageView imageView;
    @FXML
    private Label labelnom;
    @FXML
    private ListView<Coach> listview;
 private String imagePath;
 
  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
}

    public void setLabelnom(String labelnom){
        this.labelnom.setText(labelnom);
    }



@Override
public void initialize(URL url, ResourceBundle rb) {
    // TODO
    CoachService ps = new CoachService();
    List<Coach> listp = ps.readAll();

    ObservableList<Coach> obs = FXCollections.observableArrayList(listp);

    listview.setItems(obs);

    listview.setCellFactory(lv -> new CoachListCell());
    // Set the image
        if (imagePath != null) {
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
       

}

   


    
private class CoachListCell extends ListCell<Coach> {
    private final Label nomLabel = new Label();
    private final Label descriptionLabel = new Label();
    private final Label weightLabel = new Label();
    private final Label heightLabel = new Label();
    private final Label occupationLabel = new Label();
    private final Label ageLabel = new Label();
    private final ImageView imageView = new ImageView();
    private final Button editButton = new Button();
    private final Button deleteButton = new Button();
    private final HBox hbox = new HBox(imageView, new VBox(nomLabel, descriptionLabel, weightLabel, heightLabel, occupationLabel, ageLabel), deleteButton, editButton);

    public CoachListCell() {
        super();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(10);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
 editButton.setTooltip(new Tooltip("Modifier"));
Image editImage = new Image("file:/Users/dorraayari/Downloads/resLogoAndroid/delete.png");
ImageView editImageView = new ImageView(editImage);
editImageView.setFitHeight(30);
editImageView.setFitWidth(30);
editButton.setGraphic(editImageView);

        deleteButton.setTooltip(new Tooltip("Supprimer"));
Image deleteImage = new Image("file:/Users/dorraayari/Downloads/resLogoAndroid/edit.png");
ImageView deleteImageView = new ImageView(deleteImage);
deleteImageView.setFitHeight(30);
deleteImageView.setFitWidth(30);
deleteButton.setGraphic(deleteImageView);


        editButton.setOnAction(event -> {
            Coach selectedCoach = getItem();
            if (selectedCoach != null) {
                System.out.println("Selected coach ID: " + selectedCoach.getId());

                // Open a new window or dialog to edit the coach's information
                EditCoachController dialog = new EditCoachController(selectedCoach);
                Optional<Coach> result = dialog.showAndWait();
                if (result.isPresent()) {
                    // Update the coach's information in the database and refresh the list view
                    CoachService service = new CoachService();
                    service.update(result.get());

                    // Refresh the list view with updated data from the database
                    List<Coach> listp = service.readAll();
                    ObservableList<Coach> obs = FXCollections.observableArrayList(listp);
                    listview.setItems(obs);
                }
            }
        });

        deleteButton.setOnAction(event -> {
            Coach selectedCoach = getItem();
            if (selectedCoach != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce coach ?");
                alert.setContentText(selectedCoach.getNom());

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    CoachService service = new CoachService();
                    service.delete(selectedCoach.getId());
                    listview.getItems().remove(selectedCoach);
                }
            }
        });
    }

    @Override
    protected void updateItem(Coach coach, boolean empty) {
        setText(null);
        setGraphic(null);
        super.updateItem(coach, empty);

        if (empty || coach == null) {
            setText(null);
            setGraphic(null);
        } else {
            nomLabel.setText("Nom: " + coach.getNom());
            descriptionLabel.setText("Description: " + coach.getDescription());
            weightLabel.setText("Poids: " + coach.getWeight());
            heightLabel.setText("Taille: " + coach.getHeight());
            occupationLabel.setText("Occupation: " + coach.getOccupation());
            ageLabel.setText("Age: " + coach.getAge());
            deleteButton.setTooltip(new Tooltip("Supprimer"));

            // Set the image of the ImageView
            String imagePath = coach.getImage();
            if (imagePath != null) {
                Image image = new Image("file:/Users/dorraayari/Downloads/resLogoAndroid/" + new File(imagePath).getName());
                imageView.setImage(image);
            }

            setGraphic(hbox);
        }
    }
}


}
