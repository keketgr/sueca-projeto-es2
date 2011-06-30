package br.uff.es.pkg.sueca;

import java.util.Random;
import java.util.ArrayList;

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
		 int resposta;
		 Carta c[] = this.getCartasNaMao();
		 Carta cJogada;
                 do {
                     resposta = r.nextInt(10);
                 }
		 while (c[resposta]==null);
		 cJogada = c[resposta];
		 c[resposta] = null;
		 this.setCartasNaMao(c);
                 this.printCartas();
		 //System.out.println(this.getNome()+" jogou.("+cJogada.getNaipe()+"/"+cJogada.getValor());
		 return cJogada;
	}

        @Override
        public Carta Joga(Carta carta) {
            return Joga();
        }
        
        @Override
        public Carta Joga(Carta carta, Naipe nTrunfo){
            Carta[] cartasPossiveis = buscaCartasNaipe(nTrunfo);
            if (cartasPossiveis!=null) {
                Random r = new Random();
                int resposta = r.nextInt(cartasPossiveis.length);
                Carta c[] = this.getCartasNaMao();
                for (int i=0;i<c.length;i++)
                    if (c[i]!=null && c[i].equals(cartasPossiveis[resposta]))
                        c[i] = null;
                return cartasPossiveis[resposta];
            } else {
                return Joga();
            }

            /*if (temCartadeNaipe(nTrunfo)){
                //System.out.println("Tem carta.");

                ArrayList<Integer> posCartasPossiveis = buscaPosCartasNaipe(nTrunfo);
                
                this.printCartas();
                Random r = new Random();
                int resposta = r.nextInt(posCartasPossiveis.size());
                Carta c[] = this.getCartasNaMao();
                Carta cJogada = c[posCartasPossiveis.get(resposta)];
                c[resposta] = null;
                this.setCartasNaMao(c);
                //System.out.println(this.getNome()+" jogou.("+cJogada.getNaipe()+"/"+cJogada.getValor());
		return cJogada;
                
            }  else {
                //System.out.println("Nao tem carta.");
                return Joga();
            }*/
        }
}
