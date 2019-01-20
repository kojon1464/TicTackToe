package view.popup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class PopupPanel extends JPanel {

	private int WIDTH = 200;
	private int HEIGHT = 250;
	
	private JButton retryButton;
	private JButton mainMenuButton;
	private JButton resumeButton;
	
	public PopupPanel() {
		
		initializateButtons();
		initializatePanel();
	}
	
	protected void addComponentAfterGlue(Component component) {
		
		this.add(component, 3);
	}
	
	private void initializatePanel() {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBounds(150, 150, WIDTH, HEIGHT);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		addThreeGluesToPanel();
		this.add(Box.createGlue());
		this.add(retryButton);
		this.add(Box.createGlue());
		this.add(mainMenuButton);
		this.add(Box.createGlue());
		this.add(resumeButton);
		addThreeGluesToPanel();
	}
	
	private void initializateButtons() {

		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setAlignmentX(CENTER_ALIGNMENT);
		mainMenuButton.setFont(mainMenuButton.getFont().deriveFont(20.0f));

		resumeButton = new JButton("  Resume  ");
		resumeButton.setAlignmentX(CENTER_ALIGNMENT);
		resumeButton.setFont(resumeButton.getFont().deriveFont(20.0f));
		
		retryButton = new JButton("    Retry    ");
		retryButton.setAlignmentX(CENTER_ALIGNMENT);
		retryButton.setFont(retryButton.getFont().deriveFont(20.0f));
	}
	
	public void setMainMenuButtonListener(ActionListener listener) {
		
		mainMenuButton.addActionListener(listener);
	}
	
	public void setResumeButtonListener(ActionListener listener) {
		
		resumeButton.addActionListener(listener);
	}
	
	private void addThreeGluesToPanel() {

		this.add(Box.createGlue());
		this.add(Box.createGlue());
		this.add(Box.createGlue());
	}
	
	public void notifyFrameChangedSize(int width, int height) {
		
		this.setBounds((width - WIDTH) / 2, (height - HEIGHT) / 2, WIDTH, HEIGHT);
	}
	
	public void setRetryButtonListener(ActionListener listener) {
		
		retryButton.addActionListener(listener);
	}
}
