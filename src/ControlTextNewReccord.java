/**
 * Created by guillaume on 06/05/16.
 */
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ControlTextNewReccord extends ControlNewReccord implements DocumentListener{


        public ControlTextNewReccord(Model model, VueNewReccord vue) {
            super(model,vue);
            vue.setTextControler(this);
        }

        public void changedUpdate(DocumentEvent e) {
            updateModel(e);
        }

        public void insertUpdate(DocumentEvent e) {
            updateModel(e);
        }

        public void removeUpdate(DocumentEvent e) {
            updateModel(e);
        }

        protected void updateModel(DocumentEvent e) {
            model.setnomPseudo(vue.getPseudo().getText());
    }
}
