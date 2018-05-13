/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reseau;

import LibrairieReseau.Communication;
import LibrairieReseau.Message;
import Moteur.ModeDeJeu;
import Moteur.Moteur;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Pepefab
 */
public class Serveur {
    
    public Serveur() throws IOException{
        Lobby lobby = new Lobby();
        Thread tlobby = new Thread(lobby);
        tlobby.start();
        
        ServerSocket socketServeur = new ServerSocket(31000);
        socketServeur.setSoTimeout(0); // Pas de timeout sur l'accept
        
        while(true){
            Communication client = new Communication(socketServeur.accept(),lobby);
            System.out.println("Nouveau joueur: " + client.getSocket().getInetAddress());
            Thread t = new Thread(client);
            t.start();
            lobby.ajouterClient(client);
        }    
    }
    
}
