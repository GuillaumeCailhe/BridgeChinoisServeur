/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridgechinois;

import java.util.ArrayList;

/**
 *
 * @author Pepefab
 */
public abstract class Joueur {
    
    protected String pseudo;
    protected ArrayList<Carte> main;
    protected Moteur moteur;
    
    public Joueur(String pseudo, ArrayList<Carte> main, Moteur moteur){
        this.pseudo = pseudo;
        this.main = main;
        this.moteur = moteur;
    }
    
    public boolean estIA(){
        return this instanceof JoueurIA;
    }
    
}
