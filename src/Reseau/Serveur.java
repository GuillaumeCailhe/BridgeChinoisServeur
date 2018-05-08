/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reseau;

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
        Socket clientEnAttente = null;
        
        ServerSocket socketServeur = new ServerSocket(31000);
        socketServeur.setSoTimeout(10000);
        
        while(true){
            Socket client = socketServeur.accept();
            System.out.println("Nouveau joueur: " + client.getInetAddress());
            if(clientEnAttente != null){ // On lance la partie
                Moteur moteur = new Moteur(ModeDeJeu.JOUEUR_CONTRE_JOUEUR, 2, new CommunicationClient(clientEnAttente), new CommunicationClient(client));
                Thread t = new Thread(moteur);
                t.start();
            } else { // On met le joueur en attente
                clientEnAttente = client;
            }
        }    
    }
    
}
