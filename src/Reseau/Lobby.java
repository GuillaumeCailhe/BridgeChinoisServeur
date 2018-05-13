/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reseau;

import LibrairieReseau.CodeMessage;
import LibrairieReseau.Communication;
import Moteur.ModeDeJeu;
import Moteur.Moteur;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pepefab
 */
public class Lobby implements Runnable{
    
    private LinkedList<Communication> clientsDevantEtreTraites;
    
    private Communication clientEnAttente;
    
    public Lobby(){
        clientsDevantEtreTraites = new LinkedList<Communication>();
        clientEnAttente = null;
    }
    
    public void ajouterClient(Communication client){
        clientsDevantEtreTraites.add(client);
    }

    @Override
    public void run() {
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    boucle();
                }
            }, 
            500,
            500
        );
    }
    
    private void boucle(){
        Thread t;
        Moteur moteur;
        for(Communication client : clientsDevantEtreTraites){
            if(client.getNbMessages() > 0){
                switch(client.getPremierMessage().getCode()){
                    case PARTIE_JCJ:
                        if(clientEnAttente == null){
                            System.out.println("Un joueur demande de jouer, mais il est seul");
                            clientEnAttente = client;
                        } else {
                            System.out.println("Deux joueurs veulent joeur, c'est parti !");
                            // Nouvelle partie JCJ
                            client.envoyer(CodeMessage.PARTIE_DEMARRER);
                            clientEnAttente.envoyer(CodeMessage.PARTIE_DEMARRER);
                            moteur = new Moteur(ModeDeJeu.JOUEUR_CONTRE_JOUEUR, clientEnAttente, client);
                            t = new Thread(moteur);
                            t.start();
                        }
                        clientsDevantEtreTraites.remove(client);
                        break;
                    case PARTIE_JCFACILE:
                        moteur = new Moteur(ModeDeJeu.JOUEUR_CONTRE_IA_FACILE, client);
                        t = new Thread(moteur);
                        t.start();  
                        clientsDevantEtreTraites.remove(client);
                        break;
                    case PARTIE_JCINTERMEDIAIRE:
                        moteur = new Moteur(ModeDeJeu.JOUEUR_CONTRE_IA_INTERMEDIAIRE, client);
                        t = new Thread(moteur);
                        t.start();    
                        clientsDevantEtreTraites.remove(client);
                        break;                     
                    case PARTIE_JCDIFFICILE:
                        moteur = new Moteur(ModeDeJeu.JOUEUR_CONTRE_IA_DIFFICILE,  client);
                        t = new Thread(moteur);
                        t.start(); 
                        clientsDevantEtreTraites.remove(client);
                        break;
                        // case PARTIE_CHARGER:
                }
            }
        }
    }
    
}
