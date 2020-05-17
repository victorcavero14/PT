package tp.p1.personajes;

import tp.p1.GameObject.Plant;

public class Sunflower extends Plant{
	
	public static final int CICLOSOL = 2;
	public static final int COSTE = 20;
	public static final String REPRESENTACION = "[S] unFlower";
	public static final int DANO = 0;
	public static final int RESISTENCIA = 2;
	public static final String LETRA = "S";
	public static final int CICLOPORDEFECTO = 0;
	
	public Sunflower()
	{
		super(COSTE, RESISTENCIA, REPRESENTACION, DANO, CICLOPORDEFECTO, LETRA, CICLOSOL);
	}
	
	public void genSoles()
	{
		this.game.aumentarSoles(this.cicloSalida);
	}

	public boolean update() {
		this.genSoles();
		return false;
	}

	@Override
	public Plant parse(String plantName) {
		
		Plant planta = null;
		
		if (plantName.equalsIgnoreCase("sunflower") || plantName.equalsIgnoreCase("s"))
		{
			planta  = new Sunflower();
		}
		return planta;
	}
	
}
