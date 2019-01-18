package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.Player;
import enums.State;
import model.GameModel;
import view.AppFrame;
import view.board.GamePanel;
import view.popup.PausePanel;
import view.popup.PopupPanel;
import view.popup.WinPanel;

public class MainController implements GameStatusObserver {

	private int BOARD_WIDTH = 5;
	private int BOARD_HEIGHT = 5;

	private boolean isPaused;

	private AppFrame appFrame;
	private GameModel gameModel;

	private GamePanel gamePanel;
	private PopupPanel popupPanel;

	public MainController(AppFrame appFrame, GameModel gameModel) {

		this.appFrame = appFrame;
		this.gameModel = gameModel;

		isPaused = false;
		
		gameModel.initialize(BOARD_WIDTH, BOARD_HEIGHT, 4);
		gameModel.addObserver(this);
		
		gamePanel = new GamePanel(BOARD_WIDTH, BOARD_HEIGHT);
		gamePanel.setPlayerIcon(Player.CIRCLE);
		setupGamePanelListeners(BOARD_WIDTH, BOARD_HEIGHT);
		appFrame.setPanel(gamePanel);
	}
	
	private void setupPauseWindow() {
		
		isPaused = true;
		popupPanel = new PausePanel();
		initializatePopupPanel();
	}
	
	private void setupWinWindow() {
		
		isPaused = true;
		popupPanel = new WinPanel();
		((WinPanel)popupPanel).setWinIcon(gameModel.getPlayer());
		initializatePopupPanel();
	}
	
	private void initializatePopupPanel() {
		
		appFrame.addPanelOnPopuoLayer(popupPanel);
		popupPanel.setResumeButtonListener((e) -> resumeGame());
	}

	private void resumeGame() {

		appFrame.removePanelOnPopuoLayer(popupPanel);
		popupPanel = null;
		isPaused = false;
	}

	private void setupGamePanelListeners(int width, int height) {

		//Pause button listener
		gamePanel.setPauseButtonListener((e) -> pauseButtonClicked());
		
		//Field listener
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				gamePanel.setFieldOnClickListener(j, i, new FieldListener(j, i));
	}

	private void pauseButtonClicked() {

		if(!isPaused  &&  gameModel.isWon())
			setupWinWindow();
		else if(!isPaused && !gameModel.isWon())
			setupPauseWindow();
		else
			resumeGame();
	}

	@Override
	public void updateGameStatus(State[][] state, Player player, boolean isWon) {

		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state[0].length; j++)
				gamePanel.setFieldState(j, i, state[i][j]);

		gamePanel.setPlayerIcon(player);
		
		if(isWon)
			setupWinWindow();
			
	}

	private class FieldListener implements ActionListener {

		private int width;
		private int height;

		public FieldListener(int width, int height) {

			this.width = width;
			this.height = height;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!isPaused && !gameModel.isWon())
				gameModel.changeState(width, height);
		}
	}
}
