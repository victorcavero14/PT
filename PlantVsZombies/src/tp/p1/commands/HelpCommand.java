package tp.p1.commands;



import tp.p1.Game;

import tp.p1.commandHerencia.CommandParse;
import tp.p1.commandHerencia.NoParamsCommand;

public class HelpCommand extends NoParamsCommand {

	public HelpCommand() {
		super("Help", "Help :", "Prints this help message.");
		
	}
	
	
	public boolean execute(Game game) 
	{
		
		System.out.println(CommandParse.CommandHelp());
		game.setNoPrint(true);
		
		return false;
	}

	
}
