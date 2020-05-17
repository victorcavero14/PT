package tp.p1.personajes;

import tp.p1.GameObject.Zombie;

public class CaraCubo extends Zombie{
	
	public static final int CICLOACTUALIZACION = 4; 
	public static final int CICLOPORDEFECTO = 0;
	public static final String REPRESENTACION = "[W] CaraCubo";
	public static final int DANO = 1;
	public static final int RESISTENCIA = 8;
	public static final String LETRA = "W";
	public static final int NUMRANDOM = 1;
	
		public CaraCubo()
		{
			super(CICLOACTUALIZACION, CICLOPORDEFECTO , REPRESENTACION, DANO, RESISTENCIA , LETRA);
		}

		@Override
		public Zombie parse(String zombie) {
			Zombie zomb = null;
			
			if (zombie.equalsIgnoreCase("X"))
			{
				zomb = new Deportista();
			}
			
			return zomb;
		}

		@Override
		public Zombie parseRandom(int numRand) {
			Zombie zombie = null;
			if (numRand == NUMRANDOM)
			{
				zombie = new CaraCubo();
			}
			return zombie;
		}
		
		
		
}
