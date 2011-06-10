package br.uff.es.pkg.sueca;


public enum Valor {
    NaoDefinido, dois, tres, quatro, cinco, seis, dama, valete, rei, sete, a;

    public Valor prox(){
    	/* NaoDefinido eh usado apenas quando uma carta eh inicializada.
    	 * Caso o prox() seja chamado quando o valor da carta eh a, o valor
    	 * pula direto pra dois. O mesmo acontece na enum Naipe.java
    	 */
    	Valor vals[] = Valor.values();
    	int proximoVal = this.ordinal();
    	if(++proximoVal >= Valor.values().length){
        	proximoVal =  proximoVal % Valor.values().length ;
        	if (proximoVal == Naipe.valueOf("NaoDefinido").ordinal()){
        		proximoVal++;
        	}
        } 
        return vals[proximoVal];
    }
	
}