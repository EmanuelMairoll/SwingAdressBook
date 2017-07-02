package controller;

import javax.swing.JOptionPane;

import model.Human;
import model.HumanList;
import view.View;

public class Controller {
	private static final String dataFolder = "data/";
	private static final String contactsFile = dataFolder + "saves/contacts.xml";

	private View view;
	private HumanList humanList;

	public Controller() {
		humanList = HumanList.loadFromFile(contactsFile);
		view = new View(dataFolder);
		view.displayHumanList(getCurrentFilteredList());
		view.displayEmptyPanel();

		Listeners.setup(this, view);
	}

	public void handleNewContact() {
		if (view.isInEditMode()) {
			handleFinishContact();
		}

		view.displayHumanOnPanel(new Human());
		view.setPanelEditMode(true);
	}

	public void handleEditContact() {
		view.setPanelEditMode(true);
	}

	public void handleFinishContact() {
		Human panelHuman = view.constructHumanFromPanel();
		if (panelHuman.isEmpty()) {
			view.displayEmptyPanel();
		} else {
			Human listHuman = humanList.getById(panelHuman.getId());
			if (listHuman == null) {
				humanList.add(panelHuman);
			} else {
				humanList.remove(listHuman);
				humanList.add(panelHuman);
			}

			humanList.sort((h1, h2) -> {
				return (h1.getLastName() + h1.getFirstName()).compareTo(h2.getLastName() + h2.getFirstName());
			});

			view.displayHumanList(getCurrentFilteredList());
			humanList.saveToFile(contactsFile);
		}

		view.setPanelEditMode(false);

	}

	public void handleDeleteContact() {
		Human panelHuman = view.constructHumanFromPanel();
		Human listHuman = humanList.getById(panelHuman.getId());
		if (listHuman != null) {
			int retVal = JOptionPane.showOptionDialog(view.getContentPane(),
					"Do you really want to delete this Contact?" + System.lineSeparator()
							+ "It will be lost forever (a very very long time)",
					"Delete Contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

			if (retVal == JOptionPane.OK_OPTION) {
				humanList.remove(listHuman);
				humanList.saveToFile(contactsFile);

				view.displayEmptyPanel();
				view.setPanelEditMode(false);
				view.displayHumanList(getCurrentFilteredList());
			}
		} else {
			view.displayEmptyPanel();
			view.setPanelEditMode(false);
			view.displayHumanList(getCurrentFilteredList());
		}
	}

	public void handleEditPicture() {
		view.selectAndSetContactImage();
	}

	public void handleListSelection() {
		int index = view.getSelectedIndex();
		if (index == -1) {
			view.displayEmptyPanel();
		} else {
			view.displayHumanOnPanel(getCurrentFilteredList().get(index));
		}
	}

	public void handleSearchFieldChanged() {
		view.displayHumanList(getCurrentFilteredList());
	}

	private HumanList getCurrentFilteredList() {
		String searchTerm = view.getSearchTerm();
		if (searchTerm != null && !searchTerm.equals("") && !searchTerm.equals("Search")) {
			HumanList filtered = new HumanList();
			humanList.forEach((human) -> {
				if (human.toString().toLowerCase().contains(searchTerm.toLowerCase())) {
					filtered.add(human);
				}
			});
			return filtered;
		} else {
			return humanList;
		}
	}

}
