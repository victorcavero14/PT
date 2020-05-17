package tp.p1.commandHerencia;



import tp.p1.Game;
import tp.p1.Exceptions.CommandExecuteException;
import tp.p1.Exceptions.CommandParseException;

public abstract class Command {
	private String helpText;
	private String helpInfo;
	protected final String commandName;
	
	public Command(String commandText, String commandTextMsg, String helpTextMsg){
		commandName = commandText;
		helpText = commandTextMsg;
		helpInfo = helpTextMsg;
	}
	
	
// Some commands may generate an error in the execute or parse methods.
// In the absence of exceptions , they must the tell the controller not to print the board
	public abstract boolean execute(Game game) throws CommandExecuteException;
	
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
	public String helpText(){return " " + helpText + helpInfo;}
}
	
