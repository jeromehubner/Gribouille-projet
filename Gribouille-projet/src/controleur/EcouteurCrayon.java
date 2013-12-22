package controleur;

import java.awt.event.MouseEvent;

import vue.Fenetre;
import modele.Dessin;

public class EcouteurCrayon extends EcouteurSouris {
    public EcouteurCrayon(Fenetre fenetre,
                          Dessin dessin) {
        super(fenetre, dessin);
    }
    public void mousePressed(MouseEvent e) {
        dessin.ajouterTrace(fenetre.couleur(),
                            x1 = e.getX(), y1 = e.getY());
    }
    public void mouseDragged(MouseEvent e) {
        int x2, y2;
        super.mouseDragged(e);
        dessin.ajouter(x2 = e.getX(), y2 = e.getY());
        fenetre.dessineTrait(x1, y1, x2, y2);
        x1 = x2; y1 = y2;
    }
}
