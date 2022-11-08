package gui.supprimer;

import gui.GestionGUI;
import gui.RetourMainListener;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class SupprimerGUI extends GestionGUI {
	final private JFrame fenetreSupprimer = new JFrame("Suppression d'un etudiant");
	final private JLabel numinscri = new JLabel("Numéro d'inscription");
	final private JTextField value = new JTextField(20);
	final private JButton buttonSupprimer = new JButton ("Supprimer");
	final private JButton buttonRetourMenu = new JButton ("Retour au menu");
	public SupprimerGUI(Socket sock) {
		this.sock=sock;
		Container c1 = fenetreSupprimer.getContentPane();
		c1.setLayout(new BorderLayout());
		fenetreSupprimer.setLocation(500,200);
		JPanel Supprimer = new JPanel();
		Supprimer.setLayout(new GridLayout(2,2,20,5));
		Supprimer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		Supprimer.add(numinscri,JLabel.CENTER);
		Supprimer.add(value);
		buttonRetourMenu.addActionListener(new RetourMainListener(this));
		Supprimer.add(buttonRetourMenu);
		buttonSupprimer.addActionListener(new supprimerListener(sock,this));
		Supprimer.add(buttonSupprimer);
		c1.add(Supprimer,BorderLayout.NORTH);
		fenetreSupprimer.setSize(400,200);
		centerFrame(this.fenetreSupprimer);
		fenetreSupprimer.setVisible(true);
		fenetreSupprimer.pack();
	}
	public JFrame getFenetre() {
		return fenetreSupprimer;
	}
	public String getValue() {
		return value.getText();
	}
	public Socket getSocket() { return this.sock; }
}
