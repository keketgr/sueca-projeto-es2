/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.es;

import br.uff.es.model.Partida;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Goop
 */
public class PanelCardsController {

    private Map<String,Map<String,Integer>> cards;
    private Partida partida;




    public PanelCardsController() {
        partida = new Partida();
        initialize();
    }

    private void initialize() {
        cards = new HashMap<String, Map<String, Integer>>();
        cards.put("copas", new LinkedHashMap<String, Integer>());
        cards.put("espada", new LinkedHashMap<String, Integer>());
        cards.put("ouro", new LinkedHashMap<String, Integer>());
        cards.put("paus", new LinkedHashMap<String, Integer>());

        for (String naipe : cards.keySet()) {
            cards.get(naipe).putAll(partida.getPontuacaoCartas());

            

        }

        

    }

    public Map<String, Map<String, Integer>> getCards() {
        return cards;
    }

    public String generateCardId(String naipe,String card) {
        return card+" de "+naipe;
    }

    private List<String> generateCardsIds() {
        List<String> cardsIds = new ArrayList<String>();
        for (String naipe : cards.keySet()) {
            for (String card : cards.get(naipe).keySet()) {
                cardsIds.add(generateCardId(naipe, card));
            }
        }
        return cardsIds;
    }

    public List<String> raffleCards() {
        List<String> cardsRaffled = new ArrayList<String>();
        List<String> cardsIds = generateCardsIds();
        int max;
        while (cardsIds.size()>0) {
            max = cardsIds.size();
            int random = (int)(max*Math.random());
            cardsRaffled.add(cardsIds.get(random));
            cardsIds.remove(random);
        }
        return cardsRaffled;
    }

    public void resetPartida() {
        partida = new Partida();
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

}
