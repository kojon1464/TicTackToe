package ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import enums.Player;
import enums.State;
import model.GameModel;

public class BlockingAI implements PlayerAI {

	private State[][] state;
	private Player player;
	private int consecutiveNumber;

	@Override
	public Point makeMove(State[][] state, Player player, int consecutiveNumber) {

		this.state = state;
		this.consecutiveNumber = consecutiveNumber;

		if (player == Player.CIRCLE)
			this.player = Player.CROSS;
		else
			this.player = Player.CIRCLE;

		return findBestPointBlockingOpponent();
	}

	private Point findBestPointBlockingOpponent() {

		Point[] points = new Point[consecutiveNumber - 1];

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if (state[i][j] == State.EMPTY) {
					for (int k = consecutiveNumber - 1; k > 0; k--) {
						if (isFieldBlocking(i, j, k))
							points[k - 1] = new Point(j, i);
					}
				}

			}
		}

		for (int i = points.length - 1; i >= 0; i--)
			if (points[i] != null)
				return points[i];

		return new Point(0, 0);
	}

	private boolean isFieldBlocking(int row, int column, int number) {

		// direction in which algorithms check if field is blocking opponent. pattern :{x, y};
		int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 } };

		for (int i = 0; i < directions.length; i++) {
			
			int counter = 0;

			for (int j = 1; j <= number; j++) {

				if (validFieldRow(row + j * directions[i][1]) && validFieldColumn(column + j * directions[i][0]))
					if (state[row + j * directions[i][1]][column + j * directions[i][0]] == player.getState())
						counter++;
			}
			
			if (counter >= number)
				return true;
		}
		return false;
	}

	private boolean validFieldColumn(int column) {

		boolean valid = false;

		if (column >= 0 && column < state[0].length)
			valid = true;

		return valid;
	}

	private boolean validFieldRow(int row) {

		boolean valid = false;

		if (row >= 0 && row < state.length)
			valid = true;

		return valid;
	}
}
