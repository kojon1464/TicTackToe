package view.popup;

import javax.swing.JLabel;

public class PausePanel extends PopupPanel {

	private static final long serialVersionUID = 4939262544901172882L;
	
	private JLabel pauseLabel;

	public PausePanel() {

		initializatePauseLabel();
	}

	private void initializatePauseLabel() {

		pauseLabel = new JLabel("Pause");
		pauseLabel.setAlignmentX(CENTER_ALIGNMENT);
		pauseLabel.setFont(pauseLabel.getFont().deriveFont(25.0f));
		
		this.addComponentAfterGlue(pauseLabel);
	}
}
