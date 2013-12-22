package vue;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

import controleur.EcouteurSouris;
import modele.Dessin;
import modele.Figure;

public class ZoneDeDessin extends JPanel {
    private Color couleur;
    private Dessin dessin;
    private EcouteurSouris ecouteurActuel;
    
    public ZoneDeDessin(Dessin dessin, EcouteurSouris ecouteur) {
        couleur = Color.black;
        this.dessin = dessin;
        addMouseListener(ecouteur);
        addMouseMotionListener(ecouteur);
        ecouteurActuel = ecouteur;
    }
    
    public void selectionneOutil(EcouteurSouris ecouteurNouveau) {
        removeMouseListener(ecouteurActuel);
        removeMouseMotionListener(ecouteurActuel);
        addMouseListener(ecouteurNouveau);
        addMouseMotionListener(ecouteurNouveau);
        ecouteurActuel = ecouteurNouveau;
    }
    
    public void selectionneCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    public Color couleur() {
        return couleur;
    }
    
    public void dessineTrait(int x1, int y1, int x2, int y2) {
        Graphics g = getGraphics();
        g.setColor(couleur);
        g.drawLine(x1, y1, x2, y2);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        for(Figure f:dessin) {
            f.dessineDans(g);
        }
    }
}
