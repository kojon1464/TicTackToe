package view.popup;

import javax.swing.JLabel;

public class TiePanel extends PopupPanel {

	private static final long serialVersionUID = 49936333543462187L;
	
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
