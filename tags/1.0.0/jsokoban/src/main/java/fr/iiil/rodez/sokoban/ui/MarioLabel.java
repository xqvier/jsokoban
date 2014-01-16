package fr.iiil.rodez.sokoban.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JLabel;

/**
 * Classe surchargeant un "JLabel" swing pour y intégrer automatiquement la
 * police Mario.
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class MarioLabel extends JLabel {
	/** ID de sérialisation */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur en indiquant le texte à affiché.
	 * 
	 * @param pText
	 *            le texte.
	 */
	public MarioLabel(String pText) {
		this(pText, 24);
	}

	/**
	 * Constructeur en indiquant le texte à affiché et sa couleur.
	 * 
	 * @param pText
	 *            le texte
	 * @param pColor
	 *            la couleur.
	 */
	public MarioLabel(String pText, Color pColor) {
		this(pText);
		setForeground(pColor);
	}

	/**
	 * Constructeur en indiquant le texte et la taille de la police.
	 * 
	 * @param pText
	 *            le texte.
	 * @param pFontSize
	 *            la taille de la police
	 */
	public MarioLabel(String pText, int pFontSize) {
		super(pText);
		try {
			// Création de la police.
			Font marioFont = Font.createFont(Font.TRUETYPE_FONT,
					MarioLabel.class.getResource("/SuperMario256.ttf")
							.openStream());
			marioFont = marioFont.deriveFont(Font.PLAIN, pFontSize);
			setFont(marioFont);

		} catch (FontFormatException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
