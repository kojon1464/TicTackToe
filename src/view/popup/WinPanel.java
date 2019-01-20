package view.popup;

import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import enums.Player;
import utility.ImageReader;

public class WinPanel extends PopupPanel {

	private JLabel wonLabel;

	public WinPanel() {

		initializateWonLabel();
	}

	private void initializateWonLabel() {

		wonLabel = new JLabel("won");
		wonLabel.setAlignmentX(CENTER_ALIGNMENT);
		wonLabel.setFont(wonLabel.getFont().deriveFont(25.0f));
		
		this.addComponentAfterGlue(wonLabel);
	}
	
	public void setWinIcon(Player player) {
		
		wonLabel.setIcon(new ImageIcon(ImageReader.readImage(player.getState().getPath()).getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
	}
}
