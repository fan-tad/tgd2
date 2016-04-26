/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.Modele;

import java.util.Vector;

/**
 *
 * @author p1311388
 */
public abstract class Plateau {
    protected int longueur;
    protected int largeur;
    protected int NbNonDevoilee;
    protected int NbBombe;
    protected Case[][] etatIdPlateau;
    
    public Plateau(int longueur, int largeur, int NbBombe){
        this.longueur = longueur;
        this.largeur = largeur;
        this.NbNonDevoilee = this.longueur * this.largeur;
        this.NbBombe = NbBombe;
        this.etatIdPlateau=new caseNormal[longueur][largeur];
        initialise();
        ajouterBombe();
        ajoutNombre();
    }
    
    
    
    public abstract void initialise();
    
    public abstract Vector getVoisins(Case X);
    
    public abstract void ajouterBombe();
    
    public abstract String toStrings();
    
    public abstract void devoilementVide(Case choisi);
    
    public abstract void ajoutNombre();
    
    public void reinitialise(){
        initialise();
        ajouterBombe();
        ajoutNombre();
        this.NbNonDevoilee = this.longueur * this.largeur;
    }

    public String retourChar(int longueur, int largeur){
        String resultat="";
        if(!etatIdPlateau[longueur][largeur].isDevoilee()){
                resultat =" ";
        }
        else{
            if(getEtatIdPlateau()[longueur][largeur].getBombe()){
                resultat  = "B";
                getEtatIdPlateau()[longueur][largeur].setValue(resultat);
            }
            if(getEtatIdPlateau()[longueur][largeur].getAlentour()>0 && !etatIdPlateau[longueur][largeur].getBombe()){
                resultat  = Integer.toString(getEtatIdPlateau()[longueur][largeur].getAlentour());
                getEtatIdPlateau()[longueur][largeur].setValue(resultat);
            }
            if(getEtatIdPlateau()[longueur][largeur].isVide()){
                resultat  = " ";
                getEtatIdPlateau()[longueur][largeur].setValue(resultat);
            }
        }
        
        return resultat;
    }
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
     * @return the largeur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * @param largeur the largeur to set
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    /**
     * @return the etatIdPlateau
     */

    public Case appliquerCoup(int x, int y){
        Case selectionner;
        setNbNonDevoilee(getEtatIdPlateau()[x][y], NbNonDevoilee -1);
        getEtatIdPlateau()[x][y].setDevoilee(true);
        selectionner = getEtatIdPlateau()[x][y];
        return selectionner;
    }
    
    public boolean acceptable(int x, int y){
        return x>=0 && x<largeur && y>=0 && y<longueur;
    }
    
    

    /**
     * @return the NbNonDevoilee
     */
    public int getNbNonDevoilee() {
        return NbNonDevoilee;
    }

    /**
     * @param NbNonDevoilee the NbNonDevoilee to set
     */
    public void setNbNonDevoilee(Case X, int NbNonDevoilee) {
        if(!X.isDevoilee()){
            this.NbNonDevoilee = NbNonDevoilee;
        }
    }

    /**
     * @return the NbBombe
     */
    public int getNbBombe() {
        return NbBombe;
    }

    /**
     * @param NbBombe the NbBombe to set
     */
    public void setNbBombe(int NbBombe) {
        this.NbBombe = NbBombe;
    }

    /**
     * @return the etatIdPlateau
     */
    public Case[][] getEtatIdPlateau() {
        return etatIdPlateau;
    }

    /**
     * @param etatIdPlateau the etatIdPlateau to set
     */
    public void setEtatIdPlateau(caseNormal[][] etatIdPlateau) {
        this.etatIdPlateau = etatIdPlateau;
    }
}
