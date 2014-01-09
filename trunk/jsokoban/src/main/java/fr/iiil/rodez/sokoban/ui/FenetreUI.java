/*
 * FenetreUI.java                                    9 janv. 2014 
 * 3iL3 DOO-GL 2013-2014
 */
package fr.iiil.rodez.sokoban.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class FenetreUI extends JFrame {

    /** TODO comment field role */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel;
    private final LevelUI levelUI;
    private final MenuUI menuUI;

    /**
     * TODO comment initialization state
     */
    public FenetreUI() {
        this.setTitle("Sokoban");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setLayout(new BorderLayout());

        this.setSize(800, 600);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        this.add(contentPanel, BorderLayout.CENTER);

        levelUI = new LevelUI(this);
        levelUI.setPreferredSize(new Dimension(getWidth(), getHeight()));

        // LevelEditorUI levelEditorUI = new LevelEditorUI(fenetre.getWidth(),
        // fenetre.getHeight());

        // fenetre.addMouseListener(levelEditorUI);

        menuUI = new MenuUI(this, levelUI);
        showMenu(false);
        // contentPane.add(levelEditorUI);
    }

    /**
     * TODO comment role
     */
    public void hideMenu() {
        this.removeKeyListener(menuUI);
        this.addKeyListener(levelUI);
        contentPanel.removeAll();
        contentPanel.add(levelUI, BorderLayout.CENTER);
        levelUI.repaint();
        validate();
    }

    /**
     * TODO comment role
     * 
     * @param pVictory
     *            TODO
     */
    public void showMenu(boolean pVictory) {
        menuUI.setVictory(pVictory);
        contentPanel.removeAll();
        this.removeKeyListener(levelUI);
        this.addKeyListener(menuUI);
        contentPanel.add(menuUI, BorderLayout.CENTER);
        menuUI.repaint();
        validate();

    }
}
