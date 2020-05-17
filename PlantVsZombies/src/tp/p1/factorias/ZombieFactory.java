package tp.p1.factorias;

import tp.p1.GameObject.Zombie;
import tp.p1.personajes.CaraCubo;
import tp.p1.personajes.Deportista;
import tp.p1.personajes.ZombieComun;

public class ZombieFactory {
	
		public static Zombie[] availableZombies =
			{
					new ZombieComun(),
					new CaraCubo(),
					new Deportista()
			};
		
		public static Zombie parseRandom (int numRand) // 0, 1,2  = numRand
		{
			Zombie zomb = null;
			Boolean find = false;
			int i = 0;
			
			while (!find && i < availableZombies.length )
			{
				zomb = availableZombies[i].parseRandom(numRand);
				if (zomb != null) find = true;
				else
				{
					++i;
				}
			}
			
			return zomb;
		}
		
		public static Zombie parseString (String zombie)
		{
			Zombie zomb = null;
			Boolean find = false;
			int i = 0;
			
			while (!find && i < availableZombies.length )
			{
				zomb = availableZombies[i].parse(zombie);
				if (zomb != null) find = true;
				else
				{
					++i;
				}
			}
			return zomb;
			
		}
		
		
		public static String listOfAvilableZombies()
		{
			String help = "";
			for(int i = 0; i < availableZombies.length; i++)
			{
				help = help + availableZombies[i].getNombre() + ": Speed: " + availableZombies[i].getVelocidad() + " Harm: " + 
						availableZombies[i].getDano() + " Life: " + availableZombies[i].getResistencia() + "\n";
			}
			return help;
		}
}
