package fr.iiil.rodez.sokoban.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import fr.iiil.rodez.sokoban.model.Level;
import fr.iiil.rodez.sokoban.util.LevelEditor;

public class LevelEditorCreateDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MarioTextField levelWidth = new MarioTextField("12");
	private MarioTextField levelHeight = new MarioTextField("12");
	private MarioTextField levelName = new MarioTextField();

	public LevelEditorCreateDialog(final FenetreUI pFenetreUI,
			final LevelEditorUI pLevelEditorUI) {
		super();
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setSize(new Dimension(400, 300));
		add(new MarioLabel("Largeur : "));
		add(levelWidth);
		add(new MarioLabel("Hauteur : "));
		add(levelHeight);
		add(new MarioLabel("Nom du niveau : "));
		add(levelName);
		JButton valid = new JButton();
		valid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final MarioLabel sizeCantBeLessOrEqualZero = new MarioLabel(
						"La hauteur ou la largeur ne peut etre inferieur a 0.");
				final MarioLabel nameAlreadyExistMessage = new MarioLabel(
						"Ce nom de niveau est deja pris.");
				final MarioLabel nameCantBeEmptyMessage = new MarioLabel(
						"Le nom du niveau ne peut etre vide.");
				boolean inputCorrect = true;
				boolean nameAlreadyExist = false;
				boolean showUI = true;

				int width = 0;
				int height = 0;
				try {
					width = Integer.valueOf(levelWidth.getText());
					height = Integer.valueOf(levelHeight.getText());
				} catch (NumberFormatException ex) {
					inputCorrect = false;
				}

				if (width <= 0 || height <= 0) {
					inputCorrect = false;
				}

				if (!inputCorrect) {
					showUI = false;
				}

				if (!"".equals(levelName.getText())) {
					for (Level level : LevelEditor.LEVELS) {
						if (levelName.getText().equals(level.getName())) {
							nameAlreadyExist = true;
						}
					}
					if (nameAlreadyExist) {
						showUI = false;
					}
				} else {
					showUI = false;
				}
				if (showUI) {
					setVisible(false);
					pLevelEditorUI.showUI();
				} else {
					remove(nameAlreadyExistMessage);
					remove(nameCantBeEmptyMessage);
					remove(sizeCantBeLessOrEqualZero);
					if (!inputCorrect) {
						add(sizeCantBeLessOrEqualZero);
					}
					if ("".equals(levelName.getText())) {
						add(nameCantBeEmptyMessage);
					}
					if (nameAlreadyExist) {
						add(nameAlreadyExistMessage);
					}
					pack();
					revalidate();
					repaint();
				}
			}
		});
		valid.add(new MarioLabel("Creer"));
		add(valid);
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				pFenetreUI.showMenu(false);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		add(new JLabel());
		setVisible(true);
		pack();
		validate();
	}

	public int getLevelHeight() {
		return Integer.parseInt(levelHeight.getText());
	}

	public int getLevelWidth() {
		return Integer.parseInt(levelWidth.getText());
	}
	
	public String getLevelName(){
		return levelName.getText();
	}

}
