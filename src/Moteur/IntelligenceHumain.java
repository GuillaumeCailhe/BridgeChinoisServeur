/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moteur;

import LibrairieCarte.Carte;
import LibrairieReseau.CodeMessage;
import LibrairieReseau.Communication;
import LibrairieReseau.MessageEntier;
import java.util.ArrayList;

/**
 *
 * @author Pepefab
 */
public class IntelligenceHumain implements Intelligence {

    private Communication client;
    
    public IntelligenceHumain(Communication client){
        this.client = client;
        this.client.addNotifie(this);
    }
    
    @Override
    public int getCoup() {
        attendreMessage();
        MessageEntier msg = (MessageEntier) client.getMessageParCode(CodeMessage.JOUER);
        if(msg == null){
            return -1;
        } else {
            return msg.getDonnees();
        }
    }

    @Override
    public int getPioche() {
        attendreMessage();
        MessageEntier msg = (MessageEntier) client.getMessageParCode(CodeMessage.PIOCHER);
        if(msg == null){
            return -1;
        } else {
            return msg.getDonnees();
        }    }

    @Override
    public void avertirCoupAdversaire(Carte carte) {
        ArrayList<Carte> arrayCarte = new ArrayList<Carte>();
        arrayCarte.add(carte);
        client.envoyerCartes(CodeMessage.JOUER_ADVERSAIRE, arrayCarte);
    }

    @Override
    public void avertirPiocheAdversaire(int i) {
        client.envoyerEntier(CodeMessage.PIOCHER, (byte) i);
    }

    @Override
    public void avertirVictoire() {
        client.envoyer(CodeMessage.VICTOIRE_MANCHE);
    }

    @Override
    public void avertirEgalite() {
        client.envoyer(CodeMessage.EGALITE_MANCHE);
    }

    @Override
    public void avertirDefaite() {
        client.envoyer(CodeMessage.DEFAITE_MANCHE);
    }

    @Override
    public void avertirErreur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void avertirTour(boolean oui) {
        if(oui){
            client.envoyer(CodeMessage.TOUR_OK);
        } else {
            client.envoyer(CodeMessage.TOUR_KO);
        }
    }

    @Override
    public void montrerMain(ArrayList<Carte> main) {
        client.envoyerCartes(CodeMessage.MAIN, main);
    }

    @Override
    public void montrerPiles(ArrayList<Carte> piles) {
        client.envoyerCartes(CodeMessage.PILES, piles);
    }
    
    private void attendreMessage(){
        if(client.getNbMessages() == 0){
            try {
                synchronized(this){
                    wait();
                }
            } catch (InterruptedException ex) {
                throw new Error("Erreur wait() dans IntelligenceHumain");
            }
        }
    }

    @Override
    public void confirmerCoup(boolean oui) {
        if(oui){
            client.envoyer(CodeMessage.JOUER_OK);
        } else {
            client.envoyer(CodeMessage.JOUER_KO);
        }
    }
    
}
