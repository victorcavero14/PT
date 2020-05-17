package tp.p1.personajes;

import tp.p1.GameObject.Zombie;

public class ZombieComun extends Zombie {
	
	public static final int CICLOACTUALIZACION = 2; 
	public static final int CICLOPORDEFECTO = 0;
	public static final String REPRESENTACION = "[Z] ZombieComun";
	public static final int DANO = 1;
	public static final int RESISTENCIA = 5;
	public static final String LETRA = "Z";
	public static final int NUMRANDOM = 0;
	
	public ZombieComun()
	{
		super(CICLOACTUALIZACION, CICLOPORDEFECTO , REPRESENTACION, DANO, RESISTENCIA , LETRA);
	}

	@Override
	public Zombie parse(String zombie) {
		Zombie zomb = null;
		if (zombie.equalsIgnoreCase("Z"))
		{
			zomb = new ZombieComun();
		}
		return zomb;
	}

	@Override
	public Zombie parseRandom(int numRand) {
		Zombie zombie = null;
		
		if (numRand == NUMRANDOM)
		{
			zombie = new ZombieComun();
		}
		return zombie;
		
	}

}
