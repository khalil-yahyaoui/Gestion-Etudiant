package gui.modifier;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class modifyListener implements ActionListener{
    Socket sock;
    ModifierGUI mg;
    public modifyListener (ModifierGUI mg){this.mg = mg;this.sock = mg.getSocket();}
    @Override
    public void actionPerformed (ActionEvent e){
        if ((mg.getName()).equals("") ||(mg.getEmail()).equals("")||(mg.getPrenom()).equals("")||(mg.getNumInscription()).equals("")||(mg.getFiliere()).equals("modifier la filiere")||(mg.getClasse()).equals("modifier la classe")) {
            JOptionPane.showMessageDialog(null, "Il faut remplir tout les champs", "Missing Informations", JOptionPane.WARNING_MESSAGE);
        } // On verifie si les champs sont vides
        else{
            try {
                DataOutputStream out = new DataOutputStream(sock.getOutputStream());
                DataInputStream in = new DataInputStream(sock.getInputStream());
                out.writeInt(4);
                out.writeUTF(mg.getNumInscription());
                out.writeUTF(mg.getName());
                out.writeUTF(mg.getPrenom());
                out.writeUTF(mg.getEmail());
                out.writeUTF((mg.getFiliere()));
                out.writeUTF((mg.getClasse()));
                switch(in.readInt()){
                    case 0://l'etudiant n'existe pas
                        JOptionPane.showMessageDialog(null, "L'etudiant n'existe pas !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 1://l'etudiant existe
                        JOptionPane.showMessageDialog(null, "L'etudiant est modifié!", "Succés", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }

            }
            catch (IOException excep) {
                System.out.println(excep.getMessage());
            }

        }
    }
}
