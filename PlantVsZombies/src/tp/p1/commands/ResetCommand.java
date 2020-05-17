package tp.p1.commands;



import tp.p1.Game;
import tp.p1.commandHerencia.NoParamsCommand;

public class ResetCommand extends NoParamsCommand {
	
	public ResetCommand() {
		super("Reset", "Reset: ", "Starts a new game.");
		
	}

	
	public boolean execute(Game game) {
		game.reset();
		game.setNoPrint(false);
		
		return false;
	}
	
	
}
