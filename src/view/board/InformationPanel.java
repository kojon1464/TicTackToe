package view.board;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import enums.State;
import utility.ImageReader;

public class InformationPanel extends JPanel {

	private int ICON_SIZE = 60;
	
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
