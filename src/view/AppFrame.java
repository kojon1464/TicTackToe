package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppFrame extends JFrame {
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;
	
	public void setPanel(JPanel panel) {
		
		this.getContentPane().add(panel);
		this.revalidate();
		this.repaint();
	}
	
	private void init() {
		
		this.setTitle("TicTackToe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
	}
}
