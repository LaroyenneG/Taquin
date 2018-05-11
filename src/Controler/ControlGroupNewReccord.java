package Controler;

import Model.Model;
import View.ReccordView;

public class ControlGroupNewReccord {
    public ControlButtonNewReccord controlButton;
    public ControlTextNewReccord controlText;
    private Model model;
    private ReccordView vue;

    public ControlGroupNewReccord(Model model) {
        this.model = model;
        this.vue = new ReccordView(model);
        this.controlButton = new ControlButtonNewReccord(model, this.vue);
        this.controlText = new ControlTextNewReccord(model, this.vue);
    }
}
