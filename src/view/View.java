package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

import model.Human;
import model.HumanList;
import view.component.Editable;
import view.component.JFileImage;
import view.component.JHintTextField;
import view.component.JTextAreaLabel;
import view.component.JTextFieldLabel;
import view.component.TransparentListCellRenderer;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private String resourceFolder;
	private String savesFolder;

	protected JFileImage background;
	protected JList<String> mainList;
	protected JHintTextField searchField;
	protected JScrollPane scrollPane;
	protected JButton buttonNewContact;
	protected JButton buttonEditContact;
	protected JButton buttonFinishContact;
	protected JButton buttonDeleteContact;
	protected JButton buttonEditPicture;
	protected JLabel labelIndexBirthday;
	protected JLabel labelIndexPhone;
	protected JLabel labelIndexMail;
	protected JLabel labelIndexHomepage;
	protected JLabel labelIndexStreet;
	protected JLabel labelIndexCity;
	protected JLabel labelIndexCountry;
	protected JLabel labelIndexNotes;
	protected JFileImage imageContactImage;
	protected JTextFieldLabel labelFirstName;
	protected JTextFieldLabel labelLastName;
	protected JTextFieldLabel labelNickname;
	protected JTextFieldLabel labelBirthday;
	protected JTextFieldLabel labelPhone;
	protected JTextFieldLabel labelMail;
	protected JTextFieldLabel labelHomepage;
	protected JTextFieldLabel labelStreet;
	protected JTextFieldLabel labelCity;
	protected JTextFieldLabel labelCountry;
	protected JTextAreaLabel labelNotes;
	protected JLabel labelId;

	private ComponentRegistry registry = new ComponentRegistry();
	private boolean inEditMode = false;
	private Font standardFont;

	public View(String dataFolder) {
		super("AdressBook");
		this.resourceFolder = dataFolder + "resources/";
		this.savesFolder = dataFolder + "saves/";

		try {
			standardFont = Font.createFont(0, new File(resourceFolder + "font.ttf")).deriveFont(Font.ITALIC, 14);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setBounds(100, 100, 1026, 802);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		initializeJComponents();

		this.setVisible(true);
	}

	private void initializeJComponents() {
		mainList = new JList<String>();
		mainList.setBounds(90, 120, 360, 530);
		mainList.setFont(standardFont);
		mainList.setDragEnabled(false);
		mainList.setOpaque(false);
		mainList.setFixedCellWidth(180);
		mainList.setFixedCellHeight(20);
		mainList.setLayoutOrientation(JList.VERTICAL);
		mainList.setCellRenderer(new TransparentListCellRenderer());
		mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mainList.setSelectionForeground(new Color(0, 0, 0));
		mainList.setSelectionBackground(new Color(200, 200, 200));

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(mainList);
		scrollPane.setBounds(50, 60, 440, 655);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		registry.add(scrollPane, Tag.COMPONENT);

		imageContactImage = new JFileImage(savesFolder);
		imageContactImage.setBounds(760, 98, 161, 123);
		registry.add(imageContactImage, Tag.COMPONENT);

		buttonNewContact = new JButton();
		buttonNewContact.setBounds(440, 725, 32, 32);
		buttonNewContact.setOpaque(false);
		buttonNewContact.setBorder(BorderFactory.createEmptyBorder());
		buttonNewContact.setIcon(new ImageIcon(new File(resourceFolder + "add.png").getAbsolutePath()));
		buttonNewContact.setPressedIcon(new ImageIcon(new File(resourceFolder + "add_pressed.png").getAbsolutePath()));
		registry.add(buttonNewContact, Tag.COMPONENT, Tag.NONEDITMODEONLY);

		buttonEditContact = new JButton();
		buttonEditContact.setBounds(555, 725, 32, 32);
		buttonEditContact.setOpaque(false);
		buttonEditContact.setBorder(BorderFactory.createEmptyBorder());
		buttonEditContact.setIcon(new ImageIcon(new File(resourceFolder + "edit.png").getAbsolutePath()));
		buttonEditContact.setPressedIcon(new ImageIcon(new File(resourceFolder + "edit_pressed.png").getAbsolutePath()));
		registry.add(buttonEditContact, Tag.COMPONENT, Tag.NONEDITMODEONLY);

		buttonFinishContact = new JButton();
		buttonFinishContact.setBounds(555, 725, 32, 32);
		buttonFinishContact.setOpaque(false);
		buttonFinishContact.setBorder(BorderFactory.createEmptyBorder());
		buttonFinishContact.setIcon(new ImageIcon(new File(resourceFolder + "finish.png").getAbsolutePath()));
		buttonFinishContact.setPressedIcon(new ImageIcon(new File(resourceFolder + "finish_pressed.png").getAbsolutePath()));
		buttonFinishContact.setVisible(false);
		registry.add(buttonFinishContact, Tag.COMPONENT, Tag.EDITMODEONLY);

		buttonDeleteContact = new JButton();
		buttonDeleteContact.setBounds(605, 725, 32, 32);
		buttonDeleteContact.setOpaque(false);
		buttonDeleteContact.setBorder(BorderFactory.createEmptyBorder());
		buttonDeleteContact.setIcon(new ImageIcon(new File(resourceFolder + "delete.png").getAbsolutePath()));
		buttonDeleteContact
				.setPressedIcon(new ImageIcon(new File(resourceFolder + "delete_pressed.png").getAbsolutePath()));
		buttonDeleteContact.setVisible(false);
		registry.add(buttonDeleteContact, Tag.COMPONENT, Tag.EDITMODEONLY);

		buttonEditPicture = new JButton();
		buttonEditPicture.setBounds(760, 98, 161, 123);
		buttonEditPicture.setOpaque(false);
		buttonEditPicture.setBorder(BorderFactory.createEmptyBorder());
		buttonEditPicture.setVisible(false);
		registry.add(buttonEditPicture, Tag.COMPONENT, Tag.EDITMODEONLY);

		searchField = new JHintTextField("Search");
		searchField.setBounds(103, 720, 320, 26);
		searchField.setBorder(BorderFactory.createEmptyBorder());
		searchField.setOpaque(false);
		searchField.setFont(standardFont);
		registry.add(searchField, Tag.COMPONENT);

		labelFirstName = new JTextFieldLabel();
		labelFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		labelFirstName.setFont(standardFont.deriveFont(25f));
		labelFirstName.setBounds(550, 125, 190, 32);
		registry.add(labelFirstName, Tag.COMPONENT, Tag.EDITABLE);

		labelLastName = new JTextFieldLabel();
		labelLastName.setHorizontalAlignment(SwingConstants.CENTER);
		labelLastName.setFont(standardFont.deriveFont(25f));
		labelLastName.setBounds(550, 165, 190, 32);
		registry.add(labelLastName, Tag.COMPONENT, Tag.EDITABLE);

		labelNickname = new JTextFieldLabel();
		labelNickname.setHorizontalAlignment(SwingConstants.CENTER);
		labelNickname.setFont(standardFont.deriveFont(16f));
		labelNickname.setBounds(585, 239, 120, 24);
		registry.add(labelNickname, Tag.COMPONENT, Tag.EDITABLE);

		labelIndexBirthday = new JLabel("Birthday");
		labelIndexBirthday.setFont(standardFont);
		labelIndexBirthday.setBounds(570, 301, 250, 20);
		registry.add(labelIndexBirthday, Tag.COMPONENT, Tag.NONEMPTYPANELONLY);

		labelBirthday = new JTextFieldLabel();
		labelBirthday.setFont(standardFont);
		labelBirthday.setBounds(700, 301, 250, 20);
		registry.add(labelBirthday, Tag.COMPONENT, Tag.EDITABLE);

		labelIndexPhone = new JLabel("Phone");
		labelIndexPhone.setFont(standardFont);
		labelIndexPhone.setBounds(570, 341, 250, 20);
		registry.add(labelIndexPhone, Tag.COMPONENT, Tag.NONEMPTYPANELONLY);

		labelPhone = new JTextFieldLabel();
		labelPhone.setFont(standardFont);
		labelPhone.setBounds(700, 341, 250, 20);
		registry.add(labelPhone, Tag.COMPONENT, Tag.EDITABLE);

		labelIndexMail = new JLabel("Mail");
		labelIndexMail.setFont(standardFont);
		labelIndexMail.setBounds(570, 381, 250, 20);
		registry.add(labelIndexMail, Tag.COMPONENT, Tag.NONEMPTYPANELONLY);

		labelMail = new JTextFieldLabel();
		labelMail.setFont(standardFont);
		labelMail.setBounds(700, 381, 250, 20);
		registry.add(labelMail, Tag.COMPONENT, Tag.EDITABLE);

		labelIndexHomepage = new JLabel("Home Page");
		labelIndexHomepage.setFont(standardFont);
		labelIndexHomepage.setBounds(570, 421, 250, 20);
		registry.add(labelIndexHomepage, Tag.COMPONENT, Tag.NONEMPTYPANELONLY);

		labelHomepage = new JTextFieldLabel();
		labelHomepage.setFont(standardFont);
		labelHomepage.setBounds(700, 421, 250, 20);
		registry.add(labelHomepage, Tag.COMPONENT, Tag.EDITABLE);

		labelIndexStreet = new JLabel("Street");
		labelIndexStreet.setFont(standardFont);
		labelIndexStreet.setBounds(570, 481, 250, 20);
		registry.add(labelIndexStreet, Tag.COMPONENT, Tag.NONEMPTYPANELONLY);

		labelStreet = new JTextFieldLabel();
		labelStreet.setFont(standardFont);
		labelStreet.setBounds(700, 481, 250, 20);
		registry.add(labelStreet, Tag.COMPONENT, Tag.EDITABLE);

		labelIndexCity = new JLabel("City");
		labelIndexCity.setFont(standardFont);
		labelIndexCity.setBounds(570, 521, 250, 20);
		registry.add(labelIndexCity, Tag.COMPONENT, Tag.NONEMPTYPANELONLY);

		labelCity = new JTextFieldLabel();
		labelCity.setFont(standardFont);
		labelCity.setBounds(700, 521, 250, 20);
		registry.add(labelCity, Tag.COMPONENT, Tag.EDITABLE);

		labelIndexCountry = new JLabel("Country");
		labelIndexCountry.setFont(standardFont);
		labelIndexCountry.setBounds(570, 561, 250, 20);
		registry.add(labelIndexCountry, Tag.COMPONENT, Tag.NONEMPTYPANELONLY);

		labelCountry = new JTextFieldLabel();
		labelCountry.setFont(standardFont);
		labelCountry.setBounds(700, 561, 250, 20);
		registry.add(labelCountry, Tag.COMPONENT, Tag.EDITABLE);

		labelIndexNotes = new JLabel("Notes");
		labelIndexNotes.setFont(standardFont);
		labelIndexNotes.setBounds(570, 621, 250, 20);
		registry.add(labelIndexNotes, Tag.COMPONENT, Tag.NONEMPTYPANELONLY);

		labelNotes = new JTextAreaLabel();
		labelNotes.setFont(standardFont);
		labelNotes.setBounds(700, 621, 250, 120);
		labelNotes.setLineWrap(true);
		labelNotes.setWrapStyleWord(true);
		registry.add(labelNotes, Tag.COMPONENT, Tag.EDITABLE);

		labelId = new JLabel();
		labelId.setFont(standardFont.deriveFont(8f));
		labelId.setBounds(570, 50, 405, 10);
		labelId.setHorizontalAlignment(SwingConstants.RIGHT);
		registry.add(labelId, Tag.COMPONENT, Tag.NONEMPTYPANELONLY);

		background = new JFileImage(resourceFolder);
		background.setBounds(0, 0, this.getWidth(), this.getHeight());
		registry.add(background, Tag.COMPONENT);

		registry.getTagged(Tag.COMPONENT).forEach((comp) -> this.getContentPane().add(comp));

		setPanelEditMode(false);
	}

	public void addButtonNewContactListener(ActionListener listener) {
		buttonNewContact.addActionListener(listener);
	}

	public void addButtonEditContactListener(ActionListener listener) {
		buttonEditContact.addActionListener(listener);
	}

	public void addButtonFinishContactListener(ActionListener listener) {
		buttonFinishContact.addActionListener(listener);
	}

	public void addButtonDeleteContactListener(ActionListener listener) {
		buttonDeleteContact.addActionListener(listener);
	}

	public void addButtonEditPictureListener(ActionListener listener) {
		buttonEditPicture.addActionListener(listener);
	}

	public void addSearchFieldListener(DocumentListener listener) {
		searchField.getDocument().addDocumentListener(listener);
	}

	public void addListSelectonChangedListener(ListSelectionListener listener) {
		mainList.addListSelectionListener(listener);
	}

	public void setPanelEditMode(boolean inEditMode) {
		this.inEditMode = inEditMode;

		registry.getTagged(Tag.EDITABLE).forEach((comp) -> ((Editable) comp).setEditable(inEditMode));
		registry.getTagged(Tag.EDITMODEONLY).forEach((comp) -> ((JComponent) comp).setVisible(inEditMode));
		registry.getTagged(Tag.NONEDITMODEONLY).forEach((comp) -> ((JComponent) comp).setVisible(!inEditMode));

		if (inEditMode || imageContactImage.hasImage()) {
			background.setImage("background_photo.png", false);
		} else {
			background.setImage("background.png", false);
		}
	}

	public boolean isInEditMode() {
		return inEditMode;
	}

	public String getSearchTerm() {
		return searchField.getText();
	}

	public int getSelectedIndex() {
		return mainList.getSelectedIndex();
	}

	public boolean selectAndSetContactImage(){
		return imageContactImage.selectAndSetImage();
	}
	
	public void displayEmptyPanel() {
		labelId.setText("");
		labelFirstName.setText("");
		labelLastName.setText("");
		labelNickname.setText("");
		labelBirthday.setText("");
		labelPhone.setText("");
		labelMail.setText("");
		labelHomepage.setText("");
		labelStreet.setText("");
		labelCity.setText("");
		labelCountry.setText("");
		labelNotes.setText("");
		background.setImage("background.png", false);
		imageContactImage.displayNoImage();
		registry.getTagged(Tag.NONEMPTYPANELONLY).forEach((comp) -> comp.setVisible(false));
	}

	public void displayHumanOnPanel(Human h) {
		labelId.setText(h.getId());
		labelFirstName.setText(h.getFirstName());
		labelLastName.setText(h.getLastName());
		labelNickname.setText(h.getNickname());
		labelBirthday.setText(h.getBirthday());
		labelPhone.setText(h.getPhone());
		labelMail.setText(h.getMail());
		labelHomepage.setText(h.getHomepage());
		labelStreet.setText(h.getStreet());
		labelCity.setText(h.getCity());
		labelCountry.setText(h.getCountry());
		labelNotes.setText(h.getNotes());

		if (h.getImageFilename().equals("")) {
			background.setImage("background.png", false);
			imageContactImage.displayNoImage();
		} else {
			background.setImage("background_photo.png", false);
			imageContactImage.setImage(h.getImageFilename(), true);
		}

		registry.getTagged(Tag.NONEMPTYPANELONLY).forEach((comp) -> comp.setVisible(true));
	}

	public Human constructHumanFromPanel() {
		return new Human(labelId.getText(), labelFirstName.getText(), labelLastName.getText(), labelNickname.getText(),
				labelBirthday.getText(), labelPhone.getText(), labelMail.getText(), labelHomepage.getText(),
				labelStreet.getText(), labelCity.getText(), labelCountry.getText(), labelNotes.getText(),
				imageContactImage.hasImage() ? imageContactImage.getFilename() : "");
	}

	public void displayHumanList(HumanList list) {
		String[] names = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			names[i] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
		}
		mainList.setListData(names);
	}

}
