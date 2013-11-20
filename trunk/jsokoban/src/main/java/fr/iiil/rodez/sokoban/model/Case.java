package fr.iiil.rodez.sokoban.model;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

/**
 * Classe représentant une case du jeu Sokoban
 * 
 * @author Administrateur
 * 
 */
public class Case {
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
     */
    public void paint(Graphics2D contexte, int pPosX, int pPosY, int pWidth,
            int pHeight) {
        // TODO faire un meilleur graphisme
        switch (type) {
        case CHARACTER:
            BufferedImage img = new BufferedImage(pWidth, pHeight,
                    BufferedImage.TYPE_INT_RGB);
            try {
                img = ImageIO.read(new FileImageInputStream(new File(getClass().getResource("/mario.jpg").getFile())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // FIXME position !
            contexte.drawImage(img, pPosX, pPosY, pPosX + pWidth, pPosY
                    + pHeight, pPosX, pPosY, pPosX + pWidth, pPosY + pHeight,
                    null);

            // contexte.draw(new Ellipse2D.Double(pPosX * pWidth, pPosY *
            // pHeight,
            // pWidth, pHeight));
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
