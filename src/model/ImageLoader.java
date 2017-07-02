package model;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import view.component.JFileImage;

public class ImageLoader {

	final JFileImage fileImage;
	final String resourceFolder;

	public ImageLoader(JFileImage fileImage, String resourceFolder) {
		this.fileImage = fileImage;
		this.resourceFolder = resourceFolder;
	}

	public Image loadImage(String filename){
		return new ImageIcon(this.resourceFolder + filename).getImage();
	}

	public String copyToResources(String absolutePath) {
		Image image = new ImageIcon(absolutePath).getImage();
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();

		try {
			String filename = UUID.randomUUID().toString();
			File file = new File(this.resourceFolder + filename);
			file.getParentFile().mkdirs();
			ImageIO.write(bi, "jpg", file);
			return filename;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void remove(String filename) {
		new File(this.resourceFolder + filename).delete();
	}
	
	public boolean exists(String filename) {
		return new File(this.resourceFolder + filename).exists();
	}

}
