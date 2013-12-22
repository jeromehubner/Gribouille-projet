package controleur;

import java.awt.event.ActionEvent;

import vue.Fenetre;
import modele.Dessin;

public class EcouteurOuverture extends EcouteurOpérationRisquée {
    public EcouteurOuverture(Fenetre fenetre, Dessin dessin) {
        super(fenetre, dessin, "Ouverture d'un dessin");
    }
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (abandon) return;
        fenetre.charge();
    }
}
