package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import view.popup.PopupPanel;

public class AppFrame extends JFrame {

	private static final long serialVersionUID = -5788222127450714822L;
	
	private static final int WIDTH_START = 600;
	private static final int HEIGHT_START = 500;

	public AppFrame() {

		init();
	}

	public void setPanel(JPanel panel) {

		this.getContentPane().removeAll();
		this.getContentPane().add(panel);
		this.revalidate();
		this.repaint();
	}

	public void addPanelOnPopuoLayer(JPanel panel) {

		this.getLayeredPane().add(panel, JLayeredPane.POPUP_LAYER);
		this.revalidate();
		this.repaint();
	}

	public void removePanelOnPopuoLayer(JPanel panel) {

		this.getLayeredPane().remove(panel);
		this.revalidate();
		this.repaint();
	}

	private void init() {

		this.setTitle("TicTackToe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(WIDTH_START, HEIGHT_START));
		this.setSize(WIDTH_START, HEIGHT_START);
		this.setVisible(true);
	}

	@Override
	public void setResizable(boolean resizable) {

		super.setResizable(resizable);

		if (!resizable)
			this.setSize(WIDTH_START, HEIGHT_START);
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		if (this.getLayeredPane().getComponent(0) instanceof PopupPanel)
			((PopupPanel) this.getLayeredPane().getComponent(0)).notifyFrameChangedSize(this.getWidth(),
					this.getHeight());
	}
}
