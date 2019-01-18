package view.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import enums.State;
import utility.ImageReader;

public class FieldPanel extends JPanel {

	private JButton fieldButton;

	public FieldPanel() {

		initializateButton();
		initializatePanel();
	}

	// inatializate this panel
	private void initializatePanel() {

		this.setLayout(new FlowLayout(0, 0, 0));
		this.add(fieldButton);
	}

	private void initializateButton() {
		
		fieldButton = new JButton();
		fieldButton.setMargin(new Insets(0, 0, 0, 0));
		fieldButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setState(State.EMPTY);
	}
	
	public void setState(State state) {
		
		fieldButton.setIcon(new ResizableIcon(this, ImageReader.readImage(state.getPath())));
	}
	
	public JButton getFieldButton() {
		
		return fieldButton;
	}
}
