package tp.p1.interfaz;

import tp.p1.Game;

public class DebugPrinter extends BoardPrinter{

	public static final int CELLSIZE = 20;
	
	public DebugPrinter()
	{
		super(new String[1][Game.NCOL * Game.NFILA], 1, (Game.NCOL * Game.NFILA), "Debug", 0); //board , dimX, dimY del SUPER()
	}
	
	
	public void printGame(Game game) {
		game.infoDebug();
		this.encodeGame(game);
		System.out.println(this.boardToString(CELLSIZE));
	}

	
	public void encodeGame(Game game) {
		
		this.filasOcupadas = 0;
		
		for(int i=0; i < dimY; i++)
		{
			board[0][i] = "";
		}

		
		for(int j = 0; j < Game.NFILA; j++)
		{
			for(int i = 0; i < dimY; i++)
			{	
				if(!game.devolverStringDebugPlant(j,i).isEmpty())
				{ 
				board[0][this.filasOcupadas] = game.devolverStringDebugPlant(j,i);
				this.filasOcupadas++;
				}
				// TODO Fill the board with simulation objects
			}
			
			for(int i = 0; i < dimY; i++) 
			{
				if(!game.devolverStringDebugZombie(j,i).isEmpty())
				{		
					board[0][this.filasOcupadas] = game.devolverStringDebugZombie(j,i);//Representacion textual del elemento
					this.filasOcupadas++;
				}
			// TODO Fill the board with simulation objects
			}
		}
		
	}
}
