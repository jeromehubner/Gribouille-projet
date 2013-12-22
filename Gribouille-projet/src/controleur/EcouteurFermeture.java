package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import vue.Fenetre;
import modele.Dessin;

public class EcouteurFermeture extends EcouteurOpérationRisquée {
    public EcouteurFermeture(Fenetre fenetre, Dessin dessin) {
        super(fenetre, dessin, "Fermerture de l'application Gribouille");
    }
    private void quitter() {
        super.actionPerformed(null); //on ne se sert pas de l'objet évènement dans la classe de base
        if (!abandon) {
            System.exit(0);
        }
    }
    public void actionPerformed(ActionEvent e) {
        quitter();
    }
    public void windowClosing(WindowEvent e) {
        quitter();
    }
}
