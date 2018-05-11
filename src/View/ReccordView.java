package View;

import Model.Model;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReccordView extends JFrame {
    private Model model;
    private JTextField pseudoJTextField;
    private JButton okJButton;
    private JButton cancelJButton;
    private JButton coupeJButton;
    private JLabel titreJLabel;

    public ReccordView(Model model) {
        this.model = model;
        this.initAttribut();
        this.creerWidget();
        this.setTitle("New Reccord");
        this.setSize(510, 170);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    private String formatTemps() {
        String chaine = "";
        String brutChaine = Double.toString(this.model.getTime());

        int i;
        for (i = 0; i < brutChaine.length(); ++i) {
            chaine = chaine + brutChaine.charAt(i);
            if (brutChaine.charAt(i) == 46) {
                chaine = chaine + brutChaine.charAt(i + 1);
                i = brutChaine.length();
            }
        }

        for (i = 0; i < 6 - Double.toString(this.model.getTime()).length(); ++i) {
            chaine = chaine + " ";
        }

        return chaine;
    }

    private void initAttribut() {
        this.pseudoJTextField = new JTextField();
        this.okJButton = new JButton("   OK   ");
        this.cancelJButton = new JButton("Cancel");
        this.coupeJButton = new JButton(new ImageIcon("resource/coupe.png"));
        this.titreJLabel = new JLabel("Felictations vous avez eu un meilleur score : " + this.formatTemps());
    }

    private void creerWidget() {
        this.coupeJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane message = new JOptionPane();
                JOptionPane.showMessageDialog(message, "Tu es un champion !!", "Bravo", 1);
            }
        });
        Font titreFont = new Font("Arial", 1, 13);
        Font titreBisFont = new Font("Arial", 1, 12);
        Font pseudoJTextFieldFont = new Font("Arial", 0, 13);
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new BoxLayout(jPanel2, 1));
        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(new BoxLayout(jPanel3, 0));
        JLabel titreBisJLable = new JLabel("Veuillez saisir un pseudo :");
        this.coupeJButton.setPreferredSize(new Dimension(80, 80));
        titreBisJLable.setFont(titreBisFont);
        this.titreJLabel.setFont(titreFont);
        this.pseudoJTextField.setFont(pseudoJTextFieldFont);
        jPanel1.add(this.coupeJButton);
        jPanel2.add(this.titreJLabel);
        jPanel2.add(Box.createVerticalStrut(10));
        jPanel2.add(titreBisJLable);
        jPanel2.add(Box.createVerticalStrut(5));
        jPanel2.add(this.pseudoJTextField);
        jPanel3.add(Box.createHorizontalStrut(260));
        jPanel3.add(this.cancelJButton);
        jPanel3.add(Box.createHorizontalStrut(50));
        jPanel3.add(this.okJButton);
        jPanel.add(jPanel1);
        jPanel.add(jPanel2);
        jPanel.add(jPanel3);
        this.setContentPane(jPanel);
    }

    public void display() {
        this.setVisible(true);
    }

    public void undisplay() {
        this.setVisible(false);
    }

    public void setButtonControler(ActionListener listener) {
        this.okJButton.addActionListener(listener);
        this.cancelJButton.addActionListener(listener);
    }

    public void setTextControler(DocumentListener listener) {
        this.pseudoJTextField.getDocument().addDocumentListener(listener);
    }

    public void resetText() {
        this.pseudoJTextField.setText("");
    }

    public JTextField getPseudo() {
        return this.pseudoJTextField;
    }

    public JButton getOkJButton() {
        return this.okJButton;
    }
}
