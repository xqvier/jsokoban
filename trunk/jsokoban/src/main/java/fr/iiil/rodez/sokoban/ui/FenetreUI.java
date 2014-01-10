/*
 * FenetreUI.java                                    9 janv. 2014 
 * 3iL3 DOO-GL 2013-2014
 */
package fr.iiil.rodez.sokoban.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.iiil.rodez.sokoban.util.LevelEditor;

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
	private final LevelEditorUI levelEditorUI;

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
		menuUI = new MenuUI(this, levelUI);

		levelEditorUI = new LevelEditorUI(this);
		levelEditorUI.setPreferredSize(new Dimension(getWidth(), getHeight()));
		 showMenu(false);
//		showLevelEditor();
	}

	/**
	 * TODO comment role
	 */
	public void showLevel() {
		this.removeKeyListener(menuUI);
		levelEditorUI.removeMouseListener(levelEditorUI);
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
		this.removeKeyListener(levelUI);
		levelEditorUI.removeMouseListener(levelEditorUI);
		this.addKeyListener(menuUI);
		contentPanel.removeAll();
		contentPanel.add(menuUI, BorderLayout.CENTER);		
		menuUI.setVictory(pVictory);
		menuUI.setLevels(LevelEditor.getLevels());
		menuUI.repaint();
		validate();

	}

	public void showLevelEditor() {
		this.removeKeyListener(menuUI);
		this.removeKeyListener(levelUI);
		levelEditorUI.addMouseListener(levelEditorUI);
		contentPanel.removeAll();
		contentPanel.add(levelEditorUI, BorderLayout.CENTER);
		levelEditorUI.repaint();
		validate();

	}
}
