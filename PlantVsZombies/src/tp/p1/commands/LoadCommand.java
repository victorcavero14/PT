package tp.p1.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tp.p1.Game;
import tp.p1.Exceptions.CommandExecuteException;
import tp.p1.Exceptions.CommandParseException;
import tp.p1.Exceptions.FileContentsException;
import tp.p1.Exceptions.WrongArgumentsException;
import tp.p1.commandHerencia.Command;
import tp.pr1.util.MyStringUtils;

public class LoadCommand extends Command{
	
	private String FileName;
	private final static String header = "Plants Vs Zombies v3.0";

	public LoadCommand() {
		super("Load", "[Lo]ad <filename>: ", "Load the state of the game from a file.");
		this.FileName="";
	
	}
	

	
	public boolean execute(Game game) throws CommandExecuteException {
		String cadena;
		try
		{
			game.setNoPrint(false); //pintamos el tablero para saber si se ha cargado
			BufferedReader br=new BufferedReader(new FileReader(this.FileName+ ".dat"));
			cadena=br.readLine();
			if(cadena.equals(header))
			{
				game.load(br);
		 	}
			else
			{
				br.close();
				throw new CommandExecuteException("corrupt file");
			}
			
			br.close();
			System.out.println("Game successfully loaded from " + this.FileName + ".dat");
			
			
		}
		catch(IOException io)
		{
			throw new CommandExecuteException("Unable to load the file");
		}
		catch (FileContentsException ex)
		{
			throw new CommandExecuteException(ex.getMessage());
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		Command c=null;
		
		try {
				if (commandWords[0].equalsIgnoreCase("load")|| commandWords[0].equalsIgnoreCase("l"))
				{
					if (commandWords.length==2)
					{
						if (MyStringUtils.isValidFilename(commandWords[1]))
						{
							if  (MyStringUtils.fileExists(commandWords[1] + ".dat"))
							{
								if (MyStringUtils.isReadable(commandWords[1] + ".dat"))
								{
									this.FileName=commandWords[1];
									c=this;
								}
								else 
								{
									throw new CommandParseException("The file couldnt be read");
								}
									
							}
							else
							{
								throw new CommandParseException("File not found");
							}
						}
						else
						{
							throw new CommandParseException("Invalid File name");
						}
					}
					else
					{
						throw new WrongArgumentsException(this.commandName + " wrong arguments.");
					}
				}
		}
		catch(WrongArgumentsException ex)
		{
			throw new CommandParseException(ex.getMessage());
		}
		
		return c;
	}

}
