package tp.p1.commands;



import tp.p1.Game;
import tp.p1.commandHerencia.NoParamsCommand;

public class ExitCommand extends NoParamsCommand{

	public ExitCommand() {
		super("Exit", "Exit: ", "Terminates the program.");
	}
	

	
	public boolean execute(Game game)
	{
		game.exitJuego();
		game.setNoPrint(true);
		
		return false;
	}
	
}
