package simulator.model;

import simulator.misc.Vector;

public class Body {
	
	protected String _id;
	protected Vector _v; //velocidad
	protected Vector _a; //aceleracion
	protected Vector _p; //posicion
	protected double _m; // masa
	
	public Body(String id, Vector v, Vector a, Vector p, Double m) 
	{
		_id = id;
		_v = v;
		_a = a;
		_p = p;
		_m = m;
	}
	
	public String getId()
	{
		return _id;
	}
	
	public Vector getVelocity()
	{
		Vector v = new Vector(_v);
		return v;
	}
	
	public Vector getAcceleration()
	{
		Vector v = new Vector(_a);
		return v;
	}
	
	public Vector getPosition()
	{
		Vector v = new Vector(_p);
		return v;
	}
	
	public double getMass()
	{
		return _m;
	}
	
	protected void setVelocity(Vector v)
	{
		Vector v1 = new Vector(v);
		_v = v1;
	}
	
	protected void setAcceleration (Vector a)
	{
		Vector v1 = new Vector(a);
		_a = v1;
	}
	
	protected void setPosition (Vector p)
	{
		Vector v1 = new Vector(p);
		_p = v1;
	}
	
	protected void move(double t) 
	{
		Vector aux = (_p.plus(_v.scale(t))); // p + v*t
		Vector aux2 = _a.scale((0.5* Math.pow(t,2))); // 1/2 * a * t^2
		_p = aux.plus(aux2);
		
		_v = _v.plus(_a.scale(t));
	}
	
	protected boolean equals(Body b)
	{
		return (_id == b._id);
	}
	
	public String toString()
	{
		StringBuilder bd = new StringBuilder();
		bd.append("{ \"id\": ");
		bd.append("\""+ _id + "\"");
		bd.append(", \"mass\": ");
		bd.append(_m);
		bd.append(", \"pos\": ");
		bd.append(_p.toString());
		bd.append(", \"vel\": ");
		bd.append(_v.toString());
		bd.append(", \"acc\": ");
		bd.append(_a.toString());
		bd.append("}");
		return bd.toString();
		
	}
	
}
