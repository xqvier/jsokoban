package fr.iiil.rodez.sokoban.model;

/**
 * Classe mod�le d'un niveau de Sokoban
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 * 
 */
public class Level {
    /** Matrice des cases du niveau */
    private final Case[][] cases;

    /** Position abscisse du personnage dans le niveau */
    private int characterPosX;

    /** Position ordonn� du personnage dans le niveau */
    private int characterPosY;

    /**
     * Constructeur de cr�ation d'un niveau
     * 
     * @param pWidth
     *            La largeur en nombre de case du niveau
     * @param pHeight
     *            La hauteur en nombre de case du niveau
     * @param pCharacterPosX
     *            La position initiale en abcsisse du personnage
     * @param pCharacterPosY
     *            La position initiale en ordonn�e du personnage
     */
    public Level(int pWidth, int pHeight, int pCharacterPosX, int pCharacterPosY) {
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
     * Constructeur de cr�ation d'un niveau avec le personnage initialement
     * positionn� en position 0, 0
     * 
     * @param pWidth
     *            La largeur en nombre de case du niveau
     * @param pHeight
     *            La hauteur en nombre de case du niveau
     */
    public Level(int pWidth, int pHeight) {
        this(pWidth, pHeight, 0, 0);
    }

    /**
     * Constructeur de cr�ation d'un niveau de 20 cases par 20 cases avec le
     * personnage initialement positionn� en position 0, 0
     */
    public Level() {
        this(20, 20, 0, 0);
    }

    /**
     * @return the cases
     */
    public Case[][] getCases() {
        return cases;
    }

    /**
     * TODO comment role
     * 
     * @return TODO
     */
    public int getWidthSize() {
        return cases[0].length;
    }

    /**
     * TODO comment role
     * 
     * @return TODO
     */
    public int getHeightSize() {
        return cases.length;
    }

    /**
     * Initialise le niveau avec uniquement des cases vides.
     */
    private void initializeEmptiness() {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[0].length; j++) {
                if (i != characterPosX || j != characterPosY) {
                    cases[i][j] = new Case(CaseType.EMPTY);
                }
            }
        }
    }

    /**
     * V�rifie que des coordonn�es de cases soit dans le cadre du niveau.
     * 
     * @param pPosX
     *            La position en abscisse
     * @param pPosY
     *            La position en ordonn�
     */
    private void checkBoundaries(int pPosX, int pPosY) {
        if (pPosX < 0 || pPosX >= cases.length || pPosY < 0
                || pPosY >= cases[0].length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * TODO comment role
     * 
     * @param pPosX
     *            position en abscisse
     * @param pPosY
     *            position en ordonn�
     */
    public void setCharacter(int pPosX, int pPosY) {
        checkBoundaries(pPosX, pPosY);
        characterPosX = pPosX;
        characterPosY = pPosY;
        cases[pPosX][pPosY].setType(CaseType.CHARACTER);
    }

    /**
     * M�thode permettant de positionner une case vide aux coordonn�es donn�e
     * 
     * @param pPosX
     *            position en abscisse
     * @param pPosY
     *            position en ordonn�
     */
    public void addEmpty(int pPosX, int pPosY) {
        checkBoundaries(pPosX, pPosY);
        cases[pPosX][pPosY].setType(CaseType.EMPTY);
    }

    /**
     * M�thode permettant de positionner un mur aux coordonn�es donn�e
     * 
     * @param pPosX
     *            position en abscisse
     * @param pPosY
     *            position en ordonn�
     */
    public void addWall(int pPosX, int pPosY) {
        checkBoundaries(pPosX, pPosY);
        cases[pPosX][pPosY].setType(CaseType.WALL);
    }

    /**
     * M�thode permettant de positionner une pierre aux coordonn�es donn�e
     * 
     * @param pPosX
     *            position en abscisse
     * @param pPosY
     *            position en ordonn�
     */
    public void addStone(int pPosX, int pPosY) {
        checkBoundaries(pPosX, pPosY);
        cases[pPosX][pPosY].setType(CaseType.STONE);
    }

    /**
     * M�thode permettant de positionner un trou aux coordonn�es donn�e
     * 
     * @param pPosX
     *            position en abscisse
     * @param pPosY
     *            position en ordonn�
     */
    public void addHole(int pPosX, int pPosY) {
        checkBoundaries(pPosX, pPosY);
        cases[pPosX][pPosY].setType(CaseType.HOLE);
    }

    /**
     * D�place le personnage � droite apr�s avoir g�rer les collisions
     */
    public void moveEast() {
        if (checkMoveEast()) {
            if (cases[characterPosX + 1][characterPosY].getType().equals(
                    CaseType.STONE)
                    || cases[characterPosX + 1][characterPosY].getType()
                            .equals(CaseType.FILLED_HOLE)) {
                if (cases[characterPosX + 2][characterPosY].getType().equals(
                        CaseType.HOLE)) {
                    cases[characterPosX + 2][characterPosY]
                            .setType(CaseType.FILLED_HOLE);
                } else {
                    cases[characterPosX + 2][characterPosY]
                            .setType(CaseType.STONE);
                }
            }
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

            if (cases[characterPosX][characterPosY].getType().equals(
                    CaseType.CHARACTER_ON_HOLE)) {
                cases[characterPosX][characterPosY].setType(CaseType.HOLE);
            } else {
                cases[characterPosX][characterPosY].setType(CaseType.EMPTY);
            }

            characterPosX++;

        }
    }

    /**
     * D�place le personnage � gauche apr�s avoir g�rer les collisions
     */
    public void moveWest() {
        if (checkMoveWest()) {
            if (cases[characterPosX - 1][characterPosY].getType().equals(
                    CaseType.STONE)
                    || cases[characterPosX - 1][characterPosY].getType()
                            .equals(CaseType.FILLED_HOLE)) {
                if (cases[characterPosX - 2][characterPosY].getType().equals(
                        CaseType.HOLE)) {
                    cases[characterPosX - 2][characterPosY]
                            .setType(CaseType.FILLED_HOLE);
                } else {
                    cases[characterPosX - 2][characterPosY]
                            .setType(CaseType.STONE);
                }
            }
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

            if (cases[characterPosX][characterPosY].getType().equals(
                    CaseType.CHARACTER_ON_HOLE)) {
                cases[characterPosX][characterPosY].setType(CaseType.HOLE);
            } else {
                cases[characterPosX][characterPosY].setType(CaseType.EMPTY);
            }

            characterPosX--;
        }
    }

    /**
     * D�place le personnage en bas apr�s avoir g�rer les collisions
     */
    public void moveSouth() {
        if (checkMoveSouth()) {
            if (cases[characterPosX][characterPosY + 1].getType().equals(
                    CaseType.STONE)
                    || cases[characterPosX][characterPosY + 1].getType()
                            .equals(CaseType.FILLED_HOLE)) {
                if (cases[characterPosX][characterPosY + 2].getType().equals(
                        CaseType.HOLE)) {
                    cases[characterPosX][characterPosY + 2]
                            .setType(CaseType.FILLED_HOLE);
                } else {
                    cases[characterPosX][characterPosY + 2]
                            .setType(CaseType.STONE);
                }
            }
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

            if (cases[characterPosX][characterPosY].getType().equals(
                    CaseType.CHARACTER_ON_HOLE)) {
                cases[characterPosX][characterPosY].setType(CaseType.HOLE);
            } else {
                cases[characterPosX][characterPosY].setType(CaseType.EMPTY);
            }
            characterPosY++;
        }
    }

    /**
     * D�place le personnage en haut apr�s avoir g�rer les collisions
     */
    public void moveNorth() {
        if (checkMoveNorth()) {
            if (cases[characterPosX][characterPosY - 1].getType().equals(
                    CaseType.STONE)
                    || cases[characterPosX][characterPosY - 1].getType()
                            .equals(CaseType.FILLED_HOLE)) {
                if (cases[characterPosX][characterPosY - 2].getType().equals(
                        CaseType.HOLE)) {
                    cases[characterPosX][characterPosY - 2]
                            .setType(CaseType.FILLED_HOLE);
                } else {
                    cases[characterPosX][characterPosY - 2]
                            .setType(CaseType.STONE);
                }
            }
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

            if (cases[characterPosX][characterPosY].getType().equals(
                    CaseType.CHARACTER_ON_HOLE)) {
                cases[characterPosX][characterPosY].setType(CaseType.HOLE);
            } else {
                cases[characterPosX][characterPosY].setType(CaseType.EMPTY);
            }
            characterPosY--;
        }
    }

    /**
     * G�re les collisions pour savoir si le personnage peut bouger � droite
     * 
     * @return true si le d�placement est possible, false sinon
     */
    private boolean checkMoveEast() {
    	// Verification qu'on est dans les limites du tableau
        try {
            checkBoundaries(characterPosX + 1, characterPosY);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        // V�rification qu'il n'y ai pas de mur
        if (cases[characterPosX + 1][characterPosY].getType().equals(
                CaseType.WALL)) {
            return false;
        }
        // Existance pierre ?
        if (cases[characterPosX + 1][characterPosY].getType().equals(
                CaseType.STONE)
                || cases[characterPosX + 1][characterPosY].getType().equals(
                        CaseType.FILLED_HOLE)) {
        	// On verifie qu'elle va pas etre en dehors du tableau
            try {
                checkBoundaries(characterPosX + 2, characterPosY);
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            // On v�rifie qu'il n'y ai pas une autre pierre ou un mur derriere.
            if (cases[characterPosX + 2][characterPosY].getType().equals(
                    CaseType.WALL)
                    || cases[characterPosX + 2][characterPosY].getType()
                            .equals(CaseType.STONE)
                    || cases[characterPosX + 2][characterPosY].getType()
                            .equals(CaseType.FILLED_HOLE)) {
                return false;
            }
        }
        return true;
    }

    /**
     * G�re les collisions pour savoir si le personnage peut bouger � gauche
     * 
     * @return true si le d�placement est possible, false sinon
     */
    private boolean checkMoveWest() {
        try {
            checkBoundaries(characterPosX - 1, characterPosY);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        if (cases[characterPosX - 1][characterPosY].getType().equals(
                CaseType.WALL)) {
            return false;
        }
        if (cases[characterPosX - 1][characterPosY].getType().equals(
                CaseType.STONE)
                || cases[characterPosX - 1][characterPosY].getType().equals(
                        CaseType.FILLED_HOLE)) {
            try {
                checkBoundaries(characterPosX - 2, characterPosY);
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            if (cases[characterPosX - 2][characterPosY].getType().equals(
                    CaseType.WALL)
                    || cases[characterPosX - 2][characterPosY].getType()
                            .equals(CaseType.STONE)
                    || cases[characterPosX - 2][characterPosY].getType()
                            .equals(CaseType.FILLED_HOLE)) {
                return false;
            }
        }
        return true;
    }

    /**
     * G�re les collisions pour savoir si le personnage peut bouger en haut
     * 
     * @return true si le d�placement est possible, false sinon
     */
    private boolean checkMoveSouth() {
        try {
            checkBoundaries(characterPosX, characterPosY + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        if (cases[characterPosX][characterPosY + 1].getType().equals(
                CaseType.WALL)) {
            return false;
        }
        if (cases[characterPosX][characterPosY + 1].getType().equals(
                CaseType.STONE)
                || cases[characterPosX][characterPosY + 1].getType().equals(
                        CaseType.FILLED_HOLE)) {
            try {
                checkBoundaries(characterPosX, characterPosY + 2);
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            if (cases[characterPosX][characterPosY + 2].getType().equals(
                    CaseType.WALL)
                    || cases[characterPosX][characterPosY + 2].getType()
                            .equals(CaseType.STONE)
                    || cases[characterPosX][characterPosY + 2].getType()
                            .equals(CaseType.FILLED_HOLE)) {
                return false;
            }
        }
        return true;
    }

    /**
     * G�re les collisions pour savoir si le personnage peut bouger en bas
     * 
     * @return true si le d�placement est possible, false sinon
     */
    private boolean checkMoveNorth() {
        try {
            checkBoundaries(characterPosX, characterPosY - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        if (cases[characterPosX][characterPosY - 1].getType().equals(
                CaseType.WALL)) {
            return false;
        }
        if (cases[characterPosX][characterPosY - 1].getType().equals(
                CaseType.STONE)
                || cases[characterPosX][characterPosY - 1].getType().equals(
                        CaseType.FILLED_HOLE)) {
            try {
                checkBoundaries(characterPosX, characterPosY - 2);
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            if (cases[characterPosX][characterPosY - 2].getType().equals(
                    CaseType.WALL)
                    || cases[characterPosX][characterPosY - 2].getType()
                            .equals(CaseType.STONE)
                    || cases[characterPosX][characterPosY - 2].getType()
                            .equals(CaseType.FILLED_HOLE)) {
                return false;
            }
        }
        return true;
    }

    /**
     * V�rifie s'il reste un trou sans pierre.
     * 
     * @return true si tous les trous sont recouvert, false sinon
     */
    public boolean checkVictory() {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[0].length; j++) {
                if (cases[i][j].getType().equals(CaseType.HOLE)
                        || cases[i][j].getType().equals(
                                CaseType.CHARACTER_ON_HOLE)) {
                    return false;
                }
            }
        }
        return true;
    }

}
