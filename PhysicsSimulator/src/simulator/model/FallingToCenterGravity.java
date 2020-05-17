package simulator.model;

import java.util.List;

public class FallingToCenterGravity implements GravityLaws{

	public static final double g = 9.81;

	@Override
	public void apply(List<Body> bodies) {
		
		for(Body b : bodies)
		{
			b.setAcceleration(b.getPosition().direction().scale(-g));
			//actualizamos la aceleracion de toda la lista de cuerpos 
		}
	}
	
	public String toString()
	{
		return ("Falling To Center Gravity [ftcg]");
	}
	
}
