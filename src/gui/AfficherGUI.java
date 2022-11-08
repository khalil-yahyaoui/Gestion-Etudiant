package gui.afficher;

import gui.GestionGUI;
import gui.RetourMainListener;


import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class AfficherGUI extends GestionGUI {
    final private JFrame fenetreAfficher = new JFrame("Affichage des Infos. d'un etudiant");
    final private JTextField field = new JTextField(20);
    final private JButton buttonRetourMenu = new JButton("Retour au menu");
    final private JButton buttonAfficher = new JButton("Afficher");
    String [] entetes =  {"Nom","Prenom","Email","Classe", "Filiere"};
    String [][] donnees = {{"","","","", ""}};
    JTable tableau = new JTable(donnees,entetes);
    public AfficherGUI(Socket sock){
        this.sock = sock;
        Container c = fenetreAfficher.getContentPane();
        c.setLayout(new BorderLayout());
        JPanel afficher = new JPanel(new GridLayout(2,2,20,5));
        afficher.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        afficher.add(new JLabel("Numero d'Inscription"),JLabel.CENTER);
        afficher.add(field);
        buttonAfficher.addActionListener(new viewListener(this));
        buttonRetourMenu.addActionListener(new RetourMainListener(this));
        afficher.add(buttonRetourMenu);
        afficher.add(buttonAfficher);
        c.add(afficher,BorderLayout.NORTH);
        tableau.setRowHeight(25);
        tableau.getColumn("Nom").setPreferredWidth(40);
        tableau.getColumn("Prenom").setPreferredWidth(40);
        tableau.getColumn("Email").setPreferredWidth(80);
        tableau.getColumn("Classe").setPreferredWidth(20);
        tableau.getColumn("Filiere").setPreferredWidth(15);
        c.add(tableau.getTableHeader(),BorderLayout.CENTER);
        c.add(tableau,BorderLayout.SOUTH);
        fenetreAfficher.setSize(600,300);
        centerFrame(fenetreAfficher);
        fenetreAfficher.setVisible(true);
        fenetreAfficher.pack();

    }
    public JFrame getFenetre() {
        return fenetreAfficher;
    }
    public Socket getSocket(){ return this.sock;}
    public String getNumInscri(){return field.getText();}
    public JTable getTableau(){return this.tableau;}
}
