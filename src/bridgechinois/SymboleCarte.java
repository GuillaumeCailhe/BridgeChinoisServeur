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
public enum SymboleCarte {
    TREFLE(0),
    CARREAU(1),
    COEUR(2),
    PIQUE(3);
    
    int symbole;
    
    SymboleCarte(int symbole){
        this.symbole = symbole;
    }    
}
