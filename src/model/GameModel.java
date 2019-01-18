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
	private int consecutiveNumber;

	private List<GameStatusObserver> observers;

	public GameModel() {

		observers = new ArrayList<>();
	}

	public void initialize(int width, int height, int consecutiveNumber) {

		player = Player.CIRCLE;
		state = new State[height][width];

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				state[i][j] = State.EMPTY;

		this.consecutiveNumber = consecutiveNumber;
	}

	public void changeState(int width, int height) {

		if (isWon)
			throw new IllegalStateException("Can not change field when game is Won");

		if (state[height][width] == State.EMPTY) {

			state[height][width] = player.getState();
			if (isWinning())
				isWon = true;
			else
				changePlayer();
			notifyObservers();
		}
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

		boolean winning = false;
		int counterX = 1;
		int counterY = 1;
		int counterCROSS1 = 1;
		int counterCROSS2 = 1;

		for (int i = 1; i < consecutiveNumber; i++) {

			//
			if (validFieldColumn(column + i))
				if (state[row][column + i] == player.getState())
					counterX++;

			if (validFieldRow(row + i))
				if (state[row + i][column] == player.getState())
					counterY++;

			if (validFieldRow(row + i) && validFieldColumn(column + i))
				if (state[row + i][column + i] == player.getState())
					counterCROSS1++;

			if (validFieldRow(row + i) && validFieldColumn(column - i))
				if (state[row + i][column - i] == player.getState())
					counterCROSS2++;
		}

		if (counterX >= consecutiveNumber || counterY >= consecutiveNumber || counterCROSS1 >= consecutiveNumber
				|| counterCROSS2 >= consecutiveNumber)
			winning = true;

		return winning;
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
			observer.updateGameStatus(state, player, isWon);
	}
}
