/**
 * Created by guillaume on 08/05/16.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;


public class VueTheGrid extends JFrame {
   //Creation des couleurs et police CONSTANTE
   private static final Color colorcasevide = new Color(254, 254, 254);
   private static final Color colorcasenonvide = new Color(210, 210, 210);
   private static final Color colorcaseSelct = new Color(64, 154, 191);
   private static final Color colorTextButton = Color.black;
   private static final Font jButttonFont = new Font("Helvetica", Font.BOLD, 20);

   //Creation des icones
   private static final ImageIcon img = new ImageIcon("src/icon.png");

   private Model model;

   private JLabel temps;
   private JButton tabJButton[][];
   public JPanel theGrid;

   //Timer
   private Timer timer = new Timer();

   //Item
   private JMenuItem item2;
   private JMenuItem itemT3;
   private JMenuItem itemT4;
   private JMenuItem itemT5;
   private JMenuItem itemT6;
   private JMenuItem itemT7;
   private JMenuItem itemT8;
   private JMenuItem itemT9;
   private JMenuItem aide;

   VueTheGrid(Model model) {
      this.model = model;

      initAttribut();
      creerWidget();
      setTitle("Puzzle");
      setSize(300 + (model.getTaille() - 3) * 50, 345 + (model.getTaille() - 3) * 50);
      setIconImage(img.getImage());
      setLocationRelativeTo(null);
      setVisible(true);
      setResizable(false);

      //Pour ne pas qu'il y est de cadre autour du text du Jbutton selectionné par defaut
      setFocusableWindowState(false);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   private void initAttribut(){
      //Creation du tableau de button
      tabJButton = new JButton[model.getTaille()][model.getTaille()];

      //Temps
      temps = new JLabel("");

      //Creation des JPanels
      theGrid = new JPanel(new GridLayout(model.getTaille(), model.getTaille(),3,3));

      //Creations des Items
      item2 = new JMenuItem("Meilleurs scores");
      itemT3 = new JMenuItem("3 x 3");
      itemT4 = new JMenuItem("4 x 4");
      itemT5 = new JMenuItem("5 x 5");
      itemT6 = new JMenuItem("6 x 6");
      itemT7 = new JMenuItem("7 x 7");
      itemT8 = new JMenuItem("8 x 8");
      itemT9 = new JMenuItem("9 x 9");
      aide = new JMenuItem("Aide");
   }

   private void creerWidget() {
      //Creation des JPanels
      JPanel panoPrincipal = new JPanel(new BorderLayout());
      JPanel cadre = new JPanel();

      //Creation des objects
      JMenuBar barMenu = new JMenuBar();
      JMenu menu = new JMenu("Options");
      JMenu tailleMenu = new JMenu("Nouvelle partie");

      //Configurations des JPanels
      panoPrincipal.setBackground(colorcasevide);
      theGrid.setBackground(colorcasevide);
      cadre.setLayout(new BorderLayout(10, 10));
      cadre.setBorder(BorderFactory.createLineBorder(Color.blue, 5));

      //Confguration des objects
      temps.setHorizontalAlignment(JLabel.CENTER);

      //Mise en plan du menu
      tailleMenu.add(itemT3);
      tailleMenu.add(itemT4);
      tailleMenu.add(itemT5);
      tailleMenu.add(itemT6);
      tailleMenu.add(itemT7);
      tailleMenu.add(itemT8);
      tailleMenu.add(itemT9);
      menu.add(tailleMenu);
      menu.addSeparator();
      menu.add(item2);
      menu.add(aide);
      barMenu.add(menu);
      //Ajout du JMenu
      setJMenuBar(barMenu);

      //Mise en plan des JPanels
      panoPrincipal.add(temps, BorderLayout.NORTH);
      cadre.add(theGrid);
      panoPrincipal.add(cadre, BorderLayout.CENTER);
      setContentPane(panoPrincipal);

      //Creation et affichage de la grille dans la fenetre
      createGrid();
      showGrid();

      //Creation et configuration du timer
      timer.schedule(new TimerTask() {
         public void run() {
            model.setTime(model.getTime() + 0.1);
            temps.setText("Time : " + model.getTimeFormat() + " s");
         }
      }, 5, 100);
   }

   public void setButtonControler(ActionListener listener) {
      int x, y;

      for (y = 0; y < tabJButton.length; y++) {
         for (x = 0; x < tabJButton[y].length; x++) {
            tabJButton[y][x].addActionListener(listener);
         }
      }
   }

   public void setMenuControler(ActionListener listener) {
      item2.addActionListener(listener);
      itemT3.addActionListener(listener);
      itemT4.addActionListener(listener);
      itemT5.addActionListener(listener);
      itemT6.addActionListener(listener);
      itemT7.addActionListener(listener);
      itemT8.addActionListener(listener);
      itemT9.addActionListener(listener);
      aide.addActionListener(listener);
   }

   private void createGrid() {
      int[][] modelGrid;
      modelGrid = model.getGrid();

      //Compteur
      int x, y;

      tabJButton = new JButton[model.getTaille()][model.getTaille()];
      for (y = 0; y < model.getTaille(); y++) {
         for (x = 0; x < model.getTaille(); x++) {
            if (modelGrid[y][x] == model.getTaille() * model.getTaille()) {
               tabJButton[y][x] = new JButton("");
               tabJButton[y][x].setBackground(colorcasevide);
            } else {
               tabJButton[y][x] = new JButton(Integer.toString(modelGrid[y][x]));
               tabJButton[y][x].setBackground(colorcasenonvide);
            }
            tabJButton[y][x].setBorderPainted(false);
            tabJButton[y][x].setRequestFocusEnabled(false);
            tabJButton[y][x].setFont(jButttonFont);
            tabJButton[y][x].setForeground(colorTextButton);
         }
      }
      controlTrue();
   }

   private void showGrid() {
      int x, y;
      for (y = 0; y < tabJButton.length; y++) {
         for (x = 0; x < tabJButton[y].length; x++) {
            theGrid.add(tabJButton[y][x]);
         }
      }
   }

   public void actualizeGrid() {
      int[][] modelGrid;
      modelGrid = model.getGrid();

      int x, y;

      for (y = 0; y < tabJButton.length; y++) {
         for (x = 0; x < tabJButton[y].length; x++) {
            if (modelGrid[y][x] == (model.getTaille() * model.getTaille())) {
               tabJButton[y][x].setText("");
               tabJButton[y][x].setBackground(colorcasevide);

            } else {
               tabJButton[y][x].setText(Integer.toString(modelGrid[y][x]));
               tabJButton[y][x].setBackground(colorcasenonvide);
            }

         }
      }
   }

   public void controlFalse() {
      int[][] modelGrid;
      modelGrid = model.getGrid();

      //Compteurs
      int x, y;

      for (y = 0; y < tabJButton.length; y++) {
         for (x = 0; x < tabJButton[y].length; x++) {
               tabJButton[y][x].setEnabled(false);
            }

         }
      }

   public void controlTrue() {
      int[][] modelGrid;
      modelGrid = model.getGrid();

      //Compteurs
      int x, y;

      for (y = 0; y < tabJButton.length; y++) {
         for (x = 0; x < tabJButton[y].length; x++) {
            tabJButton[y][x].setEnabled(true);
         }

      }
   }

   public JButton getJButton(String n) {
      //Compteurs
      int x,y;

      for(y=0;y<tabJButton.length;y++){
         for (x=0;x<tabJButton[y].length;x++){
            if(tabJButton[y][x].getText().equals(n)){
               return tabJButton[y][x];
            }
         }
      }
      return tabJButton[0][0];
   }

   public void jButtonSelect(String n) {
      //Compteurs
      int x,y;

      for(y=0;y<tabJButton.length;y++){
         for (x=0;x<tabJButton[y].length;x++){
            if(tabJButton[y][x].getText().equals(n)){
               tabJButton[y][x].setBackground(colorcaseSelct);
            }
         }
      }
   }

   public Timer getTimer() {
      return timer;
   }

   public void visible(boolean b) {
      setVisible(b);
   }
}
