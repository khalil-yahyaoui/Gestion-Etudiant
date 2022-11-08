package gui.modifier;

import gui.GestionGUI;
import gui.RetourMainListener;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;
public class ModifierGUI extends GestionGUI {
    final private JFrame fenetreModifier = new JFrame("Modification d'un etudiant");
    final private JTextField numInscription = new JTextField(15);
    final private JTextField nom = new JTextField(15);
    final private JTextField prenom = new JTextField(15);
    final private JTextField email = new JTextField(15);
    final private JComboBox<String> classe = new JComboBox<String>();
    final private JComboBox<String> filiere = new JComboBox<String>();
    final private JButton buttonRetourMenu = new JButton("Retour au menu");
    final private JButton buttonModifier = new JButton("Modifier");

    public ModifierGUI(Socket sock) {
        this.sock = sock;
        Container c1 = fenetreModifier.getContentPane();
        c1.setLayout(new BorderLayout());
        JPanel modifier = new JPanel();
        modifier.setLayout(new GridLayout(6, 2, 20, 5));
        modifier.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        modifier.add(new JLabel("Numero d'inscription", JLabel.CENTER));
        modifier.add(numInscription);
        modifier.add(new JLabel("Nom", JLabel.CENTER));
        modifier.add(nom);
        modifier.add(new JLabel("Prenom", JLabel.CENTER));
        modifier.add(prenom);
        modifier.add(new JLabel("E-mail", JLabel.CENTER));
        modifier.add(email);
        filiere.addItem("Choisir la filiere");
        filiere.addItem("RT");
        filiere.addItem("GL");
        filiere.addItem("IIA");
        filiere.addItem("IMI");
        classe.addItem("Choisir la classe");
        classe.addItem("2eme");
        classe.addItem("3eme");
        classe.addItem("4eme");
        classe.addItem("5eme");
        modifier.add(classe);
        modifier.add(filiere);
        buttonRetourMenu.addActionListener(new RetourMainListener(this));
        modifier.add(buttonRetourMenu);
        buttonModifier.addActionListener(new modifyListener(this));
        modifier.add(buttonModifier);
        c1.add(modifier, BorderLayout.NORTH);
        fenetreModifier.setSize(400, 500);
        centerFrame(this.fenetreModifier);
        fenetreModifier.setVisible(true);
        fenetreModifier.pack();
    }


    public JFrame getFenetre() {
        return fenetreModifier;
    }

    public String getNumInscription() {
        return numInscription.getText();
    }

    public String getName() {
        return nom.getText();
    }

    public String getPrenom() {
        return prenom.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getFiliere() {
        return (filiere.getSelectedItem()).toString();
    }

    public String getClasse() {
        return (classe.getSelectedItem()).toString();
    }

    public Socket getSocket() {
        return this.sock;
    }
}