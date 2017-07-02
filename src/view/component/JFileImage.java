package view.component;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ImageLoader;

public class JFileImage extends JComponent {

	private static final long serialVersionUID = 1L;
	private final ImageLoader loader;
	private Image image;
	private String filename;

	public JFileImage(String resourcePath) {
		loader = new ImageLoader(this, resourcePath);
	}

	public void setImage(String filename, boolean fit) {
		if (filename.equals(this.filename)) {
			return;
		}

		image = loader.loadImage(filename);
		this.filename = filename;

		image = fit ? resizeImage(image, this.getWidth() * 2, this.getHeight() * 2) : image;
		repaint();
	}

	public boolean selectAndSetImage() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG & GIF Bilder", "jpg", "gif", "png");
		fc.setFileFilter(filter);

		if (fc.showOpenDialog(this.getParent()) == JFileChooser.APPROVE_OPTION) {
			if (loader.exists(filename)) {
				loader.remove(filename);
			}

			String path = loader.copyToResources(fc.getSelectedFile().getAbsolutePath());
			setImage(path, true);
			return true;
		} else {
			return false;
		}
	}

	public void removeImage() {
		displayNoImage();
		loader.remove(filename);
	}

	public void displayNoImage() {
		image = null;
		filename = null;
		repaint();
	}

	public boolean hasImage() {
		return image != null;
	}

	public String getFilename() {
		return filename;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (hasImage()) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.scale(0.5, 0.5);
			g2d.drawImage(image, 0, 0, this);
		}
	}

	private static Image resizeImage(Image oldImage, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(oldImage, 0, 0, width, height, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		return new ImageIcon(resizedImage).getImage();
	}
}