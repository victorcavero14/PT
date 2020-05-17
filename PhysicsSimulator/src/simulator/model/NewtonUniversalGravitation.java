package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws{

	
	static private final double G = 6.67E-11;

	@Override
	public void apply(List<Body> bodies) 
	{
		Vector force = null;
		
		for(Body b : bodies)
		{
			if(b.getMass() <= 0 )
			{
				b.setAcceleration(new Vector(b.getAcceleration().dim()));
				b.setVelocity(new Vector(b.getVelocity().dim()));
			}
			else 
			{
				force = new Vector(b.getVelocity().dim());
				
				for(Body bj : bodies)
				{
					if (!b.equals(bj))
					{
						force = new Vector(force.plus(force(b, bj)));	
					}
				}
				
				}
				
				b.setAcceleration(aij(force, b));
			}
				
	}
	
	private Vector force(Body bodi_i, Body bodi_j)
	{
		double distancia = Math.pow(bodi_j.getPosition().distanceTo(bodi_i.getPosition()), 2);
		double fij = (G * (bodi_i.getMass() * bodi_j.getMass())) / distancia;
		
		Vector dir = (bodi_j.getPosition().minus(bodi_i.getPosition())).direction();
		Vector res_force = dir.scale(fij);
		
		return res_force;
	}
	
	private Vector aij(Vector Fij, Body body_i)
	{
		Vector resul = Fij.scale(1/body_i.getMass());
		return resul;
	}
	
	public String toString ()
	{
		return ("Newton's law of universal gravitation [nlug]");
	}
	

}
