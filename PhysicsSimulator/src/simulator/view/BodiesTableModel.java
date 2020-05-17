package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver 
{
	private static final long serialVersionUID = 1L;
	
	private String[] columnNames = { "ID", "Mass", "Position",
		"Velocity", "Aceleration" };
	private List<Body> _bodies;
	
	
	BodiesTableModel(Controller ctrl) 
	{
		_bodies = new ArrayList<Body>();
		ctrl.addObserver(this);
	}

	@Override
	public int getRowCount() {
		return _bodies.size();
	// TODO complete
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	// TODO complete
	}
	// Pr�ctica 5: Interfaz gr�fica para el simulador f�sico
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	// TODO complete
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object o = null;
		switch(columnIndex)
		{
			case 0 : o = _bodies.get(rowIndex).getId(); break;
			case 1 : o = _bodies.get(rowIndex).getMass(); break;
			case 2 : o = _bodies.get(rowIndex).getPosition(); break;
			case 3 : o = _bodies.get(rowIndex).getVelocity(); break;
			case 4 : o = _bodies.get(rowIndex).getAcceleration(); break;
		}

		return o;
	// TODO complete
	}

	// SimulatorObserver methods
	// ...
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				fireTableStructureChanged();
				
			}
		});
		
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				fireTableStructureChanged();
				
			}
		});
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		_bodies = bodies;
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				fireTableStructureChanged();
				
			}
		});
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		_bodies = bodies;
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				fireTableStructureChanged();
				
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
				fireTableStructureChanged();
				
			}
		});
	}
	}

