/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
import LibrairieCarte.ValeurCarte;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Pepefab
 */
public class IAFacile extends IA {
    
    private ArrayList<Carte> main;
    private ArrayList<Carte> piles;
    private SymboleCarte atout;
    private SymboleCarte couleurAdversaire;
    
    
    public IAFacile(){
    }
    
    public int prochainCoup(){
        ArrayList<Carte> jouable = new ArrayList<Carte>();
        for(Carte c : main){
            if(couleurAdversaire == null || c.getSymbole() == couleurAdversaire || c.getSymbole() == atout){
                jouable.add(c);
            }
        }
        //System.out.println(jouable);
        couleurAdversaire = null;
        Random r = new Random();
        if(jouable.size() == 0){
            return r.nextInt(main.size());
        } else {
            return main.indexOf(jouable.get(r.nextInt(jouable.size())));
        }
    }
    
    public int prochainePioche(){
        Carte max = new Carte(ValeurCarte.DEUX, SymboleCarte.TREFLE);
        for(Carte c : piles){
            if(c != null && max.compareTo(c) < 0){
                max = c;
            }
        }
        return piles.indexOf(max);
    }
    
    public void informerMain(ArrayList<Carte> main){
        this.main = main;
        System.out.println(main);
    }
    
    public void informerPiles(ArrayList<Carte> piles){
        this.piles = piles;
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

    
    
}
