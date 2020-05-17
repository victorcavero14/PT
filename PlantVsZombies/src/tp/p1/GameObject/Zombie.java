package tp.p1.GameObject;

public abstract class Zombie extends GameObject{

	//static final private int dano = 1;
	//private int fila;
	//private int columna;
	//private int resistencia;
	//private Game game;
	
	public Zombie (int cicloActua, int ciclo, String Nombre, int dano, int resistencia, String letra)
	{
		super(Nombre, dano, resistencia, ciclo, letra, cicloActua);
	}
	
	public void QuitarResistencia(int dano)
	{
		this.resistencia = this.resistencia - dano;
	}
	
	public void AtacarplantaAdyacente()
	{
		this.game.zombieAtaca(fila, columna - 1, dano);
	}
	
	public void restarColumna()
	{
		this.columna = this.columna - this.cicloActua;
	}

	public int getCicloSalida() {
		return cicloSalida;
	}
	
	public void setCicloSalida(int cicloSalida) {
		this.cicloSalida = cicloSalida;
	}
	
	public boolean winZ()
	{
		return this.columna == 0;
	}

	public int getVelocidad ()
	{
		return this.cicloActua;
	}
	
	public void moverZombie()
	{
		if(this.game.comprobarDentroTablero(this.fila, this.columna-1) && this.game.comprobarCoordenadas(this.fila, columna-1))
		{
			this.columna = this.columna - 1;
		}
		else if (this.game.comprobarDentroTablero(this.fila, this.columna-1) && !this.game.comprobarCoordenadas(this.fila, columna-1))
		{
			this.AtacarplantaAdyacente();
		}
	}
	
	public boolean update() {
		
			boolean winZ = false;
			if((this.game.getCiclo() - this.cicloSalida) % (this.cicloActua)==0)
			{
			this.moverZombie();
			
			if(this.columna == 0)
			{
				winZ = true;
			}
			}
			return winZ;
	}
	
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getColumna() {
		return this.columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	
	public int getResistencia() 
	{
		return resistencia;
	}
	
	public String toStringDebug()
	{
		return this.letra + "[" + "l:" + this.resistencia + ",x:" + this.fila + ",y:" + this.columna + ",t:" +
				(this.cicloActua - ((this.game.getCiclo() - this.cicloSalida) % this.cicloActua)) + "]";
	}

	public abstract Zombie parse(String zombie);

	public abstract Zombie parseRandom(int numRand);
	
}
