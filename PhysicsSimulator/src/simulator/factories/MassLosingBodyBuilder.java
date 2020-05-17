package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder <Body>{
	
	public MassLosingBodyBuilder ()
	{
		_type = "mlb";
		_desc = "Mass losing body";
	}

	@Override
	protected MassLossingBody createTheInstance(JSONObject jsonObject) throws JSONException{
		
		MassLossingBody bd = null;
		
		try
		{
			String id = jsonObject.getString("id");
			double[] p = jsonArrayTodoubleArray(jsonObject.getJSONArray("pos"));
			double[] v = jsonArrayTodoubleArray(jsonObject.getJSONArray("vel"));
			double mass = jsonObject.getDouble("mass");
			double freq = jsonObject.getDouble("freq");
			double factor = jsonObject.getDouble("factor");
			Vector p_vector = new Vector(p);
			Vector v_vector = new Vector(v);
			Vector a_vector = new Vector(p.length); //Nos vale tanto la pos como la velocidad
			bd = new MassLossingBody(id, v_vector, a_vector,  p_vector, mass, factor, freq); 
		}
		catch(JSONException e)
		{
			throw e;
		}
		
		return bd; 
	}
	
	@Override
	
	protected JSONObject createData()
	{
		JSONObject plantilla = new JSONObject();
		plantilla.put("id","the identifier" );
		plantilla.put("pos", "the position");
		plantilla.put("vel", "the velocity");
		plantilla.put("mass", "the mass");
		plantilla.put("freq", "the frequency");
		plantilla.put("factor", "the factor");
		return plantilla;
		
	}
	
}
