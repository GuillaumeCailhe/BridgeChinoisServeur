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
        Paquet paquet = new Paquet();
        piles = new Piles(paquet);
    }
    
}
