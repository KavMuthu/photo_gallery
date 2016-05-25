package muthu.assign3;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import muthu.assign3.entities.Photo;
import muthu.assign3.ui.ManipulationPanel;
import muthu.assign3.ui.PhotoGalleryPanel;
import muthu.assign3.ui.PhotoPropertiesPanel;

public class Main extends JFrame {
	private ManipulationPanel manipulationPanel;
	private PhotoPropertiesPanel photoPropertiesPanel;
	public static Map<String, Photo> photoCollection;
	private PhotoGalleryPanel photoGalleryPanel;
	private JFrame mainFrame;
	private JComboBox photosCombo;
	private JLabel photosLabel, picDisplayLabel, viewLabel;
	private CardLayout cardLayout = new CardLayout();
	private JPanel addPanel, editPanel, deletePanel, viewPanel, addPhoto,
			addPhotosCard, editPhotosCard, deletePhotosCard, viewPhotosCard;
	private Photo photo;
	private JFileChooser filechooser;
	private String viewCategory;

	/**
	 * Main class calls the constructor
	 * @param args
	 */
	public static void main(String[] args) {

		new Main();
	}
	/**
	 * Constructor defines the specification for the photo gallery
	 * Manipulations panel
	 * Photo properties panel
	 * Photo gallery panel are added to the main Panel which is then added to the main frame
	 * 
	 */
	public Main() {
		mainFrame = new JFrame();
		JPanel mainPanel = new JPanel();

		mainPanel.setBackground(Color.white);
		mainPanel.setSize(new Dimension(1000,800));
		mainPanel.setLayout(new GridBagLayout());

		manipulationPanel = new ManipulationPanel();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.gridwidth = gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		mainPanel.add(manipulationPanel, gbc);

		photoPropertiesPanel = new PhotoPropertiesPanel();

		gbc.gridy = 1;
		gbc.weightx = gbc.weighty = 98;
		mainPanel.add(photoPropertiesPanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;

		photoGalleryPanel = new PhotoGalleryPanel();
		photoCollection = new LinkedHashMap<String, Photo>();

		mainPanel.add(photoGalleryPanel, gbc);

		mainFrame.setContentPane(mainPanel);
		
		handleActionListeners();					//common method that handles all the action listeners
		mainFrame.pack();
		mainFrame.setTitle("Photo Showcase");
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);

	}

	/**
	 * Action listener for add, view, edit , delete is added here
	 */
	public void handleActionListeners() {
		manipulationPanel.addPhotoActionListener(new ActionListener() {
																	//add button action handler
			@Override
			public void actionPerformed(ActionEvent e) {

				CardLayout cardLayout = (CardLayout) (photoPropertiesPanel
						.getLayout());
				cardLayout.show(photoPropertiesPanel, "1");

				CardLayout galleryCardLayout = (CardLayout) (photoGalleryPanel
						.getLayout());
				galleryCardLayout.show(photoGalleryPanel, "1");
				openFileChooserDialog();
				photoPropertiesPanel.clearTextField();
				photoPropertiesPanel.displayThumbnail(true);
				photoPropertiesPanel.displayPropertiesEdit(false);

			}

		});
		photoPropertiesPanel.savePhotoActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					savePhotoDetails();								//save button on the add page action handler
					photoGalleryPanel.visibilityForGallery(true);
					photoGalleryPanel.visibilityForEditedPicGallery(true);
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
		});
		photoPropertiesPanel.editSavePhotoActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				photoPropertiesPanel.saveEditedPhotos();
				photoGalleryPanel.visibilityForGallery(false);			//save button on the edit page action handler
				photoGalleryPanel.visibilityForEditedPicGallery(true);
				photoPropertiesPanel.displayThumbnail(false);
				photoPropertiesPanel.displayPropertiesEdit(true);
				photoPropertiesPanel.emptyComboBox();

			}
		});
		manipulationPanel.editPhotoActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				showEditPanelCard();									//edit button action handler

			}
		});

		manipulationPanel.deletePhotoActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
																			//delete button action handler
				CardLayout cardLayout = (CardLayout) (photoPropertiesPanel
						.getLayout());
				cardLayout.show(photoPropertiesPanel, "3");
				CardLayout galleryCardLayout = (CardLayout) (photoGalleryPanel
						.getLayout());
				galleryCardLayout.show(photoGalleryPanel, "3");
				photoPropertiesPanel.fetchTitlesForDelete();

			}
		});

		manipulationPanel.viewPhotoActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
																			//view button action handler				
				CardLayout cardLayout = (CardLayout) (photoPropertiesPanel
						.getLayout());
				cardLayout.show(photoPropertiesPanel, "4");
				CardLayout galleryCardLayout = (CardLayout) (photoGalleryPanel
						.getLayout());
				galleryCardLayout.show(photoGalleryPanel, "4");

				photoPropertiesPanel.displayThumbnail(true);

				photoPropertiesPanel.getViewCategoryCombo().removeAllItems();

				Set imgsList = new TreeSet();						
				 
															
				Set<String> keys = Main.photoCollection.keySet();
				Iterator<String> keyIterator = keys.iterator();
				//iterates through the key set and fetches category for the view by category combo box 
				while (keyIterator.hasNext()) {
					
					String catKey = keyIterator.next();

					imgsList.add(Main.photoCollection.get(catKey).getCategory() 
							.toString());
					

				}
				for (Object item : imgsList) {
					photoPropertiesPanel.getViewCategoryCombo().addItem(
							item.toString());
				}
				photoPropertiesPanel
						.viewCatActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								viewImagesByCat();

								photoGalleryPanel.visibilityForGallery(true);
								photoGalleryPanel
										.visibilityForEditedPicGallery(true);
							}

						});

			}
		});

		photoPropertiesPanel
				.deleteSavePhotoActionListener(new ActionListener() {
																		//delete button action listener
					@Override
					public void actionPerformed(ActionEvent e) {

						photoPropertiesPanel.saveDeletedPhotos();
						photoGalleryPanel.visibilityForGallery(false);
						photoGalleryPanel.visibilityForEditedPicGallery(false);
						photoPropertiesPanel.displayThumbnail(false);
						photoPropertiesPanel.displayPropertiesDelete(true);
						photoPropertiesPanel.emptyComboBoxForDelete();

					}
				});
	}

	
	public JPanel removeImages(JPanel ePanel) {

		for (Component c : ePanel.getComponents()) {
			if (c instanceof JLabel) {

				ePanel.remove(c);
			}
		}
		return ePanel;

	}
	/**
	 * the method below displays the images selected based on category on to the photo gallery panel
	 */

	private void viewImagesByCat() {

		String selectedCateogry = "";

		if (photoPropertiesPanel.getViewCategoryCombo().getSelectedItem() != null) {
			//
			//

			removeImages(photoGalleryPanel.getViewPhotosCard());
			viewCategory = photoPropertiesPanel.getViewCategoryCombo()
					.getSelectedItem().toString();
			
			for (Map.Entry<String, Photo> entry : Main.photoCollection
					.entrySet()) {
				String key = entry.getKey();
				Photo photo = entry.getValue();
				String category = photo.getCategory();

			
				if (category.equals(viewCategory)) {

					
					ImageIcon icon1 = new ImageIcon(photo.getFilePath()
							.getAbsolutePath());
					Image img = icon1.getImage();
					Image scaledImg1 = img.getScaledInstance(250, 250,
							java.awt.Image.SCALE_SMOOTH);
					viewLabel = new JLabel();
					viewLabel.setIcon(new ImageIcon(scaledImg1));

					photoGalleryPanel.getViewPhotosCard().add(viewLabel);
					photoGalleryPanel.getViewPhotosCard().revalidate();
					photoGalleryPanel.getViewPhotosCard().repaint();

					manipulationPanel.updateUI();
				}
			}

		}

	}

	public static final String CURRENT_DIRECTORY_LOCATION = ".";

	/**
	 * the following method opens the file dialogbox to select the images.
	 */
	public void openFileChooserDialog() {

		photoGalleryPanel.visibilityForGallery(true);
		filechooser = new JFileChooser();
		filechooser.setDialogTitle("Open File");
		filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		filechooser.setCurrentDirectory(new File(CURRENT_DIRECTORY_LOCATION));

		if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)

		{
			File selectedFile = filechooser.getSelectedFile();

			photoPropertiesPanel.setPicPath(selectedFile.toString());
			photoPropertiesPanel.displayPhoto(selectedFile);
			photoPropertiesPanel.displayThumbnail(true);

		}

	}

	/**
	 * the method below saves the puts the images into the hash map , with the title as the key 
	 * and category, date, image path, description as values(stored in a n object)
	 * @throws IOException
	 */
	public void savePhotoDetails() throws IOException {

		photo = new Photo(photoPropertiesPanel.getTitleText(),
				photoPropertiesPanel.getCategoryText(),
				photoPropertiesPanel.getFileText(),
				photoPropertiesPanel.getDateText(),
				photoPropertiesPanel.getDescriptionText());

		photoCollection.put(photo.getTitle(), photo);
		if(Main.photoCollection.containsKey(photo.getTitle())){
			JOptionPane.showMessageDialog(null, "Photo saved");
		}
		
		String path = filechooser.getSelectedFile().getAbsolutePath();
		
		photoGalleryPanel.displayImage(path);

		for (Map.Entry<String, Photo> entry : photoCollection.entrySet()) {  //iterates through the collection

			String key = entry.getKey();
			Photo pic = entry.getValue();
			File imgFile = pic.getFilePath();

			photoPropertiesPanel.displayThumbnail(false);
			
			System.out.println("--------Values added to the hashmap-------------");
			System.out.println("Title:" + pic.getTitle());
			System.out.println("Category:" + pic.getCategory());
			System.out.println("FilePath:" + pic.getFilePath().toString());
			System.out.println("Date:" + pic.getDate());
			System.out.println("Description: " + pic.getDesc());

		}
	}

	/**
	 * this method is invoke on click of the edit button to save the changes made to the photo.
	 */
	public void showEditPanelCard() {
		CardLayout cardLayout = (CardLayout) (photoPropertiesPanel.getLayout());
		cardLayout.show(photoPropertiesPanel, "2");
		CardLayout galleryCardLayout = (CardLayout) (photoGalleryPanel
				.getLayout());
		galleryCardLayout.show(photoGalleryPanel, "2");

		
		photoPropertiesPanel.displayComboBox(true);
		photoGalleryPanel.visibilityForGallery(true);
		photoPropertiesPanel.fetchTitlesForEdit(photoCollection);
		photoPropertiesPanel.displayThumbnail(true);
		

	}

}

