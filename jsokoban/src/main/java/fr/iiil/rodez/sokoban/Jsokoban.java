package fr.iiil.rodez.sokoban;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.iiil.rodez.sokoban.model.Level;
import fr.iiil.rodez.sokoban.ui.LevelUI;
import fr.iiil.rodez.sokoban.util.LevelEditor;

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
        // Initialisation des level
        Level level = LevelEditor.createLevel1();
        // Level level = LevelEditor.createEasyTestLevel();

        // Fenetre
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Sokoban");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        fenetre.setSize(800, 600);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        fenetre.add(contentPane, BorderLayout.CENTER);
        fenetre.setVisible(true);

        LevelUI levelUI = new LevelUI(level, fenetre.getWidth(),
                fenetre.getHeight());
        fenetre.addKeyListener(levelUI);

        contentPane.add(levelUI);

    }

}
