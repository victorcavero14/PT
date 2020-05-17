package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class Controller 
{

	private PhysicsSimulator _sim;
	private Factory<Body> _bodiesFactory;
	private Factory<GravityLaws> _gravityFactory;
		
	public Controller(Factory<Body> bodyfactory,PhysicsSimulator sim, Factory<GravityLaws> gf)
	{
		_bodiesFactory=bodyfactory;
		_sim=sim;
		_gravityFactory = gf;
	}
	
	public void loadBodies(InputStream in) throws IllegalArgumentException{
		try
		{
		 JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		 JSONArray bodies = jsonInupt.getJSONArray("bodies");
		 
		 for (int i = 0; i < bodies.length(); i++)
		 {
			 _sim.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));
		 }
		 
		}
		catch(JSONException e)
		{
			throw new IllegalArgumentException();
		}
		catch(IllegalArgumentException e)
		 {
			 throw e;
		 }
	}
	
	public String state(int steps)
	{
		StringBuilder bd = new StringBuilder();
		bd.append("{");
		bd.append("\n");
		bd.append("\"states\": [");
		bd.append("\n");
		
		for (int i=0;i<steps;i++)
		{
			bd.append(_sim.toString());
			if (i + 1 != steps) bd.append(",");
			bd.append("\n");
			_sim.advance();
		}
		
		bd.append("]");
		bd.append("\n");
		bd.append("}");
		
		return bd.toString();
		
	}
	
	public void run(int steps,OutputStream out)
	{
		PrintStream p = (out == null) ? null : new PrintStream(out);
		String states = state(steps);
		
		if(p == null)
		{
			System.out.println(states);
			
		}
		else
		{
			p.append(states);
			p.close();
		}
		
	}
	
	//invoca al metodo reset del simulador.
	public void reset()
	{
		_sim.reset();
	}
	
	//invoca al metodo setDeltaTime del simulador.
	public void setDeltaTime(double dt)
	{
		_sim.setDeltaTime(dt);
	}
	
	
	public void addObserver(SimulatorObserver o)
	{
		_sim.addObserver(o);
	}
	
	// ejecuta n pasos del simulador sin escribir nada en consola.
	public void run(int n)
	{
		for (int i=0;i<n;i++)
		{
			_sim.advance();
		}
	}
	
	//devuelve la factor�a de leyes de gravedad.
	public Factory<GravityLaws> getGravityLawsFactory() 
	{
		return _gravityFactory;	
	}
	
	public Double getDeltaTime()
	{
		return _sim.getDeltaTime();
	}
	public Double getTiempo()
	{
		return this._sim.getTiempoActual();
	}
	
	//usa la factoria de leyes de gravedad actual para crear un nuevo objeto de tipo GravityLaws a partir de info y cambia las
	//leyes de la gravedad del simulador por el.
	public void setGravityLaws(JSONObject info)
	{
		_sim.setGravityLaws(_gravityFactory.createInstance(info));
	}

}
