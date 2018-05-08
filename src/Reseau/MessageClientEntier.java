/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reseau;

import java.io.DataInputStream;

/**
 *
 * @author Pepefab
 */
public class MessageClientEntier extends MessageClient {
    
    public MessageClientEntier(CodeMessage code, DataInputStream fluxEntrant) {
        super(code,fluxEntrant);
    }
    
}
