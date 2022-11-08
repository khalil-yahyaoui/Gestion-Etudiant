package gui;

import gui.afficher.AfficherGUI;
import gui.ajouter.AjouterGUI;
import gui.modifier.ModifierGUI;
import gui.supprimer.SupprimerGUI;

import java.awt.event.*;

public class MenuListener implements ActionListener {
    String type;
    GestionGUI gui;
    public MenuListener(GestionGUI gui,String type){
        this.gui = gui;
        this.type = type;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getFenetre().dispose();
        switch(type){
            case "ajouter":
                AjouterGUI ag = new AjouterGUI(gui.getSocket());
                break;
            case "afficher":
                AfficherGUI afg = new AfficherGUI(gui.getSocket());
                break;
            case "supprimer":
                SupprimerGUI sg = new SupprimerGUI(gui.getSocket());
                break;
            case "modifier":
                ModifierGUI mg = new ModifierGUI(gui.getSocket());
                break;
        }
    }
}
