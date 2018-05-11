package Controler;

import Model.Model;
import View.ReccordView;

public abstract class ControlNewRecord {

    protected Model model;
    protected ReccordView view;

    public ControlNewRecord(Model model, ReccordView vue) {
        this.model = model;
        this.view = vue;
    }

    protected void registration() {
        if (this.model.savePlayer()) {
            this.view.undisplay();
        } else {
            this.view.display();
        }

    }
}
