package model;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class HumanList extends ArrayList<Human> {
	private static final long serialVersionUID = 1L;

	public static HumanList loadFromFile(String filepath) {
		try {
			File file = new File(filepath);

			if (!file.exists()) {
				return new HumanList();
			}

			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
			HumanList h = (HumanList) decoder.readObject();
			decoder.close();
			return h;
		} catch (Exception e) {
			System.err.println("Fehler beim Einlesen der Datenbank");
			e.printStackTrace();
			return new HumanList();
		}
	}

	public void saveToFile(String filepath) {
		try {
			File file = new File(filepath);

			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
			encoder.writeObject((ArrayList<Human>)this);
			encoder.close();
		} catch (Exception e) {
			System.err.println("Fehler beim Schreiben der Datenbank");
			e.printStackTrace();
		}
	}

	public void removeGhosts() {
		for (int i = this.size() - 1; i >= 0; i--) {
			if (this.get(i).isEmpty()){
				this.remove(i);
			}
		}
	}
	
	public Human getById(String id){
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getId().equals(id)){
				return this.get(i);
			}
		}
		return null;	
	}
}
