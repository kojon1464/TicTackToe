package enums;

public enum Player {
	CROSS(State.CROSS), CIRCLE(State.CIRCLE);
	
	private State state;
	
	private Player(State state) {
		
		this.state = state;
	}
	
	public State getState() {
		
		return state;
	}
	
	public Player getOpponent() {

		if (this == Player.CIRCLE)
			return Player.CROSS;
		else
			return Player.CIRCLE;
	}
}
