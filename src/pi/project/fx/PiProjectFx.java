/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.project.fx;

import Service.BlogService;
import Service.CoachService;
import Service.ProduitServise;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dorraayari
 */
public class PiProjectFx{
    
     
    
    public static void main(String[] args) {
        CoachService ps=new CoachService();
        ProduitServise pt=new ProduitServise();
     
           // ps.insert(p1);
          // ps.insertPst(p1);

          pt.readAll().forEach(System.out::println);

          ps.readAll().forEach(System.out::println);
          BlogService pss=new BlogService();
           // ps.insert(p1);
          // ps.insertPst(p1);
          pss.readAll().forEach(System.out::println);

    }
    
}
