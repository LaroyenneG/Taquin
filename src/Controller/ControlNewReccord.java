package Controller;

import Model.Model;
import Vue.VueNewReccord;

public abstract class ControlNewReccord {
    protected Model model;
    protected VueNewReccord vue;

    public ControlNewReccord(Model model, VueNewReccord vue) {
        this.model = model;
        this.vue = vue;
    }

    protected void enregistrer() {
        if(this.model.savePlayer()) {
            this.vue.undisplay();
        } else {
            this.vue.display();
        }

    }
}
