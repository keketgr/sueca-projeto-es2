package br.uff.es.pkg.sueca;

import java.util.Random;

public class Jogo {
	//private String campeao=null;
        private Jogador jogadores[];
        private Rodada rodada;
	
	public Jogo(Baralho b, Jogador jogadores[]){
            this.jogadores = jogadores;
            
            DeterminaDuplas(jogadores);
	    DistribuiCartas(b, jogadores);
	    Rodada r = new Rodada(jogadores);
	    for (int i=0;i<10;i++)
	     r.IniciaRodada(jogadores, r.getCampeaoDaRodada().getNome());
	    
	    //Calcula pontos
	    Juiz.CalculaDuplaVencedora(jogadores);
	    //Calcula vencedor
	}

        public Jogo(Jogador jogadores[]) {
            this.jogadores = jogadores;
            rodada = new Rodada(jogadores);
            DeterminaDuplas(jogadores);
        }

        public void iniciaPartida() {
            rodada = new Rodada(jogadores);
            for (int i=0;i<jogadores.length;i++)
                jogadores[i].descartaCartasJogador();
        }

        public void distrubuiCarta(Carta carta,int player) {
  
            if (jogadores[player].entregaCartaAoJogador(carta))
                carta.setPertenceA(jogadores[player].getNome());

        }

	
	public void DistribuiCartas(Baralho b, Jogador jogadores[]){
		Random r = new Random();
				
		//preenche as posicoes restantes com jogadores da maquina
		
        while (b.getNumCartasNoBaralho() > 0){
        	
        	Carta c = b.tiraCarta();
        	while (c.getPertenceA()=="Ninguem"){
        		int proxDono = r.nextInt(4);
        		if (jogadores[proxDono].entregaCartaAoJogador(c))
        			c.setPertenceA(jogadores[proxDono].getNome());
        	}
        }
    } 
	
	public void DeterminaDuplas(Jogador jogadores[]){
	    jogadores[0].setnDupla(1);
 	    jogadores[1].setnDupla(2);
 	    jogadores[2].setnDupla(1);
 	    jogadores[3].setnDupla(2);
	}

    public Rodada getRodada() {
        return rodada;
    }

    public void setRodada(Rodada rodada) {
        this.rodada = rodada;
    }



        
}
        
		

	

