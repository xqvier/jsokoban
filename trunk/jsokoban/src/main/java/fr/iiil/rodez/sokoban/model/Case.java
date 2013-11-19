package fr.iiil.rodez.sokoban.model;

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
	
	
}
