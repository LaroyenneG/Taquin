package Model;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;

public class Model {

    private int[][] grid;
    private int[][] modelGrid;
    private boolean jeuCommence = false;
    private double time = 0.0D;
    private String[] sLiseUser;
    private String[] sNom;
    private String[] sTemps;
    private String[] sGrid;
    private boolean winner;
    private String nomPseudo = "";

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

    public void newGrid(int len) {
        
        this.winner = false;
        this.jeuCommence = false;
        this.time = 0.0D;
        this.grid = new int[len][len];
        int count = 1;

        for (int y = 0; y < this.grid.length; ++y) {
            for (int x = 0; x < this.grid[y].length; ++x) {
                this.grid[y][x] = count++;
            }
        }

        this.newModelGrid(len);
        this.melanger();
    }

    private boolean canSwap(int n) {
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
        if (this.canSwap(n)) {
            for (int y = 0; y < this.grid.length; ++y) {
                for (int x = 0; x < this.grid[y].length; ++x) {
                    if (this.grid[y][x] == this.grid.length * this.grid.length) {
                        this.grid[y][x] = n;
                    } else if (this.grid[y][x] == n) {
                        this.grid[y][x] = this.grid.length * this.grid.length;
                    }
                }
            }

            this.winner = this.isWin();
        }

    }

    private boolean isWin() {
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

    public String[] getListWinner() {
        DecimalFormat df = new DecimalFormat("0.#");
        this.find();
        String[] liste = new String[this.sNom.length];

        for (int i = 0; i < this.sNom.length; ++i) {
            liste[i] = this.sNom[i] + " " + df.format(Double.parseDouble(this.sTemps[i]));
        }

        return liste;
    }

    private String findTag(char c, String tab) {
        StringBuilder chaine = new StringBuilder();

        for (int i = 0; i < tab.length(); ++i) {
            if (tab.charAt(i) == 60 && tab.charAt(i + 1) == c && tab.charAt(i + 2) == 62) {
                int var6 = i + 3;
                if (var6 + 2 < tab.length()) {
                    while (tab.charAt(var6) != 60 || tab.charAt(var6 + 1) != c || tab.charAt(var6 + 2) != 62) {
                        chaine.append(tab.charAt(var6));
                        ++var6;
                    }

                    return chaine.toString();
                }
            }
        }

        return "";
    }

    private void find() {
        String file = this.readFile();
        this.searchUser(file);
        this.searchWinner();
    }

    public boolean estBestScorers(double t) {
        this.find();
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

    private void searchWinner() {
        int w = 0;
        int z = 0;

        for (String aSLiseUser : this.sLiseUser)
            if (Integer.parseInt(this.findTag('g', aSLiseUser)) == this.grid.length) {
                ++w;
            }

        this.sNom = new String[w];
        this.sTemps = new String[w];
        this.sGrid = new String[w];

        for (String aSLiseUser : this.sLiseUser) {
            if (Integer.parseInt(this.findTag('g', aSLiseUser)) == this.grid.length) {
                this.sNom[z] = this.findTag('n', aSLiseUser);
                this.sTemps[z] = this.findTag('t', aSLiseUser);
                this.sGrid[z] = this.findTag('g', aSLiseUser);
                ++z;
            }
        }

        this.trierTab();
    }

    private void searchUser(String text) {
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
        }

        this.sLiseUser = new String[nbUser];
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

                    this.sLiseUser[count] = chaine;
                    chaine = "";
                    ++count;
                    i = var7;
                }
            }
        }

    }

    private String read() {

        String result = "";

        try {
            BufferedReader ex;
            String ligne;
            for (ex = new BufferedReader(new FileReader("src/savePlayer.puzzle")); (ligne = ex.readLine()) != null; result = result + ligne)
                ;

            ex.close();
            return result;
        } catch (IOException var4) {
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
        } catch (IOException ignored) {
        } finally {
            try {
                assert bufWriter != null;
                bufWriter.close();
                fileWriter.close();
            } catch (IOException ignored) {
            }

        }

    }

    public void writeFile(String text) {
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

            this.writeFile("<u><n>" + this.nomPseudo + "<n><t>" + Double.toString(this.time) + "<t><g>" + Integer.toString(this.grid.length) + "<g><u>");
            return true;
        }
    }

    public String readFile() {
        return this.read();
    }

    public void setNomPseudo(String text) {
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
        String nombreFormat = "";
        String nombre = Double.toString(this.time);

        int i;
        for (i = 0; i < nombre.length() && nombre.charAt(i) != 46; ++i) {
            nombreFormat = nombreFormat + nombre.charAt(i);
        }

        nombreFormat = nombreFormat + ",";
        nombreFormat = nombreFormat + nombre.charAt(i + 1);
        return nombreFormat;
    }

    public int getLen() {
        return this.grid.length;
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public boolean getWinner() {
        return this.winner;
    }
}
