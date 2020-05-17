package tp.p1.commands;

import tp.p1.Game;
import tp.p1.Exceptions.CommandParseException;
import tp.p1.Exceptions.WrongArgumentsException;
import tp.p1.commandHerencia.Command;
import tp.p1.managers.PrinterManager;

public class PrintModeCommand extends Command{

	protected String nombre;

	public PrintModeCommand()
	{
		super("PrintMode","[P]rintMode: ","change print mode [Release|Debug].");
	}
	
	public Command parse(String[] commandWords) throws CommandParseException
	{
		Command command = null;
		try 
		{
			 
				if (commandWords[0].equalsIgnoreCase("printmode")|| commandWords[0].equalsIgnoreCase("p")) 
				{
					if (commandWords.length == 2){
						if( commandWords[1].equalsIgnoreCase("release")|| commandWords[1].equalsIgnoreCase("debug"))
						{
							this.nombre = commandWords[1];
							command = this;
						}
						else
						{
							throw new CommandParseException("Unknown print mode :" + commandWords[1]);
						}
					}
					else
					{
						throw new WrongArgumentsException(" Incorrect number of arguments for printmode command: [P]rintMode release|debug");
					}
				}
			
		}
		catch(WrongArgumentsException ex)
		{
			throw new CommandParseException();
		}
		
		return command;
	}

	public boolean execute(Game game)
	{
		game.setNoPrint(false);
		game.setBoardPrinter(PrinterManager.parse(this.nombre));
		
		return false;
	}


}
