/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reseau;

import LibrairieReseau.Communication;
import LibrairieReseau.Message;
import LibrairieMoteur.ModeDeJeu;
import Moteur.Moteur;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pepefab
 */
public class Serveur {
    
    private Lobby lobby;
    private ServerSocket socketServeur;
    
    public Serveur() throws IOException{
        this.lobby = new Lobby();
        Thread tlobby = new Thread(lobby);
        tlobby.start();
        
        socketServeur = new ServerSocket(31000);
        socketServeur.setSoTimeout(0); // Pas de timeout sur l'accept
        
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    try {
                        boucle();
                    } catch (IOException ex) {
                        Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, 
            0,
            100
        );
       
    }
    
    private void boucle() throws IOException{
        Communication client = new Communication(socketServeur.accept());
        System.out.println("Nouveau joueur: " + client.getSocket().getInetAddress());
        Thread t = new Thread(client);
        t.start();
        lobby.ajouterClient(client);
    }
    
}
