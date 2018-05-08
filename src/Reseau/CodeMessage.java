/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reseau;

/**
 *
 * @author Pepefab
 */
public enum CodeMessage {
    PSEUDO((byte) 0),
    MAIN((byte) 1),
    JOUER((byte) 2),
    JOUER_OK((byte) 3),
    JOUER_KO((byte) 4),
    PIOCHER((byte) 5),
    PIOCHER_OK((byte) 6),
    PIOCHER_KO((byte) 7),
    CAPITULER((byte) 8),
    ANNULER((byte) 9),
    SAUVEGARDER((byte) 10),
    CHARGER((byte) 11),
    VICTOIRE((byte) 12),
    DEFAITE((byte) 13),
    EGALITE((byte) 14),
    MESSAGE_CHAT((byte) 15);
    
    private byte code;

    CodeMessage(byte code){
        this.code = code;
    }
    
    public byte getCode(){
        return code;
    }
    
}
