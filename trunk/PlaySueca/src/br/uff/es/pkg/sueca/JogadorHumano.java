package br.uff.es.pkg.sueca;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class JogadorHumano extends Jogador {
	
 public JogadorHumano(){
	 super();
 }
 
 public JogadorHumano(String nome){
	 super(nome);
 }
 
 @Override
 public Carta Joga(){
	 /*
	 System.out.println("Escolha uma carta pra jogar");
	 System.out.println("Cartas Disponiveis:");
	 for(int i=0;i<this.getNumCartasNaMao();i++){
		 if (this.getCartasNaMao()[i]!=null){
			 System.out.println(this.getCartasNaMao()[i].toString());
		 }
	 } */
	 Carta cJogada = new Carta();
	 try {
		 BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		 System.out.println("digite n da carta a jogar");
		 int resposta = Integer.parseInt(bf.readLine());
		 Carta c[] = this.getCartasNaMao();
		 cJogada = c[resposta];
		 c[resposta]=null;
		 this.setCartasNaMao(c);
		 System.out.println("Carta jogada: "+cJogada.toString());
		 System.out.println("Cartas na mao:"+this.getNumCartasNaMao());
		 
		 
	 } catch(IOException e){
		 e.printStackTrace();
	 }
	 
	 return cJogada;
 } 
}
