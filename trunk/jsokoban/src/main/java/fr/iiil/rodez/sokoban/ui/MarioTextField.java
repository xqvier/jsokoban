package fr.iiil.rodez.sokoban.ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JTextField;

/**
 * Classe surchargeant un "JTextField" swing pour y int�grer automatiquement la
 * police Mario.
 * 
 * @author Axel Lormeau
 * @author Xavier Mourgues
 */
public class MarioTextField extends JTextField {

	/** ID de s�rialisation */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur en indiquant un texte par d�faut dans le champs.
	 * 
	 * @param pText
	 *            Le texte par d�faut.
	 */
	public MarioTextField(String pText) {
		super(pText);
		try {
			// Cr�ation de la police.
			Font marioFont = Font.createFont(Font.TRUETYPE_FONT,
					MarioLabel.class.getResource("/SuperMario256.ttf")
							.openStream());
			marioFont = marioFont.deriveFont(Font.PLAIN, 18);
			setFont(marioFont);

		} catch (FontFormatException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		setSize(150, 50);
	}

	/**
	 * Constructeur par defaut.
	 */
	public MarioTextField() {
		this("");
	}

}
