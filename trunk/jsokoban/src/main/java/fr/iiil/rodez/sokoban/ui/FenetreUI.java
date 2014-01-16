/*
 * FenetreUI.java                                    9 janv. 2014 
 * 3iL3 DOO-GL 2013-2014
 */
package fr.iiil.rodez.sokoban.ui;

import java.awt.BorderLayout;
import java.awt.Color;
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
 * Classe d'affichage de la fenêtre principale.<br />
 * Gère la transition vers les interfaces de menu, de niveau ou d'édition de
 * niveau.<br />
 * Contient la barre de menu.
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class FenetreUI extends JFrame {

	/** ID de sérialisation */
	private static final long serialVersionUID = 1L;

	/** Panneau principale de la fenetre. */
	private final JPanel contentPanel;

	/** Interface d'un niveau */
	private final LevelUI levelUI;

	/** Interface du menu */
	private final MenuUI menuUI;

	/** Interface de l'édition de niveau */
	private final LevelEditorUI levelEditorUI;

	/**
	 * Constructeur par défaut.
	 */
	public FenetreUI() {
		// Configuration par défaut.
		this.setTitle("Sokoban");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setResizable(false);

		// Ajout du panneau principale
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		this.add(contentPanel, BorderLayout.CENTER);

		// Initialisation de l'interface de niveau
		levelUI = new LevelUI(this);
		// FIXME levelUI.setPreferredSize(new Dimension(getWidth(),
		// getHeight()));

		// Initialisation de l'interface du menu
		menuUI = new MenuUI(this, levelUI);

		// Initialisation de l'interface de l'édition de niveau.
		levelEditorUI = new LevelEditorUI(this);
		// FIXME levelEditorUI.setPreferredSize(new Dimension(getWidth(),
		// getHeight()));

		// Ajout de la barre de menu.
		setJMenuBar(new MenuBarUI(this, levelUI));

		// Affichage du menu.
		showMenu(false);
	}

	/**
	 * Méthode basculant l'affichage vers le niveau
	 * 
	 * @param pReset
	 *            true si le niveau doit être reset, false sinon.
	 */
	public void showLevel(boolean pReset) {
		// Clean de l'interface
		contentPanel.removeAll();
		this.removeKeyListener(levelUI);
		this.removeKeyListener(menuUI);
		levelEditorUI.removeMouseListener(levelEditorUI);

		// Ajout de l'écoute des touches pour le niveau.
		this.addKeyListener(levelUI);

		// Ajout de l'interface de niveau au panneau principal
		contentPanel.add(levelUI, BorderLayout.CENTER);

		// Reset du niveau
		if (pReset) {
			levelUI.setLevel(LevelEditor.LEVELS.get(menuUI.getLevelSelected()));
		}

		// Rafraichissement des changements
		levelUI.repaint();
		validate();
	}

	/**
	 * Méthode basculant l'affichage vers le menu
	 * 
	 * @param pVictory
	 *            true si le menu doit afficher la victoire, false sinon.
	 */
	public void showMenu(boolean pVictory) {
		// Clean de l'interface
		contentPanel.removeAll();
		this.removeKeyListener(levelUI);
		levelEditorUI.removeMouseListener(levelEditorUI);

		// Ajout de l'écoute des touches pour le menu.
		this.addKeyListener(menuUI);

		// Ajout de l'interface de menu au panneau principal
		contentPanel.add(menuUI, BorderLayout.CENTER);

		// Propagation de l'information de victoire.
		menuUI.setVictory(pVictory);

		// Rafraichissement des changements.
		menuUI.repaint();
		validate();

		// Si victoire
		if (pVictory) {
			// Lecture d'un son.
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

			// Création d'une "popup" d'affichage de la victoire.
			final JDialog victoryDialog = new JDialog();
			victoryDialog.setLayout(new BoxLayout(victoryDialog
					.getContentPane(), BoxLayout.PAGE_AXIS));
			victoryDialog.setSize(400, 120);
			// Positionnement de la popup au centre de la fenetre
			victoryDialog.setLocation(
					(getWidth() - victoryDialog.getWidth()) / 2,
					(getHeight() - victoryDialog.getHeight()) / 2);

			// Ajout d'un texte de victoire.
			JPanel textArea = new JPanel();
			textArea.add(new MarioLabel("Victoire !", Color.BLUE));
			victoryDialog.add(textArea);

			// Ajout d'un bouton pour continuer et fermer la popup.
			textArea = new JPanel();
			JButton valid = new JButton();
			valid.add(new MarioLabel("Appuyez sur espace"));
			valid.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					victoryDialog.setVisible(false);

				}
			});
			textArea.add(valid);
			victoryDialog.add(textArea);

			// Affichage de la popup.
			victoryDialog.setVisible(true);
			victoryDialog.validate();
			valid.transferFocus();
		}

	}

	/**
	 * Méthode basculant l'affichage vers l'édition de niveau.
	 */
	public void showLevelEditor() {
		// Clean de l'interface
		contentPanel.removeAll();
		this.removeKeyListener(menuUI);
		this.removeKeyListener(levelUI);

		// Ajout de l'écoute de la souris sur l'éditeur de niveau
		levelEditorUI.addMouseListener(levelEditorUI);

		// Ajout de l'interface d'édition de niveau à la fenetre.
		contentPanel.add(levelEditorUI, BorderLayout.CENTER);

		// Rafraichissement de l'interface
		levelEditorUI.launch();
		validate();

	}
}
