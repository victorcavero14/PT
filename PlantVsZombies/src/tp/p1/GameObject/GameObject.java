package tp.p1.GameObject;

import tp.p1.Game;

public abstract class GameObject {
	
	protected int fila;
	protected int columna;
	protected Game game;
	protected int dano;
	protected int cicloSalida;
	protected String nombre;
	protected int resistencia;
	protected String letra;
	protected int cicloActua;
	
	public GameObject (String nombre, int dano, int resistencia, int cicloSalida, String letra2, int cicloActua)
	{
		this.dano = dano;
		this.nombre = nombre;
		this.resistencia = resistencia;
		this.cicloSalida = cicloSalida;
		this.letra = letra2;
		this.cicloActua = cicloActua;
	}

	public GameObject(int fila, int columna, String nombre, Game game, int dano, int resistencia, String letra)
	{
		this.columna= columna;
		this.fila = fila;
		this.game = game;
		this.dano = dano;
		this.letra = letra;
		this.resistencia = resistencia;
	}

	public boolean isDead() {
		return this.resistencia < 1 ; // Cuando ocurre esto es porque la planta muere al siguiente turno 
	}
	public void quitarResistencia(int resistencia)
	{
		this.resistencia = this.resistencia - resistencia;
	}
	public int getColumna() {
		return columna;
	}
	
	public void setGame(Game game) {
		this.game = game;
		
	}
	public void setCiclo(int ciclo) {
		this.cicloSalida = ciclo;
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getCicloSalida() {
		return cicloSalida;
	}
	
	public int getDano() {
		return this.dano;
	}

	public void setCicloSalida(int cicloSalida) {
		this.cicloSalida = cicloSalida;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}
	
	public void setCicloActu (int cicloActu)
	{
		this.cicloActua = cicloActu;
	}
	
	public void setResistencia (int resistencia)
	{
		this.resistencia = resistencia;
	}
	public String toString()
	{
		return this.letra + " [" + this.resistencia + "]";
	}
	
	public void setGameObj(Game game)
	{
		this.game = game;
	}
	
	public abstract String toStringDebug();
	public abstract boolean update() ;
	
}
