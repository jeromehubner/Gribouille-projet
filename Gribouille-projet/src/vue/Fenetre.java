package vue; 

//import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;


//import java.awt.Graphics;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import modele.Dessin;
import controleur.EcouteurCouleur;
import controleur.EcouteurCrayon;
import controleur.EcouteurEffacement;
import controleur.EcouteurFermeture;
import controleur.EcouteurOuverture;
import controleur.EcouteurSouris;

public class Fenetre extends JFrame {
	
    private ZoneDeDessin zoneDeDessin;
    private EcouteurSouris crayon;
    private Dessin dessin;
    private BarreDEtat barreDEtat;
    private ZoneListeDeTaches zoneListeDeTaches;
    private int sizeX,sizeY;
    
    public Fenetre() {
        sizeX = 800; sizeY = 500;
        dessin = new Dessin();
        zoneListeDeTaches = new ZoneListeDeTaches(new Dimension(sizeX/3, sizeY));
        
        EcouteurFermeture ecouteurFermeture = new EcouteurFermeture(this, dessin);
        EcouteurCouleur ecouteurCouleur = new EcouteurCouleur(this);
        
        barreDEtat = new BarreDEtat();
        crayon = new EcouteurCrayon(this, dessin);
        zoneDeDessin = new ZoneDeDessin(dessin, crayon);

        setTitle("Mémo");
        setSize(sizeX, sizeY);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(ecouteurFermeture);

        setLocationRelativeTo(null);
        setResizable(false);

        /*--- Barre de menus ---*/
        JMenuBar barreDeMenus = new JMenuBar();
        JMenu menu;
        JMenuItem item;
        ItemMenuCouleur itemCouleur;
        
        
        /*--- JMenu Mémo ---*/
        menu = new JMenu("Mémo");
        
        /*--- JMenuItem Effacer ---*/
        item = new JMenuItem("Effacer");
        item.addActionListener(new EcouteurEffacement(this, dessin));
        menu.add(item);
        menu.addSeparator();
        
        /*--- JMenuItem Ouvrir ---*/
        item = new JMenuItem("Ouvrir...");
        item.addActionListener(new EcouteurOuverture(this, dessin));
        menu.add(item);
        
        /*--- JMenuItem Enregistrer ---*/
        item = new JMenuItem("Enregistrer...");
        item.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    enregistre();
                }
            }
        );
        menu.add(item);
        barreDeMenus.add(menu);

        selectionneOutil(crayon, "Crayon");
        
        
        /*--- JMenu Couleur ---*/
        menu = new JMenu("Couleur");
        itemCouleur = new ItemMenuCouleur("Noir", ecouteurCouleur, Color.black);
        itemCouleur.setState(true);
        itemCouleur.setEnabled(false);
        selectionneCouleur(Color.black, "Noir");
        menu.add(itemCouleur);
        itemCouleur = new ItemMenuCouleur("Rouge", ecouteurCouleur, Color.red);
        menu.add(itemCouleur);
        itemCouleur = new ItemMenuCouleur("Vert", ecouteurCouleur, Color.green);
        menu.add(itemCouleur);
        itemCouleur = new ItemMenuCouleur("Bleu", ecouteurCouleur, Color.blue);
        menu.add(itemCouleur);
        itemCouleur = new ItemMenuCouleur("Jaune", ecouteurCouleur, Color.yellow);
        menu.add(itemCouleur);
        barreDeMenus.add(menu);
        setJMenuBar(barreDeMenus);
        
        
        /*--- JMenu ? ---*/
        menu = new JMenu("?");
        
        /*--- JMenuItem A propos ---*/
        item = new JMenuItem("A propos");
        item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				APropos.affiche();
			}
		});
        menu.add(item);
        menu.addSeparator();
        
        /*--- JMenuItem Quitter ---*/
        item = new JMenuItem("Quitter");
        item.addActionListener(ecouteurFermeture);
        menu.add(item);
        barreDeMenus.add(menu);
                
        this.getContentPane().add(zoneDeDessin, BorderLayout.CENTER);
        this.getContentPane().add(barreDEtat, BorderLayout.SOUTH);
        this.getContentPane().add(zoneListeDeTaches, BorderLayout.EAST);
        
        setVisible(true);
    }
    public void dessineTrait(int x1, int y1, int x2, int y2) {
        zoneDeDessin.dessineTrait(x1, y1, x2, y2);
    }
    public void afficheCoordonnées(int x, int y) {
        barreDEtat.afficheCoordonnées(x, y);
    }
    public void selectionneOutil(EcouteurSouris outil, String nomOutil) {
        zoneDeDessin.selectionneOutil(outil);
        barreDEtat.afficheOutil(nomOutil);
    }
    public void selectionneCouleur(Color couleur, String nomCouleur) {
        zoneDeDessin.selectionneCouleur(couleur);
        barreDEtat.afficheCouleur(nomCouleur);
    }
    public Color couleur() {
        return zoneDeDessin.couleur();
    }
    public boolean abandonAprèsEnregistrementEventuel(String titre) {
        switch(JOptionPane.showConfirmDialog(this, "L'opération fait perdre les modifications non enregistrées !\nVoulez-voulez-vous enregistrer avant ?", titre, JOptionPane.YES_NO_CANCEL_OPTION)) {
            case JOptionPane.YES_OPTION    : return !enregistre();
            case JOptionPane.NO_OPTION     : break;
            default                        : return true;
        }
        return false;
    }
    public boolean enregistre() {
        JFileChooser dlgFichier = new JFileChooser();
        switch (dlgFichier.showSaveDialog(this)) {
            case JFileChooser.CANCEL_OPTION  : return false;
            case JFileChooser.APPROVE_OPTION :
              try {
                DataOutputStream fichier = new DataOutputStream(
                                            new FileOutputStream(
                                             dlgFichier.getSelectedFile()
                                            )
                                           );
                fichier.writeInt(1); //numéro de version
                dessin.enregistre(fichier);
                fichier.close();
                return true;
             } catch(Exception e) {
                JOptionPane.showMessageDialog(this,
                                              e.toString(),
                                              "Erreur survenue à l'enregistrement",
                                              JOptionPane.ERROR_MESSAGE);
                return false;
             }
            case JFileChooser.ERROR_OPTION   :
                JOptionPane.showMessageDialog(this,
                                              "Erreur survenue",
                                              "Enregistrement...",
                                              JOptionPane.ERROR_MESSAGE);
                return false;
            default : return false;
        }
    }
    public void charge() {
        JFileChooser dlgFichier = new JFileChooser();
        switch (dlgFichier.showOpenDialog(this)) {
            case JFileChooser.CANCEL_OPTION  : return;
            case JFileChooser.APPROVE_OPTION :
              try {
                DataInputStream fichier = new DataInputStream(
                                            new FileInputStream(
                                             dlgFichier.getSelectedFile()
                                            )
                                          );
                int version = fichier.readInt();
                if (version == 1) {
                    dessin.effacer();
                    dessin.charge(fichier);
                    repaint();
                    fichier.close();
                } else {
                    JOptionPane.showMessageDialog(this,
                                                  "Cette version de Gribouille\nne sait pas lire des fichiers produits\npar d'autres versions de Gribouille !",
                                                  "Erreur survenue à l'ouverture",
                                                  JOptionPane.ERROR_MESSAGE);
                }
              } catch(Exception e) {
                JOptionPane.showMessageDialog(this,
                                              e.toString(),
                                              "Erreur survenue à l'ouverture",
                                              JOptionPane.ERROR_MESSAGE);
              }
              break;
            case JFileChooser.ERROR_OPTION   :
                JOptionPane.showMessageDialog(this,
                                              "Erreur survenue",
                                              "Ouverture...",
                                              JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
