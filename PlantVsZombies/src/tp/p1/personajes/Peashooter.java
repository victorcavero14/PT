package tp.p1.personajes;

import tp.p1.Game;
import tp.p1.GameObject.Plant;

public class Peashooter extends Plant {
	
	public static final int COSTE = 50;
	public static final String REPRESENTACION = "[P] eashooter";
	public static final int DANO = 1;
	public static final int RESISTENCIA = 3;
	public static final String LETRA = "P";
	public static final int CICLOACTUA = 1;
	public static final int CICLOPORDEFECTO = 0;
	
	
	public Peashooter ()
	{
		super(COSTE, RESISTENCIA, REPRESENTACION, DANO, CICLOPORDEFECTO, LETRA, CICLOACTUA);
	}
	
	public boolean update() {
		
		for(int i = this.columna; i < Game.NCOL; i++)
		{
			if(!this.game.VacioZombie(this.fila, i))
			{
				this.game.atacaZombie(this.fila, i, this.dano);
			}
		}
		return false;
	}

	@Override
	public Plant parse(String plantName) {
		
		Plant planta = null;
		
		if (plantName.equalsIgnoreCase("peashooter") || plantName.equalsIgnoreCase("p"))
		{
			planta = new Peashooter();
		}
		return planta;
	}

}
