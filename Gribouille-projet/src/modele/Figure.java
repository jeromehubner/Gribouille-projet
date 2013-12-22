package modele;

import java.util.List;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.lang.Iterable;
import java.util.Iterator;
import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
//import java.io.IOException;

public abstract class Figure implements Iterable, Serializable {
    protected List<Point> points;
    private Color couleur;
    public Figure() {
        points = new LinkedList<Point>();
        couleur = null;
    }
    public Figure(Color couleur) {
        points = new LinkedList<Point>();
        this.couleur = couleur;
    }
    public Iterator<Point> iterator() {
        return points.iterator();
    }
    public void ajouter(int x, int y) {
        points.add(new Point(x, y));
    }
    public void effacer() {
        points.clear();
    }
    public Color couleur() {
        return couleur;
    }
    public void changeCouleur(Color nouvelleCouleur) {
        couleur = nouvelleCouleur;
    }
    
    public abstract void dessineDans(Graphics g);
    public abstract void enregistre(DataOutputStream fichier) throws Exception;
    public abstract void charge(DataInputStream fichier) throws Exception;
}
