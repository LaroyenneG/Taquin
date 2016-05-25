/**
 * Created by guillaume on 06/05/16.
 */
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.event.*;

public abstract class ControlNewReccord {

        protected Model model;
        protected VueNewReccord vue;


        public ControlNewReccord(Model model, VueNewReccord vue) {
            this.model = model;
            this.vue = vue;
        }


        protected void enregistrer(){
            if(model.savePlayer()){
                vue.undisplay();
            }else {
                vue.display();
            }
        }
}
