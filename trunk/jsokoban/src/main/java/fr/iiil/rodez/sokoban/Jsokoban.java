package fr.iiil.rodez.sokoban;

import javax.swing.JFrame;

import fr.iiil.rodez.sokoban.ui.FenetreUI;

/**
 * Classe principale de lancement du jeu Sokoban
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 * 
 */
public class Jsokoban {

    /**
     * Point d'entrée de lancement du jeu Sokoban
     * 
     * @param args
     *            liste des arguments passé au programme
     */
    public static void main(String[] args) {

        // Fenetre
        JFrame fenetre = new FenetreUI();

        fenetre.setVisible(true);

    }

}
