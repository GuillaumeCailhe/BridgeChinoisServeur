/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moteur;

import LibrairieReseau.Communication;
import IA.IA;
import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
import java.util.ArrayList;

/**
 *
 * @author Pepefab
 */
public interface Intelligence {
    
    public int getCoup();

    public int getPioche();
    
    public void avertirAtout(SymboleCarte atout);
    
    public void avertirCoupAdversaire(Carte carte);
    
    public void avertirPiocheAdversaire(Carte pioche, Carte revelee);
    
    public void avertirVictoirePli();
        
    public void avertirDefaitePli();
    
    public void avertirPioche(boolean ok);
    
    public void avertirVictoireManche();
    
    public void avertirEgaliteManche();
    
    public void avertirDefaiteManche();
    
    public void avertirErreur();
    
    public void avertirTour(boolean oui);
    
    public void confirmerCoup(boolean oui);
    
    public void montrerMain(ArrayList<Carte> main);
    
    public void montrerPiles(ArrayList<Carte> piles);
    
}
