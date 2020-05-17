package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body> {

	public BasicBodyBuilder()
	{
		_type = "basic";
		_desc = "Default body";
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) throws JSONException{
		
		Body bd = null;
		
		try
		{
			String id = jsonObject.getString("id");
			double[] p = jsonArrayTodoubleArray(jsonObject.getJSONArray("pos"));
			double[] v = jsonArrayTodoubleArray(jsonObject.getJSONArray("vel"));
			double mass = jsonObject.getDouble("mass");
			Vector p_vector = new Vector(p);
			Vector v_vector = new Vector(v);
			Vector a_vector = new Vector(p.length);
			
			bd = new Body(id, v_vector, a_vector ,  p_vector, mass);
		}
		catch(JSONException e)
		{
			throw e;
		}
		
		return  bd;
	}
	
	@Override
	
	protected JSONObject createData()
	{
		JSONObject plantilla = new JSONObject();
		plantilla.put("id","the identifier" );
		plantilla.put("pos", "the position");
		plantilla.put("vel", "the velocity");
		plantilla.put("mass", "the mass");
	
		return plantilla;
		
	}

	
}
