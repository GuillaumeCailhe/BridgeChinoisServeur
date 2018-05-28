/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Pepefab
 */
public class IAIntermediaire extends IA {
    
    private ArrayList<Carte> main;
    private ArrayList<Carte> piles;
    private SymboleCarte atout;
    private Carte carteAdversaire;
    private SymboleCarte couleurAdversaire;
    
    public IAIntermediaire(){
        
    }

    @Override
    public void informerMain(ArrayList<Carte> main) {
        this.main = main;
        System.out.println(main);
    }

    @Override
    public void informerPiles(ArrayList<Carte> piles) {
        this.piles = piles;
    }

    @Override
    public int prochainCoup() {
        ArrayList<Carte> jouable = new ArrayList<Carte>();
        Random r = new Random();
        for(Carte c : main){
            if(couleurAdversaire == null || c.getSymbole() == couleurAdversaire || c.getSymbole() == atout){
                jouable.add(c);
            }
        }
        
        if(jouable.size() == 0){
            return r.nextInt(main.size());
        } else {
        
            switch (r.nextInt(3)) {
                case 0:
                    return main.indexOf(jouable.get(jouable.size()));

                case 1:
                    if(jouable.size() == 1 || jouable.size() == 2)
                    {
                        return main.indexOf(jouable.get(r.nextInt(jouable.size())));
                    } else{
                        if(jouable.size()%2 == 1)
                        {
                            return main.indexOf(jouable.get((jouable.size()/2)+1));
                        }else{
                            return main.indexOf(jouable.get(jouable.size()/2));
                        }
                    }

                default:
                    int temp = 0;
                    for(Carte c: jouable){
                        if(comparerCartes(c, carteAdversaire) == 1){ //c>carteAdversaire
                            break;
                        }

                        else{
                            temp++;
                        }
                    }
                return main.indexOf(jouable.get(temp));

            }
            
        }
               
    }

    @Override
    public int prochainePioche() {
        Carte max = piles.get(0);
        for(Carte c : piles){
            if(c != null && max.compareTo(c) < 0){
                max = c;
            }
        }
        return piles.indexOf(max);
    }

    @Override
    public void informerAtout(SymboleCarte atout) {
        this.atout = atout;
    }

    @Override
    public void informerCoupAdversaire(Carte carte) {
        couleurAdversaire = carte.getSymbole();
        carteAdversaire = carte;
    }

    @Override
    public void informerPiocheAdversaire(Carte pioche, Carte revelee) {
        piles.set(piles.indexOf(pioche), revelee);
    }
    
    
    /**
     * 
     * @param c
     * @return 1 si la carte passée en paramètre est inférieure, -1 si elle est supérieure
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

//assigned the opponent's card to carteAdversaireu
