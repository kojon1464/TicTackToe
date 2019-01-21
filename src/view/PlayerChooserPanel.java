package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import enums.Player;
import enums.PlayerType;
import utility.ImageReader;

public class PlayerChooserPanel extends JPanel {

	private final int ICON_WIDTH = 50;
	private final int ICON_HEIGHT = 50;

	PlayerType[] parameters = { PlayerType.PLAYER, PlayerType.RANDOM_AI, PlayerType.BLOCKING_AI };

	private JLabel circleLabel;
	private JLabel crossLabel;
	private JComboBox circleComboBox;
	private JComboBox crossComboBox;

	public PlayerChooserPanel() {
		initializateLabels();
		initializateComboBoxes();
		initializatePanel();
	}

	private void initializatePanel() {

		this.setLayout(new GridLayout(2, 2));

		this.add(circleLabel);
		this.add(circleComboBox);
		this.add(crossLabel);
		this.add(crossComboBox);
	}

	private void initializateLabels() {

		circleLabel = new JLabel("- plays as ");
		circleLabel.setFont(circleLabel.getFont().deriveFont(25.0f));
		circleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		circleLabel.setIcon(new ImageIcon(ImageReader.readImage(Player.CIRCLE.getState().getPath())
				.getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_DEFAULT)));

		crossLabel = new JLabel("- plays as ");
		crossLabel.setFont(crossLabel.getFont().deriveFont(25.0f));
		crossLabel.setHorizontalAlignment(SwingConstants.CENTER);
		crossLabel.setIcon(new ImageIcon(ImageReader.readImage(Player.CROSS.getState().getPath())
				.getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_DEFAULT)));
	}

	private void initializateComboBoxes() {

		circleComboBox = new JComboBox(parameters);
		circleComboBox.setFont(circleComboBox.getFont().deriveFont(15.0f));
		((JLabel)circleComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		crossComboBox = new JComboBox(parameters);
		crossComboBox.setFont(crossComboBox.getFont().deriveFont(15.0f));
		((JLabel)crossComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public JComboBox getCircleComboBox() {
		
		return circleComboBox;
	}
	
	public JComboBox getCrossComboBox() {
		
		return crossComboBox;
	}
}
