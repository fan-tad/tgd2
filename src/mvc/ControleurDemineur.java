/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

/**
 *
 * @author tfano_000
 */
public class ControleurDemineur extends Controleur{
    public ControleurDemineur(Modele m){
        super(m);
        
    }
    
    public void ClickGauche(int x, int y){
        if(!m.getJeu().isJeuCommence()){
            m.JeuCommence();
        }
        m.jouerDemineur(x, y);
        m.JeuFini();
    }
    
    public void ClickDroit(int x, int y){
        if(!m.getJeu().isJeuCommence()){
            m.JeuCommence();
        }
        m.ajoutDrapeau(x, y);
    }
    
    
}
