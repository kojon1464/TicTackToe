package view.popup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PausePanel extends PopupPanel {

	private JLabel pauseLabel;

	public PausePanel() {

		initializatePauseLabel();
	}

	private void initializatePauseLabel() {

		pauseLabel = new JLabel("Pause");
		pauseLabel.setAlignmentX(CENTER_ALIGNMENT);
		pauseLabel.setFont(pauseLabel.getFont().deriveFont(25.0f));
		
		this.addComponentAfterGlue(pauseLabel);
	}
}
