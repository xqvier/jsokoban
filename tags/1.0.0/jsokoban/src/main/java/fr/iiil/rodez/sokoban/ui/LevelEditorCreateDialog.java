package fr.iiil.rodez.sokoban.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import fr.iiil.rodez.sokoban.model.Level;
import fr.iiil.rodez.sokoban.util.LevelEditor;

/**
 * Classe gérant l'affichage de la popup de configuration lors de la création
 * d'un niveau
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class LevelEditorCreateDialog extends JDialog {

	/** ID de sérialisation. */
	private static final long serialVersionUID = 1L;

	/** Champ de saisie de la largeur du niveau. */
	private final MarioTextField levelWidth = new MarioTextField("12");

	/** Champ de saisie de la hauteur du niveau */
	private final MarioTextField levelHeight = new MarioTextField("12");

	/** Champ de saisie du nom du niveau */
	private final MarioTextField levelName = new MarioTextField();

	/**
	 * Constructeur d'une popup de configuration d'un niveau.
	 * 
	 * @param pFenetreUI
	 *            La fenetre à laquelle est rattaché cette popup
	 * @param pLevelEditorUI
	 *            L'interface d'édition de niveau.
	 */
	public LevelEditorCreateDialog(final FenetreUI pFenetreUI,
			final LevelEditorUI pLevelEditorUI) {
		super();
		// Initialisation de la popup.
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setSize(new Dimension(400, 300));

		// Ajout des champs de saisie.
		add(new MarioLabel("Largeur : "));
		add(levelWidth);
		add(new MarioLabel("Hauteur : "));
		add(levelHeight);
		add(new MarioLabel("Nom du niveau : "));
		add(levelName);

		// Création d'un bouton de validation.
		JButton valid = new JButton();
		valid.add(new MarioLabel("Creer"));
		valid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Initialisation des messages d'erreurs.
				final MarioLabel sizeCantBeLessOrEqualZero = new MarioLabel(
						"La hauteur ou la largeur ne peut etre inferieur a 0.");
				final MarioLabel nameAlreadyExistMessage = new MarioLabel(
						"Ce nom de niveau est deja pris.");
				final MarioLabel nameCantBeEmptyMessage = new MarioLabel(
						"Le nom du niveau ne peut etre vide.");

				// Initialisation des booléens de vérification de la saisie.
				boolean inputCorrect = true;
				boolean nameAlreadyExist = false;
				boolean showUI = true;

				// Vérification que la hauteur et la largeur saisie soit des
				// entiers.
				int width = 0;
				int height = 0;
				try {
					width = Integer.valueOf(levelWidth.getText());
					height = Integer.valueOf(levelHeight.getText());
				} catch (NumberFormatException ex) {
					inputCorrect = false;
					showUI = false;
				}

				// Vérification que la hauteur et la largeur soit supèrieur à 0.
				if (width <= 0 || height <= 0) {
					inputCorrect = false;
					showUI = false;
				}

				// Vérification que le nom du niveau soit saisie.
				if (!"".equals(levelName.getText())) {

					// Vérification dans les niveaux que ce nom ne soit pas déja
					// pris.
					for (Level level : LevelEditor.LEVELS) {
						if (levelName.getText().equals(level.getName())) {
							nameAlreadyExist = true;
							showUI = false;
						}
					}
				} else {
					showUI = false;
				}

				// Saisie correct ?
				if (showUI) {
					// Disparition de la popup
					setVisible(false);
					// Affichage de l'édition de niveau.
					pLevelEditorUI.showUI();
				} else {
					// Supprime les message d'erreur précédents.
					remove(nameAlreadyExistMessage);
					remove(nameCantBeEmptyMessage);
					remove(sizeCantBeLessOrEqualZero);

					// Ajout des messages d'erreur correspondant aux erreurs.
					if (!inputCorrect) {
						add(sizeCantBeLessOrEqualZero);
					}
					if ("".equals(levelName.getText())) {
						add(nameCantBeEmptyMessage);
					}
					if (nameAlreadyExist) {
						add(nameAlreadyExistMessage);
					}

					// Raffraichissement de la popup.
					pack();
					revalidate();
					repaint();
				}
			}
		});

		// Ajout du bouton de validation à la popup.
		add(valid);

		// Ajout d'une écoute sur la fermeture de la popup. Si celle-ci est
		// fermer, on retourne au menu.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pFenetreUI.showMenu(false);
			}
		});

		// Spacer
		add(new JLabel());

		// Raffraichissement de la popup.
		setVisible(true);
		pack();
		validate();
	}

	/**
	 * Retourne la hauteur saisie
	 * 
	 * @return la hauteur saisie
	 */
	public int getLevelHeight() {
		return Integer.parseInt(levelHeight.getText());
	}

	/**
	 * Retourne la largeur saisie
	 * 
	 * @return la largeur saisie
	 */
	public int getLevelWidth() {
		return Integer.parseInt(levelWidth.getText());
	}

	/**
	 * Retourne le nom de niveau saisie
	 * 
	 * @return le nom saisie
	 */
	public String getLevelName() {
		return levelName.getText();
	}

}
