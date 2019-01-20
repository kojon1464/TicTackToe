package controller;

import enums.Player;
import enums.State;

public interface GameStatusObserver {

	public void updateGameStatus(State[][] state, Player player, boolean isWon, boolean isTie);
}
