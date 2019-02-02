package view.board;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ResizableIcon extends ImageIcon {

	private static final long serialVersionUID = 5076319868161326087L;
	
	private Image image;
	private Component parent;
	
	public ResizableIcon(Component parent, Image image) {
		
		super(image);
		
		this.image = image;
		this.parent = parent;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		
		super.paintIcon(c, g, x, y);
		
		g.drawImage(image, 0, 0, parent.getWidth(), parent.getHeight(), null);
	}
}
