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
public class MessageClient {
    
    CodeMessage code;
    String donnees;
    
    public MessageClient(DataInputStream fluxEntrant){
        try{
            this.code = CodeMessage.values()[fluxEntrant.readByte()];
            
            switch(code){
                case PSEUDO:
                    this.donnees = fluxEntrant.readUTF();
                    
                    
            }
        } catch (Exception e){
            throw new Error("Erreur lecture message client");
        }

        
    }
    
}
