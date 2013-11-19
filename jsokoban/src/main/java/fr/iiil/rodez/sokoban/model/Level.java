package fr.iiil.rodez.sokoban.model;

public class Level {
	private Case[][] cases;

	private int characterPosX;

	private int characterPosY;

	public Level(int pWidth, int pHeight, int pCharacterPosX, int pCharacterPosY) {
		super();
		if (pWidth <= 0 || pHeight <= 0 || pCharacterPosX <= 0
				|| pCharacterPosX >= pWidth || pCharacterPosY <= 0
				|| pCharacterPosX >= pHeight) {
			throw new ArrayIndexOutOfBoundsException();
		}
		characterPosX = pCharacterPosX;
		characterPosY = pCharacterPosY;
		cases = new Case[pWidth][pHeight];
		cases[characterPosX][characterPosY] = new Case(CaseType.CHARACTER);
		initializeEmptiness();
	}

	public Level(int pWidth, int pHeight) {
		this(pWidth, pHeight, 0, 0);
	}

	public Level() {
		this(20, 20, 0, 0);
	}

	private void initializeEmptiness() {
		for (int i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[0].length; j++) {
				if (i != characterPosX || j != characterPosY) {
					cases[i][j] = new Case(CaseType.EMPTY);
				}
			}
		}
	}

	private void checkBoundaries(int pPosX, int pPosY) {
		if (pPosX < 0 || pPosX >= cases.length || pPosY < 0
				|| pPosY >= cases[0].length) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public void addWall(int pPosX, int pPosY) {
		checkBoundaries(pPosX, pPosY);
		cases[pPosX][pPosY].setType(CaseType.WALL);
	}

	public void addStone(int pPosX, int pPosY) {
		checkBoundaries(pPosX, pPosY);
		cases[pPosX][pPosY].setType(CaseType.STONE);
	}

	public void addHole(int pPosX, int pPosY) {
		checkBoundaries(pPosX, pPosY);
		cases[pPosX][pPosY].setType(CaseType.HOLE);
	}

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

	private boolean checkMoveEast() {
		try {
			checkBoundaries(characterPosX + 1, characterPosY);
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		if (cases[characterPosX + 1][characterPosY].getType().equals(
				CaseType.WALL)) {
			return false;
		}
		if (cases[characterPosX + 1][characterPosY].getType().equals(
				CaseType.STONE)
				|| cases[characterPosX + 1][characterPosY].getType().equals(
						CaseType.FILLED_HOLE)) {
			try {
				checkBoundaries(characterPosX + 2, characterPosY);
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
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
				checkBoundaries(characterPosX + 2, characterPosY);
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

	public Case[][] getCases() {
		return cases;		
	}

}
