package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private JPanel topPanel;
	private JPanel informationPanel;
	private Canvas canvas;
	private JButton settingsButton;
	
	public GamePanel() {
		
		initializatePanels();
		initializateInformationPanel();
		initializateSettingButton();
	}



	private void initializatePanels() {

		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		this.add(topPanel, BorderLayout.NORTH);
		
		canvas = new Canvas();
		this.add(canvas, BorderLayout.CENTER);
	}
	
	private void initializateInformationPanel() {

		informationPanel = new InformationPanel();
		topPanel.add(informationPanel);
	}
	
	private void initializateSettingButton() {
		
		settingsButton = new JButton();
		topPanel.add(settingsButton);
	}
}
