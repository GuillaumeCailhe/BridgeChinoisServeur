/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
import LibrairieCarte.ValeurCarte;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pepefab
 */
public class TestCarte {
    
    
    @Test
    public void testCouleurs() {
        Carte coeur = new Carte(ValeurCarte.DIX,SymboleCarte.COEUR);
        assertEquals(coeur.estNoire(),false);
        assertEquals(coeur.estRouge(),true);
        
        Carte carreau = new Carte(ValeurCarte.DIX,SymboleCarte.CARREAU);
        assertEquals(carreau.estNoire(),false);
        assertEquals(carreau.estRouge(),true);
        
        Carte pique = new Carte(ValeurCarte.DIX,SymboleCarte.PIQUE);
        assertEquals(pique.estNoire(),true);
        assertEquals(pique.estRouge(),false);
        
        Carte trefle = new Carte(ValeurCarte.DIX,SymboleCarte.TREFLE);
        assertEquals(trefle.estNoire(),true);
        assertEquals(trefle.estRouge(),false);
    }
    
    @Test
    public void testGetters() {
        Carte coeur = new Carte(ValeurCarte.DIX,SymboleCarte.COEUR);
        assertEquals(coeur.getValeur(),ValeurCarte.DIX);
        assertEquals(coeur.getSymbole(),SymboleCarte.COEUR);
        
        Carte carreau = new Carte(ValeurCarte.NEUF,SymboleCarte.PIQUE);
        assertEquals(carreau.getValeur(),ValeurCarte.NEUF);
        assertEquals(carreau.getSymbole(),SymboleCarte.PIQUE);
    }
    
    @Test
    public void testCompareTo(){
        // Carte de valeur minimum (2 de trèfle)
        Carte min = new Carte(ValeurCarte.DEUX,SymboleCarte.TREFLE); 
        // Carte de valeur maximum (as de pique)
        Carte max = new Carte(ValeurCarte.AS,SymboleCarte.PIQUE);
        // 4 cartes de même valeurs, mais de symbole différents
        Carte moy1 = new Carte(ValeurCarte.VALET,SymboleCarte.PIQUE);
        Carte moy2 = new Carte(ValeurCarte.VALET,SymboleCarte.COEUR);
        Carte moy3 = new Carte(ValeurCarte.VALET,SymboleCarte.CARREAU);
        Carte moy4 = new Carte(ValeurCarte.VALET,SymboleCarte.TREFLE);
        
        // On vérifie la comparaison simple, et qu'elle soit réciproque
        // min < max, doit donc retourner -1
        assert min.compareTo(max) == -1;
        // max > min, doit donc retourner 1
        assert max.compareTo(min) == 1;

        // moy1 > moy2 > moy3 > moy4, on est 
        assert moy1.compareTo(moy2) == 1;
        assert moy2.compareTo(moy3) == 1;
        assert moy3.compareTo(moy4) == 1;
        // On en déduit que moy4 < moy1, donc on doit obtenir -1
        assert moy4.compareTo(moy1) == -1;    
    }
    
    @Test
    public void testToString(){
        Carte c = new Carte(ValeurCarte.ROI, SymboleCarte.COEUR);
        assert c.toString().equals("Roi de Coeur");
    }
    
}
