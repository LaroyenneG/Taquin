import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ControlTextNewReccord extends ControlNewReccord implements DocumentListener {
    public ControlTextNewReccord(Model model, VueNewReccord vue) {
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
        this.model.setnomPseudo(this.vue.getPseudo().getText());
    }
}