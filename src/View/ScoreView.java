package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreView extends JFrame {
    private JLabel nom1JLabel;
    private JLabel nom2JLabel;
    private JLabel nom3JLabel;
    private JButton okJButton;
    private String[] tbWinner;

    public ScoreView(String[] tbWinner) {
        this.tbWinner = tbWinner;
        this.setTitle("Best scores");
        this.initAttribut();
        this.creerWidget();
        this.afficheWinner();
        this.setSize(450, 210);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    private void initAttribut() {
        this.nom1JLabel = new JLabel("   1- Aucun joueur");
        this.nom2JLabel = new JLabel("   2- Aucun joueur");
        this.nom3JLabel = new JLabel("   3- Aucun joueur");
        this.okJButton = new JButton("  OK  ");
        this.okJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScoreView.this.setVisible(false);
            }
        });
    }

    private void creerWidget() {
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel(new GridLayout(1, 2));
        JPanel jPanel1_1 = new JPanel();
        JPanel jPanel1_2 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel1_2_1 = new JPanel(new GridLayout(1, 1));
        JPanel jPanel1_2_2 = new JPanel(new GridLayout(3, 1, 10, 10));
        jPanel.setLayout(new BoxLayout(jPanel, 1));
        jPanel1_1.setLayout(new BoxLayout(jPanel1_1, 1));
        jPanel1_2.setLayout(new BoxLayout(jPanel1_2, 1));
        jPanel2.setLayout(new BoxLayout(jPanel2, 0));
        jPanel.setBackground(Color.WHITE);
        jPanel1.setBackground(Color.WHITE);
        jPanel2.setBackground(Color.WHITE);
        jPanel1_1.setBackground(Color.WHITE);
        jPanel1_2.setBackground(Color.WHITE);
        jPanel1_2_1.setBackground(Color.WHITE);
        jPanel1_2_2.setBackground(Color.WHITE);
        JLabel titreJLabel = new JLabel("Les meilleurs scores sont :");
        JLabel imageJLabel = new JLabel(new ImageIcon("resource/podium1.png"));
        Font titre = new Font("Arial", 1, 14);
        Font nom = new Font("Arial", 1, 12);
        Font button = new Font("Arial", 1, 12);
        titreJLabel.setFont(titre);
        this.nom1JLabel.setFont(nom);
        this.nom2JLabel.setFont(nom);
        this.nom3JLabel.setFont(nom);
        this.okJButton.setFont(button);
        jPanel1_1.add(Box.createVerticalStrut(30));
        jPanel1_1.add(imageJLabel);
        jPanel1_2_1.add(titreJLabel);
        jPanel1_2_2.add(this.nom1JLabel);
        jPanel1_2_2.add(this.nom2JLabel);
        jPanel1_2_2.add(this.nom3JLabel);
        jPanel2.add(Box.createHorizontalStrut(280));
        jPanel2.add(this.okJButton);
        jPanel1_2.add(jPanel1_2_1);
        jPanel1_2.add(Box.createVerticalStrut(15));
        jPanel1_2.add(jPanel1_2_2);
        jPanel1.add(jPanel1_1);
        jPanel1.add(jPanel1_2);
        jPanel.add(jPanel1);
        jPanel.add(jPanel2);
        this.setContentPane(jPanel);
    }

    private void afficheWinner() {
        if (this.tbWinner.length == 1) {
            this.nom1JLabel.setText("    1- " + this.tbWinner[0]);
        } else if (this.tbWinner.length == 2) {
            this.nom1JLabel.setText("    1- " + this.tbWinner[0]);
            this.nom2JLabel.setText("    2- " + this.tbWinner[1]);
        } else if (this.tbWinner.length >= 3) {
            this.nom1JLabel.setText("    1- " + this.tbWinner[0]);
            this.nom2JLabel.setText("    2- " + this.tbWinner[1]);
            this.nom3JLabel.setText("    3- " + this.tbWinner[2]);
        }

    }
}
