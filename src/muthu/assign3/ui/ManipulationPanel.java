package muthu.assign3.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ManipulationPanel extends JPanel {
	private JButton addButton, editButton, deleteButton, viewButton;
	private PhotoPropertiesPanel photoPropertiesPanel;
/**
 * add,edit,delete,view buttons are created in this panel
 */
	public ManipulationPanel() {
		Dimension size = new Dimension(200, 50);
		this.setMaximumSize(size);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.white);
		this.setLayout(new GridBagLayout());
		create();

	}
	/**
	 * specifications for creating the cards for the card layout
	 */
	

	private void create() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		JPanel panel = new JPanel();
		Dimension size = new Dimension(200, 35);
		panel.setMaximumSize(size);
		panel.setPreferredSize(size);
		panel.setMinimumSize(size);
		panel.setAlignmentX(LEFT_ALIGNMENT);
		panel.setBackground(Color.white);

		gridBagConstraints.gridx = gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = gridBagConstraints.weighty = 90;

		this.add(panel, gridBagConstraints);

		addButton = new JButton("Add");
		panel.add(addButton, gridBagConstraints);
		gridBagConstraints.gridx = 1;
		viewButton = new JButton("View");
		panel.add(viewButton, gridBagConstraints);
		gridBagConstraints.gridx = 2;
		editButton = new JButton("Edit");
		panel.add(editButton, gridBagConstraints);
		deleteButton = new JButton("Delete");
		panel.add(deleteButton, gridBagConstraints);

	}
/**
 * the following methods register action events for add,edit,delete, save
 * @param listener
 */
	public void addPhotoActionListener(ActionListener listener) {
		addButton.addActionListener(listener);
	}

	public void editPhotoActionListener(ActionListener listener) {
		editButton.addActionListener(listener);
	}

	public void deletePhotoActionListener(ActionListener listener) {
		deleteButton.addActionListener(listener);
	}

	public void viewPhotoActionListener(ActionListener listener) {
		viewButton.addActionListener(listener);
	}
}

