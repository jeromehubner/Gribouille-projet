package controleur;

import java.awt.event.ActionEvent;

import vue.Fenetre;
import modele.Dessin;

public class EcouteurEffacement extends EcouteurOperationRisquee {
    public EcouteurEffacement(Fenetre fenetre, Dessin dessin) {
        super(fenetre, dessin, "Effacement du dessin");
    }
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (abandon) return;
        dessin.effacer();
        fenetre.repaint();
    }
}
