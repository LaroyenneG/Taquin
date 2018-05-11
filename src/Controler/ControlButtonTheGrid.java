package Controler;

import Model.Model;
import View.GridView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButtonTheGrid extends ControlGrid implements ActionListener {
    private int numJButtonSelect;

    public ControlButtonTheGrid(Model model, GridView vue) {
        super(model, vue);
        vue.setButtonControler(this);
        this.numJButtonSelect = 0;
    }

    public void actionPerformed(ActionEvent e) {
        if (!((JButton) e.getSource()).getText().equals("")) {
            this.numJButtonSelect = Integer.parseInt(((JButton) e.getSource()).getText());
            this.vue.actualizeGrid();
            this.vue.jButtonSelect(((JButton) e.getSource()).getText());
        } else if (this.numJButtonSelect != 0) {
            this.model.echanger(this.numJButtonSelect);
            this.vue.actualizeGrid();
            this.checkWinner();
            this.numJButtonSelect = 0;
        }

    }
}
