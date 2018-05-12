/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moteur;

import LibrairieReseau.Communication;
import IA.IA;
import LibrairieCarte.Carte;

/**
 *
 * @author Pepefab
 */
public interface Intelligence {
    
    public int getCoup();

    public int getPioche();
    
    public void avertirCoupAdversaire(Carte carte);
    
    public void avertirPiocheAdversaire(int i);
    
    public void avertirVictoire();
    
    public void avertirEgalite();
    
    public void avertirDefaite();
    
    public void avertirErreur();
    
}
