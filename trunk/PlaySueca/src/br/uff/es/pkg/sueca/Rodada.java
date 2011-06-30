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
    private int numRodadas;
    private Naipe naipeTrunfo;
    private Naipe naipeTrunfoPartida;

    public Naipe getNaipeTrunfo() {
        return naipeTrunfo;
    }

    public void setNaipeTrunfo(Naipe naipeTrunfo) {
        this.naipeTrunfo = naipeTrunfo;
    }
    
    public Rodada(Jogador jogadores[]){
    	ordem = new LinkedList<Jogador>();
        this.jogadores = jogadores;
        DeterminaOrdem(jogadores,0);
        this.campeaoDaRodada = new Jogador("Ninguem");
        numRodadas = 0;
        naipeTrunfo = Naipe.NaoDefinido;
    	naipeTrunfoPartida = Naipe.NaoDefinido;
    }

	public void iniciaNovaRodada() {
            this.naipeTrunfo = Naipe.NaoDefinido;
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
            numRodadas++;
        }

        public void iniciaNovaRodada(Naipe naipeTrunfoPartida) {
            this.naipeTrunfoPartida = naipeTrunfoPartida;
            iniciaNovaRodada();
        }

        public Jogador getProximoJogador() {
            return ordem.getFirst();
        }

        public Carta joga(Carta carta) {
            Jogador proxJogador = ordem.remove();
            Carta cartaJogada;
            if (proxJogador.getPrimeiroAJogar()){
                cartaJogada = proxJogador.Joga(carta);
                //System.out.println(cartaJogada.getNaipe()+"/"+this.getNaipeTrunfo());
                this.setNaipeTrunfo(cartaJogada.getNaipe());
            } 
            else {
                cartaJogada = proxJogador.Joga(carta, this.getNaipeTrunfo());
                //System.out.println(cartaJogada.getNaipe()+"/"+this.getNaipeTrunfo());
            }
            //System.out.println("PROX JOG: "+proxJogador.getNome());
            //System.out.println("CARTA JOGADA : "+cartaJogada);
            cartasJogadas[jogadas] = cartaJogada;
            ordem.addLast(proxJogador);
            jogadas++;
            return cartaJogada;
        }

        public String contabilizaPontos() {
            this.setCampeaoDaRodada(Juiz.DeterminaVencedorJogada(cartasJogadas,jogadores));
            Juiz.SomaPontos(cartasJogadas, this.getCampeaoDaRodada());
            return this.getCampeaoDaRodada().getNome();
        }

	public int getPontosDupla1() {
            return Juiz.getPontosDupla1(jogadores);
        }

        public int getPontosDupla2() {
            return Juiz.getPontosDupla2(jogadores);
        }

	public void IniciaRodada(Jogador jogadores[], String ultimoCampeao){
            //Determina ordem dos jogadores
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
                
                //Realiza as jogadas
		for(int i=0;i<jogadores.length;i++){
			Jogador proxJogador = ordem.remove();
                        cartasJogadas[i] = proxJogador.Joga();
                        //primeira carta a ser jogada eh o trunfo
                        if (i==0)
                            this.setNaipeTrunfo(cartasJogadas[i].getNaipe());
			ordem.addLast(proxJogador);
		}
		//auto-explicativo
		this.setCampeaoDaRodada(Juiz.DeterminaVencedorJogada(cartasJogadas,jogadores));                            
		Juiz.SomaPontos(cartasJogadas, this.getCampeaoDaRodada());
	}

        public boolean isFimJogo() {
            return numRodadas>10;
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

        public Naipe getNaipeTrunfoPartida() {
            return naipeTrunfoPartida;
        }

        public void setNaipeTrunfoPartida(Naipe naipeTrunfoPartida) {
            this.naipeTrunfoPartida = naipeTrunfoPartida;
        }


	private void DeterminaOrdem(Jogador jogadores[],int player){
                ordem = new LinkedList<Jogador>();
		Random ord = new Random();
                int sorteado = player==-1?ord.nextInt(4):player;
                ordem.add(jogadores[sorteado]);
                jogadores[sorteado].setPrimeiroAJogar(true);
                for (int i=(sorteado+1);i<jogadores.length;i++){
                    ordem.add(jogadores[i]);
                    jogadores[i].setPrimeiroAJogar(false);
                }
                for (int i=0;i<=sorteado-1;i++){
                    ordem.add(jogadores[i]);
                    jogadores[i].setPrimeiroAJogar(false);
                }
	}
        
      

        private void DeterminaOrdem(Jogador jogadores[]){
		DeterminaOrdem(jogadores, -1);
	}
	
}
