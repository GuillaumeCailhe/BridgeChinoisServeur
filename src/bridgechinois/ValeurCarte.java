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
public enum ValeurCarte {
    DEUX(0),
    TROIS(1),
    QUATRE(2),
    CINQ(3),
    SIX(4),
    SEPT(5),
    HUIT(6),
    NEUF(7),
    DIX(8),
    VALET(9),
    DAME(10),
    ROI(11),
    AS(12);
    
    int valeur;
    
    ValeurCarte(int valeur){
        this.valeur = valeur;
    } 
}
