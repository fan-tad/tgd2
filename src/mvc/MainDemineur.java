/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import javafx.application.Application;
import java.util.Observable;
import javafx.stage.Stage;
/**
 *
 * @author tfano_000
 */
public class MainDemineur extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Modele m = new Modele();
        
        //Création du contrôleur
        Controleur controler = new ControleurDemineur(m);
        Demineur d = new Demineur(controler, primaryStage);
        m.addObserver(d); 
    }
    
        public static void main(String[] args) {
        
        launch();    
    }

    
}
