package tp.p1.commands;

import tp.p1.Game;
import tp.p1.commandHerencia.NoParamsCommand;
import tp.p1.factorias.ZombieFactory;

public class ZombieListCommand extends NoParamsCommand{

	public ZombieListCommand() {
		super("ZombieList", "ZombieList :", "Prints the list of available zombies.");
		
	}
	
	
	public boolean execute(Game game) 
	{
		System.out.println(ZombieFactory.listOfAvilableZombies());
		game.setNoPrint(true);
		
		return false;
	}
}
