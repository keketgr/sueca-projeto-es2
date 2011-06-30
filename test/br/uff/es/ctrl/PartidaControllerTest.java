/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.es.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Goop
 */
public class PartidaControllerTest {

    PartidaController partida;

    public PartidaControllerTest() {
        partida = new PartidaController();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }


    /**
     * Testando entregaCarta e getCartasNaMesa
     */
    @Test
    public void testEntregaCartaAndGetCartasNaMesa() {
        System.out.println("testOrdenaCartas");
        PartidaController instance = new PartidaController();
        List<String> cartasNaMesaResult = new ArrayList<String>();
        for (int i=0;i<4;i++) {
            instance.entregaCarta(i, "as de copas");
            instance.entregaCarta(i, "seis de paus");
            instance.entregaCarta(i, "dama de copas");
            instance.entregaCarta(i, "dois de espada");
            instance.entregaCarta(i, "sete de paus");
            instance.entregaCarta(i, "dois de ouro");
            instance.entregaCarta(i, "cinco de paus");
            instance.entregaCarta(i, "cinco de espada");
            instance.entregaCarta(i, "quatro de paus");
            instance.entregaCarta(i, "as de ouro");
            cartasNaMesaResult.add("as de copas");
            cartasNaMesaResult.add("seis de paus");
            cartasNaMesaResult.add("dama de copas");
            cartasNaMesaResult.add("dois de espada");
            cartasNaMesaResult.add("sete de paus");
            cartasNaMesaResult.add("dois de ouro");
            cartasNaMesaResult.add("cinco de paus");
            cartasNaMesaResult.add("cinco de espada");
            cartasNaMesaResult.add("quatro de paus");
            cartasNaMesaResult.add("as de ouro");
        }
        List<String> cartasNaMesa = instance.getCartasNaMesa();
        assertEquals(cartasNaMesaResult, cartasNaMesa);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testOrdenaCartas() {
        System.out.println("testOrdenaCartas");
        PartidaController instance = new PartidaController();
        List<String> cartasNaMesaResult = new ArrayList<String>();
        for (int i=0;i<4;i++) {
            instance.entregaCarta(i, "as de copas");
            instance.entregaCarta(i, "seis de paus");
            instance.entregaCarta(i, "dama de copas");
            instance.entregaCarta(i, "dois de espada");
            instance.entregaCarta(i, "sete de paus");
            instance.entregaCarta(i, "dois de ouro");
            instance.entregaCarta(i, "cinco de paus");
            instance.entregaCarta(i, "cinco de espada");
            instance.entregaCarta(i, "quatro de paus");
            instance.entregaCarta(i, "as de ouro");

            
            cartasNaMesaResult.add("dama de copas");
            cartasNaMesaResult.add("as de copas");

            
            cartasNaMesaResult.add("dois de espada");
            cartasNaMesaResult.add("cinco de espada");

            
            cartasNaMesaResult.add("dois de ouro");
            cartasNaMesaResult.add("as de ouro");

            cartasNaMesaResult.add("quatro de paus");
            cartasNaMesaResult.add("cinco de paus");
            cartasNaMesaResult.add("seis de paus");
            cartasNaMesaResult.add("sete de paus");
        }
        instance.ordenaCartas();
        List<String> cartasNaMesa = instance.getCartasNaMesa();
        assertEquals(cartasNaMesaResult, cartasNaMesa);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }





}