/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.es.pkg.sueca.factory;

import java.util.List;

/**
 *
 * @author Goop
 */
public interface IGame {
    public List<String> iniciaJogo();
    public void entregaCarta(String carta,int player);
    public void iniciaNovaRodada();
    public void iniciaNovaRodada(String cartaTrunfoPartida);
    public String getProximoJogador();
    public int getNumeroJogador(String jogador);
    public List<String> getMaoJogador(String jogador);
    public String contabilizaPontos();
    public List<String> getCartasJogadas();
    public boolean isFimJogo();
    public int calculaDuplaVencedora();
    public int getGeralDupla1();
    public int getGeralDupla2();
    public int getPontosDupla1();
    public int getPontosDupla2();
    public boolean isFimRodada();
    public String joga(String carta);
    public String getNaipeRodada();
    
}
