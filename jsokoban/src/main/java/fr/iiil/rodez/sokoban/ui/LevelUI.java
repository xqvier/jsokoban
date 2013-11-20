package fr.iiil.rodez.sokoban.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import fr.iiil.rodez.sokoban.model.Case;
import fr.iiil.rodez.sokoban.model.Level;

public class LevelUI extends JPanel implements KeyListener {

	private Level level;

	private int width;

	private int height;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LevelUI(Level pLevel, int pWidth, int pHeight) {
		level = pLevel;
		width = pWidth;
		height = pHeight;
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.RED);
	}

	public void setLevel(Level pLevel) {
		level = pLevel;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, width, height);
		Case[][] cases = level.getCases();
		Graphics2D g2D = (Graphics2D) g;
		for (int i = 0; i < cases[0].length; i++) {
			for (int j = 0; j < cases[0].length; j++) {
				cases[i][j].paint(g2D, i, j, width
						/ level.getCases().length,
						height / level.getCases()[0].length);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			level.moveEast();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			level.moveWest();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			level.moveNorth();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			level.moveSouth();
		}

		repaint();
		if(level.checkVictory()){
			System.out.println("VICTORY GG!");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
