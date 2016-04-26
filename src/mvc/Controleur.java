/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
/**
 *
 * @author tfano_000
 */
public abstract class Controleur {
    Modele m;
    
    public Controleur(Modele m){
        this.m = m;
    }
       
    public void OuiBouton(Button btnR, BorderPane p ,Scene scene, Stage stage){
        
        btnR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                p.getChildren().clear();
                stage.setScene(scene);
                stage.show();

            }
        });
    }
    
    public void NonBouton(Button btnR, Stage stage){
        
        btnR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();

            }
        });
    }
    
    abstract void ClickGauche(int x, int y);
    
    abstract void ClickDroit(int x, int y);
    
}
