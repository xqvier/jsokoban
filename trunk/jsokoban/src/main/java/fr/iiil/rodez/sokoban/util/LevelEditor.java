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
    public static Level createLevel1() {
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

}
