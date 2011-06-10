package br.uff.es.pkg.sueca;

import java.util.Random;

public class Jogo {
	//private String campeao=null;
	
	public Jogo(Baralho b, Jogador jogadores[]){ 
		DeterminaDuplas(jogadores);
	    DistribuiCartas(b, jogadores);
	    Rodada r = new Rodada(jogadores);
	    for (int i=0;i<10;i++)
	     r.IniciaRodada(jogadores, r.getCampeaoDaRodada().getNome());
	    
	    //Calcula pontos
	    Juiz.CalculaDuplaVencedora(jogadores);
	    //Calcula vencedor
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
 	    jogadores[1].setnDupla(1);
 	    jogadores[2].setnDupla(2);
 	    jogadores[3].setnDupla(2);
	}
        	
}
        
		

	

