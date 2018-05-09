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
import LibrairieReseau.CommunicationClient;
import IA.IAFacile;
import IA.IAIntermediaire;
import IA.IADifficile;
import LibrairieCarte.Carte;
import java.util.ArrayList;
import java.util.Stack;

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
    
    Stack<Carte[]> historique; // Mon interprétation : un coup est représenté par 2 cartes jouées, je les gardent donc dans un tableau à 2 cartes.
    
    public Moteur(ModeDeJeu mode, int nbManches, CommunicationClient client1, CommunicationClient client2){
        this.mode = mode;
        this.nbManches = nbManches;
        this.mancheActuelle = 0;
        
        // Distribution des cartes
        Paquet paquet = new Paquet();
        this.piles = new Piles(paquet);
        ArrayList<Carte> main1 = new ArrayList<Carte>();
        ArrayList<Carte> main2 = new ArrayList<Carte>();
        for(int i=0; i<11; i++){
            main1.add(paquet.distribuerUneCarte());
            main2.add(paquet.distribuerUneCarte());
        }
        
       
        // Création des joueurs
        // TODO : récupérer le pseudo du joueur
        Joueur j1 = new Joueur("test1",main1,this);
        Joueur j2 = new Joueur("test2",main2,this);
        
        Intelligence int1;
        Intelligence int2;
        switch(this.mode){
            case JOUEUR_CONTRE_JOUEUR:
                int1 = new Intelligence(client1);
                int2 = new Intelligence(client2);
                joueur1 = new Couple(j1,int1);
                joueur2 = new Couple(j2,int2);
            case JOUEUR_CONTRE_IA_FACILE:
                int1 = new Intelligence(client1);
                int2 = new Intelligence(new IAFacile());
                joueur1 = new Couple(j1,int1);
                joueur2 = new Couple(j2,int2);
            case JOUEUR_CONTRE_IA_INTERMEDIAIRE:
                int1 = new Intelligence(client1);
                int2 = new Intelligence(new IAIntermediaire());
                joueur1 = new Couple(j1,int1);
                joueur2 = new Couple(j2,int2);
            case JOUEUR_CONTRE_IA_DIFFICILE:
                int1 = new Intelligence(client1);
                int2 = new Intelligence(new IADifficile());
                joueur1 = new Couple(j1,int1);
                joueur2 = new Couple(j2,int2);
        }

    }
    
    public Moteur(ModeDeJeu mode, int nbManches, CommunicationClient client1){
        this(mode,nbManches,client1,null);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public int getNbManches() {
        return nbManches;
    }

    public int getMancheActuelle() {
        return mancheActuelle;
    }


    
    
}
