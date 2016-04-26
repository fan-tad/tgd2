/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.Modele;

/**
 *
 * @author p1311388
 */
public abstract class Case {
    private int largeur;
    private int longueur;
    private boolean bombe;
    private int alentour;
    private boolean drapeau;
    private boolean devoilee;
    private boolean vide;
    private String value;
    
    public Case(int longueur, int largeur){
        this.largeur = largeur;
        this.longueur = longueur;
        bombe = false;
        alentour = 0;
        drapeau = false;
        devoilee = false;
        vide = true;
        value = "";
    }

    /**
     * @return the bombe
     */
    public boolean getBombe() {
        return bombe;
    }

    /**
     * @param bombe the bombe to set
     */
    public void setBombe(boolean bombe) {
        this.bombe = bombe;
        this.vide = false;
    }

    /**
     * @return the alentour
     */
    public int getAlentour() {
        return alentour;
    }

    /**
     * @param alentour the alentour to set
     */
    public void setAlentour(int alentour) {
        this.alentour = alentour;
        this.vide = false;
    }

    /**
     * @return the drapeau
     */
    public boolean isDrapeau() {
        return drapeau;
    }

    /**
     */
    public void enleveDrapeau(){
        drapeau=false;
    }
    /**
     * @param drapeau the drapeau to set
     */
    public void setDrapeau() {
        if(!isDevoilee()){
            if(drapeau==true){
            drapeau=false;
            }
            else{
                drapeau=true;
            }
        }
    }

    /**
     * @return the devoilee
     */
    public boolean isDevoilee() {
        return devoilee;
    }

    /**
     * @param devoilee the devoilee to set
     */
    public void setDevoilee(boolean devoilee) {
        if(!isDrapeau()){
            this.devoilee = devoilee;
        }
    }

    /**
     * @return the vide
     */
    public boolean isVide() {
        return vide;
    }

    /**
     * @param vide the vide to set
     */
    public void setVide(boolean vide) {
        this.vide = vide;
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

    
    
    
    
}
