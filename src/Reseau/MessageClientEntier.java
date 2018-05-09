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
public class MessageClientEntier extends MessageClient {
    
    int donnees;
    
    public MessageClientEntier(CodeMessage code, DataInputStream fluxEntrant) throws IOException {
        super(code,fluxEntrant);
        donnees = fluxEntrant.readInt();
    }
    
}
