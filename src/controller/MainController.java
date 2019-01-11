package controller;

import javax.swing.JFrame;

import model.GameModel;
import view.AppFrame;
import view.GamePanel;

public class MainController {

	private AppFrame appFrame;
	private GameModel gameModel;
	
	public MainController(AppFrame appFrame, GameModel gameModel) {
		
		this.appFrame = appFrame;
		this.gameModel = gameModel;
		
		appFrame.setPanel(new GamePanel());
	}
}
