package muthu.assign3.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import muthu.assign3.Main;
import muthu.assign3.entities.Photo;

public class PhotoPropertiesPanel extends JPanel {
	private JLabel picLabel, picTitle, picPath, picCategory, picDate, picDesc,
			photosLabel, photoCategory, photoDate, photoDescription,
			displayEditedPicLabel, delPhotosLabel, delPhotoCategory,
			titleLabel, photoLabel, viewLabel, dateLabel, delPhotoDate,
			delPhotoDescription, displayDelPicLabel;

	private JComboBox<String> photosCombo, delPhotosCombo, viewCategoryCombo,
			viewDateCombo;
	private DateFormat date;
	private JTextField picTitleTf, picPathTf, picCategoryTf, picDateTf,
			picDescTf;
	private JButton saveButton, saveButtonForEdit, saveButtonForDelete,
			showButton;
	private CardLayout cardLayout;
	private JPanel addPanel, editPanel, deletePanel, viewPanel;
	
	private JTextField photosCategoryTf, photosDateTf, photosDescriptionTf,
			photosDelCategoryTf, photosDelDateTf, photosDelDescriptionTf;
	private PhotoGalleryPanel photoGalleryPanel;
	private String viewImgPath;

	private ManipulationPanel manipulationPanel;
	private Photo photo;
	private Map<String, Photo> photoCollection1;

	/**
	 * class constructor defines the cards in the cardlayout and it's specifications
	 */
	public PhotoPropertiesPanel() {
		
		this.setBackground(Color.white);
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);

		addPanel = new JPanel();
		editPanel = new JPanel();
		deletePanel = new JPanel();
		viewPanel = new JPanel();

		this.add(addPanel, "1");
		this.add(editPanel, "2");
		this.add(deletePanel, "3");
		this.add(viewPanel, "4");

		cardLayout.show(this, "1");

		Dimension size1 = new Dimension(200, 80);
		addPanel.setMaximumSize(size1);
		addPanel.setPreferredSize(size1);
		addPanel.setMinimumSize(size1);
		addPanel.setAlignmentX(LEFT_ALIGNMENT);
		addPanel.setBackground(Color.WHITE);
		create();

		photoGalleryPanel = new PhotoGalleryPanel();

		// photoCollection = new LinkedHashMap<String, Photo>();
		Dimension size2 = new Dimension(200, 80);
		editPanel.setMaximumSize(size2);
		editPanel.setPreferredSize(size2);
		editPanel.setMinimumSize(size2);
		editPanel.setAlignmentX(LEFT_ALIGNMENT);
		editPanel.setBackground(Color.WHITE);
		createComboBox();
		createPhotoPropertiesForEdit();

		Dimension size3 = new Dimension(200, 80);
		deletePanel.setMaximumSize(size3);
		deletePanel.setPreferredSize(size3);
		deletePanel.setMinimumSize(size3);
		deletePanel.setAlignmentX(LEFT_ALIGNMENT);
		deletePanel.setBackground(Color.WHITE);
		createDelComboBox();
		createPhotoPropertiesForDelete();

		Dimension size4 = new Dimension(200, 80);
		viewPanel.setMaximumSize(size4);
		viewPanel.setPreferredSize(size4);
		viewPanel.setMinimumSize(size4);
		viewPanel.setAlignmentX(LEFT_ALIGNMENT);
		viewPanel.setBackground(Color.WHITE);
		createViewComboBoxes();
	}

	/**
	 * gets the photo label to display picture
	 * @return
	 */
	public JLabel getPhotoLabel() {
		return photoLabel;
	}
	/**
	 * sets the photo label
	 * @param photoLabel
	 */

	public void setPhotoLabel(JLabel photoLabel) {
		this.photoLabel = photoLabel;
	}

	public Map<String, Photo> getPhotoCollection1() {
		return photoCollection1;
	}

	public void setPhotoCollection1(Map<String, Photo> photoCollection1) {
		this.photoCollection1 = photoCollection1;
	}

	public String getViewImgPath() {
		return viewImgPath;
	}

	public void setViewImgPath(String viewImgPath) {
		this.viewImgPath = viewImgPath;
	}
	public JComboBox<String> getViewCategoryCombo() {
		return viewCategoryCombo;
	}

	public void setViewCategoryCombo(JComboBox<String> viewCategoryCombo) {
		this.viewCategoryCombo = viewCategoryCombo;
	}
/**
 * the method below creates the panel when the add button is clicked
 * grid layout is used to display information 
 */
	private void create() {

		addPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.weightx = 1.75;
		gc.weighty = 0.75;
		gc.gridheight = gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.LINE_START;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.gridx = 1;
		gc.gridy = 0;

		picLabel = new JLabel();
		picLabel.setPreferredSize(new Dimension(175, 175));
		// picLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addPanel.add(picLabel, gc);

		gc.weighty = 0.25;
		gc.anchor = GridBagConstraints.WEST;

		gc.gridx = 0;
		gc.gridy = 1;

		gc.weighty = 0.25;
		picTitle = new JLabel("Title");
		picTitle.setHorizontalAlignment(SwingConstants.LEFT);
		// picTitle.setBorder(BorderFactory.createLineBorder(Color.blue));
		addPanel.add(picTitle, gc);

		gc.gridx = 0;
		gc.gridy = 2;

		picPath = new JLabel("Path: ");
		addPanel.add(picPath, gc);

		gc.gridx = 0;
		gc.gridy = 3;

		picCategory = new JLabel("Category: ");
		addPanel.add(picCategory, gc);

		gc.gridx = 0;
		gc.gridy = 4;

		picDate = new JLabel("Date:");
		addPanel.add(picDate, gc);

		gc.gridx = 0;
		gc.gridy = 5;

		picDesc = new JLabel("Description");
		addPanel.add(picDesc, gc);

		// Second column

		gc.anchor = GridBagConstraints.WEST;
		gc.gridx = 1;
		gc.gridy = 1;

		picTitleTf = new JTextField(20);
		picTitleTf.setMaximumSize(picTitleTf.getPreferredSize());
		addPanel.add(picTitleTf, gc);

		gc.gridx = 1;
		gc.gridy = 2;

		picPathTf = new JTextField(20);
		picPathTf.setMaximumSize(picPathTf.getPreferredSize());
		addPanel.add(picPathTf, gc);

		gc.gridx = 1;
		gc.gridy = 3;

		picCategoryTf = new JTextField(20);
		picCategoryTf.setMaximumSize(picCategoryTf.getPreferredSize());
		addPanel.add(picCategoryTf, gc);

		gc.gridx = 1;
		gc.gridy = 4;

		picDateTf = new JTextField(20);
		picDateTf.setMaximumSize(picDateTf.getPreferredSize());
		addPanel.add(picDateTf, gc);

		gc.gridx = 1;
		gc.gridy = 5;

		picDescTf = new JTextField(20);
		picDescTf.setMaximumSize(picDescTf.getPreferredSize());
		addPanel.add(picDescTf, gc);

		// gc.weightx=0.5;
		gc.weighty = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 6;

		saveButton = new JButton("Save");
		addPanel.add(saveButton, gc);

		displayThumbnail(false);
		// displayPropertiesEdit(false);

	}
	/***
	 * visibility for photo information on the add photos panel
	 * @param state
	 */

	public void displayThumbnail(boolean state) {
		saveButton.setVisible(state);
		picTitle.setVisible(state);
		picPath.setVisible(state);
		picLabel.setVisible(state);
		picCategory.setVisible(state);
		picTitleTf.setVisible(state);
		picPathTf.setVisible(state);
		picCategoryTf.setVisible(state);
		picDateTf.setVisible(state);
		picDate.setVisible(state);
		picDesc.setVisible(state);
		picDescTf.setVisible(state);

	}

	/**
	 * method to display photo on the gallery
	 * @param file
	 */
	public void displayPhoto(File file) {
		try {
			BufferedImage photo = ImageIO.read(file);
			Image scaleImage = photo.getScaledInstance(200, 200,
					Image.SCALE_DEFAULT);
			picLabel.setIcon(new ImageIcon(scaleImage));
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
	/**
	 * setters and getter for category, title, date, file path
	 * @param pathText
	 */
	public void setPicPath(String pathText) {
		picPathTf.setText(pathText);
	}

	public String getTitleText() {
		return picTitleTf.getText();
	}

	public String getCategoryText() {
		return picCategoryTf.getText();
	}

	public String getCategoryForEdit() {
		return photosCategoryTf.getText();
	}

	public File getFileText() {
		return new File(picPathTf.getText());
	}

	public String getDateText() {
		return picDateTf.getText();
	}

	public String getDateForEdit() {
		return photosDateTf.getText();
	}

	public String getDescriptionText() {
		return picDescTf.getText();
	}

	public String getDescriptionForEdit() {
		return photosDescriptionTf.getText();
	}

	/**
	 * the following methods register the action events to be handled.
	 * save buttons for add,edit,delete registers the event here
	 * @param listener
	 */
	public void savePhotoActionListener(ActionListener listener) {
		saveButton.addActionListener(listener);
	}

	public void editSavePhotoActionListener(ActionListener listener) {
		saveButtonForEdit.addActionListener(listener);
	}

	public void deleteSavePhotoActionListener(ActionListener listener) {
		saveButtonForDelete.addActionListener(listener);
	}

	public void viewCatActionListener(ActionListener listener) {
		showButton.addActionListener(listener);
	}

	public void clearTextField() {
		picTitleTf.setText("");
		picCategoryTf.setText("");
		picDateTf.setText("");
		picDescTf.setText("");
	}
	/**
	 * components for the edit panel are created in this method
	 */
	public void createComboBox() {

		editPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		// gc.weightx=0;
		// gc.weighty=15;

		gc.anchor = GridBagConstraints.NORTH;
		gc.gridx = 0;
		gc.gridy = 0;

		photosLabel = new JLabel("Select Photo to Edit");

		Dimension size = new Dimension(280, 80);
		editPanel.add(photosLabel, gc);

		gc.weightx = 0;
		gc.weighty = 0;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridx = 1;
		gc.gridy = 0;

		photosCombo = new JComboBox();
		photosCombo.setPreferredSize(new Dimension(150, 35));
		photosCombo.setEditable(false);

		editPanel.add(photosCombo, gc);
	}
	/**
	 * components for view photos page are created here
	 */
	public void createViewComboBoxes() {

		titleLabel = new JLabel("View by Category: ");
		viewCategoryCombo = new JComboBox();
		viewCategoryCombo.setPreferredSize(new Dimension(150, 35));
		viewCategoryCombo.setEditable(false);
		viewPanel.add(titleLabel);
		viewPanel.add(viewCategoryCombo);

		showButton = new JButton("Show");
		viewPanel.add(showButton);

	}
	/**
	 * components for delete photos page are created in this method
	 */
	public void createDelComboBox() {
		deletePanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		delPhotosLabel = new JLabel("Please select the photo to delete: ");
		deletePanel.add(delPhotosLabel, gbc);

		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridx = 1;
		gbc.gridy = 0;

		delPhotosCombo = new JComboBox();
		delPhotosCombo.setPreferredSize(new Dimension(150, 35));
		delPhotosCombo.setEditable(false);

		deletePanel.add(delPhotosCombo, gbc);
	}
	/**
	 * a combox is populated with titles, based on which the photos are edited
	 * @param photoCollObj
	 */

	public void fetchTitlesForEdit(Map<String, Photo> photoCollObj) {

		displayComboBox(true);
		photosCombo.removeAllItems();

		Set<String> keys = photoCollObj.keySet();
		Iterator<String> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {

			String photoKey = keyIterator.next();
			photosCombo.addItem(Main.photoCollection.get(photoKey).getTitle()
					.toString());

		}
		comboBoxActionListener(photosCombo, photoCollObj);

	}
	/**
	 * a combox is populated with titles, based on which the photos are deleted
	 * @param photoCollObj
	 */

	public void fetchTitlesForDelete() {

		delPhotosCombo.removeAllItems();

		Set<String> keys = Main.photoCollection.keySet();
		Iterator<String> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			String photoKey = keyIterator.next();
			delPhotosCombo.addItem(Main.photoCollection.get(photoKey)
					.getTitle().toString());

		}
		delComboBoxActionListener(delPhotosCombo);
	}

	/**
	 * combo box visibility for edit page
	 * @param state
	 */
	public void displayComboBox(boolean state) {
		photosLabel.setVisible(state);
		photosCombo.setVisible(state);
	}
	/**
	 * components for edit photos page are created in this method
	 */
	public void createPhotoPropertiesForEdit() {

		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.WEST;

		gc.gridx = 0;
		gc.gridy = 2;

		photoCategory = new JLabel("Category: ");
		editPanel.add(photoCategory, gc);

		gc.gridx = 0;
		gc.gridy = 3;

		photosCategoryTf = new JTextField(20);
		editPanel.add(photosCategoryTf, gc);

		gc.gridx = 0;
		gc.gridy = 4;
		photoDate = new JLabel("Date:");
		editPanel.add(photoDate, gc);

		gc.gridx = 0;
		gc.gridy = 5;
		photosDateTf = new JTextField(20);
		editPanel.add(photosDateTf, gc);

		gc.gridx = 0;
		gc.gridy = 6;
		photoDescription = new JLabel("Description: ");
		editPanel.add(photoDescription, gc);

		gc.gridx = 0;
		gc.gridy = 7;
		photosDescriptionTf = new JTextField(20);
		editPanel.add(photosDescriptionTf, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 10;
		saveButtonForEdit = new JButton("Save Changes");
		editPanel.add(saveButtonForEdit, gc);

		displayPropertiesEdit(false);
	}
/**
 * components for delete photos page are created here
 */
	public void createPhotoPropertiesForDelete() {

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 0;
		gbc.gridy = 4;

		delPhotoCategory = new JLabel("Category: ");
		deletePanel.add(delPhotoCategory, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;

		photosDelCategoryTf = new JTextField(20);
		deletePanel.add(photosDelCategoryTf, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;

		delPhotoDate = new JLabel("Date:");
		deletePanel.add(delPhotoDate, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;

		photosDelDateTf = new JTextField(20);
		deletePanel.add(photosDelDateTf, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;

		delPhotoDescription = new JLabel("Description: ");
		deletePanel.add(delPhotoDescription, gbc);

		gbc.gridx = 0;
		gbc.gridy = 9;

		photosDelDescriptionTf = new JTextField(20);
		deletePanel.add(photosDelDescriptionTf, gbc);

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 12;

		saveButtonForDelete = new JButton("Delete Photo");
		deletePanel.add(saveButtonForDelete, gbc);

		displayPropertiesDelete(false);

	}

	public void displayPropertiesEdit(boolean state) {

		photoCategory.setVisible(state);
		photoDate.setVisible(state);
		photoDescription.setVisible(state);
		photosCategoryTf.setVisible(state);
		photosDateTf.setVisible(state);
		photosDescriptionTf.setVisible(state);
		saveButtonForEdit.setVisible(state);
	}

	public void displayPropertiesDelete(boolean state) {

		delPhotoCategory.setVisible(state);
		delPhotoDate.setVisible(state);
		delPhotoDescription.setVisible(state);
		photosDelCategoryTf.setVisible(state);
		photosDelDateTf.setVisible(state);
		photosDelDescriptionTf.setVisible(state);
		saveButtonForDelete.setVisible(state);
	}

	public void emptyComboBox() {
		displayEditedPicLabel.setIcon(null);
		photosCategoryTf.setText("");
		photosDateTf.setText("");
		photosDescriptionTf.setText("");
		photosCombo.addItem("");
	}

	public JLabel getDisplayEditedPicLabel() {
		return displayEditedPicLabel;
	}

	public void setDisplayEditedPicLabel(JLabel displayEditedPicLabel) {
		this.displayEditedPicLabel = displayEditedPicLabel;
	}

	public void emptyComboBoxForDelete() {
		displayDelPicLabel.setIcon(null);
		photosDelCategoryTf.setText("");
		photosDelDateTf.setText("");
		photosDelDescriptionTf.setText("");
		delPhotosCombo.addItem(null);
	}
/**
 * based the title in the edit panel page, action listener is triggered 
 * @param listener
 * @param photoCollObj
 */
	public void comboBoxActionListener(ActionListener listener,
			Map<String, Photo> photoCollObj) {

		photosCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				photosCombo = (JComboBox) e.getSource();
				Object selected = photosCombo.getSelectedItem();

				if (selected == null) {

				}
				displayPropertiesEdit(true);

				if (photosCombo.getSelectedItem() != null) {

					try {
						fetchValuesForEdit(photoCollObj);
					} catch (IOException ex) {

						ex.printStackTrace();
					}

				}
			}

		});
	}
/**
 * the action listener invokes this method, the respective values for images to be edited are displayed 
 * @param photoCollObj
 * @throws IOException
 */
	public void fetchValuesForEdit(Map<String, Photo> photoCollObj)
			throws IOException {

		String keyForEdit = (String) photosCombo.getSelectedItem();

		String photoCateogry = photoCollObj.get(keyForEdit).getCategory();
		String photoDate = photoCollObj.get(keyForEdit).getDate();
		String photoDesc = photoCollObj.get(keyForEdit).getDesc();
		String photoPath = photoCollObj.get(keyForEdit).getFilePath()
				.getAbsolutePath();

		ImageIcon icon = new ImageIcon(photoPath);

		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(200, 200,
				java.awt.Image.SCALE_SMOOTH);

		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.WEST;
		gc.gridx = 0;
		gc.gridy = 1;

		displayEditedPicLabel = new JLabel();

		//
		displayEditedPicLabel.setVerticalTextPosition(JLabel.TOP);
		displayEditedPicLabel.setHorizontalTextPosition(JLabel.LEFT);
		displayEditedPicLabel.setText("Image");

		displayEditedPicLabel.setIcon(new ImageIcon(scaledImg));

		editPanel = removeImg(editPanel, "Image");
		editPanel.add(displayEditedPicLabel, gc);

		this.setPhotoLabel(displayEditedPicLabel);

		gc.gridx = 0;
		gc.gridy = 3;
		photosCategoryTf.setText(photoCateogry);
		editPanel.add(photosCategoryTf, gc);
		gc.gridx = 0;
		gc.gridy = 5;
		photosDateTf.setText(photoDate);
		editPanel.add(photosDateTf, gc);
		gc.gridx = 0;
		gc.gridy = 7;
		photosDescriptionTf.setText(photoDesc);
		editPanel.add(photosDescriptionTf, gc);

		System.out
				.println("-----------------Edit Manipulation----------------------------");
		System.out.println("Edit Category: "
				+ Main.photoCollection.get(keyForEdit).getCategory());
		System.out.println("Edit Date: "
				+ Main.photoCollection.get(keyForEdit).getDate());
		System.out.println("Edit Description: "
				+ Main.photoCollection.get(keyForEdit).getDesc());
		System.out.println("Edit Path: "
				+ Main.photoCollection.get(keyForEdit).getFilePath()
						.getAbsolutePath());

		displayPropertiesEdit(true);
		editPanel.revalidate();
		editPanel.repaint();

	}
/**
 * the image to be replaced is searched, to display a new image
 * @param ePanel
 * @param type
 * @return
 */
	public JPanel removeImg(JPanel ePanel, String type) {

		for (Component c : ePanel.getComponents()) {
			if (c instanceof JLabel) {

				JLabel j = (JLabel) c;

				if (j.getText().equals(type)) {
					System.out.println("This is the image");
					ePanel.remove(j);
				}
			}
		}
		return ePanel;

	}
/**
 * based the title selected in delete page combo box, an action listener is triggered
 * @param listener
 */
	public void delComboBoxActionListener(ActionListener listener) {
		delPhotosCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				delPhotosCombo = (JComboBox) e.getSource();
				Object selected = delPhotosCombo.getSelectedItem();

				displayPropertiesDelete(true);

				if (delPhotosCombo.getSelectedItem() != null) {
					try {
						fetchValuesForDelete();
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

				}
			}

		});
	}
/**
 * image and it's respective value to be deleted is fetched
 * @throws IOException
 */
	public void fetchValuesForDelete() throws IOException {

		String keyForEdit = (String) delPhotosCombo.getSelectedItem();

		String DphotoCateogry = Main.photoCollection.get(keyForEdit)
				.getCategory();
		String DphotoDate = Main.photoCollection.get(keyForEdit).getDate();
		String DphotoDesc = Main.photoCollection.get(keyForEdit).getDesc();
		String DphotoPath = Main.photoCollection.get(keyForEdit).getFilePath()
				.getAbsolutePath();

		ImageIcon icon = new ImageIcon(DphotoPath);
		icon.getImage().flush();
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(200, 200,
				java.awt.Image.SCALE_SMOOTH);

		deletePanel = removeImg(deletePanel, "Image");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 0;
		gbc.gridy = 1;

		displayDelPicLabel = new JLabel();
		displayDelPicLabel.setVerticalTextPosition(JLabel.TOP);
		displayDelPicLabel.setHorizontalTextPosition(JLabel.LEFT);
		displayDelPicLabel.setIcon(new ImageIcon(scaledImg));
		displayDelPicLabel.setText("Image");

		deletePanel.add(displayDelPicLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		photosDelCategoryTf.setText(DphotoCateogry);
		deletePanel.add(photosDelCategoryTf, gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		photosDelDateTf.setText(DphotoDate);
		deletePanel.add(photosDelDateTf, gbc);
		gbc.gridx = 0;
		gbc.gridy = 9;
		photosDelDescriptionTf.setText(DphotoDesc);
		deletePanel.add(photosDelDescriptionTf, gbc);

		deletePanel.revalidate();
		deletePanel.repaint();

		//
		System.out
				.println("-----------------Delete Manipulation----------------------------");
		System.out.println("Delete Category: "
				+ Main.photoCollection.get(keyForEdit).getCategory());
		System.out.println("Delete Date: "
				+ Main.photoCollection.get(keyForEdit).getDate());
		System.out.println("Delete Description: "
				+ Main.photoCollection.get(keyForEdit).getDesc());
		System.out.println("Delete Path: "
				+ Main.photoCollection.get(keyForEdit).getFilePath()
						.getAbsolutePath());

		displayPropertiesDelete(true);

	}
/**
 * save operation for edit images
 */
	public void saveEditedPhotos() {

		String keyForEdit = (String) photosCombo.getSelectedItem();
		File imgPath = new File(Main.photoCollection.get(keyForEdit)
				.getFilePath().toString());
		String imgFile = imgPath.toString();

		photo = new Photo(keyForEdit, photosCategoryTf.getText(), imgPath,
				photosDateTf.getText(), photosDescriptionTf.getText());

		if (Main.photoCollection.containsKey(keyForEdit)) {
			Main.photoCollection.replace(keyForEdit, photo);
			JOptionPane.showMessageDialog(null, "Photo saved");
		} else {
			System.out.println("No Values to replace");
		}

		photoGalleryPanel.displayEditedImage(imgFile);

		for (Map.Entry<String, Photo> entry : Main.photoCollection.entrySet()) {
			String key = entry.getKey();
			Photo pic = entry.getValue();
			System.out.println("---------After Edit-----------");
			System.out.println("Title:" + pic.getTitle());
			System.out.println("Category:" + pic.getCategory());
			System.out.println("FilePath:" + pic.getFilePath().toString());
			System.out.println("Date:" + pic.getDate());
			System.out.println("Description: " + pic.getDesc());

		}
	}
/**
 * delete execution for deleted images
 */
	public void saveDeletedPhotos() {

		String keyForDel = (String) delPhotosCombo.getSelectedItem();
		File imgPath = new File(Main.photoCollection.get(keyForDel)
				.getFilePath().toString());

		photo = new Photo(keyForDel, photosDelCategoryTf.getText(), imgPath,
				photosDelDateTf.getText(), photosDelDescriptionTf.getText());

		if (Main.photoCollection.containsKey(keyForDel)) {
			Main.photoCollection.remove(keyForDel);
		} else {
			System.out.println("No Values to delete");
		}

		if (!Main.photoCollection.containsKey(keyForDel)) {
			JOptionPane.showMessageDialog(null, "Photo deleted");
		}

		for (Map.Entry<String, Photo> entry : Main.photoCollection.entrySet()) {
			String key = entry.getKey();
			Photo pic = entry.getValue();

			System.out.println("---------After Delete-----------");
			System.out.println("Title:" + pic.getTitle());
			System.out.println("Category:" + pic.getCategory());
			System.out.println("FilePath:" + pic.getFilePath().toString());
			System.out.println("Date:" + pic.getDate());
			System.out.println("Description: " + pic.getDesc());
		}

	}
/**
 * category for view by title method
 * @return
 */
	public String fetchCategory() {
		viewCategoryCombo.removeAllItems();
		String selectedCategory = viewCategoryCombo.getSelectedItem()
				.toString();

		return selectedCategory;

	}
/**
 * the method below shows images based on category
 * @param selectedCateogry
 * @return
 */
	public String showPhotosByCategory(String selectedCateogry) {
		System.out.println("show photos by category");
		for (Map.Entry<String, Photo> entry : Main.photoCollection.entrySet()) {
			String key = entry.getKey();
			Photo photo = entry.getValue();
			String category = photo.getCategory();

			if (category.equals(selectedCateogry)) {
				viewImgPath = Main.photoCollection.get(key).getFilePath()
						.getAbsolutePath();

			}
		}
		return viewImgPath;
	}

}

