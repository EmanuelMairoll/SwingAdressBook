package view.component;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

	public class JTextAreaLabel extends JTextArea implements SwingConstants, Editable {
		private static final long serialVersionUID = 1L;

		public JTextAreaLabel() {
			this("", false);
		}

		public JTextAreaLabel(boolean editMode) {
			this("", editMode);
		}

		public JTextAreaLabel(String title) {
			this(title, false);
		}

		public JTextAreaLabel(String title, boolean editMode) {
			super(title);

			this.setBorder(BorderFactory.createEmptyBorder());
			this.setEditable(editMode);
		}

		public void toggleEditable() {
			this.setEditable(!this.isEditable());
		}

		@Override
		public void setEditable(boolean b) {
			super.setEditable(b);
			if (b) {
				this.setForeground(new Color(100, 100, 100));
			} else {
				this.setForeground(new Color(0, 0, 0));
			}

			super.setOpaque(b);
		}

	}