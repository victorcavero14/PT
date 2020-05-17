package tp.p1.GameObject;

public abstract class Plant extends GameObject{

	protected int coste;
	
	public Plant(int Coste, int resistencia, String Nombre, int dano, int cicloSalida, String letra, int cicloActua)
	{
		super(Nombre, dano, resistencia, cicloSalida, letra, cicloActua);
		this.coste = Coste;
	}
	
	public int getCoste ()
	{
		return this.coste;
	}

	public abstract boolean update() ;
	
	public String toStringDebug()
	{
		return this.letra + "[" + "l:" + this.resistencia + ",x:" + this.fila + ",y:" + this.columna + ",t:" +
				(this.cicloActua - ((this.game.getCiclo() - this.cicloSalida) % this.cicloActua)) + "]";
	}

	public abstract Plant parse(String plantName);


}
