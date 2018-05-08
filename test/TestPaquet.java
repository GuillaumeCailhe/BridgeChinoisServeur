/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Carte.Carte;
import Carte.Paquet;
import Carte.SymboleCarte;
import Carte.ValeurCarte;
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
public class TestPaquet {
    
    @Test
    public void testDistribuer(){
        Paquet p = new Paquet();
        
        assert p.getNbCartesRestantes() == 52;
        p.distribuerUneCarte();
        assert p.getNbCartesRestantes() == 51;
    }
    
}
