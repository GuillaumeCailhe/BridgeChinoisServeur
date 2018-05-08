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
public enum SymboleCarte {
    TREFLE(0),
    CARREAU(1),
    COEUR(2),
    PIQUE(3);
    
    private int symbole;
    
    SymboleCarte(int symbole){
        this.symbole = symbole;
    }   
    
    /**
     * 
     * @return un entier représentant la valeur du symbole
     */
    public int getSymbole(){
        return this.symbole;
    }
    
    @Override
    public String toString(){
        switch(this.getSymbole()){
            case 0:
                return "Trèfle";
            case 1:
                return "Carreau";
            case 2:
                return "Coeur";
            case 3:
                return "Pique";
            default:
                throw new java.lang.Error("Erreur de valeur de carte : " + this.getSymbole()); 
        }
    }
    
}
