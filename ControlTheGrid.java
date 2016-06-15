import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public abstract class ControlTheGrid {
    Model model;
    VueTheGrid vue;

    public ControlTheGrid(Model model, VueTheGrid vue) {
        this.model = model;
        this.vue = vue;
    }

    public void checkWinner() {
        if(this.model.getWinner()) {
            this.vue.getTimer().cancel();
            this.model.setJeuCommence(false);
            this.vue.controlFalse();
            JOptionPane dialog = new JOptionPane();
            JOptionPane.showMessageDialog(dialog, "Tu as gagné !!", "Bravo", 1);
            if(this.model.estBestScorres(this.model.getTime())) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new ControlGroupNewReccord(ControlTheGrid.this.model);
                    }
                });
            }
        }

    }
}