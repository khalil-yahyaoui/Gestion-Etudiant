package gui;

import java.awt.event.*;
public class RetourMainListener implements ActionListener {
    private GestionGUI gui;
    public  RetourMainListener(GestionGUI gui){
        this.gui = gui;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        GestionGUI g = new GestionGUI(gui.getSocket());
        gui.getFenetre().dispose();
    }
}
