package tp.p1.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tp.p1.Game;
import tp.p1.Exceptions.CommandExecuteException;
import tp.p1.Exceptions.CommandParseException;
import tp.p1.Exceptions.WrongArgumentsException;
import tp.p1.commandHerencia.Command;
import tp.pr1.util.MyStringUtils;

public class SaveCommand extends Command
{

	private final static String header = "Plants Vs Zombies v3.0";
	private String fileName;
	
	public SaveCommand()
	{
		super("save","[S]ave <filename>: ","Save the state of the game to a file");
		this.fileName = "";
	}

	
	public boolean execute(Game game) throws CommandExecuteException {
		
		game.setNoPrint(true);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName + ".dat"));
			bw.write(header);
			bw.newLine();
			bw.newLine(); //Double new line for the file
			
			game.store(bw);
			bw.close(); //Close the file to see the content
			System.out.println("Game successfully saved in: " + this.fileName + ".dat" );
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CommandExecuteException("Unable to save the file");
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		Command c = null;
		
			try {
			
				if (commandWords[0].equalsIgnoreCase("save")|| commandWords[0].equalsIgnoreCase("s")) 
					{
						if (commandWords.length == 2)
						{ 
							if(MyStringUtils.isValidFilename(commandWords[1]))
							{
								this.fileName = commandWords[1];
								c = this;
							}
							else 
							{
								throw new CommandParseException("Invalid filename: the filename contains invalid characters");
							}
						}
						else 
						{
							throw new WrongArgumentsException(this.commandName + " wrong arguments.");
						}
					}
			}

		catch (WrongArgumentsException ex)
		{
			throw new CommandParseException(ex.getMessage());
	 	}
	
		return c;
	}
}
