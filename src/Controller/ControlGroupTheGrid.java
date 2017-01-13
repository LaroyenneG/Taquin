package Controller;

import Controller.ControlButtonTheGrid;
import Model.Model;
import Vue.VueTheGrid;

/**
 * Created by guillaume on 08/05/16.
 */
public class ControlGroupTheGrid {

    private Model model;
    private VueTheGrid vue;
    public ControlButtonTheGrid controlButton;
    public ControlMenuTheGrid controlMenu;


    public ControlGroupTheGrid(Model model) {

        this.model = model;

        vue = new VueTheGrid(model);

        controlButton = new ControlButtonTheGrid(model,vue);
        controlMenu = new ControlMenuTheGrid(model,vue);


    }
}