package gui.supprimer;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class supprimerListener implements ActionListener {
	Socket sock ;
	SupprimerGUI sg ;
	public void actionPerformed (ActionEvent e) {
		if (sg.getValue().equals("")) { // On verifie si les champs sont vides
			JOptionPane.showMessageDialog(null, "Il faut remplir le champs", "Missing Informations", JOptionPane.WARNING_MESSAGE);
		}else {
			try {
				DataOutputStream out = new DataOutputStream(sock.getOutputStream());
                DataInputStream in = new DataInputStream(sock.getInputStream());	
				out.writeInt(2);
				out.writeUTF(sg.getValue());
				System.out.println(sock.isConnected());
				switch (in.readInt()){
					case 0 ://l'etudiant n'existe pas
						JOptionPane.showMessageDialog(null, "Cet etudiant n'existe pas !", "Erreur", JOptionPane.ERROR_MESSAGE);
						break;
					case 1 ://l'etudiant existe
						JOptionPane.showMessageDialog(null, "L'etudiant est supprimé !", "Succées", JOptionPane.INFORMATION_MESSAGE);
						break;
				}
			} 
			catch (IOException e1) {
				System.out.print(e1.getMessage());
			}
			
			
			
			
		}
	}
	public supprimerListener (Socket sock, SupprimerGUI sg) {
		this.sock = sock;
		this.sg=sg;	
	}
}
