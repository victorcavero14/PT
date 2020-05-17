package tp.p1.commandHerencia;

import tp.p1.Exceptions.CommandParseException;
import tp.p1.Exceptions.UnknowCommandException;
import tp.p1.commands.AddCommand;
import tp.p1.commands.ExitCommand;
import tp.p1.commands.HelpCommand;
import tp.p1.commands.ListCommand;
import tp.p1.commands.LoadCommand;
import tp.p1.commands.NoneCommand;
import tp.p1.commands.PrintModeCommand;
import tp.p1.commands.ResetCommand;
import tp.p1.commands.SaveCommand;
import tp.p1.commands.ZombieListCommand;

public class CommandParse {
	private static Command[] availableCommands = {
			new AddCommand(),
			new NoneCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new ZombieListCommand(),
			new HelpCommand(),
			new PrintModeCommand(),
			new LoadCommand(),
			new SaveCommand()
			};

public static Command parseCommand(String[] commandWords) throws CommandParseException{
	Command command = null;
	boolean find = false;
	int i = 0 ;
	
	try
	{
		while (!find && i < availableCommands.length )
		{
			command = availableCommands[i].parse(commandWords);
			if (command != null) find = true;
			else
			{
				++i;
			}
		}
		
		if (command==null)
		{
			throw new UnknowCommandException("Unknown Command. Use 'Help' to see available commands.");
		}
	}
	catch (UnknowCommandException ex)
	{
		throw new CommandParseException(ex.getMessage());
	}
	catch (CommandParseException ex)
	{
		throw ex;
	}
	return command;
}

public static String CommandHelp()
{
	String help = "";
	for(int i = 0; i < availableCommands.length; i++)
	{
		help = help + availableCommands[i].helpText() + "\n";
	}
	
	return help;
}
}