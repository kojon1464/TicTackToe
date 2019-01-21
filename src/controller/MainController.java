package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import ai.PlayerAI;
import enums.Player;
import enums.PlayerType;
import enums.State;
import model.GameModel;
import view.AppFrame;
import view.MainMenu;
import view.board.GamePanel;
import view.popup.PausePanel;
import view.popup.PopupPanel;
import view.popup.TiePanel;
import view.popup.WinPanel;

public class MainController implements GameStatusObserver {

	private final int AI_DELAY = 500;

	private boolean isPaused;
	private boolean isAITurn;

	Timer timer;

	private AppFrame appFrame;
	private GameModel gameModel;

	// Panels
	private GamePanel gamePanel;
	private PopupPanel popupPanel;

	private MainMenu mainMenu;

	// GameData
	int gameWidth;
	int gameHeight;
	int gameConsecutiveNumber;
	PlayerType circlePlayer;
	PlayerType crossPlayer;

	// AI Players
	PlayerAI circlePlayerAI;
	PlayerAI crossPlayerAI;

	public MainController(AppFrame appFrame, GameModel gameModel) {

		this.appFrame = appFrame;
		this.gameModel = gameModel;

		gameModel.addObserver(this);

		setupMainMenu();
	}

	private void setupMainMenu() {

		mainMenu = new MainMenu();
		mainMenu.setStartGameButtonListener((e) -> startGame());

		appFrame.setResizable(false);
		appFrame.setPanel(mainMenu);
	}

	private void setupGamePabel(int width, int height) {

		isPaused = false;
		isAITurn = false;

		gamePanel = new GamePanel(gameWidth, gameHeight);
		gamePanel.setPlayerIcon(Player.CIRCLE);

		
			setupGamePanelListeners(gameWidth, gameHeight);

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
		((WinPanel) popupPanel).setWinIcon(gameModel.getPlayer());
		initializatePopupPanel();
	}

	private void setupTieWindow() {

		isPaused = true;
		popupPanel = new TiePanel();
		initializatePopupPanel();
	}

	private void initializatePopupPanel() {

		appFrame.addPanelOnPopuoLayer(popupPanel);
		popupPanel.setResumeButtonListener((e) -> resumeGame());
		popupPanel.setMainMenuButtonListener((e) -> goToMenu());
		popupPanel.setRetryButtonListener((e) -> restartGame());
	}

	private void removePopupPaneFromFrame() {

		appFrame.removePanelOnPopuoLayer(popupPanel);
		popupPanel = null;
	}

	private void restartGame() {

		removePopupPaneFromFrame();
		startGame();
	}

	private void resumeGame() {

		removePopupPaneFromFrame();
		isPaused = false;
	}

	private void goToMenu() {

		removePopupPaneFromFrame();
		setupMainMenu();
	}

	private void setupGamePanelListeners(int width, int height) {

		// Pause button listener
		gamePanel.setPauseButtonListener((e) -> pauseButtonClicked());
		
		//returning if both players are computes, because we don't won't to click by mistake
		if (circlePlayerAI != null && crossPlayerAI != null)
			return;

		// Field listener
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				gamePanel.setFieldOnClickListener(j, i, new FieldListener(j, i));
	}

	private void pauseButtonClicked() {

		if (!isPaused && gameModel.isWon())
			setupWinWindow();
		else if (!isPaused && gameModel.isTie())
			setupTieWindow();
		else if (!isPaused && !gameModel.isWon() && !gameModel.isTie())
			setupPauseWindow();
		else
			resumeGame();
	}

	private void startGame() {

		// making sure that timer is stopped, bacuse it can be set by previous game
		if (timer != null)
			timer.stop();

		getGameData();
		initializatePlayerAI();
		setupGamePabel(gameWidth, gameHeight);
		gameModel.initialize(gameWidth, gameHeight, gameConsecutiveNumber);
		appFrame.setResizable(true);
	}

	private void initializatePlayerAI() {

		if (circlePlayer != PlayerType.PLAYER)
			try {
				circlePlayerAI = (PlayerAI) Class.forName(circlePlayer.getClassName()).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		else
			circlePlayerAI = null;

		if (crossPlayer != PlayerType.PLAYER)
			try {
				crossPlayerAI = (PlayerAI) Class.forName(crossPlayer.getClassName()).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		else
			crossPlayerAI = null;
	}

	private void getGameData() {

		gameWidth = mainMenu.getBoardWidth();
		gameHeight = mainMenu.getBoardHeight();
		gameConsecutiveNumber = mainMenu.getBoardConsecutiveNumber();
		circlePlayer = mainMenu.getCirclePlayerSelected();
		crossPlayer = mainMenu.getCrossPlayerSelected();
	}

	private void doAIMoves(State[][] state, Player player) {

		Point move = null;

		if (player == Player.CIRCLE && circlePlayerAI != null) {
			isAITurn = true;
			move = circlePlayerAI.makeMove(state, player, gameConsecutiveNumber);
		}

		else if (player == Player.CROSS && crossPlayerAI != null) {
			isAITurn = true;
			move = crossPlayerAI.makeMove(state, player, gameConsecutiveNumber);
		}

		if (move != null) {
			int x = move.x;
			int y = move.y;

			timer = new Timer(AI_DELAY, (e) -> {
				if (!isPaused) {
					gameModel.changeState(x, y);
					isAITurn = false;
				} else
					doAIMoves(state, player);
			});
			timer.setRepeats(false);
			timer.start();
		}

	}

	@Override
	public void updateGameStatus(State[][] state, Player player, boolean isWon, boolean isTie) {

		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state[0].length; j++)
				gamePanel.setFieldState(j, i, state[i][j]);

		gamePanel.setPlayerIcon(player);

		if (isWon)
			setupWinWindow();
		else if (isTie)
			setupTieWindow();
		else
			doAIMoves(state, player);
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
			if (!isPaused && !gameModel.isWon() && !gameModel.isTie() && !isAITurn)
				gameModel.changeState(width, height);
		}
	}
}
