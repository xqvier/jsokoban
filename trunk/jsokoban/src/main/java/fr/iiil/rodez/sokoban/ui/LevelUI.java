package fr.iiil.rodez.sokoban.ui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fr.iiil.rodez.sokoban.model.Case;
import fr.iiil.rodez.sokoban.model.CaseType;
import fr.iiil.rodez.sokoban.model.Level;

public class LevelUI extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LevelUI(Level pLevel) {
		paint(pLevel);
	}

	public void setLevel(Level pLevel) {
		paint(pLevel);
	}

	private void paint(Level pLevel) {
		Case[][] cases = pLevel.getCases();
		DefaultTableModel model = new DefaultTableModel(cases[0].length, cases.length);
		for (int i = 0; i < cases.length; i++) {
			Object[] data = new Object[cases[0].length];
			for (int j = 0; j < cases[0].length; j++) {
				if (cases[i][j].getType().equals(CaseType.CHARACTER)) {
					data[j] = "CHAR";
				}
				if (cases[i][j].getType().equals(CaseType.CHARACTER_ON_HOLE)) {
					data[j] = "CHARHOLE";
				}
				if (cases[i][j].getType().equals(CaseType.STONE)) {
					data[j] = "STONE";
				}
				if (cases[i][j].getType().equals(CaseType.WALL)) {
					data[j] = "WALL";
				}
				if (cases[i][j].getType().equals(CaseType.FILLED_HOLE)) {
					data[j] = "HOLESTONE";
				}
				if (cases[i][j].getType().equals(CaseType.HOLE)) {
					data[j] = "HOLE";
				}
				if (cases[i][j].getType().equals(CaseType.EMPTY)) {
					data[j] = "EMPTY";
				}							
			}
			model.addRow(data);
		}
		this.setModel(model);

	}
}
