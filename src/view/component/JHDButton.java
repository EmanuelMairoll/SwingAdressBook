package view.component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

	public class JHDButton extends JButton {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.scale(0.25, 0.25);
			super.paintComponent(g2d);
		}
	}
