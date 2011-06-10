package br.uff.es.pkg.sueca;

public class Juiz {
	
  private static int pontosD1=0;
  private static int pontosD2=0;
	
  public static Jogador DeterminaVencedorJogada(Carta c[], Jogador j[]){
	  //Retorna o nome do jogador vencedor
	  //Comecamos assumindo que o vencedor eh o trunfo
	  Carta cVencedor = c[0];
	  for(int i=1;i<4;i++){
		  if ((c[i].getNaipe()==cVencedor.getNaipe())&&(c[i].getValor().ordinal()>cVencedor.getValor().ordinal())){
			  cVencedor = c[i];
		  }
	  }
	  for (int i=0;i<j.length;i++){
		  if (cVencedor.getPertenceA()==j[i].getNome()) return j[i];
	  }
	  return null;
  }
  
  public static void SomaPontos(Carta c[], Jogador j){
	  for (int i=0; i<c.length;i++){
		  switch(c[i].getValor()){
		  	case dama:
		  		j.setPontos(j.getPontos()+2);
		  		System.out.println(j.getPontos());
		  		break;
		  	case valete:
		  		j.setPontos(j.getPontos()+3);
		  		System.out.println(j.getPontos());
		  		break;
		  	case rei:
		  		j.setPontos(j.getPontos()+4);
		  		System.out.println(j.getPontos());
		  		break;
		  	case sete:
		  		j.setPontos(j.getPontos()+10);
		  		System.out.println(j.getPontos());
		  		break;
		  	case a:
		  		j.setPontos(j.getPontos()+11);
		  		System.out.println(j.getPontos());
		  		break;	
	      }
		  c[i].setPertenceA(j.getNome());
		  System.out.println("Carta "+c[i].toString()+" pertence agora a"+j.getNome());
	  }
  }
  
  public static void CalculaDuplaVencedora(Jogador j[]){
	  int d1 = j[0].getPontos()+j[1].getPontos();
	  int d2 = j[2].getPontos()+j[3].getPontos();
	  if (d1>=90){
		  pontosD1+=2;
		  System.out.println("Dupla 1 venceu com "+d1+" pontos!");
	  }
	  else if (d1>=60){
		   pontosD1+=1;
		   System.out.println("Dupla 1 venceu com "+d1+" pontos!");
	  	  }
	  	   else if (d2>=90){
	          pontosD2+=2;
	          System.out.println("Dupla 2 venceu com "+d1+" pontos!");
	  	   }   
	  	        else if (d2>60){
	  	        	pontosD2+=1;
	  	        	System.out.println("Dupla 2 venceu com "+d1+" pontos!");
	  	        }
  }
  
  
}
