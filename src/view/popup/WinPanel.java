package view.popup;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import enums.Player;
import utility.ImageReader;

public class WinPanel extends PopupPanel {

	private static final long serialVersionUID = -8862006000594777616L;
	
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
