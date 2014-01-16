package fr.iiil.rodez.sokoban.model;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * Classe représentant une case du jeu Sokoban
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class Case implements Serializable {
	/** Id de serialisation */
	private static final long serialVersionUID = 1L;

	/** Image représentant le personnage */
	private static BufferedImage CHARACTER_IMAGE;

	/** Image représentant un trou */
	private static BufferedImage HOLE_IMAGE;

	/** Image représentant une pierre */
	private static BufferedImage STONE_IMAGE;

	/** Image représentant un mur */
	private static BufferedImage WALL_IMAGE;

	/** Image représentant une case vide */
	private static BufferedImage EMPTY_IMAGE;

	static {
		try {
			// Initialisation des images.
			CHARACTER_IMAGE = ImageIO.read(ClassLoader
					.getSystemResourceAsStream("mario.jpg"));
			HOLE_IMAGE = ImageIO.read(ClassLoader
					.getSystemResourceAsStream("hole.jpg"));
			WALL_IMAGE = ImageIO.read(ClassLoader
					.getSystemResourceAsStream("wall.jpg"));
			EMPTY_IMAGE = ImageIO.read(ClassLoader
					.getSystemResourceAsStream("empty.jpg"));
			STONE_IMAGE = ImageIO.read(ClassLoader
					.getSystemResourceAsStream("stone.jpg"));

		} catch (IOException e) {
			throw new RuntimeException("Image non trouvé", e);
		}

	}
	/** Le type de la case */
	private CaseType type;

	/**
	 * Constructeur d'une case
	 * 
	 * @param type
	 *            le type de la case créée
	 */
	public Case(CaseType type) {
		super();
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public CaseType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(CaseType type) {
		this.type = type;
	}

	/**
	 * Méthode permettant de peindre une case dans le contexte graphique
	 * 
	 * @param contexte
	 *            Le contexte graphique
	 * @param pPosX
	 *            La position d'abscisse du coin supérieur gauche du dessin
	 * @param pPosY
	 *            La position d'ordonné du coin supérieur gauche du dessin
	 * @param pWidth
	 *            La largeur du dessin
	 * @param pHeight
	 *            La hauteur du dessin
	 * @param pBorder
	 *            Affichage d'une bordure autour ou non
	 */
	public void paint(Graphics2D contexte, int pPosX, int pPosY, int pWidth,
			int pHeight, boolean pBorder) {
		BufferedImage img = null;

		// Selection de l'image en fonction du type
		switch (type) {
		case CHARACTER:
			/* fall-through */
		case CHARACTER_ON_HOLE:
			img = CHARACTER_IMAGE;
			break;
		case HOLE:
			img = HOLE_IMAGE;
			break;
		case FILLED_HOLE:
			/* fail-through */
		case STONE:
			img = STONE_IMAGE;
			break;
		case WALL:
			img = WALL_IMAGE;
			break;
		case EMPTY:
			img = EMPTY_IMAGE;
			break;
		default:
			break;
		}

		// Calcul de la position (coin haut-gauche) de l'image dans le contexte.
		final int x = pPosX * pWidth;
		final int y = pPosY * pHeight;

		contexte.drawImage(img, x, y, pPosX * pWidth + pWidth, y + pHeight, 0,
				0, img.getWidth(), img.getHeight(), null);

		if (pBorder) {
			// Affichage d'une bordure.
			contexte.drawRect(x, y, pWidth - 2, pHeight - 2);
		}
	}

	/**
	 * Méthode permettant de récupérer le curseur suivant le type de case.
	 * 
	 * @param x
	 *            La position x du curseur à afficher.
	 * @param y
	 *            La position y du curseur à afficher.
	 * @return Le curseur à afficher.
	 */
	public Cursor getCursor(int x, int y) {
		Toolkit tk = Toolkit.getDefaultToolkit();

		// Création du curseur en fonction du type
		switch (type) {
		case CHARACTER:
			return tk.createCustomCursor(CHARACTER_IMAGE, new Point(x, y),
					"img");
		case HOLE:
			return tk.createCustomCursor(HOLE_IMAGE, new Point(x, y), "img");
		case STONE:
			return tk.createCustomCursor(STONE_IMAGE, new Point(x, y), "img");
		case WALL:
			return tk.createCustomCursor(WALL_IMAGE, new Point(x, y), "img");
		case EMPTY:
			return tk.createCustomCursor(EMPTY_IMAGE, new Point(x, y), "img");
		default:
			return Cursor.getDefaultCursor();
		}
	}
}
