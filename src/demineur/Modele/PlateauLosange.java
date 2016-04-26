/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.Modele;
import java.util.*;
/**
 *
 * @author tfano_000
 */


public class PlateauLosange extends Plateau{
    private Case[][] etatIdPlateau2;
    
    public PlateauLosange(int longueur, int largeur, int NbBombe){
        super(longueur, largeur, NbBombe );
       
    }

    @Override
    public void initialise() {
        int n = 5;
        etatIdPlateau = new Case[10][10];
        etatIdPlateau2 = new Case[10][10];
        for (int i = 0; i < n - 1; ++i){
            for (int j = 0; j < n - i; ++j){
            }
            for (int j = 0; j <= i * 2; ++j){
                System.out.println("i: " + i + " j: " + j);
                etatIdPlateau[i][j] = new caseNormal(i,j);
            }
        }
        for (int i = n; i > 0; --i){
            for (int j = n - i; j >= 0; --j){
            }
            for (int j = 1; j < i * 2; ++j){
                System.out.println("i: " + i + " j: " + j);
                etatIdPlateau2[i][j] = new caseNormal(i,j);
            }
        }
    }

    @Override
    public Vector getVoisins(Case X) {
    Vector resultat = new Vector();
        if(super.acceptable(X.getLargeur(),X.getLongueur()-1)){
            resultat.add(etatIdPlateau[X.getLongueur()-1][X.getLargeur()]);
        }
        if(super.acceptable(X.getLargeur(),X.getLongueur()+1)){
            resultat.add(etatIdPlateau[X.getLongueur()+1][X.getLargeur()]);
        }
        if(super.acceptable(X.getLargeur()-1,X.getLongueur())){
            resultat.add(etatIdPlateau[X.getLongueur()][X.getLargeur()-1]);
        }
        if(super.acceptable(X.getLargeur()+1,X.getLongueur())){
            resultat.add(etatIdPlateau[X.getLongueur()][X.getLargeur()+1]);
        }
        return resultat;
    }

    @Override
    public void ajouterBombe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toStrings() {
        int n = 5;
        String resultat = "";
        etatIdPlateau = new Case[10][10];
        for (int i = 0; i < n - 1; ++i){    // On part de zéro pour arriver jusqu'au nombre de lignes désirées (première moitié)        
            //System.out.println(" ");
            resultat += " ";
            resultat += "\n";
            for (int j = 0; j < n - i; ++j){
                //System.out.print(" ");
                resultat += " ";
            }
            for (int j = 0; j <= i * 2; ++j){
                System.out.println("|");
                if(etatIdPlateau[0][0].isDevoilee()){
                    if(etatIdPlateau[j][i].getBombe()){
                        resultat += "B";
                    }
                    /*if(etatIdPlateau[j][i].getAlentour()>0 && !etatIdPlateau[j][i].getBombe()){
                        resultat += Integer.toString(etatIdPlateau[j][i].getAlentour());
                    }*/
                    else
                    {
                        resultat+=" ";
                    }
                }
                else{
                    if(etatIdPlateau[j][i].getBombe()){
                        resultat += "B";
                    }
                    if(etatIdPlateau[j][i].getAlentour()>0 && !etatIdPlateau[j][i].getBombe()){
                        resultat += Integer.toString(etatIdPlateau[j][i].getAlentour());
                    }
                    if(etatIdPlateau[j][i].isDevoilee() && etatIdPlateau[j][i].isVide()){
                        resultat += "V";
                    }
                }
            }
        }
        for (int i = n; i > 0; --i){
            //System.out.println(" ");
            resultat += " ";
            resultat += "\n";
            for (int j = n - i; j >= 0; --j){
                //System.out.print(" ");
                resultat += " ";
            }
            for (int j = 1; j < i * 2; ++j){
                //System.out.println("i: " + i + " j: " + j);
                if(!etatIdPlateau2[i][j].isDevoilee()){
                    if(etatIdPlateau2[j][i].getBombe()){
                        resultat += "B";
                    }
                    /*if(etatIdPlateau[j][i].getAlentour()>0 && !etatIdPlateau[j][i].getBombe()){
                        resultat += Integer.toString(etatIdPlateau[j][i].getAlentour());
                    }*/
                    else
                    {
                        resultat+=" ";
                    }
                }
                else{
                    if(etatIdPlateau2[j][i].getBombe()){
                        resultat += "B";
                    }
                    if(etatIdPlateau2[j][i].getAlentour()>0 && !etatIdPlateau2[j][i].getBombe()){
                        resultat += Integer.toString(etatIdPlateau2[j][i].getAlentour());
                    }
                    if(etatIdPlateau2[j][i].isDevoilee() && etatIdPlateau2[j][i].isVide()){
                        resultat += "V";
                    }
                }
            }
        }
        return resultat;
    }
    
    
    @Override
    public void devoilementVide(Case choisi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajoutNombre() {
        /*for (int i = 0; i < n - 1; ++i){    // On part de zéro pour arriver jusqu'au nombre de lignes désirées (première moitié)        
            System.out.println(" ");
        
            for (int j = 0; j < n - i; ++j){
                System.out.print(" ");
            }
            for (int j = 0; j <= i * 2; ++j){
                if(getEtatIdPlateau()[i][j].getBombe()){
                    Case X = getEtatIdPlateau()[i][j];
                    Vector voisin = getVoisins(X);
                    for(int k=0; k<voisin.size(); k++){
                        Case tmp = (Case)voisin.get(k);
                        tmp.setAlentour(tmp.getAlentour()+1);
                    }
                    voisin.clear();
            }
        }
        for (int i = n; i > 0; --i){
            System.out.println(" ");
            for (int j = n - i; j >= 0; --j){
                System.out.print(" ");
            }
            for (int j = 1; j < i * 2; ++j){
                if(getEtatIdPlateau()[i][j].getBombe()){
                    Case X = getEtatIdPlateau()[i][j];
                    Vector voisin = getVoisins(X);
                    for(int k=0; k<voisin.size(); k++){
                        Case tmp = (Case)voisin.get(k);
                        tmp.setAlentour(tmp.getAlentour()+1);
                    }
                    voisin.clear();
            }
        }*/
    }
}
