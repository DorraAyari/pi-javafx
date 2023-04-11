/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entite.Coach;
import java.awt.Insets;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
 * @author dorraayari
 */
class EditCoachController extends Dialog<Coach> {

    private final TextField nomField = new TextField();
    private final TextField descriptionField = new TextField();
    private final TextField occupationField = new TextField();
    private final TextField weightField = new TextField();
    private final TextField heightField = new TextField();
    private final TextField imageField = new TextField();
    private final TextField ageField = new TextField();

    private final Button selectImageButton = new Button("Sélectionner une image");
    private final ImageView imageView = new ImageView();
    private final FileChooser fileChooser = new FileChooser();

    public EditCoachController(Coach coach) {

        setTitle("Modifier un coach");
        setHeaderText("Modifiez les informations du coach et cliquez sur Enregistrer");

        // Set the initial values of the fields to the coach's information
        nomField.setText(coach.getNom());
        descriptionField.setText(coach.getDescription());
        occupationField.setText(coach.getOccupation());
        weightField.setText(coach.getWeight());
        heightField.setText(coach.getHeight());
        ageField.setText(String.valueOf(coach.getAge()));
        imageView.setImage(new Image(coach.getImage()));

        // Add the fields to the dialog
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
grid.setPrefSize(500, 500);

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Occupation:"), 0, 2);
        grid.add(occupationField, 1, 2);
        grid.add(new Label("Poids:"), 0, 3);
        grid.add(weightField, 1, 3);
        grid.add(new Label("Taille:"), 0, 4);
        grid.add(heightField, 1, 4);
        grid.add(new Label("Âge:"), 0, 5);
        grid.add(ageField, 1, 5);
        grid.add(new Label("Image:"), 0, 6);
        grid.add(selectImageButton, 1, 6);
     grid.add(imageView, 2, 6, 1, 2);
       

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
                Coach editedCoach = new Coach(
                        nomField.getText(),
                        descriptionField.getText(),
                        occupationField.getText(),
                        weightField.getText(),
                        heightField.getText(),
                        Integer.parseInt(ageField.getText()),
                        coach.getId(),
                        imageField.getText()
                );
                return editedCoach;
            }
            return null;
        });
    }

}
