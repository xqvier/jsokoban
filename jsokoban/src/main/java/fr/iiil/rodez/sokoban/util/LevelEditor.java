package fr.iiil.rodez.sokoban.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import fr.iiil.rodez.sokoban.model.Level;

/**
 * Classe utilitaire de cr�ation de niveau Sokoban
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class LevelEditor {

	/**
	 * R�pertoire o� sont stocker les niveaux (r�pertoire o� se situe le
	 * programme).
	 */
	private static final File levelDirectory = new File(
			System.getProperty("user.dir"));

	/** Liste des niveaux de l'application */
	public static final List<Level> LEVELS = new LinkedList<Level>();

	static {
		initializeLevels();
	}

	/**
	 * Initialise les niveaux du jeu.<br />
	 * Ajoute d'abord les niveaux programm� en dur.<br />
	 * Ajoute ensuite les niveaux cr��s par l'utilisateur.
	 */
	public static void initializeLevels() {
		// Vidage de la liste.
		if (LEVELS.size() != 0) {
			LEVELS.clear();
		}

		// Ajout des niveaux programm�s en dur.
		LEVELS.add(createLevel1());
		LEVELS.add(createLevel2());
		LEVELS.add(createLevel3());
		LEVELS.add(createLevel4());

		// Scan du r�pertoire des niveaux pour ajout dans la liste.
		for (File file : levelDirectory.listFiles()) {
			if (file.isFile() && file.getName().endsWith(".lvl")) {
				ObjectInputStream ois;
				try {
					ois = new ObjectInputStream(new FileInputStream(file));
					LEVELS.add((Level) ois.readObject());
					ois.close();
				} catch (FileNotFoundException e) {
					throw new RuntimeException(e);
				} catch (IOException e) {
					throw new RuntimeException(e);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * C�ation du niveau 1 du jeu Sokoban
	 * 
	 * @return Le niveau cr�e
	 */
	private static Level createLevel1() {
		Level level = new Level(6, 7, 1, 1);

		level.setName("Niveau 1");
		level.addWall(0, 0);
		level.addWall(0, 1);
		level.addWall(0, 2);
		level.addWall(0, 3);
		level.addWall(1, 0);
		level.addWall(1, 3);
		level.addWall(1, 4);
		level.addWall(1, 5);
		level.addWall(2, 0);
		level.addWall(2, 5);
		level.addWall(3, 0);
		level.addWall(3, 5);
		level.addWall(4, 0);
		level.addWall(4, 1);
		level.addWall(4, 2);
		level.addWall(4, 3);
		level.addWall(4, 4);
		level.addWall(4, 5);

		level.addHole(1, 2);
		level.addHole(3, 1);

		level.addStone(2, 1);
		level.addStone(2, 2);
		return level;
	}

	/**
	 * C�ation du niveau 2 du jeu Sokoban
	 * 
	 * @return Le niveau cr�e
	 */
	private static Level createLevel2() {
		Level level = new Level(12, 12, 5, 1);

		level.setName("Niveau 2");
		level.addWall(0, 0);
		level.addWall(1, 0);
		level.addWall(2, 0);
		level.addWall(3, 0);
		level.addWall(6, 0);
		level.addWall(7, 0);
		level.addWall(8, 0);
		level.addWall(9, 0);
		level.addWall(10, 0);
		level.addWall(11, 0);
		level.addWall(0, 1);
		level.addWall(1, 1);
		level.addWall(2, 1);
		level.addWall(3, 1);
		level.addWall(11, 1);
		level.addWall(0, 2);
		level.addWall(1, 2);
		level.addWall(2, 2);
		level.addWall(3, 2);
		level.addWall(7, 2);
		level.addWall(8, 2);
		level.addWall(11, 2);
		level.addWall(3, 3);
		level.addWall(5, 3);
		level.addWall(7, 3);
		level.addWall(8, 3);
		level.addWall(10, 3);
		level.addWall(11, 3);
		level.addWall(8, 4);
		level.addWall(10, 4);
		level.addWall(11, 4);
		level.addWall(0, 5);
		level.addWall(3, 5);
		level.addWall(5, 5);
		level.addWall(10, 5);
		level.addWall(11, 5);
		level.addWall(0, 6);
		level.addWall(1, 6);
		level.addWall(2, 6);
		level.addWall(3, 6);
		level.addWall(7, 6);
		level.addWall(8, 6);
		level.addWall(10, 6);
		level.addWall(11, 6);
		level.addWall(0, 7);
		level.addWall(1, 7);
		level.addWall(2, 7);
		level.addWall(3, 7);
		level.addWall(4, 7);
		level.addWall(5, 7);
		level.addWall(6, 7);
		level.addWall(7, 7);
		level.addWall(8, 7);
		level.addWall(11, 7);
		level.addWall(0, 8);
		level.addWall(1, 8);
		level.addWall(2, 8);
		level.addWall(3, 8);
		level.addWall(11, 8);
		level.addWall(0, 9);
		level.addWall(1, 9);
		level.addWall(2, 9);
		level.addWall(3, 9);
		level.addWall(4, 9);
		level.addWall(5, 9);
		level.addWall(6, 9);
		level.addWall(7, 9);
		level.addWall(8, 9);
		level.addWall(9, 9);
		level.addWall(10, 9);
		level.addWall(11, 9);
		level.addWall(0, 10);
		level.addWall(1, 10);
		level.addWall(2, 10);
		level.addWall(3, 10);
		level.addWall(4, 10);
		level.addWall(5, 10);
		level.addWall(6, 10);
		level.addWall(7, 10);
		level.addWall(8, 10);
		level.addWall(9, 10);
		level.addWall(10, 10);
		level.addWall(11, 10);
		level.addWall(0, 11);
		level.addWall(1, 11);
		level.addWall(2, 11);
		level.addWall(3, 11);
		level.addWall(4, 11);
		level.addWall(5, 11);
		level.addWall(6, 11);
		level.addWall(7, 11);
		level.addWall(8, 11);
		level.addWall(9, 11);
		level.addWall(10, 11);
		level.addWall(11, 11);

		level.addHole(1, 3);
		level.addHole(2, 3);
		level.addHole(4, 8);

		level.addStone(5, 4);
		level.addStone(9, 4);
		level.addStone(9, 6);

		return level;
	}

	/**
	 * C�ation du niveau 3 du jeu Sokoban
	 * 
	 * @return Le niveau cr�e
	 */
	private static Level createLevel3() {
		Level level = new Level(10, 10, 3, 6);

		level.setName("Niveau 3");
		level.addWall(1, 3);
		level.addWall(1, 4);
		level.addWall(1, 5);
		level.addWall(1, 6);
		level.addWall(1, 7);
		level.addWall(2, 1);
		level.addWall(2, 2);
		level.addWall(2, 3);
		level.addWall(2, 7);
		level.addWall(3, 1);
		level.addWall(3, 7);
		level.addWall(4, 1);
		level.addWall(4, 3);
		level.addWall(4, 5);
		level.addWall(4, 7);
		level.addWall(5, 1);
		level.addWall(5, 3);
		level.addWall(5, 7);
		level.addWall(6, 1);
		level.addWall(6, 6);
		level.addWall(6, 7);
		level.addWall(7, 1);
		level.addWall(7, 2);
		level.addWall(7, 6);
		level.addWall(8, 2);
		level.addWall(8, 3);
		level.addWall(8, 4);
		level.addWall(8, 5);
		level.addWall(8, 6);

		level.addHole(3, 3);
		level.addHole(3, 4);
		level.addHole(4, 4);

		level.addStone(6, 3);
		level.addStone(5, 4);
		level.addStone(5, 5);

		return level;
	}

	/**
	 * C�ation du niveau 4 du jeu Sokoban
	 * 
	 * @return Le niveau cr�e
	 */
	private static Level createLevel4() {
		Level level = new Level(16, 19, 2, 7);

		level.setName("Niveau 4");
		level.addWall(0, 5);
		level.addWall(0, 6);
		level.addWall(0, 7);
		level.addWall(0, 8);
		level.addWall(0, 9);
		level.addWall(1, 5);
		level.addWall(1, 9);
		level.addWall(2, 5);
		level.addWall(2, 9);
		level.addWall(2, 10);
		level.addWall(2, 11);
		level.addWall(2, 12);
		level.addWall(2, 13);
		level.addWall(2, 14);
		level.addWall(2, 15);
		level.addWall(3, 3);
		level.addWall(3, 4);
		level.addWall(3, 5);
		level.addWall(3, 6);
		level.addWall(3, 15);
		level.addWall(4, 2);
		level.addWall(4, 3);
		level.addWall(4, 6);
		level.addWall(4, 15);
		level.addWall(5, 1);
		level.addWall(5, 2);
		level.addWall(5, 11);
		level.addWall(5, 13);
		level.addWall(5, 14);
		level.addWall(5, 15);
		level.addWall(6, 0);
		level.addWall(6, 1);
		level.addWall(6, 6);
		level.addWall(6, 13);
		level.addWall(7, 0);
		level.addWall(7, 5);
		level.addWall(7, 7);
		level.addWall(7, 11);
		level.addWall(7, 12);
		level.addWall(7, 13);
		level.addWall(8, 0);
		level.addWall(8, 4);
		level.addWall(8, 7);
		level.addWall(8, 9);
		level.addWall(8, 11);
		level.addWall(9, 0);
		level.addWall(9, 3);
		level.addWall(9, 7);
		level.addWall(9, 11);
		level.addWall(10, 0);
		level.addWall(10, 4);
		level.addWall(10, 5);
		level.addWall(10, 8);
		level.addWall(10, 9);
		level.addWall(10, 10);
		level.addWall(10, 11);
		level.addWall(11, 0);
		level.addWall(11, 3);
		level.addWall(11, 11);
		level.addWall(11, 12);
		level.addWall(11, 13);
		level.addWall(12, 0);
		level.addWall(12, 4);
		level.addWall(12, 7);
		level.addWall(12, 8);
		level.addWall(12, 9);
		level.addWall(12, 10);
		level.addWall(12, 13);
		level.addWall(12, 14);
		level.addWall(12, 15);
		level.addWall(13, 0);
		level.addWall(13, 1);
		level.addWall(13, 3);
		level.addWall(13, 15);
		level.addWall(14, 0);
		level.addWall(14, 7);
		level.addWall(14, 9);
		level.addWall(14, 10);
		level.addWall(14, 15);
		level.addWall(15, 0);
		level.addWall(15, 5);
		level.addWall(15, 10);
		level.addWall(15, 12);
		level.addWall(15, 15);
		level.addWall(16, 0);
		level.addWall(16, 3);
		level.addWall(16, 7);
		level.addWall(16, 8);
		level.addWall(16, 9);
		level.addWall(16, 10);
		level.addWall(16, 15);
		level.addWall(17, 0);
		level.addWall(17, 1);
		level.addWall(17, 2);
		level.addWall(17, 3);
		level.addWall(17, 4);
		level.addWall(17, 5);
		level.addWall(17, 6);
		level.addWall(17, 7);
		level.addWall(17, 10);
		level.addWall(17, 14);
		level.addWall(17, 15);
		level.addWall(18, 10);
		level.addWall(18, 11);
		level.addWall(18, 12);
		level.addWall(18, 13);
		level.addWall(18, 14);

		level.addHole(4, 4);
		level.addHole(4, 5);
		level.addHole(5, 3);
		level.addHole(5, 4);
		level.addHole(5, 5);
		level.addHole(6, 2);
		level.addHole(6, 3);
		level.addFilledHole(6, 4);
		level.addHole(6, 5);
		level.addHole(7, 1);
		level.addHole(7, 2);
		level.addFilledHole(7, 3);
		level.addHole(7, 4);
		level.addHole(8, 1);
		level.addFilledHole(8, 2);
		level.addHole(8, 3);

		level.addStone(2, 8);
		level.addStone(3, 7);
		level.addStone(3, 9);
		level.addStone(3, 12);
		level.addStone(4, 9);
		level.addStone(4, 11);
		level.addStone(5, 7);
		level.addStone(6, 8);
		level.addStone(6, 10);
		level.addStone(10, 2);
		level.addStone(14, 3);
		level.addStone(14, 4);
		level.addStone(15, 2);

		return level;
	}

	/**
	 * M�thode de sauvegarde d'un niveau.<br />
	 * cr�e un fichier NOM.lvl dans le r�pertoire des niveaux.<br />
	 * Sauvegarde par s�rialisation le niveau.
	 * 
	 * @param level
	 *            le niveau � sauvegarder.
	 */
	public static void saveLevel(Level level) {
		// Cr�ation du fichier
		File levelFile = new File(levelDirectory.getAbsolutePath()
				+ File.separatorChar + level.getName() + ".lvl");
		try {
			levelFile.createNewFile();
			// S�rialisation du niveau dans le fichier.
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(levelFile));
			oos.writeObject(level);
			oos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// Ajout du niveau � la liste.
		LEVELS.add(level);
	}
}
