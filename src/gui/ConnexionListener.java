package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
public class ConnexionListener implements ActionListener {
    Login log;
    public ConnexionListener(Login log){
        this.log = log;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            try {
                if (log.getPassword().equals("insat2020")) {
                    Socket cnx = new Socket(log.getAdresse(), Integer.parseInt(log.getPort()));
                    log.getState().setForeground(Color.green);
                    log.getState().setText("Connecté");
                    log.getFenetre().dispose();
                    GestionGUI gest = new GestionGUI(cnx);
                } else {
                    log.getState().setForeground(Color.red);
                    log.getState().setText("Mot de passe invalide");
                }
            } catch (IOException exc) {
                log.getState().setForeground(Color.orange);
                log.getState().setText("En attente de serveur ...");
            }

    }
}
