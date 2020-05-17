package tp.p1.commands;

import tp.p1.Game;
import tp.p1.Exceptions.CasillaOcupadaException;
import tp.p1.Exceptions.CommandExecuteException;
import tp.p1.Exceptions.CommandParseException;
import tp.p1.Exceptions.NoSuncoinsException;
import tp.p1.Exceptions.OutOfBoardException;
import tp.p1.Exceptions.WrongArgumentsException;
import tp.p1.GameObject.Plant;
import tp.p1.commandHerencia.Command;
import tp.p1.factorias.*;

public class AddCommand extends Command  {
protected int x;
protected int y;
protected String nombre;


public AddCommand()
{
	super("Add","Add <plant> <x> <y>: ","Adds a plant in position x, y.");
}

public AddCommand(int x2, int y2, String nombre2) {
	super("Add","Add <plant> <x> <y>: ","Adds a plant in position x, y.");
	this.x = x2;
	this.y = y2;
	this.nombre = nombre2;
}

public Command parse(String[] commandWords) throws CommandParseException {
	Command command = null;
	
	try {
			
			if (commandWords[0].equalsIgnoreCase("add")|| commandWords[0].equalsIgnoreCase("a")) 
				{
					if (commandWords.length == 4)
					{ 
			
						Plant planta = PlantFactory.parse(commandWords[1]);
					
						if(planta != null)
						{
							int x=Integer.parseInt(commandWords[2]);
							int y=Integer.parseInt(commandWords[3]);
							String nombre=commandWords[1];
							command = new AddCommand(x , y , nombre);
						}
						else 
						{
							throw new CommandParseException("Unknown plant name " + commandWords[1]);
						}
					}
					else 
					{
						throw new WrongArgumentsException("Incorrect number of arguments for add command: [A]dd <plant> <x> <y>");
					}
			}
			
			
		}
		catch (NumberFormatException ex)
		{
			throw new CommandParseException("Invalid arguments for add commands, number expected: [A]dd <plant> <x> <y>");
		}
		catch (WrongArgumentsException ex)
		{
			throw new CommandParseException(ex.getMessage());
	 	}
	 
	return command;
}

public boolean execute(Game game) throws CommandExecuteException
{
	boolean added = false;
	try
	{

		Plant plant = PlantFactory.parse(this.nombre);
		
		game.incluirPlanta(plant, this.x, this.y);
		
		added = game.addPlanta(plant);
		game.setNoPrint(false);
	}
	catch (CasillaOcupadaException ex)
	{
		throw new CommandExecuteException(ex.getMessage());
	}
	catch (NoSuncoinsException ex)
	{
		throw new CommandExecuteException(ex.getMessage());
	}
	catch (OutOfBoardException ex)
	{
		throw new CommandExecuteException(ex.getMessage());
	}
	
	return added;
}

}

