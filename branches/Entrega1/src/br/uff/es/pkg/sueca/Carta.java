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
		return this.getValor().toString()+" de "+this.getNaipe().toString();
	}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.valor != other.valor) {
            return false;
        }
        if (this.naipe != other.naipe) {
            return false;
        }
        return true;
    }




	
}
