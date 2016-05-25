/**
 * Created by guillaume on 08/05/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlMenuTheGrid  extends ControlTheGrid implements ActionListener{

    private static int taille;

    public ControlMenuTheGrid(Model model, VueTheGrid vue) {
        super(model, vue);
        vue.setMenuControler(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(((JMenuItem) e.getSource()).getText().equals("Meilleurs scores")){
            //Creaton de la liste des winners
            String[] joueur = model.getListWinner();
            //Affichage des Winners
            FBestScore f = new FBestScore(joueur);

        }else {
            if (((JMenuItem) e.getSource()).getText().equals("Aide")){
                JOptionPane dialog = new JOptionPane();
                dialog.showMessageDialog(dialog, "Vous devez trier les cellules dans l'ordre croissant." +System.getProperty("line.separator")+"Pour deplacer une cellule vous devez selectionner" +System.getProperty("line.separator")+"une cellule avec un nombre, puis la cellule vide.", "Comment jouer ?", JOptionPane.INFORMATION_MESSAGE);
            }else {
                if (((JMenuItem) e.getSource()).getText().length()==5){

                    try{
                        //On prend le premier caractere pour avoir la taille de la nouvelle grille
                        taille=Character.getNumericValue(((JMenuItem) e.getSource()).getText().charAt(0));
                        vue.visible(false);
                    }catch (Exception ex){
                        taille=3;
                        System.out.println(ex);
                    }

                    javax.swing.SwingUtilities.invokeLater( new Runnable() {
                        public void run() {
                            Model model = new Model();
                            model.newGrid(taille);
                            ControlGroupTheGrid controler = new ControlGroupTheGrid(model);
                        }
                    });
                }
            }


        }
    }
}




