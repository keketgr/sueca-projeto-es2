/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.es.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Goop
 */
public class Partida {
    //private List<String> cartasNaMesa;
    private Object[] cartasNaMesa;
    private List<String> cartasDaRodada;
    private int totalCartas;
    private int pontosDupla1;
    private int pontosDupla2;
    private String trunfo;
    private Map<String, Integer> pontuacaoCartas;


    public Partida() {
        //cartasNaMesa = new ArrayList<String>();
        cartasNaMesa = new Object[4];
        cartasDaRodada = new ArrayList<String>();
        buildPontuacaoCartasMap();
    }

    public void buildPontuacaoCartasMap() {
        pontuacaoCartas = new LinkedHashMap<String, Integer>();
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
    }

    public Map<String, Integer> getPontuacaoCartas() {
        return pontuacaoCartas;
    }

    public void setPontuacaoCartas(Map<String, Integer> pontuacaoCartas) {
        this.pontuacaoCartas = pontuacaoCartas;
    }

    
    //public void setCartasNaMesa(List<String> cartasNaMesa) {
    //    totalCartas = cartasNaMesa.size();
    //    this.cartasNaMesa = cartasNaMesa;
    //}

    

    public List<String> getCartasDaRodada() {
        return cartasDaRodada;
    }

    public int getPontosDupla1() {
        return pontosDupla1;
    }

    public void setPontosDupla1(int pontosDupla1) {
        this.pontosDupla1 = pontosDupla1;
    }

    public int getPontosDupla2() {
        return pontosDupla2;
    }

    public void setPontosDupla2(int pontosDupla2) {
        this.pontosDupla2 = pontosDupla2;
    }

    public String getTrunfo() {
        return trunfo;
    }

    public void setTrunfo(String trunfo) {
        this.trunfo = trunfo;
    }

    private void updateNumeroCartas() {
        totalCartas = 0;
        for (int i=0;i<cartasNaMesa.length;i++) {
            totalCartas += cartasNaMesa[i]!=null?((List)cartasNaMesa[i]).size():0;
        }
    }

    private boolean isMesmoNaipe(String candidata,String analisada) {
        return analisada.contains(candidata.substring(candidata.indexOf(" de ")+4, candidata.length()));
    }

    private boolean isMaiorPontuacao(String candidata,String analisada) {
        String valCartaCandidata = candidata.substring(0,candidata.indexOf(" de "));
        String valCartaAnalisada = analisada.substring(0,analisada.indexOf(" de "));
        System.out.println("valCartaAnalisada: "+pontuacaoCartas.get(valCartaAnalisada));
        System.out.println("valCartaCandidata: "+pontuacaoCartas.get(valCartaCandidata));
        return pontuacaoCartas.get(valCartaAnalisada)>pontuacaoCartas.get(valCartaCandidata);
    }

    public void ordenaCartas() {
        for (int player=0;player<4;player++) {
            List<String> cartasFromPlayer = (List<String>)cartasNaMesa[player];
            for (int i=1;i<cartasFromPlayer.size();i++) {
                String cartaCandidata = cartasFromPlayer.get(i);
                int search = i-1;
                while (search>0 && !isMesmoNaipe(cartaCandidata, cartasFromPlayer.get(search)))
                    search--;
                while (search>0 && isMesmoNaipe(cartaCandidata, cartasFromPlayer.get(search)) && isMaiorPontuacao(cartaCandidata, cartasFromPlayer.get(search)))
                    search--;
                String carta = cartasFromPlayer.remove(i);
                cartasFromPlayer.add(search+1, carta);
            }
        }
    }

    public void entregaCarta(int player,String card) {
        if (cartasNaMesa[player]==null)
            cartasNaMesa[player] = new ArrayList<String>();
        ((List<String>)cartasNaMesa[player]).add(card);
        updateNumeroCartas();
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

    public int getJogadorNaMesa() {
        return (totalCartas-getCartasNaMesa().size()) % 4;
    }

    public void jogaCarta(String card) {
        ((List)cartasNaMesa[getJogadorNaMesa()]).remove(card);
        cartasDaRodada.add(card);
    }





}
