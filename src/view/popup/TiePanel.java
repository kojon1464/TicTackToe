package view.popup;

import javax.swing.Box;
import javax.swing.JLabel;

public class TiePanel extends PopupPanel {

	private JLabel tieLabel;

	public TiePanel() {

		initializateTieLabel();
	}

	private void initializateTieLabel() {

		tieLabel = new JLabel("Tie!");
		tieLabel.setAlignmentX(CENTER_ALIGNMENT);
		tieLabel.setFont(tieLabel.getFont().deriveFont(25.0f));
		
		this.addComponentAfterGlue(tieLabel);
	}
}
