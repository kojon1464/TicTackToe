package model;

import java.util.ArrayList;
import java.util.List;

import controller.GameStatusObserver;
import enums.Player;
import enums.State;

public class GameModel implements GameStatusProvider {

	private Player player;
	private State[][] state;
	private boolean isWon;
	private boolean isTie;
	private int consecutiveNumber;

	private List<GameStatusObserver> observers;

	public GameModel() {

		observers = new ArrayList<>();
	}

	public GameModel(State[][] state, Player player, int consecutiveNumber) {

		observers = new ArrayList<>();

		this.state = state;
		this.player = player;
		this.consecutiveNumber = consecutiveNumber;
		isWon = false;
		isTie = false;

		//Changing player because no move have been made before and model doesn't know that
		this.player = player.getOpponent();

		if (isWinning())
			isWon = true;
		if (isTied())
			isTie = true;

		if (!isWon)
			this.player = player;
	}

	public void initialize(int width, int height, int consecutiveNumber) {

		player = Player.CIRCLE;
		state = new State[height][width];
		isWon = false;
		isTie = false;

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				state[i][j] = State.EMPTY;

		this.consecutiveNumber = consecutiveNumber;

		notifyObservers();
	}

	public void changeState(int width, int height) {

		if (isWon || isTie)
			throw new IllegalStateException("Can not change field when game is Won");

		if (state[height][width] == State.EMPTY) {

			state[height][width] = player.getState();
			if (isWinning())
				isWon = true;
			else if (isTied())
				isTie = true;
			else
				changePlayer();
			notifyObservers();
		}
	}

	private boolean isTied() {

		boolean tie = true;

		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state[0].length; j++)
				if (state[i][j] == State.EMPTY)
					tie = false;

		return tie;
	}

	private boolean isWinning() {

		boolean winning = false;

		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state[0].length; j++)
				if (state[i][j] == player.getState())
					if (isFieldWinning(i, j))
						winning = true;

		return winning;
	}

	private boolean isFieldWinning(int row, int column) {

		// direction in which algorithms check if field is winning. pattern :{x, y};
		int[][] directions = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, 1 } };

		for (int i = 0; i < directions.length; i++) {

			int counter = 1;

			for (int j = 1; j < consecutiveNumber; j++) {

				if (validFieldRow(row + j * directions[i][1]) && validFieldColumn(column + j * directions[i][0]))
					if (state[row + j * directions[i][1]][column + j * directions[i][0]] == player.getState())
						counter++;
			}

			if (counter >= consecutiveNumber)
				return true;
		}
		return false;
	}

	private boolean validFieldRow(int row) {

		boolean valid = false;

		if (row >= 0 && row < state.length)
			valid = true;

		return valid;
	}

	private boolean validFieldColumn(int column) {

		boolean valid = false;

		if (column >= 0 && column < state[0].length)
			valid = true;

		return valid;
	}

	private void changePlayer() {

		if (player == Player.CIRCLE)
			player = Player.CROSS;
		else
			player = Player.CIRCLE;
	}

	public boolean isWon() {

		return isWon;
	}

	public boolean isTie() {

		return isTie;
	}

	public Player getPlayer() {

		return player;
	}

	@Override
	public void addObserver(GameStatusObserver observer) {

		if (!observers.contains(observer))
			observers.add(observer);
	}

	@Override
	public void removeObserver(GameStatusObserver observer) {

		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {

		for (GameStatusObserver observer : observers)
			observer.updateGameStatus(state, player, isWon, isTie);
	}
}
