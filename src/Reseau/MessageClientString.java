/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reseau;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Pepefab
 */
public class MessageClientString extends MessageClient {
    
    String donnees;
    
    public MessageClientString(CodeMessage code, DataInputStream fluxEntrant) throws IOException {
        super(code,fluxEntrant);
        int taille = fluxEntrant.readByte();
        donnees = "";
        for(int i=0; i<taille; i++){
            donnees += fluxEntrant.readChar();
        }
    }
    
}
