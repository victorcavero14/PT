package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies

	StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT, 12, 5));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		
	
		_currTime=new JLabel("Time: ");
		_numOfBodies =new JLabel("Bodies: ");
		_currLaws=new JLabel("Law: ");
				
		this.add(_numOfBodies);
		this.addVerticalSeparator();
		this.add(_currLaws);
		this.addVerticalSeparator();
		this.add(_currTime);
		
		// TODO complete the code to build the tool bar
	}
	
	private void addVerticalSeparator()
	{
		JSeparator js = (new JSeparator(SwingConstants.VERTICAL));
		js.setPreferredSize(new Dimension(3,15));
		this.add(js);
	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				
				String inf_bodies="Bodies: " + bodies.size();
				_numOfBodies.setText(inf_bodies);
				
				String inf_time ="Time: " + time;
				_currTime.setText(inf_time);
				
				String inf_law="Laws: " + gLawsDesc;
				_currLaws.setText(inf_law);
			}
		});
		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				
				String inf_bodies="Bodies: " + bodies.size();
				_numOfBodies.setText(inf_bodies);
				
				String inf_time ="Time: " + time;
				_currTime.setText(inf_time);
				
				String inf_law="Laws: " + gLawsDesc;
				_currLaws.setText(inf_law);
			}
		});
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				
				String inf_bodies="Bodies: " + bodies.size();
				_numOfBodies.setText(inf_bodies);
				
			}
		});
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				
				String inf_bodies="Bodies: " + bodies.size();
				_numOfBodies.setText(inf_bodies);
				
				String inf_time ="Time: " + time;
				_currTime.setText(inf_time);
				
			}
		});
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				String Law_inf="Law: "+ gLawsDesc;
				_currLaws.setText(Law_inf);
			}
		});
		
	}
	
}

