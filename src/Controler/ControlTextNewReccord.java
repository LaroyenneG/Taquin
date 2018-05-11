package Controler;

import Model.Model;
import View.ReccordView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ControlTextNewReccord extends ControlNewRecord implements DocumentListener {
    public ControlTextNewReccord(Model model, ReccordView vue) {
        super(model, vue);
        vue.setTextControler(this);
    }

    public void changedUpdate(DocumentEvent e) {
        this.updateModel(e);
    }

    public void insertUpdate(DocumentEvent e) {
        this.updateModel(e);
    }

    public void removeUpdate(DocumentEvent e) {
        this.updateModel(e);
    }

    protected void updateModel(DocumentEvent e) {
        this.model.setNomPseudo(this.view.getPseudo().getText());
    }
}