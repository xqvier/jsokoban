package fr.iiil.rodez.sokoban.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.iiil.rodez.sokoban.model.Level;
import fr.iiil.rodez.sokoban.util.LevelEditor;

public class MenuBarUI extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MenuBarUI(final FenetreUI pFenetreUI, final LevelUI pLevelUI) {
		JMenu levelsMenu = new JMenu("Niveaux");
		add(levelsMenu);
		for(final Level level : LevelEditor.LEVELS){
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
		
		JMenu toolsMenu = new JMenu("Outils");
		add(toolsMenu);
		JMenuItem levelEditor = new JMenuItem("Créer un niveau");
		toolsMenu.add(levelEditor);
		levelEditor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pFenetreUI.showLevelEditor();				
			}
		});
		JMenuItem resetLevels = new JMenuItem("Réinitialiser niveaux");
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
