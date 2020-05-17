package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.*;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4873174135198153730L;
	// ...
	private Controller _ctrl;
	
	private JToolBar _toolBar;
	private JButton _open, _physics, _run, _stop, _exit;
	private JLabel _label, _labelDelta;
	private JTextField _tf;
	private JSpinner _spinner, _spinnerDelay;
	private volatile Thread _thread;
	
	ControlPanel(Controller ctrl) 
	{
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI()
	{
		this.setLayout(new BorderLayout() );
	
			_toolBar = new JToolBar();

			_open=createButton("src/icons/open.png");
			_open.setToolTipText("Open");
			_toolBar.add(_open);
			
			_open.addActionListener(new ActionListener()
			{
	 			 
				public void actionPerformed(ActionEvent arg0)
				{
					JFileChooser fc = new JFileChooser();
					int returnVal = fc.showOpenDialog(null);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						File file = fc.getSelectedFile();
						System.out.println("Opening: " + file.getName());
						_ctrl.reset();
						try {
							_ctrl.loadBodies(new FileInputStream(file.getAbsolutePath()));
							
						} catch (IllegalArgumentException ex) {
							JFrame jf = new JFrame();
							
							JOptionPane.showMessageDialog(jf,
									 "Fichero invalido", "Error",
									 JOptionPane.ERROR_MESSAGE);
							
						} catch (FileNotFoundException ex) {
							JFrame jf = new JFrame();
							
							JOptionPane.showMessageDialog(jf,
									 "Fichero no encontrado", "Error",
									 JOptionPane.ERROR_MESSAGE);
						}
					} 
					else 
					{
						System.out.println("load cancelled by user.");
					}
					
				}
			});
			
			// Create a file chooser only once, and declare it as a filed, this way
			// it remembers in which it was every time we open it, etc.
			
				
			
			_physics=createButton("src/icons/physics.png");
			_physics.setToolTipText("Change law gravitation");
			_toolBar.add(_physics);
			_physics.addActionListener(new ActionListener()
			{
				 
				public void actionPerformed(ActionEvent arg0)
				{
						GravitySelector();
				}
			
			});
			
			_run=createButton("src/icons/run.png");
			_run.setToolTipText("Run");
			_toolBar.add(_run);
			_run.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
						try {
							activarBotones(false);
							Double a = Double.parseDouble(_tf.getText());
							if (a<0)
							{
								JFrame jf = new JFrame();
								
								JOptionPane.showMessageDialog(jf,
										"Number of negative steps", "Error",
										JOptionPane.ERROR_MESSAGE);
								activarBotones(true);
							}
							else
							{
							activarBotones(false);
							_ctrl.setDeltaTime(a);
							_thread = new Thread(new Runnable()
									{
										@Override
										public void run() {
											run_sim(Integer.parseInt(_spinner.getValue().toString()), Long.parseLong(_spinnerDelay.getValue().toString()));
											_thread = null;
											activarBotones(true);
										}
								
									});
							
							_thread.start();
							
							}
						
						}
						catch (NumberFormatException ex)
						{
							
							JFrame jf = new JFrame();
					
							JOptionPane.showMessageDialog(jf,
									ex.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
								activarBotones(true);
							
								
						}
					}
					
			});
				
			_stop=createButton("src/icons/stop.png");
			_stop.setToolTipText("Stop");
			_toolBar.add(_stop);
			_stop.addActionListener(new ActionListener()
			{
				 
				public void actionPerformed(ActionEvent arg0)
				{
					if(_thread != null )
					{
						_thread.interrupt();
					}
				}
			});
			_toolBar.addSeparator();
			
			_label = new JLabel("Delay:");
			_toolBar.add(_label);
			
			// Creacion del JSpinner y valor incial.
			int min = 0, max = 1000, current = 1, step = 1;
			SpinnerModel model = new SpinnerNumberModel(current,min,max,step);
			_spinnerDelay = new JSpinner(model);
			_spinnerDelay.setValue(current);
			
			_spinnerDelay.setSize(new Dimension(100,50));
			_spinnerDelay.setMinimumSize(new Dimension(0,50));
			_spinnerDelay.setMaximumSize(new Dimension(500,50));
					
			_toolBar.add(_spinnerDelay);
						
			_toolBar.addSeparator();
	
			_label = new JLabel("Steps:");
			_toolBar.add(_label);
			
			
			// Creacion del JSpinner y valor incial.
			_spinner = new JSpinner();
			_spinner.setValue(10000);
			_spinner.setSize(new Dimension(1000,50));
			_spinner.setMaximumSize(new Dimension(1500,50));
		
			_toolBar.add(_spinner);
			
			_toolBar.addSeparator();
			
			_labelDelta = new JLabel("Delta-Time:");
			_toolBar.add(_labelDelta);
			_tf = new JTextField(String.valueOf(_ctrl.getDeltaTime()));
			_tf.setEditable(true);
			_tf.setSize(new Dimension(1000,50));
			_tf.setMaximumSize(new Dimension(1500,50));
			_toolBar.add(_tf);
		
			
			JPanel separador=new JPanel();
			_toolBar.add(separador);
			
			_exit=this.createButton("src/icons/exit.png");
			_toolBar.add(_exit);
			_exit.setToolTipText("Exit");
			_exit.addActionListener(new ActionListener()
			{
				 
				public void actionPerformed(ActionEvent arg0)
				{
					quit();
					
				}
			});
			_toolBar.setVisible(true);
			this.add(_toolBar);
			this.setSize(200, 200);
	}
	
	private void activarBotones(boolean activar)
	{
		_open.setEnabled(activar);
		_spinner.setEnabled(activar);
		_physics.setEnabled(activar);
		_run.setEnabled(activar);
		_exit.setEnabled(activar);
		_tf.setEnabled(activar);
		_spinnerDelay.setEnabled(activar);
	}
	
	private void GravitySelector()
	{
		int cont=0;
		Object [] possibilities = new Object[_ctrl.getGravityLawsFactory().getInfo().size()];
		for(JSONObject laws:_ctrl.getGravityLawsFactory().getInfo())
		{
			possibilities[cont]=laws.get("desc");
			cont++;
		}
		
		String n = (String) JOptionPane.showInputDialog(new JFrame("Gravity Laws Selector"),
	              // contenedor padre
	            "Elige una opcion:",  // mensaje en la ventana
	            "Opciones Posibles",  // etiqueta de la ventana 
	            JOptionPane.INFORMATION_MESSAGE, // icono seleccionado
	            null, // icono seleccionado por el usuario (Icon)
	            possibilities, // opciones para seleccionar 
	            "Plane");
		
	
		for(JSONObject laws:_ctrl.getGravityLawsFactory().getInfo())
		{
			if( laws.get("desc").equals(n))
			{
				_ctrl.setGravityLaws(laws);
			}
		}
		          
		this.setVisible(true);
		

	}
 
	private void quit() {
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		if (n == 0) {
			// finalize your app
			System.exit(0);
		}
	}
	private JButton createButton(String icono)
	{
		JButton aux = new JButton();
		aux.setIcon(new ImageIcon(icono));
		
		return aux;
	}
	
	// other private/protected methods
	
	private void run_sim(int n, long delay) 
	{
		while(n > 0  && !Thread.interrupted())// && the current thread has not been interrupted
		{
			try
			{
				_ctrl.run(1);
				Thread.sleep(delay);
			}
			catch(InterruptedException e)
			{
				//_thread.interrupt();
				return;	
			}
			
			n--;
		}

	}
		// SimulatorObserver methods

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		
		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		
		
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				_tf.setText(String.valueOf(dt));
			}
		});
		
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		
		
	}

}
