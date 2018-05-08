/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carte;

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
    
    private int valeur;
    
    ValeurCarte(int valeur){
        this.valeur = valeur;
    } 
    
    /**
     * 
     * @return un entier représentant la valeur de la valeur.
     */
    public int getValeur(){
        return valeur;
    }
    
    @Override
    public String toString(){
        switch(this.getValeur()){
            case 0:
                return "2";
            case 1:
                return "3";
            case 2:
                return "4";
            case 3:
                return "5";
            case 4:
                return "6";
            case 5:
                return "7";
            case 6:
                return "8";
            case 7:
                return "9";
            case 8:
                return "10";
            case 9:
                return "Valet";
            case 10:
                return "Dame";
            case 11:
                return "Roi";
            case 12:
                return "As";
            default:
                throw new java.lang.Error("Erreur de valeur de carte : " + this.getValeur()); 
        }
    }
}
