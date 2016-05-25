/**
 * Created by guillaume on 08/05/16.
 */
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.event.*;

public abstract class ControlTheGrid {

    Model model;
    VueTheGrid vue;

    public ControlTheGrid(Model model, VueTheGrid vue) {
        this.model = model;
        this.vue = vue;
    }

    public void checkWinner(){
        if (model.getWinner()) {
            vue.getTimer().cancel();
            model.setJeuCommence(false);
            vue.controlFalse();

            JOptionPane dialog = new JOptionPane();
            dialog.showMessageDialog(dialog, "Tu as gagné !!", "Bravo", JOptionPane.INFORMATION_MESSAGE);

            if (model.estBestScorres(model.getTime())) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ControlGroupNewReccord controler = new ControlGroupNewReccord(model);
                    }
                });
            }

        }
    }


}