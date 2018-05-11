package Controler;

import Model.Model;
import View.GridView;
import View.ScoreView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenuGrid extends ControlGrid implements ActionListener {

    private static int len;

    public ControlMenuGrid(Model model, GridView vue) {
        super(model, vue);
        vue.setMenuControler(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (((JMenuItem) e.getSource()).getText().equals("Meilleurs scores")) {
            String[] ex = this.model.getListWinner();
            new ScoreView(ex);
        } else if (((JMenuItem) e.getSource()).getText().equals("Aide")) {
            JOptionPane ex1 = new JOptionPane();
            JOptionPane.showMessageDialog(ex1, "Vous devez trier les cellules dans l\'ordre croissant." + System.getProperty("line.separator") + "Pour deplacer une cellule vous devez selectionner" + System.getProperty("line.separator") + "une cellule avec un nombre, puis la cellule vide.", "Comment jouer ?", 1);
        } else if (((JMenuItem) e.getSource()).getText().length() == 5) {
            try {
                len = Character.getNumericValue(((JMenuItem) e.getSource()).getText().charAt(0));
                this.vue.visible(false);
            } catch (Exception exp) {
                len = 3;
            }

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Model model = new Model();
                    model.newGrid(ControlMenuGrid.len);
                    new ControlGroupGrid(model);
                }
            });
        }

    }
}




