package gui.afficher;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class viewListener implements ActionListener {
    Socket sock;
    AfficherGUI ag;
    public viewListener(AfficherGUI ag){ this.ag = ag;this.sock=ag.getSocket();}
    @Override
    public void actionPerformed(ActionEvent e){
        if(ag.getNumInscri().equals("")){ // On verifie si les champs sont vides
            JOptionPane.showMessageDialog(null, "Il faut indiquer le numero d'inscription", "Manque d'Informations", JOptionPane.WARNING_MESSAGE);
        }
        else {
            try {
                DataOutputStream out = new DataOutputStream(sock.getOutputStream());
                DataInputStream in = new DataInputStream(sock.getInputStream());
                out.writeInt(3);
                out.writeUTF(ag.getNumInscri());
                switch(in.readInt()){
                    case 0: //l'etudiant n'existe pas
                        JOptionPane.showMessageDialog(null, "Cet etudiant n'existe pas !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 1: //l'etudiant existe
                        ag.getTableau().setValueAt(in.readUTF(),0,0);
                        ag.getTableau().setValueAt(in.readUTF(),0,1);
                        ag.getTableau().setValueAt(in.readUTF(),0,2);
                        ag.getTableau().setValueAt(in.readUTF(),0,3);
                        ag.getTableau().setValueAt(in.readUTF(),0,4);
                        break;
                }

            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
