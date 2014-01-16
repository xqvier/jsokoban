/*
 * MenuUI.java                                    9 janv. 2014 
 * 3iL3 DOO-GL 2013-2014
 */
package fr.iiil.rodez.sokoban.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.iiil.rodez.sokoban.util.LevelEditor;

/**
 * Classe d'interface du menu principale du jeu.
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class MenuUI extends JPanel implements KeyListener {
	/** ID de s�rialisation */
	private static final long serialVersionUID = 1L;

	/** Fenetre parente. */
	private FenetreUI fenetre = null;

	/** Interface de niveau */
	private LevelUI levelUI = null;

	/** Information pour savoir si le menu affiche victoire */
	private boolean victory = false;

	/** Le niveau selectionn� dans le menu */
	private int levelSelected = 0;

	/**
	 * Constructeur par d�faut
	 */
	public MenuUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		setPreferredSize(new Dimension(800, 600));
		setVisible(true);

		repaint();
		validate();
	}

	/**
	 * Constructeur
	 * 
	 * @param pFenetre
	 *            fenetre parente
	 * @param pLevelUI
	 *            interface de niveau
	 */
	public MenuUI(FenetreUI pFenetre, LevelUI pLevelUI) {
		this();
		fenetre = pFenetre;
		levelUI = pLevelUI;
	}

	/**
	 * @return the levelSelected
	 */
	public int getLevelSelected() {
		if (levelSelected == LevelEditor.LEVELS.size()) {
			incrementLevel();
		}
		return levelSelected;
	}

	/**
	 * @param victory
	 *            the victory to set
	 */
	public void setVictory(boolean victory) {
		this.victory = victory;
		if (victory) {
			incrementLevel();
		}

	}

	@Override
	public void repaint() {
		// Clean de l'interface
		removeAll();

		JPanel textArea;
		// Ajout d'un texte de victoire.
		if (victory) {
			textArea = new JPanel();
			textArea.add(new MarioLabel("Victoire !", Color.BLUE));
			add(textArea);
		}

		// Si les niveaux ont �t� intialis�s.
		if (LevelEditor.LEVELS != null) {
			// Affichage des niveaux disponibles
			for (int i = 0; i < LevelEditor.LEVELS.size(); i++) {
				textArea = new JPanel();
				if (i == levelSelected) {
					// Distinction pour le niveau selectionn�
					textArea.add(new MarioLabel("x"));
				}
				textArea.add(new MarioLabel(
						LevelEditor.LEVELS.get(i).getName(), Color.RED));
				add(textArea);
			}

			// Ajout d'un menu pour cr�er un niveau.
			textArea = new JPanel();
			if (levelSelected == LevelEditor.LEVELS.size()) {
				textArea.add(new MarioLabel("x"));
			}
			textArea.add(new MarioLabel("Creer un niveau"));
			add(textArea);
		}

		// Rafraichissement de l'interface
		validate();

	}

	/**
	 * M�thode pour d�cr�menter le niveau selectionn� (si le niveau �tait 0 on
	 * repart � la fin de la liste).
	 */
	private void decrementLevel() {
		if (levelSelected == 0) {
			levelSelected = LevelEditor.LEVELS.size();
		} else {
			levelSelected--;
		}
	}

	/**
	 * M�thode pour incr�menter le niveau selectionn� (si le niveau �tait
	 * "cr�ation de niveau" on repart au d�but de la liste).
	 */
	private void incrementLevel() {
		if (levelSelected == LevelEditor.LEVELS.size()) {
			levelSelected = 0;
		} else {
			levelSelected++;
		}
	}

	@Override
	@Deprecated
	public void keyTyped(KeyEvent e) {
		// Non utilis�
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed");

		// Navigation dans le menu.
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			incrementLevel();
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			decrementLevel();
			repaint();
		}
		// Selection d'une entr�e du menu.
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// Si cr�ation de niveau on affiche l'�diteur
			if (levelSelected == LevelEditor.LEVELS.size()) {
				fenetre.showLevelEditor();
			}
			// Sinon on affiche le niveau selectionn�
			else {
				levelUI.setLevel(LevelEditor.LEVELS.get(levelSelected));
				fenetre.showLevel(false);
			}
		}
		// Sortie du menu vers le niveau pr�cedement jou�, pas de reset
		// effectu�.
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			fenetre.showLevel(false);
		}

	}

	@Override
	@Deprecated
	public void keyReleased(KeyEvent e) {
		// Non utilis�
	}

}
