package view.popup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class PopupPanel extends JPanel {

	private int WIDTH = 200;
	private int HEIGHT = 250;
	
	private MenuAndResumePanel menuAndResumePanel;
	
	public PopupPanel() {
		
		initializateMenuAndPausePanel();
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
		this.add(menuAndResumePanel);
		addThreeGluesToPanel();
	}
	
	private void initializateMenuAndPausePanel() {

		menuAndResumePanel = new MenuAndResumePanel();
	}
	
	public void setMainMenuButtonListener(ActionListener listener) {
		
		menuAndResumePanel.getMainMenuButton().addActionListener(listener);
	}
	
	public void setResumeButtonListener(ActionListener listener) {
		
		menuAndResumePanel.getResumeButton().addActionListener(listener);
	}
	
	private void addThreeGluesToPanel() {

		this.add(Box.createGlue());
		this.add(Box.createGlue());
		this.add(Box.createGlue());
	}
	
	public void notifyFrameChangedSize(int width, int height) {
		
		this.setBounds((width - WIDTH) / 2, (height - HEIGHT) / 2, WIDTH, HEIGHT);
	}
}
