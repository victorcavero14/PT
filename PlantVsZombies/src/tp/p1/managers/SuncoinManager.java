package tp.p1.managers;

public class SuncoinManager {
	
	static final int SOLESINICIALES = 50;
	private int soles;
	
	public SuncoinManager()
	{
		this.soles = SOLESINICIALES;
	}
	
	
	public int getSoles()
	{
		return this.soles;
	}
	public void restarSoles(int soles)
	{
		this.soles = this.soles - soles;
	}

	@Override
	public String toString() {
		return "Sun coins: " + soles ;
	}
	
	public void aumentarSoles (int cicloInicial,int cicloActual)
	{
		if((cicloActual-cicloInicial) % 2==0)
		{
		this.soles =this.soles+10; 
		}
	}
	
	public void setSoles(int soles)
	{
		this.soles = soles;
	}

	public void resetearSoles() {
		this.soles = SOLESINICIALES;
	}
	
}
