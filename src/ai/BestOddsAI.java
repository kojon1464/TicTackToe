package ai;

import java.awt.Point;

import ai.mcts.Node;
import enums.Player;
import enums.State;

public class BestOddsAI implements PlayerAI {

	@Override
	public Point makeMove(State[][] state, Player player, int consecutiveNumber) {

		long startTime = System.currentTimeMillis();

		Node root = new Node(state, player, consecutiveNumber, true);

		do{
			//selection
			Node bestToSimulate = root.selectBestLeafNode();

			//expansion
			if(bestToSimulate.getChildren().size() == 0 && !bestToSimulate.isOver())
				bestToSimulate.expand();
			
			//roll-out
			if (bestToSimulate.getChildren().size() > 0)
				bestToSimulate = bestToSimulate.getRandomChild();

			//backpropagation
			int score = bestToSimulate.simulate(root.getPlayer().getOpponent());
			if (player != bestToSimulate.getPlayer())
				score = 0;

			bestToSimulate.backpropagate(score);
			
		} while(System.currentTimeMillis() - startTime < 100);

		Node bestChild = root.getMostVisitedChild();

		return findDifference(state, bestChild.getState());
	}

	public Point findDifference(State[][] state1, State[][] state2) {

		for (int i = 0; i < state1.length; i++)
			for (int j = 0; j < state1[0].length; j++)
				if (state1[i][j] != state2[i][j])
					return new Point(j, i);

		return null;
	}
}
