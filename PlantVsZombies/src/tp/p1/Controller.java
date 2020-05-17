package tp.p1;

import java.util.Scanner;

import tp.p1.Exceptions.CommandExecuteException;
import tp.p1.Exceptions.CommandParseException;
import tp.p1.commandHerencia.Command;
import tp.p1.commandHerencia.CommandParse;

public class Controller {

	private Game game;
	private Scanner in;
	
	
	public Controller (Game game) 
	{
		this.game = game;
		this.in = new Scanner(System.in);
		
	}
	
	public void run ()
	{
		while(!this.game.getExit() && !game.isFinished())
		{
			
				if(!this.game.getNoPrint()) this.printGame();
				this.game.setNoPrint(false);
				
				System.out.print("Command > ");
				String[]  words = this.in.nextLine().toLowerCase().trim().split("\\s+");
				try 
				{
					Command command = CommandParse.parseCommand(words);
					if (command != null)
					{
					
						if (command.execute(game)) this.game.update();
					}
				
				}
				catch (CommandParseException ex)
				{
					System.out.println(ex.getMessage());
					game.setNoPrint(true);
				}
				catch (CommandExecuteException ex)
				{
					System.out.println(ex.getMessage());
					game.setNoPrint(true);
				}
		}
		
		if(this.game.getWinU())
		{
			this.printGame();
			System.out.println("USER wins");
		}
		
		else if(this.game.getWinZ())
		{
			this.printGame();
			System.out.println("ZOMBIE wins");
		}
		if (this.game.getExit()) System.out.print("****** " + 
				"Game over!: User exit " + 
				"******");
	}


	private void printGame() {
		
			System.out.println(this.game.CicloToString());
			System.out.println(this.game.getSuncoinManager().toString());
			System.out.println(this.game.getZombieManager().toString());
			
			this.mostrarListas();
	}
	
	 public void mostrarListas()
	{
		this.game.getbp().printGame(this.game); 
	}
	 
	 public void infoDebug()
		{
			System.out.println("Seed:" + this.game.getseed());
			System.out.println("Level: " + this.game.getlevel().toString());
		}		
	 

}
