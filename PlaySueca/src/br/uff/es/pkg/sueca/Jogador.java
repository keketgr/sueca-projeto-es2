package br.uff.es.pkg.sueca;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
	private Carta cartasNaMao[]=new Carta[10];
	private String nome;
	private int pontos;
	private int nDupla;
        private boolean primeiroAJogar=false;

    public boolean getPrimeiroAJogar() {
        return primeiroAJogar;
    }

    public void setPrimeiroAJogar(boolean primeiroAJogar) {
        this.primeiroAJogar = primeiroAJogar;
    }

	
	public Carta[] getCartasNaMao() {
		return cartasNaMao;
	}
	public int getNumCartasNaMao(){
		int n=0;
		for (int i=0;i<cartasNaMao.length;i++)
			if((cartasNaMao[i]!=null)&&(cartasNaMao[i].getPertenceA()==this.getNome())){
				n++;
			}
		return n;
	}
	public void setCartasNaMao(Carta[] cartasNaMao) {
		this.cartasNaMao = cartasNaMao;
	}
	public boolean entregaCartaAoJogador(Carta cartaNova){
		if (this.getNumCartasNaMao() < 10) {
		   this.cartasNaMao[this.getNumCartasNaMao()] = cartaNova;
		   return true;
		} else return false;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int p) {
		this.pontos = p;
	}
	
	
	public int getnDupla() {
		return nDupla;
	}
	public void setnDupla(int nDupla) {
		this.nDupla = nDupla;
	}
	public Jogador(String nome){
		this.nome = nome;
		this.pontos = 0;
	}
	
	public Jogador(){
		this.nome = "default";
		this.pontos = 0;
                this.primeiroAJogar=false;
	}
	public String toString(){
		int result=0;
		for (int i=0;i<cartasNaMao.length;i++){
			if (cartasNaMao[i]!=null) {
				result++;
			}
		}
		return nome+"  Cartas na mao:"+result+"Pontos: "+pontos;
	}
	
   public Carta Joga(){
	   return new Carta();
   }

   public Carta Joga(Carta carta){
	   return carta;
   }
   
   public Carta Joga(Carta carta, Naipe trunfo){
           return carta;
   }
   
   public boolean temCartadeNaipe(Naipe n){
        Carta c[] = this.getCartasNaMao();
        for (int i=0; i<c.length; i++)
            if (c[i]!=null){
                if (c[i].getNaipe().ordinal()==n.ordinal()){
                    return true;
                }
            }
                
        return false;
    }

   public Carta[] buscaCartasNaipe(Naipe n) {
       List<Carta> cartasNaipe =  new ArrayList<Carta>();

       if (temCartadeNaipe(n)){
           Carta c[] = this.getCartasNaMao();
           for (int i=0;i<c.length;i++){
               if (c[i]!=null){
                if (c[i].getNaipe().ordinal()==n.ordinal())
                   cartasNaipe.add(c[i]);
                }

           }
           return cartasNaipe.toArray(new Carta[0]);
       }
       else return null;
   }
   
   public ArrayList<Integer> buscaPosCartasNaipe(Naipe n){
       ArrayList<Integer> pos = new ArrayList<Integer>();
       
       if (temCartadeNaipe(n)){
           Carta c[] = this.getCartasNaMao();
           for (int i=0;i<c.length;i++){
               if (c[i]!=null){
                if (c[i].getNaipe().ordinal()==n.ordinal())
                   pos.add(i);           
                }
               
           }
           return pos;
       }
       else return null;
   }

   public void descartaCartasJogador() {
       for (int i=0;i<cartasNaMao.length;i++) {
           cartasNaMao[i] = null;
       }
       setPontos(0);
   }
   
     public void printCartas(){
        Carta c[] = this.getCartasNaMao();
        String resultado="";
        for (int i=0;i<c.length;i++){
            if (c[i]==null) resultado+="null";
            else resultado+=c[i].toString();
            resultado+="/";
        }
        //System.out.println(resultado);
    }

   
}


