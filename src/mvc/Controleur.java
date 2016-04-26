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
                //m.jeu.getP().reinitialise();
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

    void modeleNotify(Button []plateau, Modele m){
        m.addObserver(new Observer() {
            
            @Override
            public void update(Observable o, Object arg) {
                for(int i=0; i<m.getJeu().getP().getLongueur();i++){
                    for(int j=0; j<m.getJeu().getP().getLargeur();j++){
                        plateau[j*m.getJeu().getP().getLongueur()+i].setText(m.getPl().getEtatIdPlateau()[i][j].getValue());
                        if(m.getPl().getEtatIdPlateau()[i][j].isDevoilee()){
                            plateau[j*m.getJeu().getP().getLongueur()+i].getStyleClass().add("case-uncovered");
                        }
                    }
                }
            }

        });
    }
    
    abstract void ClickGauche(int x, int y);
    
    abstract void ClickDroit(int x, int y);
    
}
