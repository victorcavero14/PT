package tp.p1.personajes;

import tp.p1.GameObject.Zombie;

public class Deportista extends Zombie{

	public static final int CICLOACTUALIZACION = 1; 
	public static final int CICLOPORDEFECTO = 0;
	public static final String REPRESENTACION = "[X] Deportista";
	public static final int DANO = 1;
	public static final int RESISTENCIA = 2;
	public static final String LETRA = "X";
	public static final int NUMRANDOM = 2;
	
	
	public Deportista()
	{
		super(CICLOACTUALIZACION, CICLOPORDEFECTO , REPRESENTACION, DANO, RESISTENCIA , LETRA);
	}


	@Override
	public Zombie parse(String zombie) {
		Zombie zomb = null;
		if (zombie.equalsIgnoreCase("W"))
		{
			zomb = new CaraCubo();
		}
		return zomb;
	}


	@Override
	public Zombie parseRandom(int numRand) {
		Zombie zombie = null;
		if (numRand == NUMRANDOM)
		{
			zombie = new Deportista();
		}
		return zombie;
	}
	
}
