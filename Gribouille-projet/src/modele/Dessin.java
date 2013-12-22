package modele;

import java.util.List;
import java.util.LinkedList;
import java.lang.Iterable;
import java.util.Iterator;
import java.awt.Color;
import java.io.*;
import java.io.Serializable;

public class Dessin implements Iterable<Figure>, Serializable {
	public List<Figure> figures;
	private Figure courante;
	private boolean modifié;
	
	public Dessin() {
		figures = new LinkedList<Figure>();
		modifié = false;		
	}

	public Iterator<Figure> iterator() {
		return figures.iterator();
	}
	
	public boolean vide() {
		return figures.isEmpty();
	}
	
	public void ajouterTrace(Color couleur, int X, int Y) {
		courante = new Trace(couleur, X, Y);
		figures.add(courante);
		modifié = true;
	}
	
	public void ajouter(int x, int y) {
		courante.ajouter(x, y);
		modifié = true;
	}
	
	public void effacer() {
		for(Figure f:figures) {
			f.effacer();
		}
		figures.clear();
		modifié = false;
	}
	
	public boolean modifié() {
		return modifié;
	}
	
	public void enregistre(File f) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(figures);
		oos.close();
		modifié = false;
	}
	
	public void charge(File f) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		figures = (List<Figure>)ois.readObject();
		ois.close();
		modifié = false;
		courante = null;
	}
	
	public void enregistre(DataOutputStream fichier) throws Exception {
		fichier.writeInt(figures.size());
		for(Figure fig:figures) {
			fig.enregistre(fichier);
		}
		modifié = false;
	}
	
	public void charge(DataInputStream fichier) throws Exception {
		int nbFig = fichier.readInt();
		for(int i=0; i<nbFig; i++) {
			byte type = fichier.readByte();
			Figure fig;
			switch(type) {
			case  1: fig = new Trace(); break;
			default: fig = null;
			}
			fig.charge(fichier);
			figures.add(fig);
		}
		modifié = false;
		courante = null;
	}
}