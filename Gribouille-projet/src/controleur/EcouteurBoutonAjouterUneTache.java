package controleur;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.Fenetre;
import vue.ZoneDeDessin;
import vue.ZoneListeDeTaches;
import modele.Dessin;

/**
 * Cette classe écoute le JButton Ajouter une tâche de la fenêtre
 * @author Jerome
 * @version 18/12/2013
 *
 */
public class EcouteurBoutonAjouterUneTache implements ActionListener {
	private Fenetre fenetre;
	private ZoneListeDeTaches zoneListeDeTaches;
	
	public EcouteurBoutonAjouterUneTache(Fenetre fenetre, ZoneListeDeTaches zoneListeDeTaches) {
		this.fenetre = fenetre;
		this.zoneListeDeTaches = zoneListeDeTaches;
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		zoneListeDeTaches.ajouterUneTache();
	}
}