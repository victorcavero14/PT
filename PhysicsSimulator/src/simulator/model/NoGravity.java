package simulator.model;

import java.util.List;

public class NoGravity implements GravityLaws{

	@Override
	public void apply(List<Body> bodies) {
		// No hace nada, metodo apply vacio	
	}

	public String toString()
	{
		return("No Gravity Builder [ng]");
	}
	
}
