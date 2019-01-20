package ai;

import java.awt.Point;

import enums.Player;
import enums.State;
import model.GameModel;

public class BestOddAI implements PlayerAI {

	private final int MAX_ACURRACY = 10000000;
	
	private int counter;
	
	private Player player;
	private Player opponent;
	private int consecutiveNumber;

	@Override
	public Point makeMove(State[][] state, Player player, int consecutiveNumber) {

		this.player = player;

		if (player == Player.CIRCLE)
			this.opponent = Player.CROSS;
		else
			this.opponent = Player.CIRCLE;

		MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();
		mcts.setLevel(10);
		Board board1 = new Board(createBoardValues(state));
		Board board2 = mcts.findNextMove(board1, player == Player.CIRCLE ? Board.P1 : Board.P2);
		
		return findDiference(board1, board2);
	}
	
	private int[][] createBoardValues(State[][] state){
		int[][] values = new int[state.length][state[0].length];
		
		for(int i = 0; i < state.length; i++) {
			for(int j = 0; j < state[0].length; j++) {
				if(state[i][j] == Player.CIRCLE.getState())
					values[i][j] = Board.P1;
				else if(state[i][j] == Player.CROSS.getState())
					values[i][j] = Board.P2;
				else
					values[i][j] = 0;
			}
		}
		return values;
	}
	
	private Point findDiference(Board b1, Board b2) {
		Point point = null;
		
		for(int i = 0; i < b1.getBoardValues().length; i++) {
			for(int j = 0; j < b1.getBoardValues()[0].length; j++) {
				if(b1.getBoardValues()[i][j] != b2.getBoardValues()[i][j])
					point = new Point(j, i);
			}
		}
		return point;
	}


}
