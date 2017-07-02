package controller;

import javax.swing.JList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.View;

public class Listeners {

	@SuppressWarnings("unchecked")
	public static void setup(Controller controller, View view) {
		view.addButtonNewContactListener((e) -> controller.handleNewContact());
		view.addButtonEditContactListener((e) -> controller.handleEditContact());
		view.addButtonFinishContactListener((e) -> controller.handleFinishContact());
		view.addButtonDeleteContactListener((e) -> controller.handleDeleteContact());
		view.addButtonEditPictureListener((e) -> controller.handleEditPicture());

		view.addSearchFieldListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				controller.handleSearchFieldChanged();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				controller.handleSearchFieldChanged();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				controller.handleSearchFieldChanged();
			}
		});

		view.addListSelectonChangedListener((e) -> {
			if (!e.getValueIsAdjusting() && !((JList<String>) e.getSource()).isSelectionEmpty()) {
				controller.handleListSelection();
			}
		});

	}
}
