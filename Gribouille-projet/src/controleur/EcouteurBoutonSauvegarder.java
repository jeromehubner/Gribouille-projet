package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Dessin;

/**
 * Cette classe écoute le JButton Sauvegarder de la fenêtre
 * @author Jerome
 * @version 18/12/2013
 *
 */
public class EcouteurBoutonSauvegarder implements ActionListener {

	private Dessin dessin;
	
	
	public EcouteurBoutonSauvegarder(Dessin dessin) {
		this.dessin = dessin;
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	
}
