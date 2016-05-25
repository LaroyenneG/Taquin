/**
 * Created by guillaume on 08/05/16.
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import  java.awt.event.*;

public class ControlButtonTheGrid extends ControlTheGrid implements ActionListener
{

    private int numJButtonSelect;

    public ControlButtonTheGrid(Model model, VueTheGrid vue) {
        super(model, vue);
        vue.setButtonControler(this);
        numJButtonSelect=0;

    }

    public void actionPerformed(ActionEvent e)
    {

        if(!((JButton) e.getSource()).getText().equals("")){
            numJButtonSelect = Integer.parseInt(((JButton) e.getSource()).getText());
            //Couleur du JButton selectionne
            vue.actualizeGrid();
            vue.jButtonSelect(((JButton) e.getSource()).getText());

        }else{
            if (numJButtonSelect!=0) {
                model.echanger(numJButtonSelect);
                vue.actualizeGrid();
                checkWinner();
                numJButtonSelect = 0;
            }
        }
    }
}
