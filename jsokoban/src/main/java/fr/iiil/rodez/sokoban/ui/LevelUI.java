package fr.iiil.rodez.sokoban.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import fr.iiil.rodez.sokoban.model.Case;
import fr.iiil.rodez.sokoban.model.Level;
import fr.iiil.rodez.sokoban.util.LevelEditor;

/**
 * Classe d'interface Swing affichant un niveau de Sokoban et gérant les
 * evenement de déplacement.
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 * 
 */
public class LevelUI extends JPanel implements KeyListener {

    private final FenetreUI fenetreUI;

    /** Le niveau associé à afficher */
    private Level level;

    /** identifiant de sérialisation */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur de création du l'UI du niveau
     * 
     * @param pFenetreUI
     *            TODO
     * 
     */
    public LevelUI(FenetreUI pFenetreUI) {
        fenetreUI = pFenetreUI;
        level = LevelEditor.getLevel(1);
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, getParent().getParent().getWidth(), getParent()
                .getParent().getHeight());

        Case[][] cases = level.getCases();
        Graphics2D g2D = (Graphics2D) g;
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[0].length; j++) {
                cases[i][j].paint(g2D, i, j, getWidth()
                        / level.getCases().length,
                        getHeight() / level.getCases()[0].length);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            level.moveEast();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            level.moveWest();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            level.moveNorth();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            level.moveSouth();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            fenetreUI.showMenu(false);
        }

        if (level.checkVictory()) {
            fenetreUI.showMenu(true);
        } else {
            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
