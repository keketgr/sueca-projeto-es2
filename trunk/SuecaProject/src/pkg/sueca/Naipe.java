package pkg.sueca;

public enum Naipe {
	 NaoDefinido, Espada, Paus, Copas, Ouro; 
	 
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
