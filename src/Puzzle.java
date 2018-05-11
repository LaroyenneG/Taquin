import Controler.ControlGroupGrid;
import Model.Model;

import javax.swing.*;

class Puzzle {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Model model = new Model();
                new ControlGroupGrid(model);
            }
        });
    }
}