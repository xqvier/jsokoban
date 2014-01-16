package fr.iiil.rodez.sokoban.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.iiil.rodez.sokoban.model.Level;
import fr.iiil.rodez.sokoban.util.LevelEditor;

/**
 * Classe d'interface de la barre de menu.
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class MenuBarUI extends JMenuBar {

	/** ID de s�rialisation */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par d�faut.
	 * 
	 * @param pFenetreUI
	 *            fenetre parente.
	 * @param pLevelUI
	 *            interface de niveau pour changer un niveau.
	 */
	public MenuBarUI(final FenetreUI pFenetreUI, final LevelUI pLevelUI) {
		// Initialisation de la barre.

		// Ajout d'un menu de s�lection des niveau.
		JMenu levelsMenu = new JMenu("Niveaux");
		add(levelsMenu);
		// Ajout de chaque niveau suivant leur nom.
		for (final Level level : LevelEditor.LEVELS) {
			JMenuItem item = new JMenuItem(level.getName());
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pLevelUI.setLevel(level);
					pFenetreUI.showLevel(false);
				}
			});
			levelsMenu.add(item);
		}

		// Ajout d'un menu d'outils
		JMenu toolsMenu = new JMenu("Outils");
		add(toolsMenu);

		// Bouton de cr�ation de niveau pour pointer vers l'�dition de niveau.
		JMenuItem levelEditor = new JMenuItem("Cr�er un niveau");
		toolsMenu.add(levelEditor);
		levelEditor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pFenetreUI.showLevelEditor();
			}
		});

		// Bouton de r�initialisation des niveaux.
		JMenuItem resetLevels = new JMenuItem("R�initialiser niveaux");
		toolsMenu.add(resetLevels);
		resetLevels.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LevelEditor.initializeLevels();
				pFenetreUI.showLevel(true);
			}
		});
	}

}
