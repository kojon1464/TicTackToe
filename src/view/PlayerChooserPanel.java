package view;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import enums.Player;
import enums.PlayerType;
import utility.ImageReader;

public class PlayerChooserPanel extends JPanel {

	private static final long serialVersionUID = 4778604918181644140L;
	
	private static final int ICON_WIDTH = 50;
	private static final int ICON_HEIGHT = 50;

	PlayerType[] parameters;

	private JLabel circleLabel;
	private JLabel crossLabel;
	private JComboBox<PlayerType> circleComboBox;
	private JComboBox<PlayerType> crossComboBox;

	public PlayerChooserPanel() {
		
		initializateParameters();
		initializateLabels();
		initializateComboBoxes();
		initializatePanel();
	}

	private void initializateParameters() {

		parameters = new PlayerType[PlayerType.values().length];

		for(int i = 0; i < PlayerType.values().length; i++)
			parameters[i] = PlayerType.values()[i];
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

		circleComboBox = new JComboBox<PlayerType>(parameters);
		circleComboBox.setFont(circleComboBox.getFont().deriveFont(15.0f));
		((JLabel)circleComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		crossComboBox = new JComboBox<PlayerType>(parameters);
		crossComboBox.setFont(crossComboBox.getFont().deriveFont(15.0f));
		((JLabel)crossComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public JComboBox<PlayerType> getCircleComboBox() {
		
		return circleComboBox;
	}
	
	public JComboBox<PlayerType> getCrossComboBox() {
		
		return crossComboBox;
	}
}
