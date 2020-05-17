package tp.p1.commands;



import tp.p1.Game;
import tp.p1.commandHerencia.NoParamsCommand;
import tp.p1.factorias.PlantFactory;

public class ListCommand extends NoParamsCommand {

	
	public ListCommand() {
		super("List", "List :", "Prints the list of available plants.");
		
	}
	
	
	public boolean execute(Game game) 
	{
		System.out.println(PlantFactory.listOfAvilablePlants());
		game.setNoPrint(true);
		
		return false;
	}

}
