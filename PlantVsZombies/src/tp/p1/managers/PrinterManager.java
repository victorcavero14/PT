package tp.p1.managers;

import tp.p1.interfaz.BoardPrinter;
import tp.p1.interfaz.DebugPrinter;
import tp.p1.interfaz.ReleasePrinter;

public class PrinterManager {
	private BoardPrinter bp;
	
	public PrinterManager ()
	{
		bp =new ReleasePrinter();
	}

	public BoardPrinter getbp() {
		// TODO Auto-generated method stub
		return this.bp;
	}

	public void setbp(BoardPrinter bp2) {
		// TODO Auto-generated method stub
		this.bp = bp2;
	}

	public static BoardPrinter parse(String nombre) {
		BoardPrinter bp = null;
		if(nombre.equalsIgnoreCase("release"))
		{
			bp = new ReleasePrinter();
		}
		else if (nombre.equalsIgnoreCase("debug"))
		{
			bp = new DebugPrinter();
		}
		return bp;
	}
}
