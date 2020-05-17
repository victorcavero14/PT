package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws>
{
	public FallingToCenterGravityBuilder()
	{
		_type = "ftcg";
		_desc = "Falling To Center Gravity";
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) throws JSONException {
		
		return new FallingToCenterGravity();
	}
	

}
