/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridgechinois;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Pepefab
 */
public class Moteur {

    Piles piles;
    ValeurCarte atout;

    Stack<Carte[]> historique; // Mon interprétation : un coup est représenté par 2 cartes jouées, je les gardent donc dans un tableau à 2 cartes.
    
    public Moteur(){
        // Distribution des cartes
        Paquet paquet = new Paquet();
        this.piles = new Piles(paquet);
        ArrayList<Carte> main1 = new ArrayList<Carte>();
        ArrayList<Carte> main2 = new ArrayList<Carte>();
        for(int i=0; i<11; i++){
            main1.add(paquet.distribuerUneCarte());
            main2.add(paquet.distribuerUneCarte());
        }
    }
    
}
