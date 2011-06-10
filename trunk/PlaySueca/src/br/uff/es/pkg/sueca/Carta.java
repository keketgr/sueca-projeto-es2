package br.uff.es.pkg.sueca;



public class Carta {
    //private ImageIcon img = null;
	private Valor valor;
	private Naipe naipe;
	private String pertenceA;
	
	public Naipe getNaipe() {
		return naipe;
	}

	public void setNaipe(Naipe naipe) {
		this.naipe = naipe;
	}
	
	public Carta(){
		valor = Valor.NaoDefinido;
		naipe = Naipe.NaoDefinido;
		pertenceA = "Ninguem";
	}

	public Carta(Valor v, Naipe n){
		this.valor = v;
		this.naipe = n;
		this.pertenceA = "Ninguem";
		
	}

	public String getPertenceA() {
		return pertenceA;
	}

	public void setPertenceA(String pertenceA) {
		this.pertenceA = pertenceA;
	}

	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor v) {
		this.valor = v;
	}
	
	public String toString(){
		return this.getNaipe().toString()+this.getValor().toString();
	}
	
}
