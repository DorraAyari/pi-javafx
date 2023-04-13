/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Util.DataSource;
import Entite.Reclamation;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author msi
 */
public class ServiceReclamation implements IService<Reclamation> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceReclamation() {
        con = DataSource.getInstance().getCnx();

    }

    @Override
    public void insert(Reclamation a)  {
        try {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `reclamation` (`nom`, `prenom`, `email`, `message`) VALUES (?, ?, ?, ? );");
        PS.setString(1, a.getNom());
        PS.setString(2, a.getPrenom());
        PS.setString(3, a.getEmail());
        PS.setString(4, a.getMessage());
        PS.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Reclamation t)  {
        try {
            String requete = " update reclamation set nom=? , prenom=? , email=? , message=?   where id='"+t.getId()+"'"  ;
            pst = con.prepareStatement(requete);
            pst.setString(1,t.getNom());
            pst.setString(2,t.getPrenom());
            pst.setString(3,t.getEmail());
            pst.setString(4,t.getMessage());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public List<Reclamation> readAll() {
                 
        List<Reclamation> arr=new ArrayList<>();

        try {
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select * from reclamation");
            while (rs.next()) {
                int id=rs.getInt(1);
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                String email=rs.getString("email");
                String message=rs.getString("message");
                Reclamation p=new Reclamation(id,nom, prenom,email,message);
                arr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return arr;

    }
    
 

    @Override
    public void delete(int id) {
        try {
            String requete = " delete from reclamation where id='"+id+"'" ;
            pst = con.prepareStatement(requete);
              ste=con.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public Reclamation readById(int id) {
          Reclamation a = null;
         String requete = " select* from Produit  where id='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Reclamation(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;    }

}
