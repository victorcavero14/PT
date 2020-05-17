package tp.p1.commands;

import tp.p1.Game;
import tp.p1.commandHerencia.NoParamsCommand;

public class NoneCommand extends NoParamsCommand {

	public NoneCommand() {
		super("None","[none]: " , "Skips cycle.");
		
	}

	
	public boolean execute(Game game) 
	{
		game.setNoPrint(false);
		
		return true;
	}
	
}
