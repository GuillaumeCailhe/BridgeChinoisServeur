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
public class IAIntermediaire extends IA {
    
    private ArrayList<Carte> main;
    private ArrayList<Carte> piles;
    private SymboleCarte atout;
    private Carte carteAdversaire;
    private SymboleCarte couleurAdversaire;
    
    public IAIntermediaire(){
        
    }

    public void informerMain(ArrayList<Carte> main) {
        this.main = main;
        System.out.println(main);
    }

    public void informerPiles(ArrayList<Carte> piles) {
        this.piles = piles;
        System.out.println("informerPiles");
    }

    public int prochainCoup() {
        ArrayList<Carte> jouable = new ArrayList<Carte>();
        Random r = new Random();
        for(Carte c : main){
            if(couleurAdversaire == null || c.getSymbole() == couleurAdversaire || c.getSymbole() == atout){
                jouable.add(c);
            }
        }
        System.out.println("prochain coup jouable");
        
        if(jouable.size() == 0){
            System.out.println("prochain coup random");
            return r.nextInt(main.size());
        } else {
            int random = r.nextInt(3);
            int temp = 0;
            if(random == 0)
            {
                System.out.println("prochain coup max");
                return main.indexOf(jouable.get(jouable.size()-1));
            }
            
            else if(random == 1)
            {
                if(jouable.size() == 1 || jouable.size() == 2)
                    {
                        System.out.println("prochain coup middle card of 1 or 2");
                        return main.indexOf(jouable.get(r.nextInt(jouable.size())));
                    } 
                
                else{
                        if(jouable.size()%2 == 1)
                        {
                            System.out.println("prochain coup middle card odd");
                            return main.indexOf(jouable.get((jouable.size()/2)+1));
                        }
                        
                        else{
                            System.out.println("prochain coup middle card even");
                            return main.indexOf(jouable.get(jouable.size()/2));
                        }
                }
            }
                
            else{
                    for(Carte c: jouable){
                        
                        if(carteAdversaire.compareTo(c) <0){ //c>carteAdversaire
                            System.out.println("prochain coup lowest winning card found");
                            break;
                        }

                        else{
                            System.out.println("prochain coup lowest winning card not found");
                            temp++;
                        }                       
                    }
                    
                    if(temp == 0)
                    {
                    return main.indexOf(jouable.get(temp));
                    }
                    
                    else{
                        temp = temp - 1;
                        return main.indexOf(jouable.get(temp));
                    }
            }
            
        }        
                                 
    }
               
    public int prochainePioche() {
        System.out.println("prochaine pioche");
        Carte max = new Carte(ValeurCarte.DEUX, SymboleCarte.TREFLE);
        for(Carte c : piles){
            if(c != null && max.compareTo(c) < 0){
                max = c;
            }
        }
        return piles.indexOf(max);
    }

    public void informerAtout(SymboleCarte atout) {
        System.out.println("informerAtout");
        this.atout = atout;
    }

    public void informerCoupAdversaire(Carte carte) {
        System.out.println("informerCoupAdversaire");
        couleurAdversaire = carte.getSymbole();
        carteAdversaire = carte;
    }
    
    @Override
    public void informerPiocheAdversaire(Carte pioche, Carte revelee) {
        System.out.println("informerPiocheAdversaire");
        piles.set(piles.indexOf(pioche), revelee);
    }
    
    
    /**
     * 
     * @param c
     * @return 1 si la carte passée en paramètre est inférieure, -1 si elle est supérieure
     */
    private int comparerCartes(Carte carte1, Carte carte2){
        System.out.println("comparerCartes");
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

