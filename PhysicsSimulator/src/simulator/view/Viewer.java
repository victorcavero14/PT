package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;
import simulator.misc.Vector;

public class Viewer extends JComponent implements SimulatorObserver {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;

	Viewer(Controller ctrl) 
	{
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		// TODO add border with title
		
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black, 2),
				"Viewer",
				TitledBorder.RIGHT, TitledBorder.TOP));
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case '-':
					_scale = _scale * 1.1;
					break;
				case '+':
					_scale = Math.max(1000.0, _scale / 1.1);
				break;
				case '=':
					autoScale();
				break;
				case 'h':
					_showHelp = !_showHelp;
					break;
				default:
				}
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
		});
		
	}
	
	@Override
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;

		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// calculate the center
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		// TODO draw a cross at center
		gr.setColor(Color.red);
		gr.drawLine(_centerX+10,_centerY, _centerX-10, _centerY);
		gr.drawLine(_centerX,_centerY+10, _centerX, _centerY-10);
		// TODO draw bodies
		// TODO draw help if _showHelp is true
		
		if(_showHelp == true) 
		{
			gr.setColor(Color.red);
			gr.drawString("h: toogle help, +: zoom-in, -: zoom-out, =: fit", 8 , 23);
			gr.drawString("Scaling ratio: " + _scale, 8 , 38);
			
		}
		for (Body b : _bodies)
		{
			int x = _centerX + (int) (b.getPosition().coordinate(0)/_scale);
			int y = _centerY - (int) (b.getPosition().coordinate(1)/_scale);
			gr.setColor(Color.blue);
			gr.fillOval(x - 5, y - 5, 10, 10);
			gr.setColor(Color.black);
			gr.drawString(b.getId(), x - 5, y - 10);
			
		}
	}

	// other private/protected methods
	
	private void autoScale() {
		double max = 1.0;
		for (Body b : _bodies) {
			Vector p = b.getPosition();
			for (int i = 0; i < p.dim(); i++)
				max = Math.max(max,
						Math.abs(b.getPosition().coordinate(i)));
		}
		double size = Math.max(1.0, Math.min((double) getWidth(),
				(double) getHeight()));
			_scale = max > size ? 4.0 * max / size : 1.0;
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();
			}
		});
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();
			}
		});
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();
			}
		});
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				_bodies = bodies;
				repaint();
			}
		});
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				autoScale();
				repaint();
			}
		});
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				autoScale();
				repaint();
			}
		});
	}
	
	// SimulatorObserver methods
}

