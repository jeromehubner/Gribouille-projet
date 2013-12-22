package vue;

import javax.swing.JCheckBoxMenuItem;
import java.awt.Color;

import controleur.EcouteurCouleur;

public class ItemMenuCouleur extends JCheckBoxMenuItem {
    private Color couleur;
    public ItemMenuCouleur(String libellé,
                           EcouteurCouleur ecouteur,
                           Color couleur) {
        super(libellé);
        addActionListener(ecouteur);
        this.couleur = couleur;
    }
    public Color couleur() {
        return couleur;
    }
}
