package fr.iiil.rodez.sokoban.model;

/**
 * Enumération de type de case.
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public enum CaseType {
	/** Type de case vide */
	EMPTY,
	/** Type de case mur */
	WALL,
	/** Type de case pierre */
	STONE,
	/** Type de case personnage */
	CHARACTER,
	/** Type de case trou */
	HOLE,
	/** Type de case pierre sur trou */
	FILLED_HOLE,
	/** Type de case personnage sur trou */
	CHARACTER_ON_HOLE;

}
