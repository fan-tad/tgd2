/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.Observable;
import demineur.Modele.JeuDemineur;
import demineur.Modele.Plateau;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class Modele extends Observable{
    
    private Plateau pl;
    private JeuDemineur jeu;
    private String value = "";
    private int index, longueur, largeur;
    
    public void initialiseJeu(char mode)
    {
        jeu = new JeuDemineur(mode);
    }
    
     private ArrayList<Observer> listObserver = new ArrayList<Observer>();  
    public void jouerDemineur(int longueur, int largeur) {
        if(!this.getJeu().isJeuFini()){
            if(!this.jeu.getP().getEtatIdPlateau()[longueur][largeur].isDrapeau()){
                if(jeu.getP().appliquerCoup(longueur, largeur).getBombe()){
                    this.getJeu().setJeuFini(true);
                    getJeu().setPlayerMort(true);
                    for(int i = 0; i<jeu.getP().getLongueur();i++){
                        for(int j = 0; j<jeu.getP().getLargeur();j++){
                            if(jeu.getP().getEtatIdPlateau()[i][j].getBombe()){
                                jeu.getP().getEtatIdPlateau()[i][j].setDevoilee(true);
                                notifyBombe(i,j,true);
                            }
                        }
                    }
                }
                getJeu().getP().devoilementVide(getJeu().getP().getEtatIdPlateau()[longueur][largeur]);
                if(getJeu().getP().getNbNonDevoilee() == getJeu().getP().getNbBombe()){
                    getJeu().setJeuFini(true);
                    getJeu().setPlayerMort(false);
                    notifyBombe(longueur,largeur,false);
                }
                setPl(getJeu().getP());
                setValue(getJeu().getP().retourChar(longueur, largeur));
                setLongueur(longueur);
                setLargeur(largeur);

                // notification de la vue, suite Ã  la mise Ã  jour du champ lastValue
                setChanged();
                notifyObserver();
                //notifyObservers();
            }    
        }   
    }
    
    public void JeuCommence(){
        this.jeu.setJeuCommence(true);
        setChanged();
        notifyObserver(true);
    }
    
    public void JeuFini(){
        if(this.jeu.isJeuFini()){
            setChanged();
            notifyObserver(false);
        }
    }
    public void ajoutDrapeau(int longueur, int largeur){
        if(!this.getJeu().isJeuFini()){
            jeu.getP().getEtatIdPlateau()[longueur][largeur].setDrapeau();
            setChanged();
            if(jeu.getP().getEtatIdPlateau()[longueur][largeur].isDrapeau())
                notifyFlag(longueur, largeur);
            if(!jeu.getP().getEtatIdPlateau()[longueur][largeur].isDrapeau())
                notifyUnFlag(longueur, largeur);
        } 
        
    }

    
    //@Override
    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }
    
    public void notifyObserver() {
        for(Observer obs : listObserver)
            obs.update();
    }
    
    public void notifyObserver(Boolean fini) {
        for(Observer obs : listObserver)
            obs.update(fini);
    }
    public void notifyFlag(int longueur, int largeur) {
        for(Observer obs : listObserver)
            obs.updateCase(longueur, largeur);
    }
    
    public void notifyUnFlag(int longueur, int largeur) {
        for(Observer obs : listObserver)
            obs.updateUnFlag(longueur, largeur);
    }
    
    public void notifyBombe(int longueur, int largeur, Boolean mort) {
        for(Observer obs : listObserver)
            obs.updateBombe(longueur, largeur, mort);
    }
    
    
        /**
     * @return the jeu
     */
    public JeuDemineur getJeu() {
        return jeu;
    }

    /**
     * @param jeu the jeu to set
     */
    public void setJeu(JeuDemineur jeu) {
        this.jeu = jeu;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the index
     */
    /*public int getIndex() {
        return index;
    }*/

    /**
     * @param index the index to set
     */
    /*public void setIndex(int index) {
        this.index = index;
    }*/

    /**
     * @return the longueur
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * @param longueur the longueur to set
     */
    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    /**
     * @return the largueur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * @param largueur the largueur to set
     */
    public void setLargeur(int largueur) {
        this.largeur = largeur;
    }

    /**
     * @return the pl
     */
    public Plateau getPl() {
        return pl;
    }

    /**
     * @param pl the pl to set
     */
    public void setPl(Plateau pl) {
        this.pl = pl;
    }
}
