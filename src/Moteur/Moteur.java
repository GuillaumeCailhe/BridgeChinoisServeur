/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moteur;

import LibrairieMoteur.ModeDeJeu;
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
import java.util.Collections;
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
    
    public Moteur(ModeDeJeu mode, Communication client1, Communication client2){
        this.mode = mode;
        this.nbManches = nbManches;
        this.mancheActuelle = 0;
        
        client1.setNotifie(this);
        if(mode == ModeDeJeu.JOUEUR_CONTRE_JOUEUR){
            client2.setNotifie(this);
        }
        
        this.c1 = client1;
        this.c2 = client2;
        
        this.nbManches = this.negociationNbManches();
        System.out.println("Il y aura " + this.nbManches + " manches");
    }
    
    /**
     * A appeler si on a qu'un joueur humain
     * @param mode le mode de jeu (ne pas mettre JOUEUR_CONTRE_JOUEUR)
     * @param nbManches 
     * @param client1 le joueur humain de la partie
     */
    public Moteur(ModeDeJeu mode, Communication client1){
        this(mode,client1,null);
    }

    @Override
    public void run() {
        // Echange des pseudos
        String[] pseudos = echangePseudos();
        
        // Création des joueurs
        Joueur j1 = new Joueur(pseudos[0],this);
        Joueur j2 = new Joueur(pseudos[1],this);

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
    
        jeu();
    }
    
    private void jeu(){
        Couple gagnant;
        Couple perdant;
        Carte carteGagnant;
        Carte cartePerdant;
        
        for(mancheActuelle = 1; mancheActuelle <= nbManches; mancheActuelle++){
            
            // Choix du joueur qui commence
            if(mancheActuelle % 2 == 1){ // Manches 1,3,5...
                gagnant = joueur1;
                perdant = joueur2;
            } else {
                gagnant = joueur2;
                perdant = joueur1;
            }
           
            this.distribuerCartes();
            
            // Boucle principale de la manche
            while(!partieFinie()){
                // Calcul de l'atout
                this.atout = this.piles.calculerAtout();
                
                // Avertir ordre du tour
                gagnant.getIntelligence().avertirTour(true);
                perdant.getIntelligence().avertirTour(false);
                
                // Lire le coup du gagnant / demande de sauvegarde ou abandon
                carteGagnant = gagnant.getJoueur().jouerCarte(gagnant.getIntelligence().getCoup());
                
                // Avertir l'autre joueur
                perdant.getIntelligence().avertirCoupAdversaire(carteGagnant);
                
                // Lire le coup du perdant / demande de sauvegarde ou abandon, ou demande annulation par le premier joueur
                cartePerdant = perdant.getJoueur().jouerCarte(perdant.getIntelligence().getCoup());
                
                // Avertir l'autre joueur
                gagnant.getIntelligence().avertirCoupAdversaire(cartePerdant);
                
                // Attendre x secondes pour laisser le joueur annuler
                
                // Ajouter le coup à l'historique
                
                // Comparer les cartes pour déterminer le nouveau gagnant/perdant et incrémenter le pli du joueur gagnant
                
                
                break;
            }
            
            // Fin manche
            // Afficher vainqueur de la manche
            
            // Mettre à jour les manches pour chaque joueur
            
        }
        // Afficher vainqueur de la partie
    }
    
    private boolean partieFinie(){
        return (joueur1.getJoueur().getMain().isEmpty());
    }
    
    /**
     * Compare 2 cartes en prenant en compte l'atout
     * @param carte1
     * @param carte2
     * @return 1 si carte1>carte2, -1 si carte2>carte1
     */
    private int comparerCartes(Carte carte1, Carte carte2){
        if(carte1.getValeur() == atout && carte2.getValeur() != atout){
            return 1;
        } else if (carte2.getValeur() == atout && carte1.getValeur() != atout){
            return -1;
        } else {
            return carte1.compareTo(carte2);
        }
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
        
        // Récupération du pseudo du joueur 1
        attendreMessage(c1);
        pseudo1 = (String) c1.getMessageParCode(CodeMessage.PSEUDO).getDonnees();
        System.out.println("Pseudo joueur 1: " + pseudo1);
        
        // Récupération du pseudo du joueur 2 ("IA" si c'est une IA)
        if(mode == ModeDeJeu.JOUEUR_CONTRE_JOUEUR){
            attendreMessage(c2);
            pseudo2 = (String) c2.getMessageParCode(CodeMessage.PSEUDO).getDonnees();            
        } else {
            pseudo2 = "IA";
        }
        System.out.println("Pseudo joueur 2: " + pseudo2);
        
        // Retransmission les pseudos
        c1.envoyerString(CodeMessage.PSEUDO, pseudo2);
        c2.envoyerString(CodeMessage.PSEUDO, pseudo1);

        return new String[] {pseudo1, pseudo2};
    }
    
    /**
     * Négocie le nombre de manches de la partie entre les 2 clients. N'effectue les modifications qu'entre les 2 communications, pas dans Joueur() ou Moteur()
     * @return le nombre de manches
     */
    private int negociationNbManches(){
        int nb1; // Le nombre de manches voulues par le joueur 1
        int nb2; // Le nombre de manches voulues par le joueur 2
        
        attendreMessage(this.c1);
        nb1 = (int) this.c1.getMessageParCode(CodeMessage.PARTIE_NBMANCHES).getDonnees();
        System.out.println("Le joueur 1 veut " + nb1 + " manches");
        if(this.mode == ModeDeJeu.JOUEUR_CONTRE_JOUEUR){
            attendreMessage(this.c2);
            nb2 = (int) this.c2.getMessageParCode(CodeMessage.PARTIE_NBMANCHES).getDonnees();
            System.out.println("Le joueur 2 veut " + nb2 + " manches");
        } else {
            nb2 = nb1;
        }
        
        c1.envoyerEntier(CodeMessage.PARTIE_NBMANCHES,(byte) Math.min(nb1,nb2));
        if(this.mode == ModeDeJeu.JOUEUR_CONTRE_JOUEUR){
            c2.envoyerEntier(CodeMessage.PARTIE_NBMANCHES,(byte) Math.min(nb1,nb2));
        }
        return Math.min(nb1, nb2);
    }
    
    
    /**
     * Envoi les mains/piles aux joueurs
     */
    private void distribuerCartes(){
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
        Collections.sort(main1);
        Collections.sort(main2);
        joueur1.getJoueur().setMain(main1);
        joueur2.getJoueur().setMain(main2);
        System.out.println("terminé.");
        
        // Appel aux fonctions envoyerMain(), envoyerPiles(), et envoyerTour() dans les Intelligences, la partie peut ensuite démarrer
        joueur1.getIntelligence().montrerMain(joueur1.getJoueur().getMain());
        joueur2.getIntelligence().montrerMain(joueur2.getJoueur().getMain());
        joueur1.getIntelligence().montrerPiles(this.piles.getVisibles());
        joueur2.getIntelligence().montrerPiles(this.piles.getVisibles());
    }
    
    private void attendreMessage(Communication origine){
        if(origine.getNbMessages() == 0){
            synchronized(this){
                do{
                    try{
                        wait();
                    } catch (InterruptedException ex) {
                         Logger.getLogger(Moteur.class.getName()).log(Level.SEVERE, null, ex);
                     }
                } while(origine.getNbMessages() == 0);
            }
        }
    }
     
    /**
     * 
     * @return le nombre de manches dans la partie
     */
    public int getNbManches() {
        return nbManches;
    }

    /**
     * 
     * @return le numéro de la manche actuelle
     */
    public int getMancheActuelle() {
        return mancheActuelle;
    }
    
    public Piles getPiles(){
        return this.piles;
    }
}
