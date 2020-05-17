package tp.p1.commandHerencia;



import tp.p1.Game;
import tp.p1.Exceptions.CommandParseException;
import tp.p1.Exceptions.WrongArgumentsException;

public abstract class NoParamsCommand extends Command{

	public NoParamsCommand(String commandText, String commandTextMsg, String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
		// TODO Auto-generated constructor stub
	}
	
	public abstract boolean execute(Game game);
	
	public Command parse(String[] commandWords) throws CommandParseException
	{
		String primeraletra = this.commandName.charAt(0)+ "";
		Command command = null;
		try {
		
			if (commandWords[0].equalsIgnoreCase(this.commandName)
				|| commandWords[0].equalsIgnoreCase(primeraletra) ||
				commandWords[0].isEmpty())
				{
					if (commandWords.length == 1) 
					{ 
						command = this;
					}
					else
					{
						throw new WrongArgumentsException(this.commandName + " command has no arguments");
					}
				}
			
		
		}
		
		catch(WrongArgumentsException ex)
			{
				throw new CommandParseException(ex.getMessage());
			}
		
		
		
		return command;
	}
}
