package Controler;

import Model.Model;
import View.GridView;

import javax.swing.*;

public abstract class ControlGrid {
    Model model;
    GridView vue;

    public ControlGrid(Model model, GridView vue) {
        this.model = model;
        this.vue = vue;
    }

    public void checkWinner() {
        if (this.model.getWinner()) {
            this.vue.getTimer().cancel();
            this.model.setJeuCommence(false);
            this.vue.controlFalse();
            JOptionPane dialog = new JOptionPane();
            JOptionPane.showMessageDialog(dialog, "Tu as gagné !!", "Bravo", 1);
            if (this.model.estBestScorers(this.model.getTime())) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new ControlGroupNewReccord(ControlGrid.this.model);
                    }
                });
            }
        }

    }
}