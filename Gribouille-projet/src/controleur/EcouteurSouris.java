package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vue.Fenetre;
//import vue.BarreDEtat;
import vue.ZoneDeDessin;
import modele.Dessin;

public class EcouteurSouris extends MouseAdapter {
    protected Fenetre fenetre;
    protected ZoneDeDessin zoneDeDessin;
    protected Dessin dessin;
    protected int x1, y1;
    public EcouteurSouris(Fenetre fenetre,
                          Dessin dessin) {
        this.fenetre = fenetre;
        this.dessin = dessin;
    }
    public void mouseMoved(MouseEvent e) {
        fenetre.afficheCoordonnées(e.getX(), e.getY());
    }
    public void mouseDragged(MouseEvent e) {
        fenetre.afficheCoordonnées(e.getX(), e.getY());
    }
}