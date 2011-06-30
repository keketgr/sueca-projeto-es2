/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.es.pkg.sueca.factory;

import br.uff.es.pkg.sueca.Carta;
import br.uff.es.pkg.sueca.Jogador;
import br.uff.es.pkg.sueca.JogadorCPU;
import br.uff.es.pkg.sueca.JogadorHumano;
import br.uff.es.pkg.sueca.Jogo;
import br.uff.es.pkg.sueca.Juiz;
import br.uff.es.pkg.sueca.Naipe;
import br.uff.es.pkg.sueca.Valor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Goop
 */
public class Game implements IGame {

    Jogo jogo;
    
    public Game() {

    }

    public void iniciaJogo() {
        Jogador jogadores[]=new Jogador[4];
        jogadores[0] = new JogadorHumano("Player 1");
        for (int i = 0; i < 4; i++) {
            if (jogadores[i] == null) {
                jogadores[i] = new JogadorCPU("CPU" + i);
            }
        }
        jogo = new Jogo(jogadores);
    }

    public void entregaCarta(String carta,int player) {
        String naipe = carta.substring(carta.indexOf(" de ")+4, carta.length());
        String valor = carta.substring(0,carta.indexOf(" de "));
        Valor v = Valor.valueOf(valor);
        Naipe n = Naipe.valueOf(naipe);
        Carta mCarta = new Carta(v,n);
        jogo.distrubuiCarta(mCarta, player);
    }

    public void iniciaNovaRodada() {
        jogo.getRodada().iniciaNovaRodada();
    }
    
    public String getProximoJogador() {
        return jogo.getRodada().getProximoJogador().getNome();
    }

    public int getNumeroJogador(String jogador) {
        Jogador[] jogadores = jogo.getRodada().getJogadores();
        for (int i=0;i<jogadores.length;i++) {
            if (jogadores[i].getNome().equals(jogador)) {
                return i;
            }
        }
        return -1;
    }

    public List<String> getMaoJogador(String jogador) {
        Jogador[] jogadores = jogo.getRodada().getJogadores();
        Jogador cJogador = null;
        for (int i=0;i<jogadores.length;i++) {
            if (jogadores[i].getNome().equals(jogador)) {
                cJogador = jogadores[i];
                break;
            }
        }
        Carta[] cartas = cJogador.getCartasNaMao();
        List<String> cartasNaMao = new ArrayList<String>();
        for (int i=0;i<cartas.length;i++) {
            if (cartas[i]!=null)
                cartasNaMao.add(cartas[i].toString());
        }
        return cartasNaMao;
    }

    public String contabilizaPontos() {
        return jogo.getRodada().contabilizaPontos();
    }

    public List<String> getCartasJogadas() {
        List<String> cartasJogadasList = new ArrayList<String>();
        Carta[] cartasJogadas = jogo.getRodada().getCartasJogadas();
        for (int i=0;i<cartasJogadas.length;i++) {
            cartasJogadasList.add(cartasJogadas[i].toString());
        }
        return cartasJogadasList;
    }

    public boolean isFimJogo() {
        return jogo.getRodada().isFimJogo();
    }

    public int calculaDuplaVencedora() {
        return Juiz.CalculaDuplaVencedora(jogo.getRodada().getJogadores());
    }

    public int getGeralDupla1() {
        return Juiz.getPontosGeralDupla1();
    }

    public int getGeralDupla2() {
        return Juiz.getPontosGeralDupla2();
    }

    public int getPontosDupla1() {
        return jogo.getRodada().getPontosDupla1();
    }

    public int getPontosDupla2() {
        return jogo.getRodada().getPontosDupla2();
    }

    public boolean isFimRodada() {
        return jogo.getRodada().isFimRodada();
    }

    public String joga(String carta) {
        if (carta!=null) {
            String naipe = carta.substring(carta.indexOf(" de ")+4, carta.length());
            String valor = carta.substring(0,carta.indexOf(" de "));
            Valor v = Valor.valueOf(valor);
            Naipe n = Naipe.valueOf(naipe);
            Carta mCarta = new Carta(v,n);
            return jogo.getRodada().joga(mCarta).toString();
        }
        return jogo.getRodada().joga(null).toString();
    }
}
