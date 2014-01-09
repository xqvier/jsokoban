/*
 * ListCaseTypeUI.java                                    6 janv. 2014 
 * 3iL3 DOO-GL 2013-2014
 */
package fr.iiil.rodez.sokoban.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.iiil.rodez.sokoban.model.Case;
import fr.iiil.rodez.sokoban.model.CaseType;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class ListCaseTypeUI extends JPanel {

    /** TODO comment field role */
    private static final long serialVersionUID = 1L;

    private final int width;
    private final int height;

    private CaseType caseTypeSelected = null;

    /**
     * TODO comment initialization state
     * 
     * @param pWidth
     *            largeur du panneau
     * @param pHeight
     *            hauteur du panneau
     */
    public ListCaseTypeUI(int pWidth, int pHeight) {
        width = pWidth;
        height = pHeight;
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, width, height);
        Graphics2D g2D = (Graphics2D) g;
        Case caze = new Case(CaseType.CHARACTER);
        caze.paint(g2D, 0, 0, width, width);
        caze = new Case(CaseType.EMPTY);
        caze.paint(g2D, 0, 1, width, width);
        caze = new Case(CaseType.HOLE);
        caze.paint(g2D, 0, 2, width, width);
        caze = new Case(CaseType.STONE);
        caze.paint(g2D, 0, 3, width, width);
        caze = new Case(CaseType.WALL);
        caze.paint(g2D, 0, 4, width, width);
    }

    /**
     * TODO comment role
     * 
     * @param y
     *            TODO
     */
    public void selectCase(int y) {

        if (y < width) {
            caseTypeSelected = CaseType.CHARACTER;
        } else if (y < width * 2) {
            caseTypeSelected = CaseType.EMPTY;
        } else if (y < width * 3) {
            caseTypeSelected = CaseType.HOLE;
        } else if (y < width * 4) {
            caseTypeSelected = CaseType.STONE;
        } else if (y < width * 5) {
            caseTypeSelected = CaseType.WALL;
        } else {
            caseTypeSelected = null;
        }

    }

    /**
     * @return the caseTypeSelected
     */
    public CaseType getCaseTypeSelected() {
        return caseTypeSelected;
    }

}
