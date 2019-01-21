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
		
		for(int i = 0; i < state.length; i++) {
			for(int j = 0; j < state[0].length; j++) {
				if(state[i][j] == State.EMPTY) {
					for(int k = consecutiveNumber - 1; k > 0; k--) {
						if(isFieldBlocking(i, j, k))
							points[k - 1] = new Point(j, i);
					}
				}
				
			}
		}
		
		for(int i = points.length - 1; i >= 0; i--)
			if(points[i] != null)
				return points[i];
		
		return new Point(0, 0);
	}
	
	private boolean isFieldBlocking(int row, int column, int number) {

		boolean blocking = false;
		int counterX1 = 0;
		int counterY1 = 0;
		int counterCROSS11 = 0;
		int counterCROSS21 = 0;
		int counterX2 = 0;
		int counterY2 = 0;
		int counterCROSS12 = 0;
		int counterCROSS22 = 0;

		for (int i = 1; i <= number; i++) {

			//
			if (validFieldColumn(column + i))
				if (state[row][column + i] == player.getState())
					counterX1++;
			
			if (validFieldColumn(column - i))
				if (state[row][column - i] == player.getState())
					counterX2++;

			if (validFieldRow(row + i))
				if (state[row + i][column] == player.getState())
					counterY1++;
			
			if (validFieldRow(row - i))
				if (state[row - i][column] == player.getState())
					counterY2++;

			if (validFieldRow(row + i) && validFieldColumn(column + i))
				if (state[row + i][column + i] == player.getState())
					counterCROSS11++;
			
			if (validFieldRow(row - i) && validFieldColumn(column - i))
				if (state[row - i][column - i] == player.getState())
					counterCROSS12++;

			if (validFieldRow(row + i) && validFieldColumn(column - i))
				if (state[row + i][column - i] == player.getState())
					counterCROSS21++;
			
			if (validFieldRow(row - i) && validFieldColumn(column + i))
				if (state[row - i][column + i] == player.getState())
					counterCROSS22++;
		}

		if (counterX1 >= number || counterY1 >= number || counterCROSS11 >= number
				|| counterCROSS21 >= number || counterX2 >= number || counterY2 >= number || counterCROSS12 >= number
				|| counterCROSS22 >= number)
			blocking = true;

		return blocking;
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
