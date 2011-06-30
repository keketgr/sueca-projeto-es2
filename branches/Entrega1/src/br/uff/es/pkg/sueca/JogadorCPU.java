package br.uff.es.pkg.sueca;

import java.util.Random;

public class JogadorCPU extends Jogador {

	public JogadorCPU(){
		super();
	}
	public JogadorCPU(String nome){
		super(nome);
	}
	
	@Override
	 public Carta Joga(){
		 Random r = new Random();
		 int resposta = r.nextInt(10);
		 Carta c[] = this.getCartasNaMao();
		 Carta cJogada;
		 while (c[resposta]==null){
		   resposta = r.nextInt(10);
		 }
		 cJogada = c[resposta];
		 c[resposta] = null;
		 this.setCartasNaMao(c);
		 System.out.println(this.getNome()+" jogou.("+cJogada.getNaipe()+"/"+cJogada.getValor());
		 return cJogada;
	}

        @Override
        public Carta Joga(Carta carta) {
            return Joga();
        }
}
