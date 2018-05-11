package Controler;

import Model.Model;
import View.GridView;

/**
 * Created by guillaume on 08/05/16.
 */
public class ControlGroupGrid {

    public ControlButtonTheGrid controlButton;
    public ControlMenuGrid controlMenu;
    private Model model;
    private GridView vue;


    public ControlGroupGrid(Model model) {

        this.model = model;

        vue = new GridView(model);

        controlButton = new ControlButtonTheGrid(model, vue);
        controlMenu = new ControlMenuGrid(model, vue);


    }
}