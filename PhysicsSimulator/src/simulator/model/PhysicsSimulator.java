package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSimulator {

	
	private double _tiempoActual;
	private double _tiempoRealPorPaso;
	private List<Body> _listaBodies;
	private GravityLaws _gl;
	private List<SimulatorObserver> observers;
	
	public PhysicsSimulator (Double tiempoRealPorPaso, GravityLaws gl) throws IllegalArgumentException
	{
		_tiempoActual = 0.0;
		if (tiempoRealPorPaso > 0 && gl != null) 
		{
			_tiempoRealPorPaso = tiempoRealPorPaso;
			_gl = gl;
		}
		else
		{
			throw new IllegalArgumentException();
		}
		
		observers = new ArrayList<SimulatorObserver>();
		_listaBodies = new ArrayList<Body>();
	}
	
	
	public void advance()
	{
		_gl.apply(_listaBodies);
		
		for(Body b:_listaBodies)
		{
			b.move(_tiempoRealPorPaso);
		}
		
		_tiempoActual += _tiempoRealPorPaso;
		
		for(SimulatorObserver observ:observers)
		{
			observ.onAdvance(_listaBodies, _tiempoActual);
		}
	}
	
	public void addBody(Body b) throws IllegalArgumentException
	{
		
		if (_listaBodies.contains(b)) throw new IllegalArgumentException();
			
		_listaBodies.add(b);
		
		for(SimulatorObserver observ:observers)
		{
			observ.onBodyAdded(_listaBodies, b);
		}
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{ \"time\": ");
		sb.append(_tiempoActual);
		sb.append(",");
		sb.append(" \"bodies\": ");
		sb.append("[");

		for (int i = 0; i < _listaBodies.size(); i++)
		{
			sb.append(_listaBodies.get(i).toString());
			if(i + 1 != _listaBodies.size()) sb.append(", ");
		}
		
		sb.append("]");
		sb.append("}");
		return sb.toString();
	}
	
	/* PRACTICA 5*/
	
	//vacia la lista de cuerpos y pone el tiempo a 0,0.
	public void reset()
	{
		_listaBodies.clear();
		_tiempoActual = 0.0;
		
		for(SimulatorObserver observ:observers)
		{
			observ.onReset(_listaBodies, _tiempoActual, _tiempoRealPorPaso, _gl.toString());
		}
	}
	
	/*cambia el tiempo real por paso (delta-time de aqu�
	en adelante) a dt. Si dt tiene un valor no v�lido lanza una excepci�n de tipo IllegalArgumentException */
	
	public void setDeltaTime(double dt) throws IllegalArgumentException
	{
		if (dt > 0)
		{
			_tiempoRealPorPaso = dt;
			
			for(SimulatorObserver observ:observers)
			{
				observ.onDeltaTimeChanged(_tiempoRealPorPaso);
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/*cambia las leyes de gravedad del
	simulador a gravityLaws. Lanza una IllegalArgumentException si el valor no es v�lido,
	es decir, si es null.*/
	
	public void setGravityLaws(GravityLaws gravityLaws)
	{
		_gl = gravityLaws;
		
		for(SimulatorObserver observ:observers)
		{
			observ.onGravityLawChanged(_gl.toString());
		}
	}
	public String getGravityLaws()
	{
		return _gl.toString();
	}
	
	public Double getDeltaTime()
	{
		return this._tiempoRealPorPaso;
	}
	public Double getTiempoActual()
	{
		return this._tiempoActual;
	}
	
	public void addObserver(SimulatorObserver o)
	{
		if(!this.observers.contains(o))
		{
		this.observers.add(o);
		o.onRegister(_listaBodies, _tiempoActual, _tiempoRealPorPaso, _gl.toString());
		}
	}
	
 }

















