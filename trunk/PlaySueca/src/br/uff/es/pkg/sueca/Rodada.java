package br.uff.es.pkg.sueca;

import java.util.LinkedList;
import java.util.Deque;
import java.util.Random;

public class Rodada {
	private Deque<Jogador> ordem;
	private Jogador campeaoDaRodada;
    private Carta[] cartasJogadas=new Carta[4];
    private Jogador[] jogadores;
    private int jogadas;
    
    public Rodada(Jogador jogadores[]){
    	ordem = new LinkedList<Jogador>();
        this.jogadores = jogadores;
        DeterminaOrdem(jogadores);
        this.campeaoDaRodada = new Jogador("Ninguem");
    	
    }

	public void iniciaNovaRodada() {
            String ultimoCampeao = getCampeaoDaRodada().getNome();
            if (!ultimoCampeao.equals("Ninguem")){
                int player = -1;
                for (int i=0;i<jogadores.length;i++) {
                    if (jogadores[i].getNome().equals(ultimoCampeao)) {
                        player = i;
                        break;
                    }
                }
                DeterminaOrdem(jogadores, player);
            }
            jogadas = 0;
        }

        public Jogador getProximoJogador() {
            return ordem.getFirst();
        }

        public Carta joga(Carta carta) {
            Jogador proxJogador = ordem.remove();
            Carta cartaJogada = proxJogador.Joga(carta);
            cartasJogadas[jogadas] = cartaJogada;
            ordem.addLast(proxJogador);
            jogadas++;
            return cartaJogada;
        }

        public void contabilizaPontos() {
            this.setCampeaoDaRodada(Juiz.DeterminaVencedorJogada(cartasJogadas,jogadores));
            Juiz.SomaPontos(cartasJogadas, this.getCampeaoDaRodada());
        }

	
	public void IniciaRodada(Jogador jogadores[], String ultimoCampeao){
		if (ultimoCampeao!="Ninguem"){
			Deque<Jogador> temp = new LinkedList<Jogador>();
			while(!ordem.isEmpty()){
				Jogador j = ordem.remove();
				String proxElem = j.getNome();
				if (proxElem==ultimoCampeao) temp.addFirst(j);
				else temp.addLast(j);
			}
			ordem = temp;
		}
		for(int i=0;i<jogadores.length;i++){
			Jogador proxJogador = ordem.remove();
			cartasJogadas[i] = proxJogador.Joga();
			ordem.addLast(proxJogador);
		}
		
		this.setCampeaoDaRodada(Juiz.DeterminaVencedorJogada(cartasJogadas,jogadores));
		Juiz.SomaPontos(cartasJogadas, this.getCampeaoDaRodada());
	}
	
	public Deque<Jogador> getOrdem() {
		return ordem;
	}

	public void setOrdem(Deque<Jogador> ordem) {
		this.ordem = ordem;
	}

	public Carta[] getCartasJogadas() {
		return cartasJogadas;
	}

	public void setCartasJogadas(Carta[] cartasJogadas) {
		this.cartasJogadas = cartasJogadas;
	}
	public Jogador getCampeaoDaRodada() {
		return campeaoDaRodada;
	}

	public void setCampeaoDaRodada(Jogador campeaoDaRodada) {
		this.campeaoDaRodada = campeaoDaRodada;
	}

        public Jogador[] getJogadores() {
            return jogadores;
        }

        public boolean isFimRodada() {
            return !(jogadas<jogadores.length);
        }
        


	private void DeterminaOrdem(Jogador jogadores[],int player){
                ordem = new LinkedList<Jogador>();
		Random ord = new Random();
                int sorteado = player==-1?ord.nextInt(4):player;
                ordem.add(jogadores[sorteado]);
                for (int i=(sorteado+1);i<jogadores.length;i++)
                    ordem.add(jogadores[i]);
                for (int i=0;i<=sorteado-1;i++)
                    ordem.add(jogadores[i]);

		
	}

        private void DeterminaOrdem(Jogador jogadores[]){
		DeterminaOrdem(jogadores, -1);


	}
	
}
