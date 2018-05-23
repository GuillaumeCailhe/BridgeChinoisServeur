/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
import java.util.ArrayList;

/**
 *
 * @author Pepefab
 */
public abstract class IA {
    
    public abstract void informerMain(ArrayList<Carte> main);
    
    public abstract void informerPiles(ArrayList<Carte> piles);

    public abstract int prochainCoup();

    public abstract int prochainePioche();

    public abstract void informerAtout(SymboleCarte atout);
    
    public abstract void informerCoupAdversaire(Carte carte);
    
    public abstract void informerPiocheAdversaire(Carte pioche, Carte revelee);
}
