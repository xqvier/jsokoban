package fr.iiil.rodez.sokoban.model;

import java.io.Serializable;

/**
 * Classe modèle d'un niveau de Sokoban
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class Level implements Serializable {
	/** ID de sérialisation */
	private static final long serialVersionUID = 1L;

	/** Nom du niveau */
	private String name;

	/** Matrice des cases du niveau */
	private final Case[][] cases;

	/**
	 * Position abscisse du personnage dans le niveau (sauvegarde pour permettre
	 * des opération plus rapide)
	 */
	private int characterPosX;

	/**
	 * Position ordonné du personnage dans le niveau (sauvegarde pour permettre
	 * des opération plus rapide)
	 */
	private int characterPosY;

	/**
	 * Constructeur de création d'un niveau
	 * 
	 * @param pWidth
	 *            La largeur en nombre de case du niveau
	 * @param pHeight
	 *            La hauteur en nombre de case du niveau
	 * @param pCharacterPosX
	 *            La position initiale en abcsisse du personnage
	 * @param pCharacterPosY
	 *            La position initiale en ordonnée du personnage
	 */
	public Level(int pHeight, int pWidth, int pCharacterPosX, int pCharacterPosY) {
		super();
		if (pWidth <= 0 || pHeight <= 0 || pCharacterPosX < 0
				|| pCharacterPosX >= pWidth || pCharacterPosY < 0
				|| pCharacterPosX >= pHeight) {
			throw new ArrayIndexOutOfBoundsException();
		}
		characterPosX = pCharacterPosX;
		characterPosY = pCharacterPosY;
		cases = new Case[pWidth][pHeight];
		cases[characterPosX][characterPosY] = new Case(CaseType.CHARACTER);
		initializeEmptiness();
	}

	/**
	 * Constructeur de création d'un niveau avec le personnage initialement
	 * positionné en position 0, 0
	 * 
	 * @param pWidth
	 *            La largeur en nombre de case du niveau
	 * @param pHeight
	 *            La hauteur en nombre de case du niveau
	 */
	public Level(int pHeight, int pWidth) {
		this(pHeight, pWidth, 0, 0);
	}

	/**
	 * Constructeur de création d'un niveau de 20 cases par 20 cases avec le
	 * personnage initialement positionné en position 0, 0
	 */
	// XXX XMO
	// public Level() {
	// this(20, 20, 0, 0);
	// }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cases
	 */
	public Case[][] getCases() {
		return cases;
	}

	/**
	 * Retourne la taille en largeur du niveau (en nombre de cases)
	 * 
	 * @return La largeur du niveau
	 */
	public int getWidthSize() {
		return cases.length;
	}

	/**
	 * Retourne la taille en hauteur du niveau (en nombre de cases)
	 * 
	 * @return La hauteur du niveau
	 */
	public int getHeightSize() {
		return cases[0].length;
	}

	/**
	 * Positionne un personnage suivant ses coordonnées.
	 * 
	 * @param pPosX
	 *            position en abscisse
	 * @param pPosY
	 *            position en ordonné
	 */
	public void setCharacter(int pPosX, int pPosY) {
		// Vérification des limites
		checkBoundaries(pPosX, pPosY);

		// On restore la case où été situé le personnage (suivant s'il était sur
		// une case vide ou sur un trou).
		if (cases[characterPosX][characterPosY].getType().equals(
				CaseType.CHARACTER_ON_HOLE)) {
			cases[characterPosX][characterPosY].setType(CaseType.HOLE);
		} else {
			cases[characterPosX][characterPosY].setType(CaseType.EMPTY);
		}

		// Positionnement du personnage.
		characterPosX = pPosX;
		characterPosY = pPosY;
		// Conditionnement suivant s'il existe un trou à l'endroit ou l'on veut
		// positionner le personnage.
		if (cases[pPosX][pPosY].getType().equals(CaseType.HOLE)) {
			cases[pPosX][pPosY].setType(CaseType.CHARACTER_ON_HOLE);
		} else {
			cases[pPosX][pPosY].setType(CaseType.CHARACTER);
		}
	}

	/**
	 * Initialise le niveau avec uniquement des cases vides.<br />
	 * Laisse la case personnage telle quelle.
	 */
	private void initializeEmptiness() {
		for (int i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[0].length; j++) {
				// Attribution du type EMPTY a toutes les cases sauf celle ou le
				// personnage est situé
				if (i != characterPosX || j != characterPosY) {
					cases[i][j] = new Case(CaseType.EMPTY);
				}
			}
		}
	}

	/**
	 * Vérifie que des coordonnées de cases soit dans le cadre du niveau.
	 * 
	 * @param pPosX
	 *            La position en abscisse
	 * @param pPosY
	 *            La position en ordonné
	 */
	private void checkBoundaries(int pPosX, int pPosY) {
		if (pPosX < 0 || pPosX >= cases.length || pPosY < 0
				|| pPosY >= cases[0].length) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	/**
	 * Méthode permettant de positionner une case vide aux coordonnées donnée
	 * 
	 * @param pPosX
	 *            position en abscisse
	 * @param pPosY
	 *            position en ordonné
	 */
	public void addEmpty(int pPosX, int pPosY) {
		checkBoundaries(pPosX, pPosY);
		cases[pPosX][pPosY].setType(CaseType.EMPTY);
	}

	/**
	 * Méthode permettant de positionner un mur aux coordonnées donnée
	 * 
	 * @param pPosX
	 *            position en abscisse
	 * @param pPosY
	 *            position en ordonné
	 */
	public void addWall(int pPosX, int pPosY) {
		checkBoundaries(pPosX, pPosY);
		cases[pPosX][pPosY].setType(CaseType.WALL);
	}

	/**
	 * Méthode permettant de positionner un trou sous une pierre aux coordonnées
	 * donnée
	 * 
	 * @param pPosX
	 *            position en abscisse
	 * @param pPosY
	 *            position en ordonné
	 */
	public void addFilledHole(int pPosX, int pPosY) {
		checkBoundaries(pPosX, pPosY);
		cases[pPosX][pPosY].setType(CaseType.FILLED_HOLE);

	}

	/**
	 * Méthode permettant de positionner une pierre aux coordonnées donnée
	 * 
	 * @param pPosX
	 *            position en abscisse
	 * @param pPosY
	 *            position en ordonné
	 */
	public void addStone(int pPosX, int pPosY) {
		checkBoundaries(pPosX, pPosY);
		if (cases[pPosX][pPosY].getType().equals(CaseType.HOLE)) {
			cases[pPosX][pPosY].setType(CaseType.FILLED_HOLE);
		} else {
			cases[pPosX][pPosY].setType(CaseType.STONE);
		}
	}

	/**
	 * Méthode permettant de positionner un trou aux coordonnées donnée
	 * 
	 * @param pPosX
	 *            position en abscisse
	 * @param pPosY
	 *            position en ordonné
	 */
	public void addHole(int pPosX, int pPosY) {
		checkBoundaries(pPosX, pPosY);
		cases[pPosX][pPosY].setType(CaseType.HOLE);
	}

	/**
	 * Déplace le personnage à droite après avoir gérer les collisions
	 */
	public void moveEast() {
		// Si le mouvement est possible.
		if (checkMoveEast()) {
			// Si la case où l'on veut aller est occupé par une pierre, on
			// déplace d'abord la pierre.
			if (cases[characterPosX + 1][characterPosY].getType().equals(
					CaseType.STONE)
					|| cases[characterPosX + 1][characterPosY].getType()
							.equals(CaseType.FILLED_HOLE)) {
				// Positionnement de la pierre en fonction de s'il existe un
				// trou ou non à la position voulue.
				if (cases[characterPosX + 2][characterPosY].getType().equals(
						CaseType.HOLE)) {
					cases[characterPosX + 2][characterPosY]
							.setType(CaseType.FILLED_HOLE);
				} else {
					cases[characterPosX + 2][characterPosY]
							.setType(CaseType.STONE);
				}
			}

			// Positionnement du personnage en fonction de s'il existe un trou
			// ou non à la position voulue.
			if (cases[characterPosX + 1][characterPosY].getType().equals(
					CaseType.HOLE)
					|| cases[characterPosX + 1][characterPosY].getType()
							.equals(CaseType.FILLED_HOLE)) {
				cases[characterPosX + 1][characterPosY]
						.setType(CaseType.CHARACTER_ON_HOLE);
			} else {
				cases[characterPosX + 1][characterPosY]
						.setType(CaseType.CHARACTER);
			}

			// On restore la case où été situé le personnage (suivant s'il était
			// sur une case vide ou sur un trou).
			if (cases[characterPosX][characterPosY].getType().equals(
					CaseType.CHARACTER_ON_HOLE)) {
				cases[characterPosX][characterPosY].setType(CaseType.HOLE);
			} else {
				cases[characterPosX][characterPosY].setType(CaseType.EMPTY);
			}

			// Mise à jour de la position du personnage sauvegardé.
			characterPosX++;

		}
	}

	/**
	 * Déplace le personnage à gauche après avoir gérer les collisions
	 */
	public void moveWest() {
		// Si le mouvement est possible.
		if (checkMoveWest()) {
			// Si la case où l'on veut aller est occupé par une pierre, on
			// déplace d'abord la pierre.
			if (cases[characterPosX - 1][characterPosY].getType().equals(
					CaseType.STONE)
					|| cases[characterPosX - 1][characterPosY].getType()
							.equals(CaseType.FILLED_HOLE)) {
				// Positionnement de la pierre en fonction de s'il existe un
				// trou ou non à la position voulue.
				if (cases[characterPosX - 2][characterPosY].getType().equals(
						CaseType.HOLE)) {
					cases[characterPosX - 2][characterPosY]
							.setType(CaseType.FILLED_HOLE);
				} else {
					cases[characterPosX - 2][characterPosY]
							.setType(CaseType.STONE);
				}
			}

			// Positionnement du personnage en fonction de s'il existe un trou
			// ou non à la position voulue.
			if (cases[characterPosX - 1][characterPosY].getType().equals(
					CaseType.HOLE)
					|| cases[characterPosX - 1][characterPosY].getType()
							.equals(CaseType.FILLED_HOLE)) {
				cases[characterPosX - 1][characterPosY]
						.setType(CaseType.CHARACTER_ON_HOLE);
			} else {
				cases[characterPosX - 1][characterPosY]
						.setType(CaseType.CHARACTER);
			}

			// On restore la case où été situé le personnage (suivant s'il était
			// sur une case vide ou sur un trou).
			if (cases[characterPosX][characterPosY].getType().equals(
					CaseType.CHARACTER_ON_HOLE)) {
				cases[characterPosX][characterPosY].setType(CaseType.HOLE);
			} else {
				cases[characterPosX][characterPosY].setType(CaseType.EMPTY);
			}

			// Mise à jour de la position du personnage sauvegardé.
			characterPosX--;
		}
	}

	/**
	 * Déplace le personnage en bas après avoir gérer les collisions
	 */
	public void moveSouth() {
		// Si le mouvement est possible.
		if (checkMoveSouth()) {
			// Si la case où l'on veut aller est occupé par une pierre, on
			// déplace d'abord la pierre.
			if (cases[characterPosX][characterPosY + 1].getType().equals(
					CaseType.STONE)
					|| cases[characterPosX][characterPosY + 1].getType()
							.equals(CaseType.FILLED_HOLE)) {
				// Positionnement de la pierre en fonction de s'il existe un
				// trou ou non à la position voulue.
				if (cases[characterPosX][characterPosY + 2].getType().equals(
						CaseType.HOLE)) {
					cases[characterPosX][characterPosY + 2]
							.setType(CaseType.FILLED_HOLE);
				} else {
					cases[characterPosX][characterPosY + 2]
							.setType(CaseType.STONE);
				}
			}

			// Positionnement du personnage en fonction de s'il existe un trou
			// ou non à la position voulue.
			if (cases[characterPosX][characterPosY + 1].getType().equals(
					CaseType.HOLE)
					|| cases[characterPosX][characterPosY + 1].getType()
							.equals(CaseType.FILLED_HOLE)) {
				cases[characterPosX][characterPosY + 1]
						.setType(CaseType.CHARACTER_ON_HOLE);
			} else {
				cases[characterPosX][characterPosY + 1]
						.setType(CaseType.CHARACTER);
			}

			// On restore la case où été situé le personnage (suivant s'il était
			// sur une case vide ou sur un trou).
			if (cases[characterPosX][characterPosY].getType().equals(
					CaseType.CHARACTER_ON_HOLE)) {
				cases[characterPosX][characterPosY].setType(CaseType.HOLE);
			} else {
				cases[characterPosX][characterPosY].setType(CaseType.EMPTY);
			}

			// Mise à jour de la position du personnage sauvegardé.
			characterPosY++;
		}
	}

	/**
	 * Déplace le personnage en haut après avoir gérer les collisions
	 */
	public void moveNorth() {
		// Si le mouvement est possible.
		if (checkMoveNorth()) {
			// Si la case où l'on veut aller est occupé par une pierre, on
			// déplace d'abord la pierre.
			if (cases[characterPosX][characterPosY - 1].getType().equals(
					CaseType.STONE)
					|| cases[characterPosX][characterPosY - 1].getType()
							.equals(CaseType.FILLED_HOLE)) {
				// Positionnement de la pierre en fonction de s'il existe un
				// trou ou non à la position voulue.
				if (cases[characterPosX][characterPosY - 2].getType().equals(
						CaseType.HOLE)) {
					cases[characterPosX][characterPosY - 2]
							.setType(CaseType.FILLED_HOLE);
				} else {
					cases[characterPosX][characterPosY - 2]
							.setType(CaseType.STONE);
				}
			}

			// Positionnement du personnage en fonction de s'il existe un trou
			// ou non à la position voulue.
			if (cases[characterPosX][characterPosY - 1].getType().equals(
					CaseType.HOLE)
					|| cases[characterPosX][characterPosY - 1].getType()
							.equals(CaseType.FILLED_HOLE)) {
				cases[characterPosX][characterPosY - 1]
						.setType(CaseType.CHARACTER_ON_HOLE);
			} else {
				cases[characterPosX][characterPosY - 1]
						.setType(CaseType.CHARACTER);
			}

			// On restore la case où été situé le personnage (suivant s'il était
			// sur une case vide ou sur un trou).
			if (cases[characterPosX][characterPosY].getType().equals(
					CaseType.CHARACTER_ON_HOLE)) {
				cases[characterPosX][characterPosY].setType(CaseType.HOLE);
			} else {
				cases[characterPosX][characterPosY].setType(CaseType.EMPTY);
			}

			// Mise à jour de la position du personnage sauvegardé.
			characterPosY--;
		}
	}

	/**
	 * Gère les collisions pour savoir si le personnage peut bouger à droite
	 * 
	 * @return true si le déplacement est possible, false sinon
	 */
	private boolean checkMoveEast() {
		// Verification qu'on est dans les limites du tableau
		try {
			checkBoundaries(characterPosX + 1, characterPosY);
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}

		// Vérification qu'il n'y ai pas de mur
		if (cases[characterPosX + 1][characterPosY].getType().equals(
				CaseType.WALL)) {
			return false;
		}

		// Existance pierre ?
		if (cases[characterPosX + 1][characterPosY].getType().equals(
				CaseType.STONE)
				|| cases[characterPosX + 1][characterPosY].getType().equals(
						CaseType.FILLED_HOLE)) {
			// On verifie que la pierre ne va pas être déplacer en dehors des
			// limites du tableau.
			try {
				checkBoundaries(characterPosX + 2, characterPosY);
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}

			// On vérifie qu'il n'y ai pas une autre pierre ou un mur derriere.
			if (cases[characterPosX + 2][characterPosY].getType().equals(
					CaseType.WALL)
					|| cases[characterPosX + 2][characterPosY].getType()
							.equals(CaseType.STONE)
					|| cases[characterPosX + 2][characterPosY].getType()
							.equals(CaseType.FILLED_HOLE)) {
				return false;
			}
		}

		// Tous les tests OK.
		return true;
	}

	/**
	 * Gère les collisions pour savoir si le personnage peut bouger à gauche
	 * 
	 * @return true si le déplacement est possible, false sinon
	 */
	private boolean checkMoveWest() {
		// Verification qu'on est dans les limites du tableau
		try {
			checkBoundaries(characterPosX - 1, characterPosY);
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}

		// Vérification qu'il n'y ai pas de mur
		if (cases[characterPosX - 1][characterPosY].getType().equals(
				CaseType.WALL)) {
			return false;
		}

		// Existance pierre ?
		if (cases[characterPosX - 1][characterPosY].getType().equals(
				CaseType.STONE)
				|| cases[characterPosX - 1][characterPosY].getType().equals(
						CaseType.FILLED_HOLE)) {
			// On verifie que la pierre ne va pas être déplacer en dehors des
			// limites du tableau.
			try {
				checkBoundaries(characterPosX - 2, characterPosY);
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}

			// On vérifie qu'il n'y ai pas une autre pierre ou un mur derriere.
			if (cases[characterPosX - 2][characterPosY].getType().equals(
					CaseType.WALL)
					|| cases[characterPosX - 2][characterPosY].getType()
							.equals(CaseType.STONE)
					|| cases[characterPosX - 2][characterPosY].getType()
							.equals(CaseType.FILLED_HOLE)) {
				return false;
			}
		}

		// Tous les tests OK.
		return true;
	}

	/**
	 * Gère les collisions pour savoir si le personnage peut bouger en haut
	 * 
	 * @return true si le déplacement est possible, false sinon
	 */
	private boolean checkMoveSouth() {
		// Verification qu'on est dans les limites du tableau
		try {
			checkBoundaries(characterPosX, characterPosY + 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}

		// Vérification qu'il n'y ai pas de mur
		if (cases[characterPosX][characterPosY + 1].getType().equals(
				CaseType.WALL)) {
			return false;
		}

		// Existance pierre ?
		if (cases[characterPosX][characterPosY + 1].getType().equals(
				CaseType.STONE)
				|| cases[characterPosX][characterPosY + 1].getType().equals(
						CaseType.FILLED_HOLE)) {
			// On verifie que la pierre ne va pas être déplacer en dehors des
			// limites du tableau.
			try {
				checkBoundaries(characterPosX, characterPosY + 2);
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}

			// On vérifie qu'il n'y ai pas une autre pierre ou un mur derriere.
			if (cases[characterPosX][characterPosY + 2].getType().equals(
					CaseType.WALL)
					|| cases[characterPosX][characterPosY + 2].getType()
							.equals(CaseType.STONE)
					|| cases[characterPosX][characterPosY + 2].getType()
							.equals(CaseType.FILLED_HOLE)) {
				return false;
			}
		}

		// Tous les tests OK.
		return true;
	}

	/**
	 * Gère les collisions pour savoir si le personnage peut bouger en bas
	 * 
	 * @return true si le déplacement est possible, false sinon
	 */
	private boolean checkMoveNorth() {
		// Verification qu'on est dans les limites du tableau
		try {
			checkBoundaries(characterPosX, characterPosY - 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}

		// Vérification qu'il n'y ai pas de mur
		if (cases[characterPosX][characterPosY - 1].getType().equals(
				CaseType.WALL)) {
			return false;
		}

		// Existance pierre ?
		if (cases[characterPosX][characterPosY - 1].getType().equals(
				CaseType.STONE)
				|| cases[characterPosX][characterPosY - 1].getType().equals(
						CaseType.FILLED_HOLE)) {
			// On verifie que la pierre ne va pas être déplacer en dehors des
			// limites du tableau.
			try {
				checkBoundaries(characterPosX, characterPosY - 2);
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}

			// On vérifie qu'il n'y ai pas une autre pierre ou un mur derriere.
			if (cases[characterPosX][characterPosY - 2].getType().equals(
					CaseType.WALL)
					|| cases[characterPosX][characterPosY - 2].getType()
							.equals(CaseType.STONE)
					|| cases[characterPosX][characterPosY - 2].getType()
							.equals(CaseType.FILLED_HOLE)) {
				return false;
			}
		}

		// Tous les tests OK.
		return true;
	}

	/**
	 * Vérifie s'il reste un trou sans pierre.
	 * 
	 * @return true si tous les trous sont recouvert, false sinon
	 */
	public boolean checkVictory() {
		for (int i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[0].length; j++) {
				// On regarde s'il existe un trou libre ou si le personnage est
				// sur un trou.
				if (cases[i][j].getType().equals(CaseType.HOLE)
						|| cases[i][j].getType().equals(
								CaseType.CHARACTER_ON_HOLE)) {
					return false;
				}
			}
		}

		// Tous les trous sont pris par des pierres
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		// Le niveau est le même s'il a le même nom.
		return name.equals(((Level) obj).getName());
	}

}
