package controller;

import javax.swing.JFrame;

import model.GameModel;

public class MainController {

	private JFrame appFrame;
	private GameModel gameModel;
	
	public MainController(JFrame appFrame, GameModel gameModel) {
		
		this.appFrame = appFrame;
		this.gameModel = gameModel;
	}
	
	public void start() {
		
	}
}
