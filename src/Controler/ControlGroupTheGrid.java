package Controler;

import Model.Model;
import View.VueTheGrid;

/**
 * Created by guillaume on 08/05/16.
 */
public class ControlGroupTheGrid {

    public ControlButtonTheGrid controlButton;
    public ControlMenuTheGrid controlMenu;
    private Model model;
    private VueTheGrid vue;


    public ControlGroupTheGrid(Model model) {

        this.model = model;

        vue = new VueTheGrid(model);

        controlButton = new ControlButtonTheGrid(model, vue);
        controlMenu = new ControlMenuTheGrid(model, vue);


    }
}