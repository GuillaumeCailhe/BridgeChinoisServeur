/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import LibrairieCarte.Carte;
import java.util.ArrayList;

/**
 *
 * @author Pepefab
 */
public class NoeudArbre {
    
    private ArrayList<NoeudArbre> fils;
    private Carte carteJouee;
    private Carte carteAdversaire;
    
    public NoeudArbre(Carte carteJouee, Carte carteAdversaire){
        this.carteJouee = carteJouee;
        this.carteAdversaire = carteAdversaire;
        this.fils = new ArrayList<NoeudArbre>();
    }
    
}
