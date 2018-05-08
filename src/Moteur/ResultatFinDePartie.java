/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moteur;

/**
 *
 * @author helgr
 */
public enum ResultatFinDePartie {
    DEFAITE(0),
    VICTOIRE(1),
    EGALITE(2);
    
    int resultat;
    
    ResultatFinDePartie(int resultat){
        this.resultat = resultat;
    }
}
