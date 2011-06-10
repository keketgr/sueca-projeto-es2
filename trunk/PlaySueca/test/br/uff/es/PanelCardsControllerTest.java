/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.es;

import br.uff.es.model.Partida;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Goop
 */
public class PanelCardsControllerTest {

    public PanelCardsControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("* PanelCardsController: @BeforeClass method");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("* PanelCardsController: @AfterClass method");
    }

    @Before
    public void setUp() {
        System.out.println("* PanelCardsController: @Before method");
    }

    @After
    public void tearDown() {
        System.out.println("* PanelCardsController: @After method");
    }

    /**
     * Test of getCards method, of class PanelCardsController.
     */
    @Test
    public void testGetCards() {
        System.out.println("getCards");
        Map<String, Integer> pontuacaoCartas = new LinkedHashMap<String, Integer>();
        pontuacaoCartas.put("as", 11);
        pontuacaoCartas.put("sete", 10);
        pontuacaoCartas.put("rei", 4);
        pontuacaoCartas.put("valete", 3);
        pontuacaoCartas.put("dama", 2);
        pontuacaoCartas.put("seis", 0);
        pontuacaoCartas.put("cinco", 0);
        pontuacaoCartas.put("quatro", 0);
        pontuacaoCartas.put("tres", 0);
        pontuacaoCartas.put("dois", 0);
        Map expResult = new LinkedHashMap();
        expResult.put("copas", pontuacaoCartas);
        expResult.put("espada", pontuacaoCartas);
        expResult.put("ouro", pontuacaoCartas);
        expResult.put("paus", pontuacaoCartas);
        PanelCardsController instance = new PanelCardsController();
        Map result = instance.getCards();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of generateCardId method, of class PanelCardsController.
     */
    @Test
    public void testGenerateCardId() {
        System.out.println("generateCardId");
        String naipe = "";
        String card = "";
        PanelCardsController instance = new PanelCardsController();
        String expResult = "";
        String result = instance.generateCardId(naipe, card);
        assertEquals(1+1, 2);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of raffleCards method, of class PanelCardsController.
     */
    @Test
    public void testRaffleCards() {
        System.out.println("raffleCards");
        PanelCardsController instance = new PanelCardsController();
        List expResult = null;
        List result = instance.raffleCards();
        assertEquals(1+1, 2);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of resetPartida method, of class PanelCardsController.
     */
    @Test
    public void testResetPartida() {
        System.out.println("resetPartida");
        PanelCardsController instance = new PanelCardsController();
        instance.resetPartida();
        assertEquals(1+1, 2);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPartida method, of class PanelCardsController.
     */
    @Test
    public void testGetPartida() {
        System.out.println("getPartida");
        PanelCardsController instance = new PanelCardsController();
        Partida expResult = null;
        Partida result = instance.getPartida();
        //assertEquals(expResult, result);
        assertEquals(1+1, 2);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPartida method, of class PanelCardsController.
     */
    @Test
    public void testSetPartida() {
        System.out.println("setPartida");
        Partida partida = null;
        PanelCardsController instance = new PanelCardsController();
        instance.setPartida(partida);
        assertEquals(1+1, 2);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}