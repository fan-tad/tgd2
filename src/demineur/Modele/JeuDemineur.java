/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.Modele;
import java.util.Scanner;
/**
 *
 * @author p1311388
 */
public class JeuDemineur {
    private Plateau p;
    private Player p1;
    private boolean JeuFini;
    private boolean PlayerMort;
    private boolean JeuCommence;
    private int NbDrapeau;
    private int dureeSeconde;
    
    
    public JeuDemineur(char c){
        switch (c){
            case 'E':
                p  = new plateauDemineur(8, 8, 10);
                JeuFini = false;
                PlayerMort = false;
                break;
                
            case 'M':
                p  = new plateauDemineur(16, 16, 40);
                JeuFini = false;
                PlayerMort = false;
                break;
                
            case 'H':
                p  = new plateauDemineur(16, 30, 99);
                JeuFini = false;
                PlayerMort = false;
                break;
            
            case 'L':
                p  = new PlateauLosange(8, 8, 10);
                JeuFini = false;
                PlayerMort = false;
                break;
                
        }
        
    }
    
    
    
    /**
     * @return the p
     */
    

    /**
     * @param p the p to set
     */
    

    /**
     * @return the p1
     */
    public Player getP1() {
        return p1;
    }

    /**
     * @param p1 the p1 to set
     */
    public void setP1(Player p1) {
        this.p1 = p1;
    }

    /**
     * @return the JeuFini
     */
    public boolean isJeuFini() {
        return JeuFini;
    }

    /**
     * @param JeuFini the JeuFini to set
     */
    public void setJeuFini(boolean JeuFini) {
        this.JeuFini = JeuFini;
    }

    /**
     * @return the PlayerMort
     */
    public boolean isPlayerMort() {
        return PlayerMort;
    }

    /**
     * @param PlayerMort the PlayerMort to set
     */
    public void setPlayerMort(boolean PlayerMort) {
        this.PlayerMort = PlayerMort;
    }

    /**
     * @return the NbDrapeau
     */
    public int getNbDrapeau() {
        return NbDrapeau;
    }

    /**
     * @param NbDrapeau the NbDrapeau to set
     */
    public void setNbDrapeau(int NbDrapeau) {
        this.NbDrapeau = NbDrapeau;
    }
    
    public void jouerPartie(JeuDemineur jeu){
        while(!jeu.JeuFini){
            System.out.println("entrer largeur");
            Scanner sc1 = new Scanner(System.in);
            int longuer = sc1.nextInt();
            System.out.println("entrer longueur");
            Scanner sc2 = new Scanner(System.in);
            int larger = sc2.nextInt();
            if(jeu.getP().appliquerCoup(larger, longuer ).getBombe()){
                jeu.setJeuFini(true);
            }
            jeu.getP().devoilementVide(jeu.getP().getEtatIdPlateau()[longuer][larger]);
            System.out.println("Nb non dev: " + jeu.getP().getNbBombe());
            String pl = "";
            pl += jeu.getP().toStrings();
            System.out.println(pl);
            //System.out.println("Nb non devoilee: " + jeu.p.getNbNonDevoilee());
            //System.out.println("Nb bombe: " + jeu.p.getNbBombe());
            if(jeu.getP().getNbNonDevoilee() == jeu.getP().getNbBombe()){
                jeu.JeuFini=true;
            }
        }
    }
   public static void main(String[] args) {
        System.out.println("/*******Demineur*********/");
        JeuDemineur jeu = new JeuDemineur('E');

        String pl = "";
        pl += jeu.getP().toStrings();
        Case n1;
        jeu.getP().getVoisins(jeu.getP().getEtatIdPlateau()[1][1]);
        System.out.println(pl);
        jeu.jouerPartie(jeu);
        /*int n = 5;
        for (int i = 0; i < n - 1; ++i){    // On part de zéro pour arriver jusqu'au nombre de lignes désirées (première moitié)        
            System.out.println(" ");
        
            for (int j = 0; j < n - i; ++j){
                System.out.print(" ");
            }
            for (int j = 0; j <= i * 2; ++j){
                System.out.print("*");
            }
        }
        for (int i = n; i > 0; --i){
            System.out.println(" ");
            for (int j = n - i; j >= 0; --j){
                System.out.print(" ");
            }
            for (int j = 1; j < i * 2; ++j){
                System.out.print("*");
            }
        }*/
        System.out.println();
    }



    /**
     * @return the dureeSeconde
     */
    public int getDureeSeconde() {
        return dureeSeconde;
    }

    /**
     * @param dureeSeconde the dureeSeconde to set
     */
    public void setDureeSeconde(int dureeSeconde) {
        this.dureeSeconde = dureeSeconde;
    }
    
    public void incrSecond() {
        setDureeSeconde(getDureeSeconde()+1);
    }

    /**
     * @return the p
     */
    public Plateau getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Plateau p) {
        this.p = p;
    }

    /**
     * @return the JeuCommence
     */
    public boolean isJeuCommence() {
        return JeuCommence;
    }

    /**
     * @param JeuCommence the JeuCommence to set
     */
    public void setJeuCommence(boolean JeuCommence) {
        this.JeuCommence = JeuCommence;
    }
}
