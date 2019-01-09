package controller;

import javax.swing.JFrame;

import model.GameModel;
import view.AppFrame;

public class Main {

	public static void main(String[] args) {

		GameModel model = new GameModel();
		JFrame view = new AppFrame();
		MainController controller = new MainController(view, model);
		
		controller.start();
	}

}