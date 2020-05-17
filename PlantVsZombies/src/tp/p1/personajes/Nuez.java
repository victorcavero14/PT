package tp.p1.personajes;


import tp.p1.GameObject.Plant;

public class Nuez extends Plant{
	
	public static final int COSTE = 50;
	public static final String REPRESENTACION = "[N] uez";
	public static final int DANO = 0;
	public static final int RESISTENCIA = 10;
	public static final String LETRA = "N";
	public static final int CICLOACTUA = 1;
	public static final int CICLOPORDEFECTO = 0;
		
	public Nuez()
		{
			super(COSTE, RESISTENCIA, REPRESENTACION, DANO , CICLOPORDEFECTO, LETRA, CICLOACTUA);
		}

	
	public  boolean update() {
		return false;
	}


	@Override
	public Plant parse(String plantName) {
		Plant planta = null;
		
		if(plantName.equalsIgnoreCase("nuez") || plantName.equalsIgnoreCase("n"))
		{
			planta=new Nuez();
		}
		return planta;
	}
		
		
		
	
}
