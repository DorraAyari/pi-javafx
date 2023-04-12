/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entite.Reclamation;
import Service.ServiceReclamation;
import Util.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ReclamationFXMLController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField message;
    @FXML
    private TableView<Reclamation> TableReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> idT;
    @FXML
    private TableColumn<Reclamation, String> NomT;
    @FXML
    private TableColumn<Reclamation, String> PrenomT;
    @FXML
    private TableColumn<Reclamation, String> EmailT;
    @FXML
    private TableColumn<Reclamation, String> MessageT;

        
    private final ObservableList<Reclamation> data = FXCollections.observableArrayList();

        
    private Statement ste;
    private Connection con;
    ServiceReclamation sr = new ServiceReclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            Aff();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

           
    public void Aff() throws SQLException {
                        try {
            con = DataSource.getInstance().getCnx();
            ste = con.createStatement();
            data.clear();

            ResultSet res = ste.executeQuery("select * from Reclamation");
            while(res.next()){
                Reclamation f=new Reclamation(res.getInt(1), res.getString("nom"),res.getString("prenom"), res.getString("email"), res.getString("message"));
                data.add(f);
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
            idT.setCellValueFactory(new PropertyValueFactory<>("id"));
            NomT.setCellValueFactory(new PropertyValueFactory<>("nom"));
            PrenomT.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            EmailT.setCellValueFactory(new PropertyValueFactory<>("email"));
            MessageT.setCellValueFactory(new PropertyValueFactory<>("message"));
            
            TableReclamation.setItems(data);
            TableReclamation.setEditable(true);
            NomT.setCellFactory(TextFieldTableCell.forTableColumn());
            PrenomT.setCellFactory(TextFieldTableCell.forTableColumn());
            MessageT.setCellFactory(TextFieldTableCell.forTableColumn());
            EmailT.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    
        
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 2;
        //return !T.getText().isEmpty();
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
                String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.getText().matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Format email incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un email valide !");
            alert.showAndWait();
        }
        else
        {
                    if(Validchamp(nom) && Validchamp(prenom)  && Validchamp(message))
        {

         Reclamation r = new Reclamation(nom.getText(), prenom.getText() , email.getText(),message.getText());
         sr.insert(r);
   
        Aff();
             nom.setText("");
             prenom.setText("");
             email.setText("");
             message.setText("");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouté");
        alert.setHeaderText(null);
        alert.setContentText("Ajouté avec succès!");

        alert.showAndWait();

        }
        else
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Verifiez les champs!");

        alert.showAndWait();
        }   
        }

    }

    @FXML
    private void Change_Nom(TableColumn.CellEditEvent event) {
                
        Reclamation tabRec = TableReclamation.getSelectionModel().getSelectedItem();
        tabRec.setNom(event.getNewValue().toString());
        sr.update(tabRec);
               
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Modifier");
        alert.setHeaderText(null);
        alert.setContentText("Modifier avec succès!");

        alert.showAndWait();

    }

    @FXML
    private void Change_Prenom(TableColumn.CellEditEvent event) {
               
        Reclamation tabRec = TableReclamation.getSelectionModel().getSelectedItem();
        tabRec.setPrenom(event.getNewValue().toString());
        sr.update(tabRec);
               
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Modifier");
        alert.setHeaderText(null);
        alert.setContentText("Modifier avec succès!");

        alert.showAndWait();
    }

    @FXML
    private void Change_Email(TableColumn.CellEditEvent event) {
        Reclamation tabRec = TableReclamation.getSelectionModel().getSelectedItem();
        tabRec.setEmail(event.getNewValue().toString());
        sr.update(tabRec);
               
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Modifier");
        alert.setHeaderText(null);
        alert.setContentText("Modifier avec succès!");

        alert.showAndWait();
    }

    @FXML
    private void Change_Message(TableColumn.CellEditEvent event) {
                
        Reclamation tabRec = TableReclamation.getSelectionModel().getSelectedItem();
        tabRec.setMessage(event.getNewValue().toString());
        sr.update(tabRec);
               
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Modifier");
        alert.setHeaderText(null);
        alert.setContentText("Modifier avec succès!");

        alert.showAndWait();
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        
                   
            TableReclamation.setItems(data);

             ObservableList<Reclamation> allReclamations,SingleReclamation ;
             allReclamations=TableReclamation.getItems();
             SingleReclamation=TableReclamation.getSelectionModel().getSelectedItems();
             Reclamation A = SingleReclamation.get(0);
             sr.delete(A.getId());
             SingleReclamation.forEach(allReclamations::remove);
             Aff();
                   
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Supprimer");
             alert.setHeaderText(null);
             alert.setContentText("Supprimé avec succès!");

             alert.showAndWait();
    }
    
}
