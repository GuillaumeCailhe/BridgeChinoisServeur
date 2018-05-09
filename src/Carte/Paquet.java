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
import java.util.Collections;

/**
 *
 * @author Pepefab
 */
public class Paquet {
    private ArrayList<Carte> cartes;
    
    public Paquet(){
        this.cartes = new ArrayList<Carte>();
        this.sortir();
        this.melanger();
    }
    
    /**
     * On "sort" le paquet de carte, correspond à l'initialisation de cartes
     */
    private void sortir(){
        for(SymboleCarte symbole : SymboleCarte.values()){
            for(ValeurCarte valeur : ValeurCarte.values()){
                cartes.add(new Carte(valeur,symbole));
            }
        }
    }
    
    /**
     * On mélange le paquet de carte
     */
    private void melanger(){
        Collections.shuffle(cartes);
    }
    
    /**
     * Retourne le nombre de cartes restantes dans la paquet
     * @return 
     */
    public int getNbCartesRestantes(){
        return this.cartes.size();
    }
    
    public Carte distribuerUneCarte(){
        if(cartes.size() > 0 ){
            Carte c = cartes.get(0);
            cartes.remove(0);
            return c; 
        } else {
            throw new Error("Plus de cartes dans le paquet");
        }
    }
    
        
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("PAQUET: ");
        for(Carte c : cartes){
            sb.append(c + "; ");
        }
        return sb.toString();
    }
   
    
}
