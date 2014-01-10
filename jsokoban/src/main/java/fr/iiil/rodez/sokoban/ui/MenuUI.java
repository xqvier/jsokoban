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
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class MenuUI extends JPanel implements KeyListener {
	/** TODO comment field role */
	private static final long serialVersionUID = 1L;

	private FenetreUI fenetre = null;

	private LevelUI levelUI = null;

	private boolean victory = false;

	private int levelSelected = 0;

	/**
	 * TODO comment initialization state
	 */
	public MenuUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	/**
	 * TODO comment initialization state
	 * 
	 * @param pFenetre
	 *            TODO
	 * @param pLevelUI
	 *            TODO
	 * 
	 */
	public MenuUI(FenetreUI pFenetre, LevelUI pLevelUI) {
		this();
		fenetre = pFenetre;
		levelUI = pLevelUI;

		this.setPreferredSize(new Dimension(800, 600));
		this.setVisible(true);
		repaint();
		validate();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#repaint()
	 */
	@Override
	public void repaint() {
		this.removeAll();
		if (getGraphics() != null) {
			getGraphics().clearRect(0, 0, getWidth(), getHeight());
		}
		JPanel textArea;
		if (victory) {
			textArea = new JPanel();
			textArea.add(new MarioLabel("Victoire !", Color.BLUE));
			this.add(textArea);
		}
		if (LevelEditor.LEVELS != null) {
			for (int i = 0; i < LevelEditor.LEVELS.size(); i++) {
				textArea = new JPanel();
				if (i == levelSelected) {
					textArea.add(new MarioLabel("x"));
				}
				textArea.add(new MarioLabel(
						LevelEditor.LEVELS.get(i).getName(), Color.RED));

				this.add(textArea);
			}
			textArea = new JPanel();
			if (levelSelected == LevelEditor.LEVELS.size()) {
				textArea.add(new MarioLabel("x"));
			}
			textArea.add(new MarioLabel("Creer un niveau"));
			this.add(textArea);
		}
		this.validate();

	}

	/**
	 * TODO comment role
	 */
	private void decrementLevel() {
		if (levelSelected == 0) {
			levelSelected = LevelEditor.LEVELS.size();
		} else {
			levelSelected--;
		}
	}

	/**
	 * TODO comment role
	 */
	private void incrementLevel() {
		if (levelSelected == LevelEditor.LEVELS.size()) {
			levelSelected = 0;
		} else {
			levelSelected++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			incrementLevel();
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			decrementLevel();
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (levelSelected == LevelEditor.LEVELS.size()) {
				fenetre.showLevelEditor();
			} else {
				levelUI.setLevel(LevelEditor.LEVELS.get(levelSelected));
				fenetre.showLevel(false);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			fenetre.showLevel(false);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public int getSelectedLevel() {
		if (levelSelected == LevelEditor.LEVELS.size()) {
			incrementLevel();
		}
		return levelSelected;

	}
}
