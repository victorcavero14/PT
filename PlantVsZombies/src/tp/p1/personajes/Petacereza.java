package tp.p1.personajes;

import tp.p1.GameObject.Plant;

public class Petacereza extends Plant {
	
	public static final int CICLOEXPLOTA = 2;
	public static final int COSTE = 50;
	public static final String REPRESENTACION = "Peta[c] ereza";
	public static final int DANO = 10;
	public static final int RESISTENCIA = 2;
	public static final String LETRA = "PC";
	public static final int CICLOPORDEFECTO = 0;
	
	public Petacereza()
	{
		super(COSTE, RESISTENCIA, REPRESENTACION, DANO, CICLOPORDEFECTO, LETRA, CICLOEXPLOTA);
	}
	
	
	public boolean update()
	{
	if ((this.game.getCiclo() - this.cicloSalida) % CICLOEXPLOTA == 0)
	{
		this.direccionalAtaque(-1, 0);
		this.direccionalAtaque(+1, 0);	
		this.direccionalAtaque(0, -1);
		this.direccionalAtaque(0, +1);
		this.direccionalAtaque(+1, +1);
		this.direccionalAtaque(-1, -1);
		this.direccionalAtaque(-1, +1);
		this.direccionalAtaque(+1, -1);
		
		this.game.explotaPetacereza(fila, columna, 2);
	}
							
	return false;
	}
	
	private void direccionalAtaque(int a, int b)
	{
		if(!this.game.VacioZombie(this.fila + a, this.columna + b) && this.game.comprobarDentroTablero(this.fila + a, this.columna + b))
		{
			this.game.atacaZombie(this.fila + a, this.columna + b, this.dano);
		}
	}


	@Override
	public Plant parse(String plantName) {
		Plant planta = null;
		
		if(plantName.equalsIgnoreCase("petacereza") || plantName.equalsIgnoreCase("c"))
		{
			planta=new Petacereza();
		}
		
		return planta;
	}


}
  //Petacereza:esunaplantaqueexplotayquita10puntosdeda�oatodosloszombis que est�n a su alrededor. 
//La planta una vez que explota muere. La explosi�n ocurre dos ciclos despu�s de ser plantada. 
//Los zombis tambi�n se pueden comer a la planta que tiene resistencia 2. Su coste es 50 suncoins.