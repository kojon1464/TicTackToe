package ai;

import java.awt.Point;

import ai.mcts.Node;
import enums.Player;
import enums.State;

public class BestOddsAI implements PlayerAI {

	@Override
	public Point makeMove(State[][] state, Player player, int consecutiveNumber) {

		int counter = 0;

		Node root = new Node(state, player, consecutiveNumber, true);

		while (counter <= 10000) {

			Node bestToSimulate = root.selectBestLeafNode();

			if(bestToSimulate.getChildren().size() == 0 && !bestToSimulate.isOver())
				bestToSimulate.expand();

			if (bestToSimulate.getChildren().size() > 0)
				bestToSimulate = bestToSimulate.getRandomChild();

			int score = bestToSimulate.simulate(root.getPlayer().getOpponent());
			if (player != bestToSimulate.getPlayer())
				score = 0;

			bestToSimulate.backpropagate(score);
			
			for(int i = 0; i < root.getChildren().size(); i++)
				System.out.println("Score: " + root.getChildren().get(i).score + "visits: " + root.getChildren().get(i).numberOfVisits + "uts: " + root.getChildren().get(i).getUCT() + "children: " + root.getChildren().get(i).getChildren().size() + "isOver: " + root.getChildren().get(i).isOver());
			
			counter++;
		}

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
