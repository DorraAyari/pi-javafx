/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Util.DataSource;
import Entite.Reclamation;
import Entite.ReponseReclamation;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author msi
 */
public class ServiceReponse implements IService<ReponseReclamation> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceReponse() {
        con = DataSource.getInstance().getCnx();

    }

    @Override
    public void insert(ReponseReclamation a)  {
        try {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `reponse_reclamation` (`reponse`, `reclamation_id`) VALUES (?, ? );");
        PS.setString(1, a.getReponse());
        PS.setInt(2, a.getReclamation_id());
        PS.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(ReponseReclamation t)  {
        try {
            String requete = " update reponse_reclamation set reponse=?  where id='"+t.getId()+"'"  ;
            pst = con.prepareStatement(requete);
            pst.setString(1,t.getReponse());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public List<ReponseReclamation> readAll() {
                 
        List<ReponseReclamation> arr=new ArrayList<>();

        try {
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select * from reponse_reclamation");
            while (rs.next()) {
                
                ReponseReclamation p=new ReponseReclamation(rs.getInt(1),rs.getString(2), rs.getInt(3));
                arr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return arr;

    }
        private ResultSet rs;

        public ObservableList<ReponseReclamation> afficherReponse(int id_reclamtion){
         String req="select * from reponse_reclamation where reclamation_id = "+id_reclamtion+"";   
            ObservableList <ReponseReclamation> list=FXCollections.observableArrayList();
         try {
            ste=con.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
                list.add(new ReponseReclamation(rs.getInt("id"),rs.getString("reponse"),rs.getInt("reclamation_id")));
            }
        } catch (SQLException ex) {
             Logger.getLogger(ReponseReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }   
         return list;
    }

    @Override
    public void delete(int id) {
        try {
            String requete = " delete from reponse_reclamation where id='"+id+"'" ;
            pst = con.prepareStatement(requete);
              ste=con.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public ReponseReclamation readById(int id) {
          ReponseReclamation a = null;
         String requete = " select* from reponse_reclamation  where id='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new ReponseReclamation(res.getInt(1),res.getString(2),res.getInt(3));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;    }

}
