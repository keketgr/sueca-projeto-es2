package pkg.sueca;

public class Mesa {
	
	private Baralho baralho;
	private Jogador jogadores[]=new Jogador[4];
	final int numTotal = 4;

	public Mesa(short njog){
		baralho = new Baralho();
		VerificaJogadoresProntos();
		ComecaJogo();
		/*System.out.println(jogadores.length);
		for (int i=0; i<jogadores.length;i++){
			jogadores[i].toString();
		} */
	}
	
	public void ComecaJogo(){
		    Jogo j = new Jogo(baralho, jogadores);
	}
	
	
	public int VerificaJogadoresProntos(){
		//SuecaInterface.pegaNumJogadores();
	   jogadores[0] = new JogadorHumano("Player 1");
	   for (int i=0; i<4; i++)
       	if (jogadores[i]==null)
       		jogadores[i] = new JogadorCPU("CPU"+i);
	   return 1;
	}
	
	
}
