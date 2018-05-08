package Moteur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pepefab
 */
public enum ModeDeJeu {
    JOUEUR_CONTRE_JOUEUR(0),
    JOUEUR_CONTRE_IA_FACILE(1),
    JOUEUR_CONTRE_IA_INTERMEDIAIRE(2),
    JOUEUR_CONTRE_IA_DIFFICILE(3);
    
    int mode;

    ModeDeJeu(int mode){
        this.mode = mode;
    }
}