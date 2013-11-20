package fr.iiil.rodez.sokoban.model;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Case {
	private CaseType type;

	public Case(CaseType type) {
		super();
		this.type = type;
	}

	public CaseType getType() {
		return type;
	}

	public void setType(CaseType type) {
		this.type = type;
	}

	public void paint(Graphics2D contexte, int pPosX, int pPosY, int pWidth,
			int pHeight) {
		switch (type) {
		case CHARACTER:
			contexte.draw(new Ellipse2D.Double(pPosX * pWidth, pPosY * pHeight,
					pWidth, pHeight));
			break;
		case CHARACTER_ON_HOLE:
			contexte.draw(new Ellipse2D.Double(pPosX * pWidth, pPosY * pHeight,
					pWidth, pHeight));
			contexte.draw(new Ellipse2D.Double(pPosX * pWidth + pWidth / 4,
					pPosY * pHeight + pHeight / 4, pWidth / 2, pHeight / 2));

			break;
		case HOLE:
			contexte.draw(new Ellipse2D.Double(pPosX * pWidth + pWidth / 4,
					pPosY * pHeight + pHeight / 4, pWidth / 2, pHeight / 2));

			break;
		case FILLED_HOLE:
			contexte.draw(new Rectangle2D.Double(pPosX * pWidth + pWidth / 4,
					pPosY * pHeight + pHeight / 4, pWidth / 2, pHeight / 2));
			contexte.draw(new Ellipse2D.Double(pPosX * pWidth + pWidth / 4,
					pPosY * pHeight + pHeight / 4, pWidth / 2, pHeight / 2));

			break;
		case STONE:
			contexte.draw(new Rectangle2D.Double(pPosX * pWidth + pWidth / 4,
					pPosY * pHeight + pHeight / 4, pWidth / 2, pHeight / 2));

			break;
		case WALL:
			contexte.draw(new Rectangle2D.Double(pPosX * pWidth, pPosY
					* pHeight, pWidth, pHeight));
			break;

		default:
			break;
		}
	}

}
