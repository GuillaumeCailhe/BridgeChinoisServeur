/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reseau;

import Carte.Carte;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Pepefab
 */
public class CommunicationClient {
    
    Socket client;
    DataInputStream fluxEntrant;
    DataOutputStream fluxSortant;

    Queue<MessageClient> buffer;
    
    public CommunicationClient(Socket client){
        this.client = client;
        try{
            this.fluxEntrant = new DataInputStream(client.getInputStream());
            this.fluxSortant = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            throw new java.lang.Error("Erreur I/O socket");
         }

        this.buffer = new LinkedList<MessageClient>();
    }
    
    /**
     * Sert à envoyer le pseudo de l'adversaire
     * @param pseudo 
     */
    public void envoyerPseudo(String pseudo){
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.PSEUDO);
        sb.append((byte) pseudo.length());
        sb.append(pseudo);
        envoyerDonnees(sb.toString());
    }
    
    /**
     * Sert à envoyer la main au début d'une manche
     * @param main 
     */
    public void envoyerMain(ArrayList<Carte> main){
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.MAIN);
        for(Carte c : main){
            sb.append((byte) c.getValeur().getValeur());
            sb.append((byte) c.getSymbole().getSymbole());
        }
        envoyerDonnees(sb.toString());
    }
    
    private void envoyerDonnees(String donnees){
        try {
            fluxSortant.writeChars(donnees);
        } catch(Exception e) {
            throw new java.lang.Error("Erreur d'envoi de données");
        }
    }
    
    public void recevoirDonnees(){
        MessageClient msg = new MessageClient(fluxEntrant);
        this.buffer.add(msg);
    }
    
    
}
