/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moteur;

import IA.IA;
import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
import java.util.ArrayList;

/**
 *
 * @author Pepefab
 */
public class IntelligenceIA implements Intelligence{

    private IA ia;
    
    public IntelligenceIA(IA ia){
        this.ia = ia;
    }
    
    @Override
    public int getCoup() {
        return ia.prochainCoup();
    }

    @Override
    public int getPioche() {
        return ia.prochainePioche();
    }

    @Override
    public void avertirAtout(SymboleCarte atout){
        ia.informerAtout(atout);
    }
    
    @Override
    public void avertirCoupAdversaire(Carte carte) {
        ia.informerCoupAdversaire(carte);
    }

    @Override
    public void avertirPiocheAdversaire(Carte pioche, Carte revelee) {
        ia.informerPiocheAdversaire(pioche,revelee);
    }
    
    public void avertirPioche(boolean ok){
        
    }

    @Override
    public void avertirErreur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void avertirTour(boolean oui) {
    }

    @Override
    public void montrerMain(ArrayList<Carte> main) {
        ia.informerMain(main);
    }

    @Override
    public void montrerPiles(ArrayList<Carte> piles) {
        ia.informerPiles(piles);
    }

    @Override
    public void confirmerCoup(boolean oui) {
    }

    @Override
    public void avertirVictoirePli() {
    }

    @Override
    public void avertirDefaitePli() {
    }

    @Override
    public void avertirVictoireManche() {
    }

    @Override
    public void avertirEgaliteManche() {
    }

    @Override
    public void avertirDefaiteManche() {
    }
    
}
