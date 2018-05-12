/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moteur;

import LibrairieCarte.ValeurCarte;
import Carte.Piles;
import Carte.Paquet;
import Joueur.Joueur;
import LibrairieReseau.Communication;
import IA.IAFacile;
import IA.IAIntermediaire;
import IA.IADifficile;
import LibrairieCarte.Carte;
import LibrairieReseau.CodeMessage;
import LibrairieReseau.Message;
import LibrairieReseau.MessageString;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pepefab
 */
public class Moteur implements Runnable{

    private ModeDeJeu mode;
    
    private int nbManches;
    private int mancheActuelle;
    
    private Couple joueur1;
    private Couple joueur2;
    
    private Piles piles;
    private ValeurCarte atout;
    private Carte[] pli;
    
    Stack<Carte[]> historique; // Mon interprétation : un coup est représenté par 2 cartes jouées, je les gardes donc dans un tableau à 2 cartes.
    
    // J'aime pas trop sauvegarder les Communications, sachant qu'on peut y accéder dans Couple.getIntelligence(), mais c'est pour déplacer le maximum de traitement du constructeur au .run()
    private Communication c1;
    private Communication c2;
    
    public Moteur(ModeDeJeu mode, int nbManches, Communication client1, Communication client2){
        this.mode = mode;
        this.nbManches = nbManches;
        this.mancheActuelle = 0;
        
        this.c1 = client1;
        this.c2 = client2;
    }
    
    /**
     * A appeler si on a qu'un joueur humain
     * @param mode le mode de jeu (ne pas mettre JOUEUR_CONTRE_JOUEUR)
     * @param nbManches 
     * @param client1 le joueur humain de la partie
     */
    public Moteur(ModeDeJeu mode, int nbManches, Communication client1){
        this(mode,nbManches,client1,null);
    }

    @Override
    public void run() {
        // Distribution des cartes
        System.out.println("Distribution des cartes en cours...");
        Paquet paquet = new Paquet();
        this.piles = new Piles(paquet);
        ArrayList<Carte> main1 = new ArrayList<Carte>();
        ArrayList<Carte> main2 = new ArrayList<Carte>();
        for(int i=0; i<11; i++){
            main1.add(paquet.distribuerUneCarte());
            main2.add(paquet.distribuerUneCarte());
        }
        System.out.println("terminé.");
        
        // Echange des pseudos
        String[] pseudos = echangePseudos();
        
        // Création des joueurs
        Joueur j1 = new Joueur(pseudos[0],main1,this);
        Joueur j2 = new Joueur(pseudos[1],main2,this);

        Intelligence int1;
        Intelligence int2;
        switch(this.mode){
            case JOUEUR_CONTRE_JOUEUR:
                int1 = new IntelligenceHumain(c1);
                int2 = new IntelligenceHumain(c2);
                joueur1 = new Couple(j1,int1);
                joueur2 = new Couple(j2,int2);
                break;
            case JOUEUR_CONTRE_IA_FACILE:
                int1 = new IntelligenceHumain(c1);
                int2 = new IntelligenceIA(new IAFacile());
                joueur1 = new Couple(j1,int1);
                joueur2 = new Couple(j2,int2);
                break;
            case JOUEUR_CONTRE_IA_INTERMEDIAIRE:
                int1 = new IntelligenceHumain(c1);
                int2 = new IntelligenceIA(new IAIntermediaire());
                joueur1 = new Couple(j1,int1);
                joueur2 = new Couple(j2,int2);
                break;
            case JOUEUR_CONTRE_IA_DIFFICILE:
                int1 = new IntelligenceHumain(c1);
                int2 = new IntelligenceIA(new IADifficile());
                joueur1 = new Couple(j1,int1);
                joueur2 = new Couple(j2,int2);
                break;
        }
        
        // Appel aux fonctions envoyerMain(), envoyerPiles(), et envoyerTour() dans les Intelligences, la partie peut ensuite démarrer
        joueur1.getIntelligence().montrerMain(joueur1.getJoueur().getMain());
        joueur2.getIntelligence().montrerMain(joueur2.getJoueur().getMain());
        joueur1.getIntelligence().montrerPiles(this.piles.getVisibles());
        joueur2.getIntelligence().montrerPiles(this.piles.getVisibles());
        joueur1.getIntelligence().avertirTour(true);
        joueur2.getIntelligence().avertirTour(false);
        System.out.println("Fin de l'initialisation");
    }
    
    
    public int getNbManches() {
        return nbManches;
    }

    public int getMancheActuelle() {
        return mancheActuelle;
    }

    /**
     * Réceptionne les pseudos des joueurs et les retransmet à l'adversaire
     * @return un tableau à 2 éléments contenant le pseudo du joueur 1, puis celui du joueur 2
     */
    private String[] echangePseudos(){
        // Récupération des pseudos
        // On peut améliorer, en regardant les 2 à la fois
        System.out.println("Récupération des pseudos");
        String pseudo1;
        String pseudo2;
        while(c1.getNbMessages() == 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Moteur.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        pseudo1 = (String) c1.getMessageParCode(CodeMessage.PSEUDO).getDonnees();
        System.out.println("Pseudo joueur 1: " + pseudo1);
        if(mode == ModeDeJeu.JOUEUR_CONTRE_JOUEUR){
             while(c2.getNbMessages() == 0) {
                 try {
                     Thread.sleep(10);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Moteur.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             pseudo2 = (String) c2.getMessageParCode(CodeMessage.PSEUDO).getDonnees();            
        } else {
            pseudo2 = "IA";
        }
        System.out.println("Pseudo joueur 2: " + pseudo2);
        
        // Retransmettre les pseudos
        c1.envoyerString(CodeMessage.PSEUDO, pseudo2);
        c2.envoyerString(CodeMessage.PSEUDO, pseudo1);

        return new String[] {pseudo1, pseudo2};
    }
    
    
}
