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
	private JButton retryButton;

	public WinPanel() {

		initializateRetryButton();
		initializatePauseLabel();
	}

	private void initializatePauseLabel() {

		wonLabel = new JLabel("won");
		wonLabel.setAlignmentX(CENTER_ALIGNMENT);
		wonLabel.setFont(wonLabel.getFont().deriveFont(25.0f));
		
		this.addComponentAfterGlue(Box.createGlue());
		this.addComponentAfterGlue(wonLabel);
	}
	
	private void initializateRetryButton() {
		
		retryButton = new JButton("    Retry    ");
		retryButton.setAlignmentX(CENTER_ALIGNMENT);
		retryButton.setFont(retryButton.getFont().deriveFont(20.0f));
		
		this.addComponentAfterGlue(retryButton);
	}
	
	public void setRetryButtonListener(ActionListener listener) {
		
		retryButton.addActionListener(listener);
	}
	
	public void setWinIcon(Player player) {
		
		wonLabel.setIcon(new ImageIcon(ImageReader.readImage(player.getState().getPath()).getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
	}
}
