package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import enums.PlayerType;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = 2425314677300139546L;
	
	private BoardChooserPanel boardChooserPanel;
	private PlayerChooserPanel playerChoooserPanel;
	private JButton startGameButton;

	public MainMenu() {

		initializateInternalPanels();
		initializateButton();
		initializatePanel();
	}

	private void initializatePanel() {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

		this.add(boardChooserPanel);
		this.add(new Box.Filler(new Dimension(0, 30), new Dimension(0, 30), new Dimension(0, 30)));
		this.add(playerChoooserPanel);
		this.add(new Box.Filler(new Dimension(0, 30), new Dimension(0, 30), new Dimension(0, 30)));
		this.add(startGameButton);
	}

	private void initializateInternalPanels() {

		boardChooserPanel = new BoardChooserPanel();
		playerChoooserPanel = new PlayerChooserPanel();
	}

	private void initializateButton() {

		startGameButton = new JButton("Start Game");
		startGameButton.setFont(startGameButton.getFont().deriveFont(25.0f));
		startGameButton.setAlignmentX(CENTER_ALIGNMENT);
	}

	public void setStartGameButtonListener(ActionListener listener) {

		startGameButton.addActionListener(listener);
	}

	public PlayerType getCirclePlayerSelected() {

		return (PlayerType) playerChoooserPanel.getCircleComboBox().getSelectedItem();
	}
	
	public PlayerType getCrossPlayerSelected() {

		return (PlayerType) playerChoooserPanel.getCrossComboBox().getSelectedItem();
	}
	
	public int getBoardWidth() {
		
		return (int) boardChooserPanel.getWidthSpinner().getModel().getValue();
	}
	
	public int getBoardHeight() {
		
		return (int) boardChooserPanel.getHeightSpinner().getModel().getValue();
	}
	
	public int getBoardConsecutiveNumber() {
		
		return (int) boardChooserPanel.getConsecutiveNumberSpinner().getModel().getValue();
	}
}
