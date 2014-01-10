package fr.iiil.rodez.sokoban.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JLabel;

public class MarioLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MarioLabel(String pText) {
		super(pText);
		try {
			Font marioFont = Font.createFont(Font.TRUETYPE_FONT, MarioLabel.class
					.getResource("/SuperMario256.ttf").openStream());
			marioFont = marioFont.deriveFont(Font.PLAIN, 24);
			setFont(marioFont);
			
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MarioLabel(String name, Color pColor) {
		this(name);
		setForeground(pColor);
	}
}
