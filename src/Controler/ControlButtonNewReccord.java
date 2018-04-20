package Controler;

import Model.Model;
import View.VueNewReccord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButtonNewReccord extends ControlNewReccord implements ActionListener {
    private VueNewReccord vue;

    public ControlButtonNewReccord(Model model, VueNewReccord vue) {
        super(model, vue);
        this.vue = vue;
        vue.setButtonControler(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getText().equals("Cancel")) {
            this.vue.undisplay();
        } else {
            this.enregistrer();
        }

    }
}
