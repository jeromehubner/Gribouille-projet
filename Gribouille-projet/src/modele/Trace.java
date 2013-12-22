package modele;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;
import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
//import java.io.IOException;

public class Trace extends Figure implements Serializable {
    public Trace() {
        super();
    }
    public Trace(Color couleur, int x, int y) {
        super(couleur);
        ajouter(x, y);
    }
    public void dessineDans(Graphics g) {
        int x1, y1, x2, y2;
        Iterator<Point> it = this.iterator();
        Point pt = it.next();
        x1 = (int)(pt.getX());
        y1 = (int)(pt.getY());
        g.setColor(couleur());
        while(it.hasNext()) {
            pt = it.next();
            x2 = (int)(pt.getX());
            y2 = (int)(pt.getY());
            g.drawLine(x1,y1,x2,y2);
            x1 = x2; y1 = y2;
        }
    }
    public void enregistre(DataOutputStream fichier) throws Exception {
        fichier.writeByte(1);
        fichier.writeInt(couleur().getRGB());
        fichier.writeInt(points.size());
        for(Point pt:points) {
            fichier.writeInt((int)(pt.getX()));
            fichier.writeInt((int)(pt.getY()));
        }
    }
    public void charge(DataInputStream fichier) throws Exception {
        changeCouleur(new Color(fichier.readInt()));
        int nbPts = fichier.readInt();
        for(int i=0; i<nbPts; i++) {
            int x = fichier.readInt();
            int y = fichier.readInt();
            points.add(new Point(x, y));
        }
    }
}
