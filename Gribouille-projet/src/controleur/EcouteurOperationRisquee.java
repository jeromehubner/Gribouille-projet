package controleur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;

import vue.Fenetre;
import modele.Dessin;

public class EcouteurOperationRisquee
//pour la gestion de la case de fermeture de la fenêtre de l'application
					extends WindowAdapter
//pour les options de menu déclenchant des opérations qui font perdre les données
					implements ActionListener {
	
    protected Fenetre fenetre;
    protected Dessin dessin;
    protected boolean abandon;
    protected String nomOpération;
    public EcouteurOperationRisquee(Fenetre fenetre,
                                    Dessin dessin,
                                    String nomOpération) {
        this.fenetre = fenetre;
        this.dessin = dessin;
        this.nomOpération = nomOpération;
    }
    public void actionPerformed(ActionEvent e) { //remarque : on ne sert pas de l'objet e
        abandon = false;
        if (dessin.modifié()) {
            if (fenetre.abandonAprèsEnregistrementEventuel(nomOpération))
                abandon = true;
        }
    }
}