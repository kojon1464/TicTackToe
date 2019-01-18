package view.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import enums.Player;
import enums.State;
import utility.ImageReader;

public class GamePanel extends JPanel {
	
	private JPanel topPanel;
	private InformationPanel informationPanel;
	private JPanel boardPanelWrapper;
	private BoardPanel boardPanel;
	private JButton pauseButton;
	
	public GamePanel(int width, int height) {
		
		initializateTopPanel();
		initializateBoard(width, height);
		initializatePanel();
	}

	//initalizate this panel
	private void initializatePanel() {
		
		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(boardPanelWrapper, BorderLayout.CENTER);
	}
	
	private void initializateTopPanel() {

		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		
		initializateInformationPanel();
		initializatePauseButton();
	}
	
	private void initializateBoard(int width, int height) {
		
		boardPanelWrapper = new JPanel();
		boardPanelWrapper.setLayout(new GridBagLayout());
		
		boardPanel = new BoardPanel(width, height);
		boardPanelWrapper.add(boardPanel);
	}
	
	private void initializateInformationPanel() {

		informationPanel = new InformationPanel();
		topPanel.add(informationPanel);
	}
	
	private void initializatePauseButton() {
		
		pauseButton = new JButton();
		pauseButton.setIcon(new ImageIcon(ImageReader.readImage("image/gear.png")));
		pauseButton.setMargin(new Insets(0, 0, 0, 0));
		pauseButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		topPanel.add(pauseButton);
	}
	
	public void setFieldOnClickListener(int width, int height, ActionListener listener) {
		
		boardPanel.getFieldPanel(width, height).getFieldButton().addActionListener(listener);;
	}
	
	public void setPauseButtonListener(ActionListener listener) {
		
		pauseButton.addActionListener(listener);
	}
	
	public void setFieldState(int width, int height, State state) {
		
		boardPanel.getFieldPanel(width, height).setState(state);
	}
	
	public void setPlayerIcon(Player player) {
		
		informationPanel.setImageIcon(player.getState().getPath());
	}
}
