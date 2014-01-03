package vue;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

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
    
//    /**
//     * Cette méthode permet de transférer le contenu de la zone de dessin dans une image
//     * 
//     * @return Image L'image du contenu de la {@link ZoneDeDessin}
//     */
//    public Image getImage() {
//    	BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
//    	Graphics2D g = image.createGraphics();
//    	paintAll(g);
//    	g.dispose();
//    	return image;
//	}

    /**
     * Convertit les dimensions du dessin (à l'aide de facteur multiplicateur).
     * 
     * @param Dimension Les dimensions du {@link Dessin} à convertir
     * @return dessin Le dessin aux dimensions converties.
     */
    public Dessin ModifierDimensionDessin(Dimension dimension){
    	Dessin dessin = new Dessin();
    	//TODO : Compléter cette méthode
    	setPreferredSize(dimension);
    	return dessin;
    }
    
	public Dessin getDessin() {
		return dessin;
	}
    
}