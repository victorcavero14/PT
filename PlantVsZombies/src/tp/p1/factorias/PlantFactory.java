package tp.p1.factorias;

import tp.p1.GameObject.Plant;
import tp.p1.personajes.Nuez;
import tp.p1.personajes.Peashooter;
import tp.p1.personajes.Petacereza;
import tp.p1.personajes.Sunflower;

public class PlantFactory
{
	public static Plant[] availablePlants =
		{
				new Sunflower(),
				new Peashooter(),
				new Nuez(),
				new Petacereza()
				
		};
	
	public static Plant parse (String plantName)
	{
		Plant planta = null;
		Boolean find = false;
		int i = 0;
		
		while (!find && i < availablePlants.length )
		{
			planta = availablePlants[i].parse(plantName);
			if (planta != null) find = true;
			else
			{
				++i;
			}
		}
		
		return planta;
	}
	
	public static String listOfAvilablePlants()
	{
		String help = "";
		for(int i = 0; i < availablePlants.length; i++)
		{
			help = help + availablePlants[i].getNombre() + ": Cost: " + availablePlants[i].getCoste() + " Harm: " + 
					availablePlants[i].getDano() + "\n";
		}
		return help;
	}
	 
	
}
