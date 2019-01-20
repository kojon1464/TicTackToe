package ai;

import java.awt.Point;

import enums.Player;
import enums.State;

public interface PlayerAI {

	public Point makeMove(State[][] state, Player player, int consecutiveNumber);
}
