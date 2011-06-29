package br.uff.es.pkg.sueca;

public enum Naipe {
	 NaoDefinido, espada, paus, copas, ouro;
	 
	 public Naipe prox(){
		 Naipe vals[] = Naipe.values();
	    	int proximoVal = this.ordinal();
	        if(++proximoVal >= Naipe.values().length){
	        	proximoVal =  proximoVal % Naipe.values().length ;
	        	if (proximoVal == Naipe.valueOf("NaoDefinido").ordinal()){
	        		proximoVal++;
	        	}
	        } 
	        return vals[proximoVal];
	    
	 }
}
