package vue;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class APropos extends JDialog {
	private static APropos uniqueInstance = null;

	private APropos() {
		super((Frame)null, "A propos de Mémo", false);
		
		JLabel etiq;
		
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setSize(450,130);
		setResizable(false);
		setLayout(new FlowLayout());

		etiq = new JLabel("Mini-application de dessin", JLabel.CENTER);
		etiq.setFont(new Font("Arial", Font.BOLD, 20));
		add(etiq);
		
		etiq = new JLabel("Une application de Abderrahmane, Florian et Jérôme", JLabel.CENTER);
		etiq.setFont(new Font("Times", Font.PLAIN, 18));
		add(etiq);
		
		etiq = new JLabel("Version du 29 novembre 2013", JLabel.CENTER);
		etiq.setFont(new Font("Courier New", Font.ITALIC, 16));
		add(etiq);
	}
	
	public static void affiche() {
		if (uniqueInstance == null)
			uniqueInstance = new APropos();
		uniqueInstance.setVisible(true);
	}
}