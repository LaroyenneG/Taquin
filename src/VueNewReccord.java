/**
 * Created by guillaume on 04/05/16.
 */

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class VueNewReccord extends JFrame{
    private Model model;

    private JTextField pseudoJTextField;
    private JButton okJButton;
    private JButton cancelJButton;
    private JButton coupeJButton;
    private JLabel titreJLabel;

    public VueNewReccord(Model model){
        this.model = model;
        initAttribut();
        creerWidget();
        setTitle("New Reccord");
        setSize(510,170);
        setResizable(false);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    private String formatTemps(){
        int i;
        String chaine="";
        String brutChaine =Double.toString(model.getTime());
        for(i=0;i<brutChaine.length();i++){
            chaine=chaine+brutChaine.charAt(i);
            if(brutChaine.charAt(i)=='.'){
                chaine=chaine+brutChaine.charAt(i+1);
                i=brutChaine.length();
            }
        }

        for(i=0;i<6-(Double.toString(model.getTime())).length();i++){
            chaine=chaine+" ";
        }
        return chaine;
    }

    private void initAttribut(){
        pseudoJTextField = new JTextField();
        okJButton = new JButton("   OK   ");
        cancelJButton = new JButton("Cancel");
        coupeJButton = new JButton((new ImageIcon("src/coupe.png")));
        titreJLabel = new JLabel("Felictations vous avez eu un meilleur score : "+formatTemps());
    }

    private void creerWidget(){

        //
        coupeJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JOptionPane message = new JOptionPane();
                message.showMessageDialog( message, "Tu es un champion !!", "Bravo", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        // Creation des polices
        Font titreFont = new Font("Arial", Font.BOLD, 13);
        Font titreBisFont = new Font("Arial", Font.BOLD, 12);
        Font pseudoJTextFieldFont = new Font("Arial",Font.PLAIN,13);

        //Creation des JPanels
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.X_AXIS));

        //Creation des objets
        JLabel titreBisJLable = new JLabel("Veuillez saisir un pseudo :");

        //Configuration des objets
        coupeJButton.setPreferredSize( new Dimension( 80, 80 ) );
        titreBisJLable.setFont(titreBisFont);
        titreJLabel.setFont(titreFont);
        pseudoJTextField.setFont(pseudoJTextFieldFont);

        //Ajout des objets dans les JPanels
        jPanel1.add(coupeJButton);

        jPanel2.add(titreJLabel);
        jPanel2.add(Box.createVerticalStrut(10));
        jPanel2.add(titreBisJLable);
        jPanel2.add(Box.createVerticalStrut(5));
        jPanel2.add(pseudoJTextField);

        jPanel3.add(Box.createHorizontalStrut(260));
        jPanel3.add(cancelJButton);
        jPanel3.add(Box.createHorizontalStrut(50));
        jPanel3.add(okJButton);

        //Mise en plan des JPanels
        jPanel.add(jPanel1);
        jPanel.add(jPanel2);
        jPanel.add(jPanel3);
        setContentPane(jPanel);
    }

    public void display() {
        setVisible(true);
    }

    public void undisplay() {
        setVisible(false);
    }

    public void setButtonControler(ActionListener listener) {
        okJButton.addActionListener(listener);
        cancelJButton.addActionListener(listener);
    }


    public void setTextControler(DocumentListener listener) {
        pseudoJTextField.getDocument().addDocumentListener(listener);
    }

    public void resetText(){
        pseudoJTextField.setText("");
    }

    public JTextField getPseudo(){
        return pseudoJTextField;
    }

    public JButton getOkJButton(){
        return okJButton;
    }

}
