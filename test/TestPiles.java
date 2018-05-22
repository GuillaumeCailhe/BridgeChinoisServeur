/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Carte.Paquet;
import Carte.Piles;
import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
import LibrairieCarte.ValeurCarte;
import java.util.ArrayList;
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
public class TestPiles {
    
    @Test
    public void testDistribution(){
        Paquet paquet = new Paquet();
        Piles piles = new Piles(paquet);
        assert paquet.getNbCartesRestantes() == 22;
        
        Carte c1 = piles.regarderCarte(0);
        Carte c1bis = piles.regarderCarte(0);
        assert c1 == c1bis;
        
        Carte c2 = piles.recupererCarte(1);
        Carte c3 = piles.recupererCarte(1);
        // On vérifie que la carte du dessus est bien retirée : on obtient une autre carte
        assert c2 != c3;
        // Permet de vérifier qu'on récupère des cartes différentes (surtout pour être sûr qu'il n'y a pas de problème d'indice dans la récupération.
        assert c1 != c2;
    }
    
    /*@Test
    public void testAtout(){
        Paquet paquet = new Paquet();
        Piles piles = new Piles(paquet);
        
        SymboleCarte atout = piles.calculerAtout();
        for(int i=0; i<6; i++){
            assert atout.getSymbole() >= piles.recupererCarte(i).getSymbole().getSymbole();
        }
    }*/
    
    public void testVisibles(){
        Paquet paquet = new Paquet();
        Piles piles = new Piles(paquet);
        
        ArrayList<Carte> visibles = piles.getVisibles();
        for(int i = 0; i < 6; i++){
            assert visibles.get(i) == piles.regarderCarte(i);
        }
    }
    
}
