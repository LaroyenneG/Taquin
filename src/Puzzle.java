/**
 * Created by guillaume on 08/05/16.
 */
class Puzzle {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                Model model = new Model();
                ControlGroupTheGrid controler = new ControlGroupTheGrid(model);
            }
        });
    }
}


