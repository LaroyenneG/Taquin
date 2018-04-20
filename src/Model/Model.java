package Model;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;

public class Model {
    private final String nomFichier = "src/savePlayer.puzzle";
    private int[][] grid;
    private int[][] modelGrid;
    private boolean jeuCommence = false;
    private double time = 0.0D;
    private String[] sListeUser;
    private String[] sNom;
    private String[] sTemps;
    private String[] sGrid;
    private boolean winner;
    private String nomPseudo = new String("");

    public Model() {
        this.newGrid(4);
    }

    private void newModelGrid(int taille) {
        this.modelGrid = new int[taille][taille];
        int count = 1;

        for (int y = 0; y < this.modelGrid.length; ++y) {
            for (int x = 0; x < this.modelGrid[y].length; ++x) {
                this.modelGrid[y][x] = count++;
            }
        }

    }

    public void newGrid(int taille) {
        this.winner = false;
        this.jeuCommence = false;
        this.time = 0.0D;
        this.grid = new int[taille][taille];
        int count = 1;

        for (int y = 0; y < this.grid.length; ++y) {
            for (int x = 0; x < this.grid[y].length; ++x) {
                this.grid[y][x] = count++;
            }
        }

        this.newModelGrid(taille);
        this.melanger();
    }

    private void showGrid(int[][] tab) {
        System.out.println(tab);
        System.out.println("[");

        for (int y = 0; y < tab.length; ++y) {
            System.out.print("[");

            for (int x = 0; x < tab[y].length; ++x) {
                System.out.print(tab[y][x]);
                if (x + 1 < tab[y].length) {
                    System.out.print(", ");
                }
            }

            System.out.println("]");
        }

        System.out.println("]");
    }

    private boolean peuxEchanger(int n) {
        int xvide = 0;
        int yvide = 0;
        int xcase = 0;
        int ycase = 0;

        for (int y = 0; y < this.grid.length; ++y) {
            for (int x = 0; x < this.grid[y].length; ++x) {
                if (this.grid[y][x] == this.grid.length * this.grid.length) {
                    xvide = y;
                    yvide = x;
                }

                if (this.grid[y][x] == n) {
                    xcase = y;
                    ycase = x;
                }
            }
        }

        if (ycase == yvide && xvide == xcase + 1) {
            return true;
        } else if (ycase == yvide && xvide == xcase - 1) {
            return true;
        } else if (ycase == yvide + 1 && xvide == xcase) {
            return true;
        } else return ycase == yvide - 1 && xvide == xcase;
    }

    public void echanger(int n) {
        if (this.peuxEchanger(n)) {
            for (int y = 0; y < this.grid.length; ++y) {
                for (int x = 0; x < this.grid[y].length; ++x) {
                    if (this.grid[y][x] == this.grid.length * this.grid.length) {
                        this.grid[y][x] = n;
                    } else if (this.grid[y][x] == n) {
                        this.grid[y][x] = this.grid.length * this.grid.length;
                    }
                }
            }

            this.winner = this.estGagne();
        }

    }

    private boolean estGagne() {
        if (!this.jeuCommence) {
            return false;
        } else {
            for (int y = 0; y < this.grid.length; ++y) {
                for (int x = 0; x < this.grid[y].length; ++x) {
                    if (this.grid[y][x] != this.modelGrid[y][x]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private void melanger() {
        this.jeuCommence = false;

        for (int i = 0; (double) i <= Math.pow(5.0D, (double) this.grid.length); ++i) {
            int rand = (int) (Math.random() * (double) (this.grid.length * this.grid.length - 1 - 1 + 1)) + 1;
            this.echanger(rand);
        }

        this.jeuCommence = true;
    }

    private int nbNom() {
        return this.sNom.length;
    }

    public String[] getListWinner() {
        DecimalFormat df = new DecimalFormat("0.#");
        this.chercher();
        String[] liste = new String[this.sNom.length];

        for (int i = 0; i < this.sNom.length; ++i) {
            liste[i] = this.sNom[i] + " " + df.format(Double.parseDouble(this.sTemps[i]));
        }

        return liste;
    }

    private String chercherBalise(char c, String tab) {
        String chaine = "";

        for (int i = 0; i < tab.length(); ++i) {
            boolean t = false;
            if (tab.charAt(i) == 60 && tab.charAt(i + 1) == c && tab.charAt(i + 2) == 62) {
                int var6 = i + 3;
                if (var6 + 2 < tab.length()) {
                    while (tab.charAt(var6) != 60 || tab.charAt(var6 + 1) != c || tab.charAt(var6 + 2) != 62 && var6 < tab.length()) {
                        chaine = chaine + tab.charAt(var6);
                        ++var6;
                    }

                    return chaine;
                }
            }
        }

        return "";
    }

    private void chercher() {
        String fichier = this.lireFicher();
        this.chercherUser(fichier);
        this.chercherWinner();
    }

    public boolean estBestScorres(double t) {
        this.chercher();
        int count = 0;

        for (int i = 0; i < this.sTemps.length && i < 4; ++i) {
            if (Double.parseDouble(this.sTemps[i]) < t || this.grid.length != Integer.parseInt(this.sGrid[i])) {
                ++count;
            }
        }

        return count < 3;
    }

    private void trierTab() {
        boolean trier = false;

        while (!trier) {
            trier = true;

            for (int i = 0; i < this.sTemps.length - 1; ++i) {
                if (Double.parseDouble(this.sTemps[i]) > Double.parseDouble(this.sTemps[i + 1])) {
                    String path = this.sTemps[i];
                    String path2 = this.sNom[i];
                    String path3 = this.sGrid[i];
                    this.sTemps[i] = this.sTemps[i + 1];
                    this.sTemps[i + 1] = path;
                    this.sNom[i] = this.sNom[i + 1];
                    this.sNom[i + 1] = path2;
                    this.sGrid[i] = this.sGrid[i + 1];
                    this.sGrid[i + 1] = path3;
                    trier = false;
                }
            }
        }

    }

    private void chercherWinner() {
        int w = 0;
        int z = 0;

        for (int q = 0; q < this.sListeUser.length; ++q) {
            if (Integer.parseInt(this.chercherBalise('g', this.sListeUser[q])) == this.grid.length) {
                ++w;
            }
        }

        this.sNom = new String[w];
        this.sTemps = new String[w];
        this.sGrid = new String[w];

        for (int x = 0; x < this.sListeUser.length; ++x) {
            if (Integer.parseInt(this.chercherBalise('g', this.sListeUser[x])) == this.grid.length) {
                this.sNom[z] = this.chercherBalise('n', this.sListeUser[x]);
                this.sTemps[z] = this.chercherBalise('t', this.sListeUser[x]);
                this.sGrid[z] = this.chercherBalise('g', this.sListeUser[x]);
                ++z;
            }
        }

        this.trierTab();
    }

    private void chercherUser(String text) {
        int count = 0;
        int nbUser = 0;

        int i;
        for (i = 0; i < text.length(); ++i) {
            if (text.charAt(i) == 60 && text.charAt(i + 1) == 117 && text.charAt(i + 2) == 62) {
                ++count;
            }
        }

        if (count % 2 == 0) {
            nbUser = count / 2;
        } else {
            System.out.println("Error dans le fichier");
        }

        this.sListeUser = new String[nbUser];
        String chaine = "";
        count = 0;
        boolean t = false;

        for (i = 0; i < text.length(); ++i) {
            t = false;
            if (text.charAt(i) == 60 && text.charAt(i + 1) == 117 && text.charAt(i + 2) == 62) {
                int var7 = i + 3;
                if (var7 + 2 < text.length()) {
                    while (text.charAt(var7) != 60 || text.charAt(var7 + 1) != 117 || text.charAt(var7 + 2) != 62 && var7 < text.length()) {
                        chaine = chaine + text.charAt(var7);
                        ++var7;
                    }

                    this.sListeUser[count] = chaine;
                    chaine = "";
                    ++count;
                    i = var7;
                }
            }
        }

    }

    private String lire() {
        String resultat = "";

        try {
            BufferedReader ex;
            String ligne;
            for (ex = new BufferedReader(new FileReader("src/savePlayer.puzzle")); (ligne = ex.readLine()) != null; resultat = resultat + ligne) {
            }

            ex.close();
            return resultat;
        } catch (IOException var4) {
            System.out.println("Error de lecture dans le fichier savePlayer.puzzle  <Laroyenne>");
            var4.printStackTrace();
            return "";
        }
    }

    private void ecrire(String text) {
        String filename = "src/savePlayer.puzzle";
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filename, true);
            bufWriter = new BufferedWriter(fileWriter);
            bufWriter.newLine();
            bufWriter.write(text);
            bufWriter.close();
        } catch (IOException var14) {
            System.out.println("Error d\'ecriture");
        } finally {
            try {
                bufWriter.close();
                fileWriter.close();
            } catch (IOException var13) {
                System.out.println("Error d\'ecriture");
            }

        }

    }

    public void ecrireFichier(String text) {
        this.ecrire(text);
    }

    public boolean savePlayer() {
        JOptionPane message = new JOptionPane();
        if (this.nomPseudo.equals("")) {
            JOptionPane.showMessageDialog(message, "Le nom est vide", "Erreur", 0);
            return false;
        } else {
            for (int i = 0; i < this.nomPseudo.length(); ++i) {
                if (this.nomPseudo.charAt(i) == 60 || this.nomPseudo.charAt(i) == 62) {
                    JOptionPane.showMessageDialog(message, "Caractere invalide", "Erreur", 0);
                    return false;
                }
            }

            this.ecrireFichier("<u><n>" + this.nomPseudo + "<n><t>" + Double.toString(this.time) + "<t><g>" + Integer.toString(this.grid.length) + "<g><u>");
            return true;
        }
    }

    public String lireFicher() {
        return this.lire();
    }

    public void setnomPseudo(String text) {
        this.nomPseudo = text;
    }

    public boolean getJeuCommence() {
        return this.jeuCommence;
    }

    public void setJeuCommence(boolean b) {
        this.jeuCommence = b;
    }

    public double getTime() {
        return this.time;
    }

    public void setTime(double t) {
        this.time = t;
    }

    public String getTimeFormat() {
        String nombreFormat = new String("");
        String nombre = new String(Double.toString(this.time));

        int i;
        for (i = 0; i < nombre.length() && nombre.charAt(i) != 46; ++i) {
            nombreFormat = nombreFormat + nombre.charAt(i);
        }

        nombreFormat = nombreFormat + ",";
        nombreFormat = nombreFormat + nombre.charAt(i + 1);
        return nombreFormat;
    }

    public int getTaille() {
        return this.grid.length;
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public boolean getWinner() {
        return this.winner;
    }

    private String getnom(int i) {
        return this.sNom[i];
    }

    private String gettemps(int i) {
        return this.sTemps[i];
    }

    private String getgrid(int i) {
        return this.sGrid[i];
    }
}
