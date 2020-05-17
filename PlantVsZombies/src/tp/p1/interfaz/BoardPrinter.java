package tp.p1.interfaz;

import tp.p1.Game;
import tp.pr1.util.MyStringUtils;

public abstract class BoardPrinter implements GamePrinter {
	
	protected String[][] board;
	int dimX; 
	int dimY;
	String nombre;
	protected int filasOcupadas;
	final String space = " ";
	
	public BoardPrinter(String[][] board, int dimX, int dimY, String nombre, int filasOcupadas)
	{
		this.board = board;
		this.dimX = dimX;
		this.dimY = dimY;
		this.nombre = nombre;
		this.filasOcupadas = filasOcupadas;
	}

	public String boardToString(int CellSize)
	{
		int cellSize = CellSize;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (this.filasOcupadas * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int i=0; i<dimX; i++) {
				str.append(margin).append(vDelimiter);
				if(this.filasOcupadas == 0) str.append(" No objects in the game |");
				for (int j=0; j<this.filasOcupadas; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public abstract void printGame (Game game);
	public abstract void encodeGame(Game game);
}

