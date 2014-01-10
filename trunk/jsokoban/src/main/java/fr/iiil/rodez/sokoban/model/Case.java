package fr.iiil.rodez.sokoban.model;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

/**
 * Classe représentant une case du jeu Sokoban
 * 
 * @author Administrateur
 * 
 */
public class Case implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static BufferedImage CHARACTER_IMAGE;
	private static BufferedImage HOLE_IMAGE;
	private static BufferedImage STONE_IMAGE;
	private static BufferedImage WALL_IMAGE;
	private static BufferedImage EMPTY_IMAGE;

	static {
		try {
			CHARACTER_IMAGE = ImageIO.read(new FileImageInputStream(new File(
					Case.class.getResource("/mario.jpg").getFile())));
			HOLE_IMAGE = ImageIO.read(new FileImageInputStream(new File(
					Case.class.getResource("/hole.jpg").getFile())));
			WALL_IMAGE = ImageIO.read(new FileImageInputStream(new File(
					Case.class.getResource("/wall.jpg").getFile())));
			EMPTY_IMAGE = ImageIO.read(new FileImageInputStream(new File(
					Case.class.getResource("/empty.jpg").getFile())));
			STONE_IMAGE = ImageIO.read(new FileImageInputStream(new File(
					Case.class.getResource("/stone.jpg").getFile())));

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
	 *            TODO
	 */
	public void paint(Graphics2D contexte, int pPosX, int pPosY, int pWidth,
			int pHeight, boolean pBorder) {
		BufferedImage img = null;
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

		final int x = pPosX * pWidth;
		final int y = pPosY * pHeight;

		contexte.drawImage(img, x, y, pPosX * pWidth + pWidth, y + pHeight, 0,
				0, img.getWidth(), img.getHeight(), null);

		if (pBorder) {
			contexte.drawRect(x, y, pWidth - 2, pHeight - 2);
		}
	}

	public Cursor getCursor(int x, int y) {
		Toolkit tk = Toolkit.getDefaultToolkit();

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
