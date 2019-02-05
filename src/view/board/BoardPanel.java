package view.board;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = -6417462928436639660L;
	
	private int width;
	private int height;

	private JPanel[][] fields;

	public BoardPanel(int width, int height) {

		this.width = width;
		this.height = height;

		initializateFields();
		initializatePanel();
	}

	// initializate this panel
	private void initializatePanel() {
		
		this.setLayout(new GridLayout(height, width, 0, 0));
	}

	private void initializateFields() {

		fields = new JPanel[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				fields[i][j] = new FieldPanel();
				this.add(fields[i][j]);
			}
		}
	}
	
	public FieldPanel getFieldPanel(int width, int height) {
		
		return (FieldPanel)fields[height][width];
	}

	@Override
	public final Dimension getPreferredSize() {
		
		Dimension dimension = super.getPreferredSize();
		Dimension prefSize = null;
		
		Component parent = getParent();

		if (parent == null)
			prefSize = new Dimension((int) dimension.getWidth(), (int) dimension.getHeight());
		else if (parent != null)
			prefSize = parent.getSize();

		int width = (int) prefSize.getWidth() / this.width;
		int height = (int) prefSize.getHeight() / this.height;

		// the smaller of the two sizes
		int smaller = (width > height ? height : width);
		return new Dimension(smaller*this.width, smaller*this.height);
	}
	
}
