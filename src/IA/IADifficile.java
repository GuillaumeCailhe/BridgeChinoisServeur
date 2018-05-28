/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import Carte.Paquet;
import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
import LibrairieCarte.ValeurCarte;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Pepefab
 */
public class IADifficile extends IA {
 
    private ArrayList<Carte> cachee;
    private ArrayList<Carte> main;
    private ArrayList<Carte> piles;
    private SymboleCarte atout;
    private SymboleCarte couleurAdversaire;
    
    public IADifficile(){
        this.cachee = new ArrayList<Carte>();
        for(ValeurCarte v : ValeurCarte.values()){
            for(SymboleCarte s : SymboleCarte.values()){
                this.cachee.add(new Carte(v,s));
            }
        }  
    }
    
    public int prochainCoup(){
        ArrayList<Carte> jouable = new ArrayList<Carte>();
        for(Carte c : main){
            if(couleurAdversaire == null || c.getSymbole() == couleurAdversaire || c.getSymbole() == atout){
                jouable.add(c);
            }
        }
        
        Carte choix = null;
        
        if(cachee.size() == 13){
            // minmax
        } else {
            
        }
        /*
        Si aucune bonne carte
            jouerPirecarte()
        Sinon si 1 bonne carte 
            jouerMeilleureCarte()
        Sinon si plusieurs bonnes cartes
            jouerCarteMoyenne()
        */
        
        
        //System.out.println(jouable);
        couleurAdversaire = null;
        return main.indexOf(choix);
    }
    
    private Carte jouerPireCarte(ArrayList<Carte> jouable){
        Carte min;
        int i = 0;
        do {
            min = jouable.get(i);
            i++;
        } while(min.getSymbole() == atout);
        
        if(jouable.indexOf(min) == jouable.size()-1){
            min = jouable.get(0);
        }
        
        return min;
    }
    
    private Carte jouerMeilleureCarte(ArrayList<Carte> jouable){
        Carte min;
        int i = jouable.size()-1;
        do {
            min = jouable.get(i);
            i--;
        } while(min.getSymbole() != atout);
        
        if(jouable.indexOf(min) == 0){
            min = jouable.get(jouable.size()-1);
        }
        
        return min;
    }
    
    /**
     * Permet de forcer l'adversaire à utiliser une bonne carte pour contrer celle là
     * @param jouable
     * @return Une carte qui est si possible un atout, mais d'une valeur comprise entre 4 et 9, sinon fait appel à jouerPireCarte()
     */
    private Carte jouerCarteMoyenne(ArrayList<Carte> jouable){
        Carte min;
        int i = 0;
        do {
            min = jouable.get(i);
            i++;
        } while(min.getSymbole() != atout || min.getValeur().getValeur() < ValeurCarte.QUATRE.getValeur() );
        
        if(jouable.indexOf(min) == jouable.size()-1 || min.getValeur().getValeur() > ValeurCarte.NEUF.getValeur()){
            min = jouable.get(0);
        }
        
        return min;
    }
    
    public int prochainePioche(){
        Carte max = piles.get(0);
        for(Carte c : piles){
            if(c != null && max.compareTo(c) < 0){
                max = c;
            }
        }
        return piles.indexOf(max);
    }
    
    public void informerMain(ArrayList<Carte> main){
        this.main = main;
        for(Carte c1 : this.main){
            for(Carte c2 : this.cachee){
                if(cartesIdentiques(c1,c2)){
                    this.cachee.remove(c2);
                }
            }
        }
        //System.out.println(main);
    }
    
    public void informerPiles(ArrayList<Carte> piles){
        this.piles = piles;
        for(Carte c1 : this.piles){
            for(Carte c2 : this.cachee){
                if(cartesIdentiques(c1,c2)){
                    this.cachee.remove(c2);
                }
            }
        }
    }
    
    public void informerAtout(SymboleCarte atout){
        this.atout = atout;
    }
    
    public void informerCoupAdversaire(Carte carte){
        couleurAdversaire = carte.getSymbole();
    }
    
    @Override
    public void informerPiocheAdversaire(Carte pioche, Carte revelee) {
        piles.set(piles.indexOf(pioche), revelee);
    }
    
     /**
     * Compare 2 cartes en prenant en compte l'atout
     * @param carte1
     * @param carte2
     * @return 1 si carte1>carte2, -1 si carte2>carte1
     */
    private int comparerCartes(Carte carte1, Carte carte2){
        if(carte1.getSymbole() == atout && carte2.getSymbole() != atout){ // règle de l'atout
            return 1;
        } else if (carte2.getSymbole() == atout && carte1.getSymbole() != atout){ // règle de l'atout
            return -1;
        } else if (carte1.getSymbole() != carte2.getSymbole()){ // défausse
                return 1;
        } else if(carte1.getSymbole() == carte2.getSymbole()){ // même symbole
            return carte1.compareTo(carte2);
        } else { // ce cas ne peut pas arriver à cause des 2 conditions précédentes
            return 0; 
        }
    }
    
    private boolean cartesIdentiques(Carte carte1, Carte carte2){
        return carte1.getSymbole() == carte2.getSymbole() && carte1.getValeur() == carte2.getValeur();
    }
    
    private boolean piocheVide(){
        for(Carte p : piles){
            if(p != null){
                return false;
            }
        }
        return true;
    }

    
    
}
