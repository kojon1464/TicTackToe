package ai.mcts;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import enums.Player;
import enums.State;
import model.GameModel;

public class Node {

	private boolean isRoot;

	private int numberOfVisits;
	private int score;

	private Node parent;
	private List<Node> children;

	private State[][] state;
	private Player player;
	private int consecutiveNumber;

	public Node(State[][] state, Player player, int consecutiveNumber, boolean isRoot) {

		this.isRoot = isRoot;

		numberOfVisits = 0;
		score = 0;

		parent = null;
		children = new ArrayList<>();

		this.state = state;
		this.player = player;
		this.consecutiveNumber = consecutiveNumber;
	}

	public Node(State[][] state, Node parent) {

		this(state, parent.player.getOpponent(), parent.consecutiveNumber, false);

		this.parent = parent;
	}

	public Player getPlayer() {

		return player;
	}

	public void backpropagate(int score) {
		
		this.numberOfVisits++;
		this.score += score;
		
		if (isRoot == true)
			return;

		parent.backpropagate(score);
	}

	public void expand() {
		
		if (children.size() > 0)
			throw new IllegalStateException("Cannot expand node that have been expanded!");

		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state[0].length; j++)
				if (state[i][j] == State.EMPTY)
					children.add(new Node(createNewState(state, i, j), this));
	}

	private State[][] createNewState(State[][] state, int row, int column) {

		State[][] newState = copyState(state);

		newState[row][column] = player.getState();

		return newState;
	}

	private State[][] copyState(State[][] state) {

		State[][] copiedState = new State[state.length][state[0].length];

		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state[0].length; j++)
				copiedState[i][j] = state[i][j];

		return copiedState;
	}

	public double getUCT() {
		
		if(numberOfVisits == 0)
			return Integer.MAX_VALUE;
		
		return (double) score / (double) numberOfVisits + 1.41 * Math.sqrt((Math.log((double) parent.numberOfVisits) / (double) numberOfVisits));
	}

	public int simulate(Player rootOpponent) {
		
		List<Point> points = new ArrayList<Point>();
		
		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state[0].length; j++)
				if(state[i][j] == State.EMPTY)
					points.add(new Point(j, i));
		
		GameModel simulation = new GameModel(copyState(state), player, consecutiveNumber);
		
		if(simulation.isWon() && simulation.getPlayer() == rootOpponent)
			parent.score = -10000;

		while(true) {
			
			if (simulation.isTie())
				return 0;
			if (simulation.isWon() && simulation.getPlayer() == player)
				return 1;
			else if(simulation.isWon())
				return 0;
			
			makeRandomMove(points, simulation);
		}
	}

	private void makeRandomMove(List<Point> points, GameModel simulation) {

		Point random = points.get((int) Math.round(Math.random() * (points.size() - 1)));
		
		simulation.changeState(random.x, random.y);
		
		points.remove(random);
	}

	public Node selectBestLeafNode() {
		
		Node leaf = this;

		while (leaf.children.size() > 0 && leaf.allChildrenVisited())
			leaf = leaf.findBestChild();
		
		return leaf;
	}

	private boolean allChildrenVisited() {
		
		boolean visited = true;
		
		for(int i = 0; i < children.size(); i++)
			if(children.get(i).numberOfVisits == 0)
				visited = false;
		
		return visited;
	}

	private Node findBestChild() {

		if (children.size() <= 0)
			throw new IllegalStateException("Cannot find best child if there are no children!");

		Node best = children.get(0);

		for (int i = 1; i < children.size(); i++)
			if (children.get(i).getUCT() > best.getUCT())
				best = children.get(i);

		return best;
	}
	
	public Node getRandomChild() {
		
		return children.get((int) Math.round(Math.random() * (children.size() - 1)));
	}
	
	public List<Node> getChildren() {
		
		return children;
	}
	
	public Node getMostVisitedChild() {
		
		if(children.size() <= 0)
			throw new IllegalStateException("Cannot find most visited child if there are no children!");
		
		Node most = children.get(0);
		
		for(int i = 1; i < children.size(); i++)
			if(children.get(i).numberOfVisits > most.numberOfVisits)
				most = children.get(i);
		
		return most;
	}
	
	public State[][] getState(){
		
		return state;
	}

	public boolean isOver() {
		
		GameModel simulation = new GameModel(copyState(state), player, consecutiveNumber);
		
		if(simulation.isTie() || simulation.isWon())
			return true;
		
		return false;
	}
}
