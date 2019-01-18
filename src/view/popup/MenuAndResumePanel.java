package view.popup;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuAndResumePanel extends JPanel {

	private JButton mainMenuButton;
	private JButton resumeButton;

	public MenuAndResumePanel() {

		initializateButtons();
		initializatePanel();
	}

	private void initializatePanel() {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(mainMenuButton);
		this.add(Box.createGlue());
		this.add(resumeButton);
	}

	private void initializateButtons() {

		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setAlignmentX(CENTER_ALIGNMENT);
		mainMenuButton.setFont(mainMenuButton.getFont().deriveFont(20.0f));

		resumeButton = new JButton("  Resume  ");
		resumeButton.setAlignmentX(CENTER_ALIGNMENT);
		resumeButton.setFont(resumeButton.getFont().deriveFont(20.0f));
	}
	
	public JButton getMainMenuButton() {
		
		return mainMenuButton;
	}
	
	public JButton getResumeButton() {
		
		return resumeButton;
	}
}
