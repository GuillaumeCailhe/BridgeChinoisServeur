/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moteur;

import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
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
        return msg.getDonnees();
    }

    @Override
    public int getPioche() {
        attendreMessage();
        MessageEntier msg = (MessageEntier) client.getMessageParCode(CodeMessage.PIOCHER);
        System.out.println("pioche ok");
        return msg.getDonnees();
    }

    @Override
    public void avertirAtout(SymboleCarte atout){
        if(atout != null){
            client.envoyerEntier(CodeMessage.ATOUT, (byte) atout.getSymbole());
        } else {
            client.envoyerEntier(CodeMessage.ATOUT, (byte) 4);
        }
    }
    
    @Override
    public void avertirCoupAdversaire(Carte carte) {
        ArrayList<Carte> arrayCarte = new ArrayList<Carte>();
        arrayCarte.add(carte);
        client.envoyerCartes(CodeMessage.JOUER_ADVERSAIRE, arrayCarte);
    }

    @Override
    public void avertirPiocheAdversaire(Carte pioche, Carte revelee) {        
        ArrayList<Carte> arrayCarte = new ArrayList<Carte>();
        arrayCarte.add(pioche);
        arrayCarte.add(revelee);
        client.envoyerCartes(CodeMessage.PIOCHER_ADVERSAIRE,arrayCarte);
    }
    
    @Override
    public void avertirPioche(boolean ok){
        if(ok){
            client.envoyer(CodeMessage.PIOCHER_OK);
        } else {
            client.envoyer(CodeMessage.PIOCHER_KO);
        }
    }
    
    @Override
    public void avertirVictoirePli() {
        client.envoyer(CodeMessage.VICTOIRE_PLI);
    }

    @Override
    public void avertirDefaitePli() {
        client.envoyer(CodeMessage.DEFAITE_PLI);
    }
    
    @Override
    public void avertirVictoireManche() {
        client.envoyer(CodeMessage.VICTOIRE_MANCHE);
    }

    @Override
    public void avertirEgaliteManche() {
        client.envoyer(CodeMessage.EGALITE_MANCHE);
    }

    @Override
    public void avertirDefaiteManche() {
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
    
    private synchronized void attendreMessage(){
        if(client.getNbMessages() == 0){
            try {
                wait();
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
