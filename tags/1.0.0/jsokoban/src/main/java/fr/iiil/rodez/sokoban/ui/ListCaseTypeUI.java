/*
 * ListCaseTypeUI.java                                    6 janv. 2014 
 * 3iL3 DOO-GL 2013-2014
 */
package fr.iiil.rodez.sokoban.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.iiil.rodez.sokoban.model.Case;
import fr.iiil.rodez.sokoban.model.CaseType;

/**
 * Classe d'interface présentant les cases disponible lors de l'édition d'un
 * niveau.
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class ListCaseTypeUI extends JPanel {

	/** ID de sérialisation */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut
	 */
	public ListCaseTypeUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	@Override
	public void paint(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2D = (Graphics2D) g;

		// Clean de l'interface
		g.clearRect(0, 0, width, height);

		// Dessin de chaque type de case.
		Case caze = new Case(CaseType.CHARACTER);
		caze.paint(g2D, 0, 0, width, width, true);
		caze = new Case(CaseType.EMPTY);
		caze.paint(g2D, 0, 1, width, width, true);
		caze = new Case(CaseType.HOLE);
		caze.paint(g2D, 0, 2, width, width, true);
		caze = new Case(CaseType.STONE);
		caze.paint(g2D, 0, 3, width, width, true);
		caze = new Case(CaseType.WALL);
		caze.paint(g2D, 0, 4, width, width, true);
	}

	/**
	 * Méthode de selection d'une case en fonction de sa position (en partant du
	 * haut, en pixel).<br />
	 * Cette méthode est le plus souvent utilisé lors du clique sur l'interface.
	 * 
	 * @param y
	 *            L'abscisse sélectionné
	 * @return Le type de case selectionné.
	 */
	public CaseType selectCase(int y) {
		int width = getWidth();
		int pos = y / width;
		CaseType caseTypeSelected = null;

		switch (pos) {
		case 0:
			caseTypeSelected = CaseType.CHARACTER;
			break;
		case 1:
			caseTypeSelected = CaseType.EMPTY;
			break;
		case 2:
			caseTypeSelected = CaseType.HOLE;
			break;
		case 3:
			caseTypeSelected = CaseType.STONE;
			break;
		case 4:
			caseTypeSelected = CaseType.WALL;
			break;
		default:
			caseTypeSelected = null;
			break;
		}

		return caseTypeSelected;

	}

}
