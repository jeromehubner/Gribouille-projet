package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Dessin;
import controleur.EcouteurBoutonAjouterUneTache;
import controleur.EcouteurSouris;

/**
 * La classe ZoneListeDeTaches permet l'affichage de dessin sous forme d'une liste 
 * à droite de la {@link ZoneDeDessin}
 * 
 * @author Jerome
 * @version 30/12/2013
 */
public class ZoneListeDeTaches extends JPanel {
	private JButton boutonSauvegarder;
	private Fenetre fenetre;
	private Dimension dimension;
	
	public ZoneListeDeTaches(Fenetre fenetre, Dimension dimension) {
		this.fenetre = fenetre;
		this.dimension = dimension;
		
		/*--- Affichage des 7 premières tâches ---*/
		setLayout(new BorderLayout());
		setPreferredSize(dimension);
		
		boutonSauvegarder = new JButton("Ajouter une tâche");
		
		add(boutonSauvegarder, BorderLayout.NORTH);
		
		boutonSauvegarder.addActionListener(new EcouteurBoutonAjouterUneTache(fenetre, this));
	}
	
    public void ajouterUneTache(){
    	Dessin dessin = fenetre.getZoneDeDessin().getDessin();
    	
    	
    }
}