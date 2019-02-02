package view.board;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.ImageReader;

public class InformationPanel extends JPanel {

	private static final long serialVersionUID = 5905789393913521702L;

	private static final int ICON_SIZE = 60;
	
	private JLabel informationLabel;
	
	public InformationPanel() {
		
		initializateLabel();
		initializatePanel();
	}
	
	private void initializatePanel() {
		
		this.add(informationLabel);
	}
	
	private void initializateLabel() {
		
		informationLabel = new JLabel("plays");
		informationLabel.setFont(informationLabel.getFont().deriveFont(35.0f));
	}
	
	public void setImageIcon(String path) {
		
		informationLabel.setIcon(new ImageIcon(ImageReader.readImage(path).getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_DEFAULT)));
	}
}
