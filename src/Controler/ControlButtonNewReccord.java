package Controler;

import Model.Model;
import View.ReccordView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButtonNewReccord extends ControlNewRecord implements ActionListener {
    private ReccordView vue;

    public ControlButtonNewReccord(Model model, ReccordView vue) {
        super(model, vue);
        this.vue = vue;
        vue.setButtonControler(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getText().equals("Cancel")) {
            this.vue.undisplay();
        } else {
            this.registration();
        }

    }
}
