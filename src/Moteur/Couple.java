/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moteur;

import Joueur.Joueur;

/**
 *
 * @author Pepefab
 */
public class Couple{
    private Joueur joueur;
    private Intelligence intelligence;

    /**
     * Une paire correspond à un joueur et son intelligence, c'est à dire l'origine des décisions (IA ou humain (réseau))
     * @param joueur
     * @param intelligence 
     */
    public Couple(Joueur joueur, Intelligence intelligence) {
      this.joueur = joueur;
      this.intelligence = intelligence;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public Intelligence getIntelligence() {
        return this.intelligence;
    }
  
  
  
}
