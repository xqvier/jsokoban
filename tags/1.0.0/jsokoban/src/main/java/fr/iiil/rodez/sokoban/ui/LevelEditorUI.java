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
 * Classe d'interface d'affichage de l'�dition de niveau.<br />
 * Cette interface inclut l'interface d'affichage de niveau classique, sans la
 * capture des touches du clavier.<br />
 * Elle rajoute sur l'interface une "liste" des cases disponibles pour
 * s�l�ction.
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class LevelEditorUI extends JPanel implements MouseListener {

	/** ID de serialisation */
	private static final long serialVersionUID = 1L;

	/** Interface de la fenetre contenant ce panneau */
	private final FenetreUI fenetreUI;

	/** Popup de configuration du niveau */
	private LevelEditorCreateDialog dialog;

	/** Le niveau en cours de cr�ation */
	private Level level;

	/** Interface d'affichage du niveau */
	private final LevelUI levelUI = new LevelUI(null);

	/** Interface d'affichage de la liste des cases disponibles */
	private final ListCaseTypeUI listCaseTypeUI = new ListCaseTypeUI();

	/** Le type de case selectionn� pour ajout sur le niveau */
	private CaseType selectedCaseType;

	/**
	 * Constructeur par d�faut
	 * 
	 * @param pFenetreUI
	 *            Fenetre parente du panneau pour ordonn� un basculement.
	 */
	public LevelEditorUI(FenetreUI pFenetreUI) {
		// Initialisation de l'interface
		fenetreUI = pFenetreUI;
		this.setLayout(new BorderLayout());
	}

	/**
	 * M�thode de lancement de l'interface. Celle-ci commence par afficher
	 * l'interface de configuration
	 */
	public void launch() {
		// Affichage de la popup de configuration.
		dialog = new LevelEditorCreateDialog(fenetreUI, this);
	}

	/**
	 * M�thode d'affichage de l'interface en fonction de la configuration
	 * saisie.
	 */
	void showUI() {
		// Clean de l'interface
		this.removeAll();

		// Cr�ation du niveau en fonction de la configuration saisie.
		level = new Level(dialog.getLevelHeight(), dialog.getLevelWidth());
		level.setName(dialog.getLevelName());
		levelUI.setLevel(level);

		// Calcul de la largeur de l'interface de s�lection des cases.
		int listCaseTypeUIWidth = getWidth() / (dialog.getLevelWidth() + 1);
		listCaseTypeUI.setPreferredSize(new Dimension(listCaseTypeUIWidth,
				getHeight()));

		// Cr�ation d'un bouton de sauvegarde.
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

		// Ajout de l'interface de niveau, de selection de case � l'interface
		// d'�dition et du bouton de sauvegarde.
		this.add(levelUI, BorderLayout.CENTER);
		this.add(listCaseTypeUI, BorderLayout.EAST);
		this.add(saveArea, BorderLayout.SOUTH);

		// Rafraichisement de l'interface
		validate();
	}

	/**
	 * M�thode de sauvegarde du niveau cr��.
	 */
	private void saveLevel() {
		// Sauvegarde du niveau
		LevelEditor.saveLevel(level);

		// Affichage du menu
		fenetreUI.showMenu(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Si l'interface � �t� initialis�.
		if (levelUI.getWidth() > 0) {
			// Si on a cliqu� sur la liste des cases disponible
			if (e.getX() > levelUI.getWidth()) {
				// R�cup�ration de la case selectionn�e.
				selectedCaseType = listCaseTypeUI.selectCase(e.getY());
				// Si une case � �t� selectionn�e.
				if (selectedCaseType != null) {
					// Remplacement du curseur par celui correspondant � la case
					// selectionn�e.
					this.setCursor(new Case(selectedCaseType).getCursor(getX(),
							getY()));
				}

			} // Sinon, on a cliqu� sur le niveau (pour ajout de la case
				// selectionn�e).
			else {
				// Si une case a �t� selectionn�e.
				if (selectedCaseType != null) {
					// R�cup�ration de la position du clique � l'�chelle de la
					// matrice du niveau.
					int posX = (level.getWidthSize() * e.getX())
							/ levelUI.getWidth();
					int posY = (level.getHeightSize() * e.getY())
							/ levelUI.getHeight();

					// Ajout de la case au niveau en fonction de son type.
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
				}
			}
		}
		// Raffraichissement de l'interface.
		repaint();
		validate();
	}

	@Override
	@Deprecated
	public void mousePressed(MouseEvent e) {
		// Non utilis�
	}

	@Override
	@Deprecated
	public void mouseReleased(MouseEvent e) {
		// Non utilis�
	}

	@Override
	@Deprecated
	public void mouseEntered(MouseEvent e) {
		// Non utilis�
	}

	@Override
	@Deprecated
	public void mouseExited(MouseEvent e) {
		// Non utilis�
	}

}
