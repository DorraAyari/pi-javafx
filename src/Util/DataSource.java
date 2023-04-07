/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dorraayari
 */
public class DataSource {
    private String url="jdbc:mysql://localhost:8889/Infantry";
private String login="root";
private String password="root";
private Connection cnx;
private static DataSource instance;



    private DataSource() {
    try {
        cnx=DriverManager.getConnection(url, login, password);
        System.out.println("la connexion est etabli");
    } catch (SQLException ex) {
        Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
      //  ex.printStackTrace();
    }
    
    }
     public static DataSource getInstance(){
        if (instance == null)
            instance=new DataSource();
            return instance;
        
    }

    public Connection getCnx() {
        return cnx;
    }
}
