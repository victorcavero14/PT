package tp.p1.main;

import java.util.Random;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.Level;
import tp.p1.Exceptions.UnknowLevelException;
import tp.p1.Exceptions.WrongArgumentsException;
public class PlantsVsZombies {

	// Level y seed de parametros de entrada 
	
	public static void main(String[] args) throws WrongArgumentsException,UnknowLevelException
	{
		
	
		
		Level level= Level.EASY;
		Random rand;
		long seed;
		
		try
		{
		
		if(args.length < 1 || args.length > 2)// error de parametros
		{
			throw new WrongArgumentsException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]");
		}
		else //Parametros correctos
		{
			
			if(args[0].toLowerCase().equalsIgnoreCase("easy")) level = Level.EASY;
			else if(args[0].toLowerCase().equalsIgnoreCase("hard")) level = Level.HARD;
			else if(args[0].toLowerCase().equalsIgnoreCase("insane")) level = Level.INSANE;
			else
			{
				throw new UnknowLevelException();
			}
			
				if(args.length == 2)
				{	
					seed = Long.parseLong(args[1]);
				}
				else 
				{
					seed = new Random().nextInt(1000);
				}
			
			
		    System.out.println("Random seed used: " + seed);
			rand = new Random(seed);
			Game game = new Game(level, rand, seed);
			Controller cont = new Controller(game);
			cont.run();
		
		}
		}
		catch (WrongArgumentsException ex)
		{
			System.err.println(ex.getMessage());
		}
		catch(UnknowLevelException ex)
		{
			System.err.println(ex.getMessage());
		}
		catch (NumberFormatException ex)
		{
			System.err.println("Usage: plantsVsZombies <" + Level.all("|") + "> [seed]. The seed must be a number");
		}
	}
	
	

}
