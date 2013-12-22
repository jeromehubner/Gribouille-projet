package vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GraphicsDevice;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Figure;

public class ZoneListeDeTaches extends JPanel {
	private JButton boutonHaut;
	private JButton boutonBas;
	private JFrame tache;
	
	public ZoneListeDeTaches() {
		setLayout(new FlowLayout());
		boutonHaut = new JButton("Haut");
		boutonBas = new JButton("Bas");
		add(boutonHaut);
		add(boutonBas);
	}
}