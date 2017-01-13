package Controller;

import Controller.ControlButtonNewReccord;
import Model.Model;
import Vue.VueNewReccord;

public class ControlGroupNewReccord {
    private Model model;
    private VueNewReccord vue;
    public ControlButtonNewReccord controlButton;
    public ControlTextNewReccord controlText;

    public ControlGroupNewReccord(Model model) {
        this.model = model;
        this.vue = new VueNewReccord(model);
        this.controlButton = new ControlButtonNewReccord(model, this.vue);
        this.controlText = new ControlTextNewReccord(model, this.vue);
    }
}
