package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

import javax.swing.JPanel;


import simulator.control.Controller;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	Controller _ctrl;
	
	public MainWindow(Controller ctrl) 
	{
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() 
	{	
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		//ToolBar
		JPanel toolBar=new ControlPanel(_ctrl);
		mainPanel.add(toolBar, BorderLayout.PAGE_START);
		//Center

		JPanel mainPanelCenter=new JPanel();
		mainPanelCenter.setLayout(new BoxLayout(mainPanelCenter, BoxLayout.Y_AXIS));
		//Bodies
		JPanel Table_Bodies=new BodiesTable(_ctrl);
		mainPanelCenter.add(Table_Bodies);
		//viewer
		
		JComponent Vista=new Viewer(_ctrl);
		mainPanelCenter.add(Vista);
		
		mainPanel.add(mainPanelCenter, BorderLayout.CENTER);
		StatusBar status=new StatusBar(_ctrl);
		mainPanel.add(status, BorderLayout.PAGE_END);
	
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(1000, 500);
		this.setMinimumSize(new Dimension(625,500));
		this.setVisible(true);
		
		
	}
	
}

