/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import LibrairieCarte.Carte;
import Moteur.Moteur;
import java.util.ArrayList;

/**
 *
 * @author Pepefab
 */
public class Joueur {
    
    private String pseudo;
    private ArrayList<Carte> main;
    private Moteur moteur;
    
    private String[] manches;
    private int scoreManche;
    
    public Joueur(String pseudo, ArrayList<Carte> main, Moteur moteur){
        this.pseudo = pseudo;
        this.main = main;
        this.moteur = moteur;
        
        this.manches = new String[moteur.getNbManches()];
        for(int i=0; i<moteur.getNbManches();i++){
            manches[i] = "?";
        }
        this.scoreManche = 0;
    }
    
    /**
     * Le joueur remporte la manche
     */
    public void victoire(){
        this.manches[this.moteur.getMancheActuelle()] = "V";
    }
    
    /**
     * Le joueur perd la manche
     */
    public void defaite(){
        this.manches[this.moteur.getMancheActuelle()] = "D";
    }
    
    /**
     * Réinitialise le nombre de plis
     */
    public void nouvelleManche(){
        this.scoreManche = 0;
    }
    
    /**
     * Pioche une carte dans la pile
     */
    public boolean piocherCarte(int indexMain){
        throw new Error("Joueur.piocherCarte() pas implémenté");
    }

    public void gagnePli(){
        this.scoreManche += 1;
    }

    public int getScoreManche() {
        return this.scoreManche;
    }
    
    
    
}
