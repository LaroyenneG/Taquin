/**
 * Created by guillaume on 06/05/16.
 */
import javax.swing.*;
import javax.swing.event.*;
import  java.awt.event.*;

public class ControlButtonNewReccord extends ControlNewReccord implements ActionListener {
    private VueNewReccord vue;
    public ControlButtonNewReccord(Model model, VueNewReccord vue) {
        super(model, vue);
        this.vue=vue;
        vue.setButtonControler(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(((JButton)e.getSource()).getText().equals("Cancel")){
            vue.undisplay();
        }else {
            enregistrer();
        }

    }

}
