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
    PIOCHER((byte) 4),
    PIOCHER_OK((byte) 5),
    CAPITULER((byte) 6),
    ANNULER((byte) 7),
    SAUVEGARDER((byte) 8),
    VICTOIRE((byte) 9),
    DEFAITE((byte) 10),
    EGALITE((byte) 11),
    MESSAGE_CHAT((byte) 12);
    
    private byte code;

    CodeMessage(byte code){
        this.code = code;
    }
    
    public byte getCode(){
        return code;
    }
    
}
