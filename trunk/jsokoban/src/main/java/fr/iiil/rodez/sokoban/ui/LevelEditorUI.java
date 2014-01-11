/*
 * LevelEditorUI.java                                    6 janv. 2014 
 * 3iL3 DOO-GL 2013-2014
 */
package fr.iiil.rodez.sokoban.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.iiil.rodez.sokoban.model.Case;
import fr.iiil.rodez.sokoban.model.CaseType;
import fr.iiil.rodez.sokoban.model.Level;
import fr.iiil.rodez.sokoban.util.LevelEditor;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class LevelEditorUI extends JPanel implements MouseListener {

	/** TODO comment field role */
	private static final long serialVersionUID = 1L;

	private FenetreUI fenetreUI;

	private LevelEditorCreateDialog dialog;

	private Level level = new Level();
	private LevelUI levelUI = new LevelUI(null);

	private ListCaseTypeUI listCaseTypeUI = new ListCaseTypeUI();

	private CaseType selectedCaseType;

	/**
	 * TODO comment initialization state
	 * 
	 * @param pWidth
	 *            largeur du panneau
	 * @param pHeight
	 *            hauteur du panneau
	 */
	public LevelEditorUI(FenetreUI pFenetreUI) {
		fenetreUI = pFenetreUI;
		this.setLayout(new BorderLayout());

	}

	public void launch() {
		dialog = new LevelEditorCreateDialog(fenetreUI, this);
	}

	void showUI() {
		this.setLayout(new BorderLayout());
		this.removeAll();
		level = new Level(dialog.getLevelHeight(), dialog.getLevelWidth());
		level.setName(dialog.getLevelName());
		levelUI.setLevel(level);

		int listCaseTypeUIWidth = getWidth()
				/ (dialog.getLevelWidth() + 1);

		listCaseTypeUI.setPreferredSize(new Dimension(listCaseTypeUIWidth,
				getHeight()));
		JPanel saveArea = new JPanel();

		JButton save = new JButton();
		saveArea.setLayout(new GridLayout(1, 3));
		save.add(new MarioLabel(" Sauvegarder !"));
		saveArea.add(new JPanel());
		saveArea.add(save);
		saveArea.add(new JPanel());
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveLevel();
			}
		});

		this.add(levelUI, BorderLayout.CENTER);
		this.add(listCaseTypeUI, BorderLayout.EAST);
		this.add(saveArea, BorderLayout.SOUTH);
		validate();
	}

	private void saveLevel() {
		LevelEditor.saveLevel(level);
		fenetreUI.showMenu(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (levelUI.getWidth() > 0) {
			if (e.getX() > levelUI.getWidth()) {
				selectedCaseType = listCaseTypeUI.selectCase(e.getY());
				if (selectedCaseType != null) {
					this.setCursor(new Case(selectedCaseType).getCursor(getX(),
							getY()));
				}

			} else {
				if (selectedCaseType != null) {
					int posX = (level.getWidthSize() * e.getX())
							/ levelUI.getWidth();
					int posY = (level.getHeightSize() * e.getY())
							/ levelUI.getHeight();

					switch (selectedCaseType) {
					case CHARACTER:
						level.setCharacter(posX, posY);
						break;
					case EMPTY:
						level.addEmpty(posX, posY);
						break;
					case HOLE:
						level.addHole(posX, posY);
						break;
					case STONE:
						level.addStone(posX, posY);
						break;
					case WALL:
						level.addWall(posX, posY);
						break;

					default:
						break;
					}
					levelUI.repaint();

				}

			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
