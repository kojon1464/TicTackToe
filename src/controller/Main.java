package controller;

import javax.swing.SwingUtilities;

import model.GameModel;
import view.AppFrame;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> run());
	}
	
	private static void run() {
		
		GameModel model = new GameModel();
		AppFrame view = new AppFrame();
		new MainController(view, model);
	}

}
