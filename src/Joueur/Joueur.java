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
    
    public Joueur(String pseudo, Moteur moteur){
        this.pseudo = pseudo;
        this.main = null;
        this.moteur = moteur;
        
        this.manches = new String[moteur.getNbManches()];
        for(int i=0; i<moteur.getNbManches();i++){
            manches[i] = "?";
        }
        this.scoreManche = 0;
    }
    
    public void setMain(ArrayList<Carte> main){
        this.main = main;
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
    
    public Carte jouerCarte(int indexMain){
        return this.main.remove(indexMain);
    }
    
    /**
     * Pioche une carte dans la pile
     */
    public void piocherCarte(int indexPile){
        main.add(moteur.getPiles().getVisibles().get(indexPile));
    }

    public void gagnePli(){
        this.scoreManche += 1;
    }

    public int getScoreManche() {
        return this.scoreManche;
    }
    
    public ArrayList<Carte> getMain(){
        return main;
    }
    
    
}
