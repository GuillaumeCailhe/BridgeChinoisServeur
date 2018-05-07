/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridgechinois;

/**
 *
 * @author Pepefab
 */
public class Carte {
    private ValeurCarte valeur;
    private SymboleCarte symbole;
    
    /**
     * 
     * @param valeur : la valeur de la carte (2,3,...,valet,dame,roi,as)
     * @param symbole : le symbole de la carte (pique, coeur, trefle, carreau)
     */
    public Carte(ValeurCarte valeur, SymboleCarte symbole){
        this.valeur = valeur;
        this.symbole = symbole;
    }
}
