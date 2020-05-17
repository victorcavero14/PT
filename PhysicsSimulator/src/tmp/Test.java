package tmp;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Test {
	
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		
		JOptionPane.showMessageDialog(jf,
				 "Error Dialog.", "Error Icon",
				 JOptionPane.ERROR_MESSAGE);
		
	}
}
