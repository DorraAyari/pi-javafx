/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Blog;
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
 * @author EMNA
 */
public class BlogService implements IService<Blog> {
    private Connection conn;
    public BlogService() {
        conn=DataSource.getInstance().getCnx();
    }
    
    // @Override
    public void insert(Blog t) {
        String requete ="insert into blog(nom,description,image,moredescreption,slogan)"+"values('"+t.getNom()+"','"+t.getDescription()+"','"+t.getMoredescription()+"','"+t.getSlogan()+"')";
    try {
        Statement ste =conn.createStatement();
        ste.executeUpdate(requete);
    } catch (SQLException ex) {
        Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void insertPst(Blog p) {
        String requete = "insert into blog (nom,description,image,moredescreption,slogan)"
                + " values (?,?,?,?,?)";
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getDescription());
            pst.setString(3, p.getMoredescription());
             pst.setString(4, p.getSlogan());
             pst.setString(5, p.getImage()); // set the image field
        
            pst.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    public void update(Blog blog) {
    String req = "UPDATE blog SET nom = ?, description = ?, moredescreption = ?, slogan = ?, image = ? WHERE id = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(req);
        pst.setString(1, blog.getNom());
        pst.setString(2, blog.getDescription());
        pst.setString(3, blog.getMoredescription());
        pst.setString(4, blog.getSlogan());
        pst.setString(5, blog.getImage());
        pst.setInt(6, blog.getId());
        pst.executeUpdate();
        System.out.println("blog updated successfully");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    //@Override
public void delete(int blogId) {
    String sql = "DELETE FROM blog WHERE id = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, blogId);
        System.out.println("SQL query: " + statement.toString());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted == 0) {
            throw new SQLException("Failed to delete blog, no rows affected.");
        }
        System.out.println(rowsDeleted + " rows deleted.");
    } catch (SQLException ex) {
        System.out.println("Error deleting blog: " + ex.getMessage());
    }
}
//@Override
    public List<Blog> readAll() {
               List<Blog> list = new ArrayList<>();
               String requete= "select * from blog";

    try { 
        Statement st = conn.createStatement();
        ResultSet rs= st.executeQuery(requete);
        while(rs.next()){

            list.add(new Blog(rs.getString("nom"),
                    rs.getString("description"),
                    rs.getString("moredescreption"),
                    rs.getString("slogan"),
                    
                       rs.getInt("id"),
                     rs.getString("image")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
    }
    
    //@Override
    public Blog readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
