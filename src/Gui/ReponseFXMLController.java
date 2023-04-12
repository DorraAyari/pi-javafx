/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entite.Reclamation;
import Entite.ReponseReclamation;
import Service.ServiceReclamation;
import Service.ServiceReponse;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ReponseFXMLController implements Initializable {

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
    @FXML
    private TextField inputReponse;
    @FXML
    private TableView<ReponseReclamation> TableReponse;
    @FXML
    private TableColumn<ReponseReclamation, String> reponseT;
    @FXML
    private TableColumn<ReponseReclamation, Integer> idRepT;

       
    private final ObservableList<Reclamation> data = FXCollections.observableArrayList();
    private final ObservableList<Reclamation> dataRep = FXCollections.observableArrayList();

        
    private Statement ste;
    private Connection con;
    ServiceReclamation sr = new ServiceReclamation();
    @FXML
    private Label idReclamation;

    ServiceReponse srep = new ServiceReponse();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Aff();
        } catch (SQLException ex) {
            Logger.getLogger(ReponseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
    }
    @FXML
    private void LoadData(MouseEvent event) {
        
        
        Reclamation rec = TableReclamation.getSelectionModel().getSelectedItem();
        
        idReclamation.setText(String.valueOf(rec.getId()));
        
        
        showRepo(rec.getId());

    }
    
        public void showRepo(int id){
        ObservableList<ReponseReclamation> list =  new ServiceReponse().afficherReponse(id);
        idRepT.setCellValueFactory(new PropertyValueFactory<ReponseReclamation, Integer>("id"));
        reponseT.setCellValueFactory(new PropertyValueFactory<ReponseReclamation, String>("reponse"));
        TableReponse.setItems(list);
        TableReponse.setEditable(true);
        reponseT.setCellFactory(TextFieldTableCell.forTableColumn());

    }

        
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 2;
        //return !T.getText().isEmpty();
    }


    @FXML
    private void Reponse(ActionEvent event) {
        if(Validchamp(inputReponse)&& !idReclamation.getText().isEmpty())
        {
            
        ReponseReclamation a = new ReponseReclamation(inputReponse.getText(), Integer.valueOf(idReclamation.getText()));
        srep.insert(a);
           
        showRepo(Integer.valueOf(idReclamation.getText()));
               
        inputReponse.setText("");
        idReclamation.setText("");
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

    @FXML
    private void Change_Reponse(TableColumn.CellEditEvent event) {
                ReponseReclamation tabRep = TableReponse.getSelectionModel().getSelectedItem();
        tabRep.setReponse(event.getNewValue().toString());
        srep.update(tabRep);
               
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Modifier");
        alert.setHeaderText(null);
        alert.setContentText("Modifier avec succès!");

        alert.showAndWait();


    }

    @FXML
    private void Supprimer(ActionEvent event) {

             ObservableList<ReponseReclamation> allReps,SingleRep ;
             allReps=TableReponse.getItems();
             SingleRep=TableReponse.getSelectionModel().getSelectedItems();
             ReponseReclamation A = SingleRep.get(0);
             srep.delete(A.getId());
             SingleRep.forEach(allReps::remove);
             showRepo(Integer.valueOf(idReclamation.getText()));
                   
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Supprimer");
             alert.setHeaderText(null);
             alert.setContentText("Supprimé avec succès!");

             alert.showAndWait();
    }
    
}
