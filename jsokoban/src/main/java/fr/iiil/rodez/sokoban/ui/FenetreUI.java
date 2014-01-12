/*
 * FenetreUI.java                                    9 janv. 2014 
 * 3iL3 DOO-GL 2013-2014
 */
package fr.iiil.rodez.sokoban.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
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

		this.setSize(800, 600);
		this.setResizable(false);
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		this.add(contentPanel, BorderLayout.CENTER);

		levelUI = new LevelUI(this);
		levelUI.setPreferredSize(new Dimension(getWidth(), getHeight()));
		menuUI = new MenuUI(this, levelUI);
		setJMenuBar(new MenuBarUI(this, levelUI));

		levelEditorUI = new LevelEditorUI(this);
		levelEditorUI.setPreferredSize(new Dimension(getWidth(), getHeight()));
		showMenu(false);
		// showLevelEditor();
	}

	/**
	 * TODO comment role
	 * 
	 * @param pReset
	 *            TODO
	 */
	public void showLevel(boolean pReset) {
		this.removeKeyListener(levelUI);
		this.removeKeyListener(menuUI);
		levelEditorUI.removeMouseListener(levelEditorUI);
		this.addKeyListener(levelUI);
		contentPanel.removeAll();
		contentPanel.add(levelUI, BorderLayout.CENTER);
		if (pReset) {
			levelUI.setLevel(LevelEditor.LEVELS.get(menuUI.getSelectedLevel()));
		}
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
		this.removeKeyListener(menuUI);
		this.removeKeyListener(levelUI);
		levelEditorUI.removeMouseListener(levelEditorUI);
		this.addKeyListener(menuUI);
		contentPanel.removeAll();
		contentPanel.add(menuUI, BorderLayout.CENTER);
		menuUI.setVictory(pVictory);
		menuUI.repaint();
		validate();

		if (pVictory) {
			Clip clip;
			try {
				clip = AudioSystem.getClip();
				AudioInputStream inputStream = AudioSystem
						.getAudioInputStream(ClassLoader
								.getSystemResourceAsStream("wololo.wav"));
				clip.open(inputStream);
				clip.start();

			} catch (LineUnavailableException e) {
				throw new RuntimeException(e);
			} catch (UnsupportedAudioFileException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			final JDialog victoryDialog = new JDialog();
			JPanel textArea = new JPanel();
			victoryDialog.setLayout(new BoxLayout(victoryDialog
					.getContentPane(), BoxLayout.PAGE_AXIS));
			victoryDialog.setSize(400, 120);
			textArea.add(new MarioLabel("Victoire !", Color.BLUE));
			victoryDialog.add(textArea);

			textArea = new JPanel();
			JButton valid = new JButton();
			valid.add(new MarioLabel("Appuyez sur espace"));
			valid.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					victoryDialog.setVisible(false);

				}
			});
			textArea.add(valid);
			victoryDialog.add(textArea);

			victoryDialog.setVisible(true);
			victoryDialog.validate();
			valid.transferFocus();
		}

	}

	public void showLevelEditor() {
		this.removeKeyListener(menuUI);
		this.removeKeyListener(levelUI);
		levelEditorUI.addMouseListener(levelEditorUI);
		contentPanel.removeAll();
		contentPanel.add(levelEditorUI, BorderLayout.CENTER);
		levelEditorUI.launch();
		validate();

	}
}
