/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carte;

import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
import LibrairieCarte.ValeurCarte;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Pepefab
 */
public class Piles {
    
    ArrayList<Stack<Carte>> piles;
    
    public Piles(Paquet paquet){
        piles = new ArrayList<Stack<Carte>>();
        for(int i=0; i<6; i++){
            piles.add(new Stack<Carte>());
        }
        distribuerPiles(paquet);
    }
    
    /**
     * Distribue les cartes dans les piles.
     * @param paquet 
     */
    private void distribuerPiles(Paquet paquet){
        for(Stack<Carte> pile : piles){
            for(int i=0; i<5; i++){
                pile.add(paquet.distribuerUneCarte());
            }
        }
    }
    
    /**
     * Met à jour la valeur de l'atout, doit être exécuté au début d'un tour. Atout = null si pas d'atout
     */
    public SymboleCarte calculerAtout(){
        Carte max = null;
        
        for(Stack<Carte> pile : piles){
            Carte val= pile.peek(); // ValeurCarte de la carte retournée
            if(max != null){ 
                if(max.compareTo(val) < 0){ // Si c'est la plus grande ValeurCarte actuelle
                    max = val;
                }
            } else if(val.getValeur().getValeur() >= ValeurCarte.DIX.getValeur()){ // Si pas encore d'atout mais la carte actuelle est au moins un valet
                max = val;
            }
        }
        if(max != null){
            return max.getSymbole();
        } else {
            return null;
        }
    }
    
 // <editor-fold  desc="récupération cartes"> 
    /**
     * Regarde la carte en haut d'une pile sans la retirer de la pile
     * @param indexPile
     * @return la carte en haut de la pile d'indice indexPile
     */
    public Carte regarderCarte(int indexPile){
        if(indexPile >= 0 && indexPile < 6){
            if(piles.get(indexPile).size() > 0){
                return piles.get(indexPile).peek();
            } else {
                return null;
            }
        } else {
            throw new Error("Tentative d'accès à la pile d'index: " + indexPile);
        }
    }
    
    /**
     * Retourne la carte en haut de la pile, en la retirant de celle ci
     * @param indexPile
     * @return la carte en haut de la pile d'indice indexPile
     */
    public Carte recupererCarte(int indexPile){
        if(indexPile >= 0 && indexPile < 6){
            return piles.get(indexPile).pop();
        } else {
            throw new Error("Tentative d'accès à la pile d'index: " + indexPile);
        }
    }
    
    public ArrayList<Carte> getVisibles(){
        ArrayList<Carte> visibles = new ArrayList<Carte>();
        for(Stack<Carte> p : piles){
            visibles.add(p.peek());
        }
        return visibles;
    }
// </editor-fold>  
    
    public boolean estVide(){
        for(Stack<Carte> p : piles){
            if(p.empty()){
                return true;
            }
        }
        return false;
    }
    
}
