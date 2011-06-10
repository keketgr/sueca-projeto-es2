package br.uff.es.pkg.sueca;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class Baralho {
	
	private Deque<Carta> cartas = new LinkedList<Carta>();

	public Baralho(){
	    int i,j = 0;
		Carta c;
		Valor v = Valor.NaoDefinido;
		Naipe n = Naipe.NaoDefinido;
	    for (j=0; j<(Naipe.values().length-1); j++){
	        n = n.prox();
	    	for (i=0;i<Valor.values().length-1;i++){
                v = v.prox();
			    c = new Carta(v,n);
			    cartas.add(c);
			}
		}
	}
	
	public Deque<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(Deque<Carta> cartas) {
		this.cartas = cartas;
	}
	
	public Carta tiraCarta(){
		/*remove a primeira carta do baralho, retorna null se o baralho estiver vazio */
		return cartas.pollFirst();
	}

	public String toString() {
		Iterator<Carta> i = cartas.iterator();
		String cartaAtual="";
		while(i.hasNext()){
			cartaAtual += i.getClass().toString();
		}
		return cartaAtual;
	}
	
	public int getNumCartasNoBaralho(){
		return cartas.size();
	}
	
	
	

}
