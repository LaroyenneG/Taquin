/**
 * Created by guillaume on 08/05/16.
 */
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class Model {
    //Grid
    private int[][] grid;
    private int[][] modelGrid;
    private boolean jeuCommence;
    private double time;

    //BestScores
    private String[] sListeUser;
    private String[] sNom;
    private String[] sTemps;
    private String[] sGrid;
    private boolean winner;

    //NewReccord
    private String nomPseudo;

    //Lecture
    private final String nomFichier= "src/savePlayer.puzzle";



    public Model(){
        //ntialisation
        jeuCommence=false;
        time=0.0;
        nomPseudo=new String("");
        //Valeur par default
        newGrid(4);
    }
//------------------------------------Grid-------------------------------------------------------------------------
    private void newModelGrid(int taille){
        //initialisation
        modelGrid = new int[taille][taille];

        //Compteurs
        int x,y,count=1;

        //initialisation de la grille
        for(y=0;y<modelGrid.length;y++){
            for (x=0;x<modelGrid[y].length;x++){
                modelGrid[y][x]=count;
                count++;
            }
        }

    }

    public void newGrid(int taille){
        //Valeur de debut de jeu
        winner=false;
        jeuCommence=false;
        time=0.0;

        //initialisation
        grid = new int[taille][taille];

        //Compteurs
        int x,y,count=1;

        //initialisation de la grille
        for(y=0;y<grid.length;y++){
            for (x=0;x<grid[y].length;x++){
                grid[y][x]=count;
                count++;
            }
        }
        newModelGrid(taille);
        melanger();

    }

    private void showGrid(int [][] tab){
        //Compteurs
        int x,y;

        System.out.println(tab);
        System.out.println("[");
        for(y=0;y<tab.length;y++){
            System.out.print("[");
            for (x=0;x<tab[y].length;x++){
                System.out.print(tab[y][x]);
                if(x+1<tab[y].length){
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    private boolean peuxEchanger(int n) {
        //Compteurs
        int y, x;
        //Intialisation
        int xvide = 0,yvide = 0, xcase = 0, ycase = 0;

        //Recherche des coordonnées de case vide et case choisi
        for (y = 0; y < grid.length; y++) {
            for (x = 0; x < grid[y].length; x++) {
                if (grid[y][x]==(grid.length*grid.length)) {
                    xvide = y;
                    yvide = x;
                }
                if (grid[y][x]==n) {
                    xcase = y;
                    ycase = x;
                }
            }
        }

        //Conditions d'un echange dans les regles
        if ((ycase == yvide) && (xvide == xcase + 1)) {
            return true;
        }
        if ((ycase == yvide) && (xvide == xcase - 1)) {
            return true;
        }
        if ((ycase == yvide + 1) && (xvide == xcase)) {
            return true;
        }
        if ((ycase == yvide - 1) && (xvide == xcase)) {
            return true;
        }
        return false;
    }

    public void echanger(int n) {
        //Compteurs
        int y, x;

        if(peuxEchanger(n)){
            //Parcour du tableau
            for (y = 0; y < grid.length; y++) {
                for (x = 0; x < grid[y].length; x++) {
                    if (grid[y][x]==(grid.length*grid.length)) {
                        grid[y][x]=n;
                    } else {
                        if (grid[y][x]==n) {
                            grid[y][x]=grid.length*grid.length;
                        }
                    }
                }
            }
            winner=estGagne();
        }

    }

    private boolean estGagne() {
        //Compteurs
        int y, x;

        if (!jeuCommence) {
            return false;
        }
        //Recherche d'un difference avec la grille resolu
        for (y = 0; y < grid.length; y++) {
            for (x = 0; x < grid[y].length; x++) {
                if (grid[y][x]!=modelGrid[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }


    private void melanger() {
        int rand,i;

        //Jeu en pause
        jeuCommence=false;

        for (i = 0; i <= Math.pow(5.0,grid.length); i++) {
            rand = (int)(Math.random() * ((grid.length * grid.length - 1) - 1 + 1)) + 1;
            echanger(rand);
        }
        //Debut du jeu
        jeuCommence = true;
    }



    //---------------------------------------BestScore----------------------------------------------------------------

    private int nbNom(){
        return(sNom.length);
    }

    public String[] getListWinner(){
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.#");
        int i;

        chercher();
        String[] liste = new String [sNom.length];

        for(i=0;i<sNom.length;i++){
            liste[i]=sNom[i]+" "+df.format(Double.parseDouble(sTemps[i]));
        }
        return liste;
    }

    private String chercherBalise(char c, String tab) {
        String chaine = "";

        //Compteurs
        int t, i;

        for (i = 0; i < tab.length(); i++) {
            t = 0;
            if (tab.charAt(i) == '<') {
                if (tab.charAt(i + 1) == c) {
                    if (tab.charAt(i + 2) == '>') {
                        t = i + 3;
                        if (t + 2 < tab.length()) {
                            while (tab.charAt(t) != '<' || tab.charAt(t + 1) != c || tab.charAt(t + 2) != '>' && (t < tab.length())) {
                                chaine = chaine + tab.charAt(t);
                                t++;
                            }
                            return chaine;
                        }
                    }
                }
            }
        }
        return "";
    }

    private void chercher(){
        String fichier;
        fichier = lireFicher();
        chercherUser(fichier);
        chercherWinner();
    }

    public boolean estBestScorres(double t){
        chercher();
        //Compteurs
        int i, count;

        //Initialisation
        count=0;

        for(i=0;i<sTemps.length && i<4;i++){
            if(Double.parseDouble(sTemps[i]) < t || grid.length!=Integer.parseInt(sGrid[i])){
                count++;
            }
        }

        if (count>=3){
            return false;
        }

        return true;
    }

    private void trierTab() {
        //Initialisation
        int i;
        String path,path2,path3;
        boolean trier = false;

        //Trie des tableaux
        while (!trier) {
            trier = true;
            for (i = 0; i < sTemps.length - 1; i++) {
                if ((Double.parseDouble(sTemps[i])) > (Double.parseDouble(sTemps[i + 1]))) {
                    path = sTemps[i];
                    path2 = sNom[i];
                    path3 = sGrid[i];
                    sTemps[i] = sTemps[i + 1];
                    sTemps[i + 1] = path;
                    sNom[i] = sNom[i + 1];
                    sNom[i + 1] = path2;
                    sGrid[i] = sGrid[i + 1];
                    sGrid[i + 1] = path3;
                    trier = false;
                }
            }
        }
    }

    private void chercherWinner() {
        //Compteurs
        int q,w=0,x,z=0;

        //Compte le nombre de joueur
        for (q = 0; q < sListeUser.length; q++) {
            if(Integer.parseInt(chercherBalise('g', sListeUser[q]))==grid.length){
                w++;
            }
        }

        sNom = new String[w];
        sTemps = new String[w];
        sGrid = new String[w];

        for (x = 0; x < sListeUser.length; x++) {
            if(Integer.parseInt(chercherBalise('g', sListeUser[x]))==grid.length){
                sNom[z] = chercherBalise('n', sListeUser[x]);
                sTemps[z] = chercherBalise('t', sListeUser[x]);
                sGrid[z] = chercherBalise('g', sListeUser[x]);
                z++;
            }

        }
        trierTab();
    }

    private void chercherUser(String text) {

        int i,count = 0, nbUser = 0;

        for (i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '<') {
                if (text.charAt(i + 1) == 'u') {
                    if (text.charAt(i + 2) == '>') {
                        count++;
                    }
                }
            }
        }
        if (count % 2 == 0) {
            nbUser = count / 2;
        } else {
            System.out.println("Error dans le fichier");
        }
        sListeUser = new String[nbUser];
        String chaine = "";
        count = 0;
        int t = 0;
        for (i = 0; i < text.length(); i++) {
            t = 0;
            if (text.charAt(i) == '<') {
                if (text.charAt(i + 1) == 'u') {
                    if (text.charAt(i + 2) == '>') {
                        t = i + 3;
                        if (t + 2 < text.length()) {
                            while (text.charAt(t) != '<' || text.charAt(t + 1) != 'u' || text.charAt(t + 2) != '>' && (t < text.length())) {
                                chaine = chaine + text.charAt(t);
                                t++;
                            }
                            sListeUser[count] = chaine;
                            chaine = "";
                            count++;
                            i = t;
                        }
                    }
                }
            }
        }
    }

    //-----------------------------------------------------Lecture du fichier------------------------------------------------------------
    private String lire() {
        String resultat = "";
        try {
            BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));

            String ligne;
            while ((ligne = fichier.readLine()) != null) {
                resultat = resultat + ligne;
            }
            fichier.close();
        } catch (IOException ex) {
            System.out.println("Error de lecture dans le fichier savePlayer.puzzle  <Laroyenne>");
            ex.printStackTrace();
            return "";
        }
        return resultat;
    }


    private void ecrire(String text) {
        String filename = nomFichier;
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename, true);
            bufWriter = new BufferedWriter(fileWriter);
            bufWriter.newLine();
            bufWriter.write(text);
            bufWriter.close();
        } catch (IOException ex) {
            System.out.println("Error d'ecriture");
        } finally {
            try {
                bufWriter.close();
                fileWriter.close();
            } catch (IOException ex) {
                System.out.println("Error d'ecriture");
            }
        }
    }

    public void ecrireFichier(String text){
        ecrire(text);
    }




    //--------------------------------------------------------NewReccord------------------------------------------------------------------------------------------------------


    public boolean savePlayer(){
        JOptionPane message = new JOptionPane();
        if(this.nomPseudo.equals("")){
            message.showMessageDialog( message, "Le nom est vide", "Erreur", JOptionPane.ERROR_MESSAGE );
            return false;
        }else{
            for(int i=0;i<nomPseudo.length();i++){
                if(nomPseudo.charAt(i)=='<'||nomPseudo.charAt(i)=='>'){
                    message.showMessageDialog( message, "Caractere invalide", "Erreur", JOptionPane.ERROR_MESSAGE );
                    return false;
                }
            }
            ecrireFichier("<u><n>"+this.nomPseudo+"<n><t>"+Double.toString(time)+"<t><g>"+Integer.toString(grid.length)+"<g><u>");
        }
        return true;

    }

    public String lireFicher(){
        return lire();
    }

    public void setnomPseudo(String text){
        nomPseudo=text;
    }

    public boolean getJeuCommence(){
        return jeuCommence;
    }
    public void setJeuCommence(boolean b){
        jeuCommence=b;
    }

    public double getTime(){
        return time;
    }

    public String getTimeFormat(){
        String nombreFormat=new String("");
        String nombre=new String(Double.toString(time));

        //Compteur
        int i;

        for (i=0;(i<nombre.length())&&(nombre.charAt(i)!='.');i++){
            nombreFormat=nombreFormat+nombre.charAt(i);
        }
        nombreFormat=nombreFormat+",";
        nombreFormat=nombreFormat+nombre.charAt(i+1);
        return nombreFormat;
    }

    public int getTaille(){return grid.length;}

    public int[][] getGrid(){return grid;}

    public boolean getWinner(){
        return winner;
    }

    public void setTime(double t){
        time=t;
    }

    private String getnom(int i){
        return sNom[i];
    }
    private String gettemps(int i){
        return sTemps[i];
    }
    private String getgrid(int i){
        return sGrid[i];
    }

}
