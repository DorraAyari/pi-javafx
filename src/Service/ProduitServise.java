/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Produit;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AA
 */
public class ProduitServise implements IService<Produit>{
private Connection conn;

    public ProduitServise() {
        conn = DataSource.getInstance().getCnx();
    }
    @Override
    public void insert(Produit t) {
        String requete = "insert into produit(prix,nom,description,image)"
                + "values('" + t.getPrix() + "','" + t.getNom()
                + "'," + t.getDescription() +"','" + t.getImage()
                + "'," + ")";
      //  Date.valueOf(t.getDns());
        try {
            Statement ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServise.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void insertPst(Produit p) {
        String requete = "insert into produit (nom,description,prix,image)"
                + " values (?,?,?,?)";
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getDescription());
           
            pst.setInt(3, p.getPrix());
             pst.setString(4, p.getImage()); // set the image field
        
            pst.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServise.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
  
 
 
 
    @Override
    public void delete(int id){
        try {
            String req = "delete from produit where id = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServise.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Produit t) {
        }
    

    @Override
    public List<Produit> readAll() {
        List<Produit> list=new ArrayList<>();
        String requete="select * from produit";
        try {
            Statement st=conn.createStatement();
            ResultSet rs= st.executeQuery(requete);
//            Personne p=new Personne();
//            p.setDns(rs.getDate("dns").toLocalDate());
            while(rs.next()){
                list.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("nom"), 
                        rs.getString("description"),
                        rs.getInt("prix"),
                rs.getString("image")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServise.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Produit readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
