package Controler;

import Model.Model;
import View.VueNewReccord;

public class ControlGroupNewReccord {
    public ControlButtonNewReccord controlButton;
    public ControlTextNewReccord controlText;
    private Model model;
    private VueNewReccord vue;

    public ControlGroupNewReccord(Model model) {
        this.model = model;
        this.vue = new VueNewReccord(model);
        this.controlButton = new ControlButtonNewReccord(model, this.vue);
        this.controlText = new ControlTextNewReccord(model, this.vue);
    }
}
