/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reseau;

import LibrairieReseau.Communication;
import java.util.LinkedList;

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
        // On parcours les clients en attentes et on regarde s'ils ont envoyé une requête de partie
        while(true){
            for(Communication client : clientsDevantEtreTraites){
                if(client.getMessage() != null){
                    // PENSE A THREADER LE MOTEUR Thread t = new Thread(moteur); t.start();
                    // Il faudra aussi modifier la façon dont le moteur s'initialise, passer le maximum de code du constructeur vers run()
                    
                    // Traiter la demande du client :
                    //  si la demande est une requête de partie (sinon ejecter le client ?)
                    //      si JcJ
                    //          si clienEnAttente != null
                    //              lancer partie
                    //          sinon
                    //              clientEnAttente = client
                    //      Sinon si JCJ
                    //          lancer partie
                    //      Sinon si charger
                    //          charger sauvegarde
                }
            }
        }
    }
    
}
