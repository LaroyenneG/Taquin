import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ControlMenuTheGrid extends ControlTheGrid implements ActionListener {
    private static int taille;

    public ControlMenuTheGrid(Model model, VueTheGrid vue) {
        super(model, vue);
        vue.setMenuControler(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(((JMenuItem)e.getSource()).getText().equals("Meilleurs scores")) {
            String[] ex = this.model.getListWinner();
            new FBestScore(ex);
        } else if(((JMenuItem)e.getSource()).getText().equals("Aide")) {
            JOptionPane ex1 = new JOptionPane();
            JOptionPane.showMessageDialog(ex1, "Vous devez trier les cellules dans l\'ordre croissant." + System.getProperty("line.separator") + "Pour deplacer une cellule vous devez selectionner" + System.getProperty("line.separator") + "une cellule avec un nombre, puis la cellule vide.", "Comment jouer ?", 1);
        } else if(((JMenuItem)e.getSource()).getText().length() == 5) {
            try {
                taille = Character.getNumericValue(((JMenuItem)e.getSource()).getText().charAt(0));
                this.vue.visible(false);
            } catch (Exception var4) {
                taille = 3;
                System.out.println(var4);
            }

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Model model = new Model();
                    model.newGrid(ControlMenuTheGrid.taille);
                    new ControlGroupTheGrid(model);
                }
            });
        }

    }
}




