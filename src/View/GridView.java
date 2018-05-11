package View;

import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class GridView extends JFrame {
    private static final Color colorcasevide = new Color(254, 254, 254);
    private static final Color colorcasenonvide = new Color(210, 210, 210);
    private static final Color colorcaseSelct = new Color(64, 154, 191);
    private static final Color colorTextButton;
    private static final Font jButttonFont;
    private static final ImageIcon img;

    static {
        colorTextButton = Color.black;
        jButttonFont = new Font("Helvetica", 1, 20);
        img = new ImageIcon("resource/icon.png");
    }

    public JPanel theGrid;
    private Model model;
    private JLabel temps;
    private JButton[][] tabJButton;
    private Timer timer = new Timer();
    private JMenuItem item2;
    private JMenuItem itemT3;
    private JMenuItem itemT4;
    private JMenuItem itemT5;
    private JMenuItem itemT6;
    private JMenuItem itemT7;
    private JMenuItem itemT8;
    private JMenuItem itemT9;
    private JMenuItem aide;

    public GridView(Model model) {
        this.model = model;
        this.initAttribut();
        this.creerWidget();
        this.setTitle("Puzzle");
        this.setSize(300 + (model.getLen() - 3) * 50, 345 + (model.getLen() - 3) * 50);
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setFocusableWindowState(false);
        this.setDefaultCloseOperation(3);
    }

    private void initAttribut() {
        this.tabJButton = new JButton[this.model.getLen()][this.model.getLen()];
        this.temps = new JLabel("");
        this.theGrid = new JPanel(new GridLayout(this.model.getLen(), this.model.getLen(), 3, 3));
        this.item2 = new JMenuItem("Meilleurs scores");
        this.itemT3 = new JMenuItem("3 x 3");
        this.itemT4 = new JMenuItem("4 x 4");
        this.itemT5 = new JMenuItem("5 x 5");
        this.itemT6 = new JMenuItem("6 x 6");
        this.itemT7 = new JMenuItem("7 x 7");
        this.itemT8 = new JMenuItem("8 x 8");
        this.itemT9 = new JMenuItem("9 x 9");
        this.aide = new JMenuItem("Aide");
    }

    private void creerWidget() {
        JPanel panoPrincipal = new JPanel(new BorderLayout());
        JPanel cadre = new JPanel();
        JMenuBar barMenu = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenu tailleMenu = new JMenu("Nouvelle partie");
        panoPrincipal.setBackground(colorcasevide);
        this.theGrid.setBackground(colorcasevide);
        cadre.setLayout(new BorderLayout(10, 10));
        cadre.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        this.temps.setHorizontalAlignment(0);
        tailleMenu.add(this.itemT3);
        tailleMenu.add(this.itemT4);
        tailleMenu.add(this.itemT5);
        tailleMenu.add(this.itemT6);
        tailleMenu.add(this.itemT7);
        tailleMenu.add(this.itemT8);
        tailleMenu.add(this.itemT9);
        menu.add(tailleMenu);
        menu.addSeparator();
        menu.add(this.item2);
        menu.add(this.aide);
        barMenu.add(menu);
        this.setJMenuBar(barMenu);
        panoPrincipal.add(this.temps, "North");
        cadre.add(this.theGrid);
        panoPrincipal.add(cadre, "Center");
        this.setContentPane(panoPrincipal);
        this.createGrid();
        this.showGrid();
        this.timer.schedule(new TimerTask() {
            public void run() {
                GridView.this.model.setTime(GridView.this.model.getTime() + 0.1D);
                GridView.this.temps.setText("Time : " + GridView.this.model.getTimeFormat() + " s");
            }
        }, 5L, 100L);
    }

    public void setButtonControler(ActionListener listener) {
        for (int y = 0; y < this.tabJButton.length; ++y) {
            for (int x = 0; x < this.tabJButton[y].length; ++x) {
                this.tabJButton[y][x].addActionListener(listener);
            }
        }

    }

    public void setMenuControler(ActionListener listener) {
        this.item2.addActionListener(listener);
        this.itemT3.addActionListener(listener);
        this.itemT4.addActionListener(listener);
        this.itemT5.addActionListener(listener);
        this.itemT6.addActionListener(listener);
        this.itemT7.addActionListener(listener);
        this.itemT8.addActionListener(listener);
        this.itemT9.addActionListener(listener);
        this.aide.addActionListener(listener);
    }

    private void createGrid() {
        int[][] modelGrid = this.model.getGrid();
        this.tabJButton = new JButton[this.model.getLen()][this.model.getLen()];

        for (int y = 0; y < this.model.getLen(); ++y) {
            for (int x = 0; x < this.model.getLen(); ++x) {
                if (modelGrid[y][x] == this.model.getLen() * this.model.getLen()) {
                    this.tabJButton[y][x] = new JButton("");
                    this.tabJButton[y][x].setBackground(colorcasevide);
                } else {
                    this.tabJButton[y][x] = new JButton(Integer.toString(modelGrid[y][x]));
                    this.tabJButton[y][x].setBackground(colorcasenonvide);
                }

                this.tabJButton[y][x].setBorderPainted(false);
                this.tabJButton[y][x].setRequestFocusEnabled(false);
                this.tabJButton[y][x].setFont(jButttonFont);
                this.tabJButton[y][x].setForeground(colorTextButton);
            }
        }

        this.controlTrue();
    }

    private void showGrid() {
        for (int y = 0; y < this.tabJButton.length; ++y) {
            for (int x = 0; x < this.tabJButton[y].length; ++x) {
                this.theGrid.add(this.tabJButton[y][x]);
            }
        }

    }

    public void actualizeGrid() {
        int[][] modelGrid = this.model.getGrid();

        for (int y = 0; y < this.tabJButton.length; ++y) {
            for (int x = 0; x < this.tabJButton[y].length; ++x) {
                if (modelGrid[y][x] == this.model.getLen() * this.model.getLen()) {
                    this.tabJButton[y][x].setText("");
                    this.tabJButton[y][x].setBackground(colorcasevide);
                } else {
                    this.tabJButton[y][x].setText(Integer.toString(modelGrid[y][x]));
                    this.tabJButton[y][x].setBackground(colorcasenonvide);
                }
            }
        }

    }

    public void controlFalse() {
        int[][] modelGrid = this.model.getGrid();

        for (int y = 0; y < this.tabJButton.length; ++y) {
            for (int x = 0; x < this.tabJButton[y].length; ++x) {
                this.tabJButton[y][x].setEnabled(false);
            }
        }

    }

    public void controlTrue() {
        int[][] modelGrid = this.model.getGrid();

        for (int y = 0; y < this.tabJButton.length; ++y) {
            for (int x = 0; x < this.tabJButton[y].length; ++x) {
                this.tabJButton[y][x].setEnabled(true);
            }
        }

    }

    public JButton getJButton(String n) {
        for (int y = 0; y < this.tabJButton.length; ++y) {
            for (int x = 0; x < this.tabJButton[y].length; ++x) {
                if (this.tabJButton[y][x].getText().equals(n)) {
                    return this.tabJButton[y][x];
                }
            }
        }

        return this.tabJButton[0][0];
    }

    public void jButtonSelect(String n) {
        for (int y = 0; y < this.tabJButton.length; ++y) {
            for (int x = 0; x < this.tabJButton[y].length; ++x) {
                if (this.tabJButton[y][x].getText().equals(n)) {
                    this.tabJButton[y][x].setBackground(colorcaseSelct);
                }
            }
        }

    }

    public Timer getTimer() {
        return this.timer;
    }

    public void visible(boolean b) {
        this.setVisible(b);
    }
}