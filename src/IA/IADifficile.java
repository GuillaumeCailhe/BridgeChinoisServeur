/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import Carte.Paquet;
import LibrairieCarte.Carte;
import LibrairieCarte.SymboleCarte;
import LibrairieCarte.ValeurCarte;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Pepefab
 */
public class IADifficile extends IA {
 
    private ArrayList<Carte> cachee;
    private ArrayList<Carte> main;
    private ArrayList<Carte> piles;
    private ArrayList<Integer> profondeurPiles;
    private SymboleCarte atout;
    private Carte carteAdversaire;
    
    public IADifficile(){
        this.cachee = new ArrayList<Carte>();
        for(ValeurCarte v : ValeurCarte.values()){
            for(SymboleCarte s : SymboleCarte.values()){
                this.cachee.add(new Carte(v,s));
            }
        }  
    }
    
    public int prochainCoup(){
        System.out.println(main);
        
        ArrayList<Carte> jouable = new ArrayList<Carte>();
        for(Carte c : main){
            if(carteAdversaire == null || c.getSymbole() == carteAdversaire.getSymbole() || c.getSymbole().estAtout()){
                jouable.add(c);
            }
        }
        
        
        
        Carte choix = null;
        
        if(cachee.size() <= 13){
            // minmax
        } else if (jouable.size() > 0){
            if(carteAdversaire == null){ // on joue en premier
                switch(valeurPile()){
                    case 0:
                        System.out.println("l'IA veut perdre");
                        choix = jouerPireCarte(jouable);
                        break;
                    case 1:
                        System.out.println("l'IA ne tente pas de gagner");
                        choix = jouerCarteMoyenne(jouable);
                        break;
                    case 2:
                        choix = jouerMeilleureCarte(jouable);
                        System.out.println("l'IA tente de gagner");
                        float proba = calculProbaGagner(choix); 
                        System.out.println("l'IA a une probabilité de " + proba + " de gagner");
                        if(proba < 0.5){
                            System.out.println("l'IA a peu de chance de gagner, elle choisit donc de jouer une carte moyenne");
                            choix = jouerCarteMoyenne(jouable);
                        }
                        break;
                }   
            } else {
                switch(valeurPile()){
                    case 0:
                        choix = jouerPireCarte(jouable);
                        break;
                    case 1:
                        choix = jouerPireCarteGagnante(jouable); // On essaye de gagner de justtesse
                        if(main.indexOf(choix) >= main.size()/2){ // Si on doit utiliser une bonne carte pour gagner le pli, il vaut mieu perdre
                            choix = jouerPireCarte(jouable);
                        }
                        break;
                    case 2: 
                        choix = jouerPireCarteGagnante(jouable);
                        break;
                }  
            }
        } else {
            choix = jouerPireCarte(main);
        }
        
        //System.out.println(jouable);
        carteAdversaire = null;
        return main.indexOf(choix);
    }
    
    private Carte jouerPireCarte(ArrayList<Carte> jouable){
        Carte min;
        int i = 0;
        do {
            min = jouable.get(i);
            i++;
        } while(i < jouable.size() && min.getSymbole() == atout);
        
        if(jouable.indexOf(min) == jouable.size()-1){
            min = jouable.get(0);
        }
        
        return min;
    }
    
    private Carte jouerMeilleureCarte(ArrayList<Carte> jouable){
        Carte min;
        int i = jouable.size()-1;
        do {
            min = jouable.get(i);
            i--;
        } while(i >= 0 && min.getSymbole() != atout);
        
        if(jouable.indexOf(min) == 0){
            min = jouable.get(jouable.size()-1);
        }
        
        return min;
    }
    
    /**
     * 
     * @param jouable
     * @return Une carte aléatoire qui n'est pas un atout ni un 2 ni un as, sinon la partie carte
     */
    private Carte jouerCarteMoyenne(ArrayList<Carte> jouable){
        ArrayList<Carte> pasAtoutOu2 = new ArrayList<Carte>();
        for(Carte c : jouable){
            if(!c.getSymbole().estAtout() && c.getValeur().getValeur() > ValeurCarte.DEUX.getValeur() && c.getValeur().getValeur() < ValeurCarte.AS.getValeur()){
                pasAtoutOu2.add(c);
            }
        }
        
        if(pasAtoutOu2.size() == 0){
            return jouable.get(0);
        } else {
            Random r = new Random();
            return pasAtoutOu2.get(r.nextInt(pasAtoutOu2.size()));
        }
    }
    
    private Carte jouerPireCarteGagnante(ArrayList<Carte> jouable){
        Carte min;
        int i = 0;
        do {
            min = jouable.get(i);
            i++;
        } while(i < jouable.size() && min.compareTo(carteAdversaire) < 0);
        
        if(jouable.indexOf(min) == jouable.size()-1 && min.compareTo(carteAdversaire) < 0){
            min = jouerPireCarte(jouable);
        }
        
        return min;
    }
    
    public int prochainePioche(){
        Carte max = null;
        for(Carte c : piles){
            if(max == null && c != null){
                max = c;
            } else if(c != null && max.compareTo(c) < 0){
                max = c;
            }
        }
        return piles.indexOf(max);
    }
    
    public void informerMain(ArrayList<Carte> main){
        this.main = main;
        
        ArrayList<Carte> retire = new ArrayList<Carte>();
        for(Carte c1 : this.main){
            for(Carte c2 : this.cachee){
                if(cartesIdentiques(c1,c2)){
                    retire.add(c2);
                }
            }
        }
        this.cachee.removeAll(retire);
        //System.out.println(main);
    }
    
    public void informerPiles(ArrayList<Carte> piles){
        this.piles = piles;
        
        ArrayList<Carte> retire = new ArrayList<Carte>();
        for(Carte c1 : this.piles){
            for(Carte c2 : this.cachee){
                if(cartesIdentiques(c1,c2)){
                    retire.add(c2);
                }
            }
        }
        this.cachee.removeAll(retire);
        
        this.profondeurPiles = new ArrayList<Integer>();
        for(int i = 0; i < 6; i++){
            profondeurPiles.add(5);
        }
    }
    
    public void informerAtout(SymboleCarte atout){
        this.atout = atout;
    }
    
    public void informerCoupAdversaire(Carte carte){
        carteAdversaire = carte;
    }
    
    @Override
    public void informerPiocheAdversaire(Carte pioche, Carte revelee) {
        piles.set(piles.indexOf(pioche), revelee);
    }
    
     /**
     * Compare 2 cartes en prenant en compte l'atout
     * @param carte1
     * @param carte2
     * @return 1 si carte1>carte2, -1 si carte2>carte1
     */
    private int comparerCartes(Carte carte1, Carte carte2){
        if(carte1.getSymbole() == atout && carte2.getSymbole() != atout){ // règle de l'atout
            return 1;
        } else if (carte2.getSymbole() == atout && carte1.getSymbole() != atout){ // règle de l'atout
            return -1;
        } else if (carte1.getSymbole() != carte2.getSymbole()){ // défausse
                return 1;
        } else if(carte1.getSymbole() == carte2.getSymbole()){ // même symbole
            return carte1.compareTo(carte2);
        } else { // ce cas ne peut pas arriver à cause des 2 conditions précédentes
            return 0; 
        }
    }
    
    private boolean cartesIdentiques(Carte carte1, Carte carte2){
        return carte1.getSymbole() == carte2.getSymbole() && carte1.getValeur() == carte2.getValeur();
    }
    
    private boolean piocheVide(){
        for(Carte p : piles){
            if(p != null){
                return false;
            }
        }
        return true;
    }

    
    /**
     * 
     * @return 0 s'il ne faut pas gagner (bonne cartes derrière), 1 si on peut gagner mais qu'il ne faut pas jouer de grosse carte (plusieurs bonnes cartes), 2 s'il faut gagner le pli 
     */
    private int valeurPile(){
        /*
        Les cartes sont réparties en plusieurs paliers :
        as de l'atout > valet dame roi de l'atout > autres atout > valet dame roi as non atout > 3-10 non atout > 2 non atout 
        Palier 1 : le gagner donne un avantage considérable car il permet de gagner un pli à coup sûr
        Palier 2 : presque sûr de gagner
        Palier 3 : utile à garder pour la fin de partie
        Palier 4 : cartes fortes contre les non atouts
        Palier 5 : permet de ne pas utiliser de bonne cartes
        Palier 6 : permet d'être sûr de perdre, si une bonne carte se cache derrière. il est bon d'en avoir un peu mais il faut essayer de les utiliser avant la fin de la pioche
        */

        // Contient les cartes cachées qui sont meilleures que la meilleure carte de la pile
        ArrayList<Carte> meilleuresQuePiles = new ArrayList<Carte>();

        Carte maxPile = piles.get(0);
        for(Carte p : piles){
            if(p != null){
                if(p.compareTo(maxPile) > 0){
                    maxPile = p;
                }
            }

        }
        
        if(this.profondeurPiles.get(piles.indexOf(maxPile)) > 1) { // On calcule la probabilité qu'une meilleure carte soit retournée (si c'est la dernière on passe à l'étape suivante)
            for(Carte c : cachee){
                if(maxPile.compareTo(c) < 0){
                    meilleuresQuePiles.add(c);
                }
            }

            // Calcule la probabilité que l'on retourne une meilleure carte
            float proba = meilleuresQuePiles.size() / cachee.size();
            
            if(proba == 1){
                return 0; // IL Y A UNE MEILLEURE CARTE DERRIERE DONC ON VEUT PERDRE
            } else if (proba >= 0.75){
                return 1; // IL Y A PROBABLEMENT UNE MEILLEURE CARTE DERRIERE
            }
        }
        
        ArrayList<Carte> topTier = new ArrayList<Carte>();
        int tier = 0;
        for(Carte p : piles){
            if(p !=  null){
                if(topTier.size() == 0){
                    topTier.add(p);
                    tier = calculTier(p);
                } else if (calculTier(p) > tier){
                    topTier.clear();
                    topTier.add(p);
                    tier = calculTier(p);
                } else if(calculTier(p) == tier){
                    topTier.add(p);
                }
            }
        }
        
        if(topTier.size() >= 2){
            return 1;
        } else if(tier == 1) {
            return 2;
        } else {
            return 0;
        }        
    }
    
    private int calculTier(Carte c){
        if(c.getSymbole().estAtout()){
            if(c.getValeur() == ValeurCarte.AS){
                return 1;
            } else if (c.getValeur().getValeur() >= ValeurCarte.VALET.getValeur()){
                return 2;
            } else {
                return 3;
            }
        } else {
            if(c.getValeur().getValeur() >= ValeurCarte.VALET.getValeur()){
                return 4;
            } else if (c.getValeur().getValeur() >= ValeurCarte.TROIS.getValeur()){
                return 5;
            } else {
                return 6;
            }
        }
    }
    
    private float calculProbaGagner(Carte carte){
        return calculRecursif(carte,0,0);
    }
    
    private float calculRecursif(Carte carte, int indiceCachee, int i){
        if(indiceCachee == cachee.size()){
            return 0;
        } else if(carte.compareTo(cachee.get(indiceCachee)) < 0){
            return main.size()/(cachee.size()-i) + (cachee.size()-i-main.size())/cachee.size() * calculRecursif(carte, indiceCachee+1, i+1);
        } else {
            return calculRecursif(carte, indiceCachee+1, i);
        }
    }

    private NoeudArbre minMax(){
        return null;
    }
    
}
