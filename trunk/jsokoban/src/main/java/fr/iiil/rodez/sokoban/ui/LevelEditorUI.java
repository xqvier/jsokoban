/*
 * LevelEditorUI.java                                    6 janv. 2014 
 * 3iL3 DOO-GL 2013-2014
 */
package fr.iiil.rodez.sokoban.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import fr.iiil.rodez.sokoban.model.CaseType;
import fr.iiil.rodez.sokoban.model.Level;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class LevelEditorUI extends JPanel implements MouseListener {

    /** TODO comment field role */
    private static final long serialVersionUID = 1L;

    private final Level level;
    private final LevelUI levelUI;

    private final ListCaseTypeUI listCaseTypeUI;

    private final int width;
    private final int height;

    /**
     * TODO comment initialization state
     * 
     * @param pWidth
     *            largeur du panneau
     * @param pHeight
     *            hauteur du panneau
     */
    public LevelEditorUI(int pWidth, int pHeight) {
        width = pWidth;
        height = pHeight;
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        level = new Level();
        levelUI = new LevelUI();
        listCaseTypeUI = new ListCaseTypeUI(45, height);
        this.add(levelUI, BorderLayout.CENTER);
        this.add(listCaseTypeUI, BorderLayout.EAST);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked");
        if (e.getX() > getWidth() - 45) {
            listCaseTypeUI.selectCase(e.getY());
            System.out.println("select case : "
                    + listCaseTypeUI.getCaseTypeSelected());
        } else {
            CaseType caseType = listCaseTypeUI.getCaseTypeSelected();
            if (caseType != null) {
                if (caseType.equals(CaseType.CHARACTER)) {
                    level.setCharacter((level.getWidthSize() * e.getX())
                            / (getWidth() - 50),
                            (level.getHeightSize() * e.getY()) / (getHeight()));
                }

                levelUI.repaint();

            }

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
