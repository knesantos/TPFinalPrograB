package extremeF1.Views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrincipalView extends JFrame implements PrincipalViewInterface{

	
	public PrincipalView() {
		setSize(1500, 1500);
		setTitle("ExtremeF1");
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.black);
			
	}
	public void setPanel(JPanel panel) {
		this.getContentPane().add(panel);
		this.repaint();
	}
	public void removePanel(JPanel panel) {
		this.getContentPane().remove(panel);
		this.repaint();
	}
	
	
}
