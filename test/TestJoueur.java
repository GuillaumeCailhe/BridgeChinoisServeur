/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bridgechinois.Joueur;
import bridgechinois.JoueurHumain;
import bridgechinois.JoueurIA;
import bridgechinois.JoueurIADifficile;
import bridgechinois.JoueurIAFacile;
import bridgechinois.JoueurIAIntermediaire;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pepefab
 */
public class TestJoueur {
    
    @Test
    public void testEstIA(){
        Joueur facile = new JoueurIAFacile(null,null,null);
        assert facile.estIA();
        Joueur inter = new JoueurIAIntermediaire(null,null,null);
        assert inter.estIA();
        Joueur dif = new JoueurIADifficile(null,null,null);
        assert dif.estIA();
        Joueur humain = new JoueurHumain(null,null,null);
        assert !humain.estIA();
    }
}
