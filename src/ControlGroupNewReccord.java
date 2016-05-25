/**
 * Created by guillaume on 06/05/16.
 */
public class ControlGroupNewReccord {
    private Model model;
    private VueNewReccord vue;
    public ControlButtonNewReccord controlButton;
    public ControlTextNewReccord controlText;


    public ControlGroupNewReccord(Model model) {

        this.model = model;

        vue = new VueNewReccord(model);

        controlButton = new ControlButtonNewReccord(model, vue);
        controlText = new ControlTextNewReccord(model, vue);

    }
}
