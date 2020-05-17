package tp.p1.interfaz;

import tp.p1.Game;

public class ReleasePrinter extends BoardPrinter {

	
	public static final int CELLSIZE = 7;
	
	public ReleasePrinter()
	{
		super(new String[Game.NFILA][Game.NCOL], Game.NFILA, Game.NCOL , "Release",  Game.NCOL);
	}
	
	
	public void printGame(Game game) {
		this.encodeGame(game);
		System.out.println(this.boardToString(CELLSIZE));
	}

	
	public void encodeGame(Game game) {

		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {

				board[i][j] = game.devolverString(i,j); //Representacion textual del elemento
				
				// TODO Fill the board with simulation objects
			}
		}
		
	}
}


