/**
 * Created by guillaume on 04/05/16.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FBestScore extends JFrame{
    private  JLabel nom1JLabel;
    private JLabel nom2JLabel;
    private JLabel nom3JLabel;
    private JButton okJButton;
    private String[] tbWinner;

    public FBestScore(String[] tbWinner){

        this.tbWinner=tbWinner;
        setTitle("Best scores");
        initAttribut();
        creerWidget();
        afficheWinner();
        setSize(450, 210);
        setResizable(false);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);

    }

    private void initAttribut(){
        nom1JLabel = new JLabel("   1- Aucun joueur");
        nom2JLabel = new JLabel("   2- Aucun joueur");
        nom3JLabel = new JLabel("   3- Aucun joueur");
        okJButton = new JButton("  OK  ");

        //action du JButton
        okJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        });

    }

    private void creerWidget() {

        //Creation des JPanels
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel(new GridLayout(1,2));
        JPanel jPanel1_1 = new JPanel();
        JPanel jPanel1_2 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel1_2_1 = new JPanel(new GridLayout(1,1));
        JPanel jPanel1_2_2 = new JPanel(new GridLayout(3,1,10,10));

        //Configuration de JPanels
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel1_1.setLayout(new BoxLayout(jPanel1_1, BoxLayout.Y_AXIS));
        jPanel1_2.setLayout(new BoxLayout(jPanel1_2, BoxLayout.Y_AXIS));
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.X_AXIS));
        jPanel.setBackground(Color.WHITE);
        jPanel1.setBackground(Color.WHITE);
        jPanel2.setBackground(Color.WHITE);
        jPanel1_1.setBackground(Color.WHITE);
        jPanel1_2.setBackground(Color.WHITE);
        jPanel1_2_1.setBackground(Color.WHITE);
        jPanel1_2_2.setBackground(Color.WHITE);

        //Creation des Jlabels
        JLabel titreJLabel = new JLabel("Les meilleurs scores sont :");
        JLabel imageJLabel = new JLabel(new ImageIcon("src/podium1.png"));

        //creation de la police
        Font titre = new Font("Arial", Font.BOLD, 14);
        Font nom = new Font("Arial", Font.BOLD, 12);
        Font button = new Font("Arial", Font.BOLD, 12);

        //application de la police
        titreJLabel.setFont(titre);
        nom1JLabel.setFont(nom);
        nom2JLabel.setFont(nom);
        nom3JLabel.setFont(nom);
        okJButton.setFont(button);

        //Ajout des objets dans les Jpanels
        jPanel1_1.add(Box.createVerticalStrut(30));
        jPanel1_1.add(imageJLabel);
        jPanel1_2_1.add(titreJLabel);
        jPanel1_2_2.add(nom1JLabel);
        jPanel1_2_2.add(nom2JLabel);
        jPanel1_2_2.add(nom3JLabel);
        jPanel2.add(Box.createHorizontalStrut(280));
        jPanel2.add(okJButton);

        //Mise en plan des Jpanels
        jPanel1_2.add(jPanel1_2_1);
        jPanel1_2.add(Box.createVerticalStrut(15));
        jPanel1_2.add(jPanel1_2_2);
        jPanel1.add(jPanel1_1);
        jPanel1.add(jPanel1_2);
        jPanel.add(jPanel1);
        jPanel.add(jPanel2);

        //Affichage
        setContentPane(jPanel);
    }

    private void afficheWinner(){
        if(tbWinner.length==1){
            nom1JLabel.setText("    1- "+tbWinner[0]);
        }else{
            if(tbWinner.length==2){
                nom1JLabel.setText("    1- "+tbWinner[0]);
                nom2JLabel.setText("    2- "+tbWinner[1]);
            }else{
                if (tbWinner.length>=3){
                    nom1JLabel.setText("    1- "+tbWinner[0]);
                    nom2JLabel.setText("    2- "+tbWinner[1]);
                    nom3JLabel.setText("    3- "+tbWinner[2]);
                }
            }
        }
    }
}
