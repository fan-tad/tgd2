/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.Modele;
import java.util.*;
/**
 *
 * @author p1311388
 */
public class plateauDemineur extends Plateau{
    
    public plateauDemineur(int longueur, int largeur, int NbBombe){
        super(longueur, largeur, NbBombe );
       
    }
    
    @Override
    public void initialise(){
        int i, j;
        for(i=0; i<super.getLongueur() ; i++){
            for(j=0; j<super.getLargeur(); j++){
                Case C = new caseNormal(i,j);
                etatIdPlateau[i][j] = C;
            }
        }
    }
    
    @Override
    public void ajouterBombe(){
        int i=0;
        while(i<NbBombe){
            int lar = (int) (Math.random() * (largeur - 0)) + 0;
            int lon = (int) (Math.random() * (longueur - 0)) + 0;
            //System.out.println("location bomb: " + lon + lar);
            if(!etatIdPlateau[lon][lar].getBombe()){
               etatIdPlateau[lon][lar].setBombe(true);
               i++;
            }  
        }
    }
    
    @Override
    public void ajoutNombre(){
        for(int i=0; i<longueur ; i++){
            for(int j=0; j<largeur; j++){
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
        }
    }
    
    @Override
    public Vector getVoisins(Case X){
        Vector resultat = new Vector();
        for(int i=X.getLongueur()-1; i<=X.getLongueur()+1; i++){
            for(int j=X.getLargeur()-1; j<=X.getLargeur()+1; j++){
                if(i!=X.getLongueur() || j!=X.getLargeur()){
                    if(super.acceptable(j,i)){
                        resultat.add(etatIdPlateau[i][j]);
                    }
                }
            }
        }
        return resultat;
    }
    
    @Override
    public String toStrings(){
        int i, j;
        String resultat="";
        for(i=0; i<getLongueur(); i++){
            resultat+="|";
            for(j=0;j<getLargeur();j++){
                if(!etatIdPlateau[i][j].isDevoilee()){
                    if(etatIdPlateau[i][j].getBombe()){
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
                    if(etatIdPlateau[i][j].getBombe()){
                        resultat += "B";
                    }
                    if(etatIdPlateau[i][j].getAlentour()>0 && !etatIdPlateau[i][j].getBombe()){
                        resultat += Integer.toString(etatIdPlateau[i][j].getAlentour());
                    }
                    if(etatIdPlateau[i][j].isDevoilee() && etatIdPlateau[i][j].isVide()){
                        resultat += "V";
                    }
                }
                resultat+="|";
            }
            resultat+="\n";
        }
        return resultat;
    }
    
    @Override
    public void devoilementVide(Case choisi){
        int i, j;
        Vector v = new Vector(10, 2);
        Vector existe = new Vector(10, 2);
        v.add(choisi);
        existe.add(choisi);
        while(!v.isEmpty()){
            Case tmp = (Case)v.firstElement();
            tmp.setValue(retourChar(tmp.getLongueur(),tmp.getLargeur()));
            if(tmp.isVide()){
                Vector voisin = getVoisins(tmp);
                for(int k=0; k<voisin.size(); k++){
                    Case c = (Case)voisin.get(k);
                    setNbNonDevoilee(c, NbNonDevoilee -1);
                    if(!c.isDevoilee()){
                        c.setDevoilee(true);
                        c.setValue(retourChar(c.getLongueur(),c.getLargeur()));
                    }
                    if(c.isVide()){
                        if(!existe.contains(c)){
                            v.add(c);
                            existe.add(c);
                        }
                    }
                }
            }
            else{
                tmp.setDevoilee(true);
                setNbNonDevoilee(tmp, NbNonDevoilee -1);
            }
            v.remove((Case)v.firstElement());
        }
        System.out.println("NbNonDev est : " + NbNonDevoilee);
        existe.removeAllElements();
    }
}
