/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Coach;
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
 * @author dorraayari
 */
public class CoachService implements IService<Coach>{
  private Connection conn;
    public CoachService() {
        conn=DataSource.getInstance().getCnx();
    }

      @Override
    public void insert(Coach t) {
        String requete ="insert into coach(nom,description,weight,height,occupation,age)"+"values('"+t.getNom()+"','"+t.getDescription()+"','"+t.getWeight()+"','"+t.getHeight()+"','"+t.getOccupation()+"','"+t.getAge()+"')";
    try {
        Statement ste =conn.createStatement();
        ste.executeUpdate(requete);
    } catch (SQLException ex) {
        Logger.getLogger(CoachService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
      

    public void insertPst(Coach p) {
        String requete = "insert into coach (nom,description,weight,height,occupation,age,image)"
                + " values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getDescription());
            pst.setString(3, p.getWeight());
             pst.setString(4, p.getHeight());
            pst.setString(5, p.getOccupation());
            pst.setInt(6, p.getAge());
             pst.setString(7, p.getImage()); // set the image field
        
            pst.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(CoachService.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
public void update(Coach coach) {
    String req = "UPDATE coach SET nom = ?, description = ?, occupation = ?, weight = ?, height = ?, age = ?, image = ? WHERE id = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(req);
        pst.setString(1, coach.getNom());
        pst.setString(2, coach.getDescription());
        pst.setString(3, coach.getOccupation());
        pst.setString(4, coach.getWeight());
        pst.setString(5, coach.getHeight());
        pst.setInt(6, coach.getAge());
        pst.setString(7, coach.getImage());
        pst.setInt(8, coach.getId());
        pst.executeUpdate();
        System.out.println("Coach updated successfully");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

@Override
public void delete(int coachId) {
    String sql = "DELETE FROM coach WHERE id = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, coachId);
        System.out.println("SQL query: " + statement.toString());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted == 0) {
            throw new SQLException("Failed to delete coach, no rows affected.");
        }
        System.out.println(rowsDeleted + " rows deleted.");
    } catch (SQLException ex) {
        System.out.println("Error deleting coach: " + ex.getMessage());
    }
}




      @Override
    public List<Coach> readAll() {
               List<Coach> list = new ArrayList<>();
               String requete= "select * from coach";

    try { 
        Statement st = conn.createStatement();
        ResultSet rs= st.executeQuery(requete);
        while(rs.next()){

            list.add(new Coach(rs.getString("nom"),
                    rs.getString("description"),
                    rs.getString("weight"),
                    rs.getString("height"),
                    rs.getString("occupation"),
                      rs.getInt("age"),
                       rs.getInt("id"),
                     rs.getString("image")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(CoachService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
    }
    @Override
    public Coach readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
