package br.uff.es.pkg.sueca;

public class Jogador {
	private Carta cartasNaMao[]=new Carta[10];
	private String nome;
	private int pontos;
	private int nDupla;

	
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
}
