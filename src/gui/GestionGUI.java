package gui;
import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class GestionGUI {
    final private JFrame fenetre = new JFrame("Gestion des etudiants");
    final private JButton ajouter = new JButton("Ajouter un etudiant");
    final private JButton afficherEtudiant = new JButton("Afficher les infos. d'un etudiant");
    final private JButton supprimer = new JButton("Supprimer un etudiant");
    final private JButton modifier = new JButton("Modifier les infos. d'un etudiant");
    final private JLabel title = new JLabel("GESTION DES ETUDIANTS",SwingConstants.CENTER);

    protected Socket sock;
    public GestionGUI(){ } // Constructeur par defaut
    protected void centerFrame(JFrame frm) { // Methode peremttant de centrer le JFrame
        Dimension windowSize = frm.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        frm.setLocation(dx, dy);
    }
    public GestionGUI(Socket sock){
        this.sock = sock;
        Container c = fenetre.getContentPane();
        c.setLayout(new BorderLayout());
        JPanel choix = new JPanel(new GridLayout(5,1,5,5));
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        choix.add(title);
        ajouter.addActionListener(new MenuListener(this,"ajouter"));
        choix.add(ajouter);
        afficherEtudiant.addActionListener(new MenuListener(this,"afficher"));
        choix.add(afficherEtudiant);
        supprimer.addActionListener(new MenuListener(this,"supprimer"));
        choix.add(supprimer);
        modifier.addActionListener(new MenuListener(this,"modifier"));
        choix.add(modifier);
        c.add(choix,BorderLayout.CENTER);
        fenetre.setSize(350,400);
        centerFrame(this.fenetre);
        fenetre.setVisible(true);
    }
    public JFrame getFenetre(){
        return fenetre;
    }
    public Socket getSocket() { return this.sock; }
}
