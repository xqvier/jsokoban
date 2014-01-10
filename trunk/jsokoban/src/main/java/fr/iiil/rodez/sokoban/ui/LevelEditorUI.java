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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
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

	private MarioTextField levelWidth = new MarioTextField("12");
	private MarioTextField levelHeight = new MarioTextField("12");

	private FenetreUI fenetreUI;

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
		final JPanel textArea = new JPanel();
		textArea.setLayout(new BoxLayout(textArea, BoxLayout.LINE_AXIS));

		this.add(textArea, BorderLayout.NORTH);
		textArea.setSize(new Dimension(getWidth(), 50));

		textArea.add(new MarioLabel("Largeur : "));
		textArea.add(levelWidth);
		textArea.add(new MarioLabel("Hauteur : "));
		textArea.add(levelHeight);
		JButton valid = new JButton();
		final MarioLabel sizeCantBeLessOrEqualZero = new MarioLabel(" La hauteur ou la largeur ne peut être inférieur à 0");
		
		valid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean inputCorrect = true;
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
				if (inputCorrect) {
					showUI();
				} else {
					textArea.remove(sizeCantBeLessOrEqualZero);
					textArea.add(sizeCantBeLessOrEqualZero);
					validate();
					
				}
			}
		});
		valid.add(new MarioLabel("Creer"));
		textArea.add(valid);

	}

	private void showUI() {
		this.setLayout(new BorderLayout());
		this.removeAll();
		level = new Level(Integer.valueOf(levelHeight.getText()),
				Integer.valueOf(levelWidth.getText()));
		levelUI.setLevel(level);

		int listCaseTypeUIWidth = getWidth()
				/ (Integer.valueOf(levelWidth.getText()) + 1);

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
		final MarioTextField name = new MarioTextField();
		JButton valid = new JButton();
		valid.add(new MarioLabel("Creer"));
		final JDialog dialog = new JDialog();
		final MarioLabel nameAlreadyExistMessage = new MarioLabel(" Ce nom de niveau est déjà pris");
		final MarioLabel nameCantBeEmptyMessage = new MarioLabel(" Le nom du niveau ne peut être vide");
		valid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!"".equals(name.getText())) {
					boolean nameAlreadyExist = false;
					for (Level level : LevelEditor.LEVELS) {
						if (name.getText().equals(level.getName())) {
							nameAlreadyExist = true;
						}
					}
					if (!nameAlreadyExist) {
						level.setName(name.getText());
						LevelEditor.saveLevel(level);
						dialog.setVisible(false);
						fenetreUI.showMenu(false);
					} else {
						dialog.add(nameAlreadyExistMessage);
						dialog.remove(nameCantBeEmptyMessage);
						dialog.setSize(600, 70);
						dialog.validate();
					}
				} else {
					dialog.add(nameCantBeEmptyMessage);
					dialog.remove(nameAlreadyExistMessage);
					dialog.setSize(600, 70);
					dialog.validate();
				}

			}
		});

		dialog.setSize(600, 70);
		dialog.setLayout(new BoxLayout(dialog.getContentPane(),
				BoxLayout.LINE_AXIS));
		dialog.add(new MarioLabel("Nom du niveau : "));
		dialog.add(name);
		dialog.add(valid);
		dialog.setVisible(true);
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
