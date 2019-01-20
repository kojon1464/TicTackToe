package ai;

import java.awt.Point;

import enums.Player;
import enums.State;

public class RandomAI implements PlayerAI {

	// creates random number form 0 to x
	private int randomize(int x) {

		return (int) Math.round(Math.random() * x);
	}

	@Override
	public Point makeMove(State[][] state, Player player, int consecutiveNumber) {

		boolean sucessful = false;
		Point point = null;
		
		do {
			int x = randomize(state[0].length - 1);
			int y = randomize(state.length - 1);

			if (state[y][x] == State.EMPTY) {
				
				sucessful = true;
				point = new Point(x, y);
			}
		} while (!sucessful);
		
		return point;
	}

}
