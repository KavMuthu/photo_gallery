package muthu.assign3.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PhotoGalleryPanel extends JPanel {
	private JLabel displayPicLabel, showEditedPicLabel, showPicsByTitleLabel;
	private PhotoPropertiesPanel photoPropertiesPanel;
	private CardLayout galleyCardLayout;
	private JPanel addPhotosCard, editPhotosCard, deletePhotosCard,
			viewPhotosCard;
/**
 * cards for photo gallery panel is created
 */
	public PhotoGalleryPanel() {

		galleyCardLayout = new CardLayout();
		this.setLayout(galleyCardLayout);
		this.setBackground(Color.black);

		addPhotosCard = new JPanel();
		editPhotosCard = new JPanel();
		deletePhotosCard = new JPanel();
		viewPhotosCard = new JPanel();

		this.add(addPhotosCard, "1");
		this.add(editPhotosCard, "2");
		this.add(deletePhotosCard, "3");
		this.add(viewPhotosCard, "4");

		galleyCardLayout.show(this, "1");

		Dimension size = new Dimension(700, 700);
		addPhotosCard.setMaximumSize(size);
		addPhotosCard.setPreferredSize(size);
		addPhotosCard.setMinimumSize(size);
		addPhotosCard.setAlignmentX(LEFT_ALIGNMENT);
		addPhotosCard.setBorder(BorderFactory.createLineBorder(Color.black));
		addPhotosCard.setBackground(Color.DARK_GRAY);

		displayPicLabel = new JLabel();
		addPhotosCard.add(displayPicLabel);

		Dimension size1 = new Dimension(700, 700);
		editPhotosCard.setMaximumSize(size1);
		editPhotosCard.setPreferredSize(size1);
		editPhotosCard.setMinimumSize(size1);
		editPhotosCard.setAlignmentX(LEFT_ALIGNMENT);
		editPhotosCard.setBorder(BorderFactory.createLineBorder(Color.black));
		editPhotosCard.setBackground(Color.DARK_GRAY);

		showEditedPicLabel = new JLabel();
		editPhotosCard.add(showEditedPicLabel);

		Dimension size2 = new Dimension(700, 700);
		deletePhotosCard.setMaximumSize(size2);
		deletePhotosCard.setPreferredSize(size2);
		deletePhotosCard.setMinimumSize(size2);
		deletePhotosCard.setAlignmentX(LEFT_ALIGNMENT);
		deletePhotosCard.setBorder(BorderFactory.createLineBorder(Color.black));
		deletePhotosCard.setBackground(Color.DARK_GRAY);

		Dimension size3 = new Dimension(700, 700);
		viewPhotosCard.setMaximumSize(size3);
		viewPhotosCard.setPreferredSize(size3);
		viewPhotosCard.setMinimumSize(size3);
		viewPhotosCard.setAlignmentX(LEFT_ALIGNMENT);
		viewPhotosCard.setBorder(BorderFactory.createLineBorder(Color.black));
		viewPhotosCard.setBackground(Color.DARK_GRAY);

		showPicsByTitleLabel = new JLabel();

		viewPhotosCard.add(showPicsByTitleLabel);

	}

	public JPanel getViewPhotosCard() {
		return viewPhotosCard;
	}

	public void setViewPhotosCard(JPanel viewPhotosCard) {
		this.viewPhotosCard = viewPhotosCard;
	}

	public void visibilityForGallery(boolean state) {
		displayPicLabel.setVisible(state);
	}

	public void visibilityForEditedPicGallery(boolean state) {
		showEditedPicLabel.setVisible(state);
	}

	public void visibilityForViewByTitle(boolean state) {
		showEditedPicLabel.setVisible(state);
	}

	/**
	 * images are displayed on the respective panels
	 * @param path
	 */
	public void displayImage(String path) {
		//

		System.out.println("file path: " + path);
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(250, 250,
				java.awt.Image.SCALE_SMOOTH);

		displayPicLabel = new JLabel(new ImageIcon(scaledImg));
		addPhotosCard.add(displayPicLabel);
		// addPhotosCard = new JPanel();
		this.visibilityForGallery(true);

	}

	public void displayEditedImage(String imgFile) {
		System.out.println(" edited img file path: " + imgFile);

		ImageIcon icon = new ImageIcon(imgFile);
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(250, 250,
				java.awt.Image.SCALE_SMOOTH);

		showEditedPicLabel = new JLabel(new ImageIcon(scaledImg));
		editPhotosCard.add(showEditedPicLabel);

		this.visibilityForEditedPicGallery(true);

	}

	public void displayImagesByCategory(String imgFilePath) {
		System.out.println("view cat file path " + imgFilePath);

		ImageIcon icon = new ImageIcon(imgFilePath);
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(250, 250,
				java.awt.Image.SCALE_SMOOTH);

		showPicsByTitleLabel = new JLabel(new ImageIcon(scaledImg));
		viewPhotosCard.add(showPicsByTitleLabel);

		this.visibilityForViewByTitle(true);

	}
}

