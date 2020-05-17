package tp.p1.managers;

import java.util.Random;

public class ZombieManager {

	private int numZombies;
	private Random rand;
	
	public ZombieManager(int zombie, Random rand)
	{
		this.numZombies = zombie;
		this.rand = rand;
	}
	
	public void setZombies(int zombies)
	{
		this.numZombies = zombies;
	}
	
	public void RestarZombies()
	{
		this.numZombies--;
	}
	
	public boolean isZombieAdded(double frecuencia)
	{
	return this.rand.nextDouble() < frecuencia;
		
	}
	
	public boolean noQuedanZombies()
	{
		return this.numZombies==0;
	}
	
	
	public String toString() {
		return "Remaining zombies: " + numZombies ;
	}

	public int getZombies() {
		// TODO Auto-generated method stub
		return this.numZombies;
	}
	
	
}
