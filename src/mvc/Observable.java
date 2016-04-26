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
public interface Observable {
    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserver(String str);
    public void notifyObserver(Boolean fini);
    public void notifyFlag(int longueur, int largeur);
    public void notifyBombe(int longueur, int largeur); 
}
