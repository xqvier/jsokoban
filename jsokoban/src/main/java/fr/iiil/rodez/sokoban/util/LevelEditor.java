package fr.iiil.rodez.sokoban.util;

import fr.iiil.rodez.sokoban.model.Level;

/**
 * Classe utilitaire de création de niveau Sokoban
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 * 
 */
public class LevelEditor {

    /** TODO comment field role */
    public static int NB_LEVEL = 4;

    /**
     * TODO comment role
     * 
     * @param levelNumber
     *            Le numéro du niveau
     * @return Le niveau.
     */
    public static Level getLevel(int levelNumber) {
        switch (levelNumber) {
        case 1:
            return createLevel1();
        case 2:
            return createLevel2();
        case 3:
            return createLevel3();
        case 4:
            return createLevel4();
        default:
            return createEasyTestLevel();
        }

    }

    /**
     * Création d'un level facile à accomplir pour des cas de test
     * 
     * @return Le niveau crée
     */
    public static Level createEasyTestLevel() {
        Level level = new Level(3, 3);

        level.addStone(1, 0);
        level.addHole(2, 0);

        return level;
    }

    /**
     * Céation du niveau 1 du jeu Sokoban
     * 
     * @return Le niveau crée
     */
    private static Level createLevel1() {
        Level level = new Level(7, 6, 1, 1);

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

    private static Level createLevel2() {
        Level level = new Level(12, 12, 5, 1);

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

    private static Level createLevel3() {
        Level level = new Level(10, 10, 3, 6);

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

    private static Level createLevel4() {
        Level level = new Level(19, 16, 2, 7);
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
}
