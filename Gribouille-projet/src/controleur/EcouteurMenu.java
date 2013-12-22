package controleur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;

import vue.Fenetre;

public class EcouteurMenu implements ActionListener {
    protected Fenetre fenetre;
    private int indexMenu;
    protected JCheckBoxMenuItem actionné;
    public EcouteurMenu(Fenetre fenetre, int indexMenu) {
        this.fenetre = fenetre;
        this.indexMenu = indexMenu;
    }
    public void actionPerformed(ActionEvent e) {
        actionné = (JCheckBoxMenuItem)(e.getSource());
        JMenu menuOutil = fenetre.getJMenuBar().getMenu(indexMenu);
        for(int i = 0; i < menuOutil.getItemCount(); i++) {
            JCheckBoxMenuItem item = (JCheckBoxMenuItem)(menuOutil.getItem(i));
            item.setState(false);
            item.setEnabled(true);            
        }
        actionné.setEnabled(false);
        actionné.setState(true);
    }
}
