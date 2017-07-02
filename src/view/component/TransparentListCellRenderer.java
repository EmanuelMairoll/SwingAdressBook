package view.component;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class TransparentListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	public TransparentListCellRenderer() {
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		setOpaque(isSelected);
		return this;
	}

}