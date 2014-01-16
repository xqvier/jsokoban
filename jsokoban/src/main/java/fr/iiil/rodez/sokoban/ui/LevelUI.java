package fr.iiil.rodez.sokoban.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import fr.iiil.rodez.sokoban.model.Case;
import fr.iiil.rodez.sokoban.model.Level;

/**
 * Classe d'interface Swing affichant un niveau de Sokoban et gérant les
 * evenement de déplacement.
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class LevelUI extends JPanel implements KeyListener {

	/** Fenetre parente de l'interface. */
	private final FenetreUI fenetreUI;

	/** Le niveau associé à afficher */
	private Level level;

	/** identifiant de sérialisation */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de création du l'UI du niveau
	 * 
	 * @param pFenetreUI
	 *            fenetre parente.
	 */
	public LevelUI(FenetreUI pFenetreUI) {
		fenetreUI = pFenetreUI;
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
		// Clean de l'interface
		g.clearRect(0, 0, getParent().getParent().getWidth(), getParent()
				.getParent().getHeight());

		// Pour chaque case, appel de sa méthode de dessin
		Case[][] cases = level.getCases();
		Graphics2D g2D = (Graphics2D) g;
		for (int i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[0].length; j++) {
				cases[i][j].paint(g2D, i, j, getWidth()
						/ level.getCases().length,
						getHeight() / level.getCases()[0].length, false);
			}
		}
	}

	@Override
	@Deprecated
	public void keyTyped(KeyEvent e) {
		// Non utilisé
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Demande de mouvement.
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
		} // Sortie vers le menu
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			fenetreUI.showMenu(false);
		}

		// Vérification de la victoire à chaque mouvement.
		if (level.checkVictory()) {
			// Affichage du menu.
			fenetreUI.showMenu(true);
		} else {
			// Rafraichissement de l'interface
			repaint();
		}

	}

	@Override
	@Deprecated
	public void keyReleased(KeyEvent e) {
		// Non utilisé
	}

}
