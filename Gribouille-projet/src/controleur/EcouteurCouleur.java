package controleur;

import java.awt.event.ActionEvent;

import vue.Fenetre;
import vue.ItemMenuCouleur;

public class EcouteurCouleur extends EcouteurMenu {
    public EcouteurCouleur(Fenetre fenetre) { super(fenetre, 3); }
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        fenetre.selectionneCouleur(((ItemMenuCouleur)actionné).couleur(),
                                   actionné.getText());
    }
}
