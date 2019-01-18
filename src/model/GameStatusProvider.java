package model;

import controller.GameStatusObserver;

public interface GameStatusProvider {

	public void addObserver(GameStatusObserver observer);
	
	public void removeObserver(GameStatusObserver observer);
	
	public void notifyObservers();
}
