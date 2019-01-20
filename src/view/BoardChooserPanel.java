package view;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class BoardChooserPanel extends JPanel {

	private JLabel widthLabel;
	private JLabel heightLabel;
	private JLabel consecutiveNumberLabel;
	private JSpinner widthSpinner;
	private JSpinner heightSpinner;
	private JSpinner consecutiveNumberSpinner;

	public BoardChooserPanel() {

		initializateLabels();
		initializateSpinners();
		initializatePanel();
	}

	private void initializatePanel() {

		this.setLayout(new GridLayout(3, 2));

		this.add(widthLabel);
		this.add(widthSpinner);
		this.add(heightLabel);
		this.add(heightSpinner);
		this.add(consecutiveNumberLabel);
		this.add(consecutiveNumberSpinner);
	}

	private void initializateLabels() {

		widthLabel = new JLabel("Select board width: ");
		widthLabel.setFont(widthLabel.getFont().deriveFont(15.0f));

		heightLabel = new JLabel("Selecy board height: ");
		heightLabel.setFont(heightLabel.getFont().deriveFont(15.0f));

		consecutiveNumberLabel = new JLabel("<html>Select number of consecutive elements needed for win: </html>");
		consecutiveNumberLabel.setFont(consecutiveNumberLabel.getFont().deriveFont(15.0f));
	}

	private void initializateSpinners() {

		widthSpinner = new JSpinner(new SpinnerNumberModel(3, 3, 10, 1));
		widthSpinner.setFont(widthSpinner.getFont().deriveFont(15.0f));

		heightSpinner = new JSpinner(new SpinnerNumberModel(3, 3, 10, 1));
		heightSpinner.setFont(heightSpinner.getFont().deriveFont(15.0f));

		consecutiveNumberSpinner = new JSpinner(new SpinnerNumberModel(3, 3, 10, 1));
		consecutiveNumberSpinner.setFont(consecutiveNumberSpinner.getFont().deriveFont(15.0f));
	}

	public JSpinner getWidthSpinner() {

		return widthSpinner;
	}

	public JSpinner getHeightSpinner() {

		return heightSpinner;
	}

	public JSpinner getConsecutiveNumberSpinner() {

		return consecutiveNumberSpinner;
	}
}
