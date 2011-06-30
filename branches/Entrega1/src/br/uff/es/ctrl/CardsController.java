/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.es.ctrl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Goop
 */
public class CardsController {

    private Map<String,List<String>> cards;
    private List<String> valores;
    //private PartidaController partida;
    private String trunfo;
    private Object[] cartasNaMesa;




    public CardsController() {
        //partida = new PartidaController();
        initialize();
        novaPartida();
    }

    private void buildPontuacaoCartasMap() {
        valores = new ArrayList<String>();
        valores.add("as");
        valores.add("sete");
        valores.add("rei");
        valores.add("valete");
        valores.add("dama");
        valores.add("seis");
        valores.add("cinco");
        valores.add("quatro");
        valores.add("tres");
        valores.add("dois");
    }

    private void initialize() {
        buildPontuacaoCartasMap();
        cards = new HashMap<String, List<String>>();
        cards.put("copas", valores);
        cards.put("espada", valores);
        cards.put("ouro", valores);
        cards.put("paus", valores);
    }

    public Map<String, List<String>> getCards() {
        return cards;
    }

    public String generateCardId(String naipe,String card) {
        return card+" de "+naipe;
    }

    private List<String> generateCardsIds() {
        List<String> cardsIds = new ArrayList<String>();
        for (String naipe : cards.keySet()) {
            for (String card : cards.get(naipe)) {
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

    private boolean isMesmoNaipe(String candidata,String analisada) {
        return analisada.contains(candidata.substring(candidata.indexOf(" de ")+4, candidata.length()));
    }

    private boolean isMaiorPontuacao(String candidata,String analisada) {
        String valCartaCandidata = candidata.substring(0,candidata.indexOf(" de "));
        String valCartaAnalisada = analisada.substring(0,analisada.indexOf(" de "));
        return valores.indexOf(valCartaAnalisada)<valores.indexOf(valCartaCandidata);
    }

    private int encontraIndexNaipe(String naipe,List<String> cartas,int offset) {
        for (int i=offset;i<cartas.size();i++) {
            String carta = cartas.get(i);
            if (carta.contains(naipe))
                return i;
        }
        return -1;
    }

    private void ordenaNaipe(String naipe,List<String> cartas) {

        int indexNaipe = encontraIndexNaipe(naipe, cartas,0);
        int search = 0;
        while (indexNaipe>=0) {
            String cartaCandidata = cartas.get(indexNaipe);
            search = indexNaipe-1;
            while (search>-1 && !isMesmoNaipe(cartaCandidata, cartas.get(search)))
                search--;
            while (search>-1 && isMesmoNaipe(cartaCandidata, cartas.get(search)) && isMaiorPontuacao(cartaCandidata, cartas.get(search)))
                search--;

            String carta = cartas.remove(indexNaipe);
            cartas.add(search+1, carta);
            indexNaipe = encontraIndexNaipe(naipe, cartas,indexNaipe+1);
        }
    }

    public void ordenaCartas() {
        for (int player=0;player<4;player++) {
            List<String> cartasFromPlayer = (List<String>)cartasNaMesa[player];

            ordenaNaipe("paus", cartasFromPlayer);
            ordenaNaipe("ouro", cartasFromPlayer);
            ordenaNaipe("espada", cartasFromPlayer);
            ordenaNaipe("copas", cartasFromPlayer);

        }
    }

    public void entregaCarta(int player,String card) {
        if (cartasNaMesa[player]==null)
            cartasNaMesa[player] = new ArrayList<String>();
        ((List<String>)cartasNaMesa[player]).add(card);
    }

    public List<String> getCartasNaMesa() {
        List<String> todasCartasNaMesa = new ArrayList<String>();
        for (int i=0;i<cartasNaMesa.length;i++) {
            if (cartasNaMesa[i]!=null)
                todasCartasNaMesa.addAll((List)cartasNaMesa[i]);
        }
        return todasCartasNaMesa;
    }

    public List<String> getCartasJogadorNaMesa(int jogador) {
        return (List)cartasNaMesa[jogador];
    }

    public String getTrunfo() {
        return trunfo;
    }

    public void setTrunfo(String trunfo) {
        this.trunfo = trunfo;
    }

    public void novaPartida() {
        trunfo = null;
        cartasNaMesa = new Object[4];
    }

}
