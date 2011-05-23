package pkg.sueca;

import java.util.LinkedList;
import java.util.Deque;
import java.util.Random;

public class Rodada {
	private Deque<Jogador> ordem;
	private Jogador campeaoDaRodada;
    private Carta[] cartasJogadas=new Carta[4];
    
    public Rodada(Jogador jogadores[]){
    	ordem = new LinkedList<Jogador>();
		DeterminaOrdem(jogadores);
		this.campeaoDaRodada = new Jogador("Ninguem");
    	
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

	private void DeterminaOrdem(Jogador jogadores[]){
		Random ord = new Random();
		int njsorts = 0;
		while (njsorts<4){
			int jsorteado = ord.nextInt(4);
			if (!ordem.contains(jogadores[jsorteado])){
					ordem.add(jogadores[jsorteado]);
					njsorts++;
			}
		}
		
	}
	
}
