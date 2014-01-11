package fr.iiil.rodez.sokoban.ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JTextField;

public class MarioTextField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MarioTextField(String pText) {
		super(pText);
		try {
			Font marioFont = Font.createFont(Font.TRUETYPE_FONT,
					MarioLabel.class.getResource("/SuperMario256.ttf")
							.openStream());
			marioFont = marioFont.deriveFont(Font.PLAIN, 18);
			setFont(marioFont);

		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSize(150, 50);
	}

	public MarioTextField() {
		this("");
	}
	
	
	
}
