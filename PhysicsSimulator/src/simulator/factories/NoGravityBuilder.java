package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder <GravityLaws>{
	
	public NoGravityBuilder()
	{
		_type = "ng";
		_desc="No Gravity Builder";
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) throws JSONException{
		
		return new NoGravity();
	}
}
