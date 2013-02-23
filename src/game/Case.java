package game;

public abstract class Case {

	protected int x;
	protected int y;
	protected String typeCase;
	protected int defense;
	protected int attaque;	
	protected boolean estOccupe;
	protected String equipe;
	
	public abstract void methode();
	public abstract void special();
		
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getTypeCase() {
		return typeCase;
	}
	public void setTypeCase(String typeCase) {
		this.typeCase = typeCase;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getAttaque() {
		return attaque;
	}
	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}
	public boolean isEstOccupe() {
		return estOccupe;
	}
	public void setEstOccupe(boolean estOccupe) {
		this.estOccupe = estOccupe;
	}
	public String getEquipe() {
		return this.equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	
}
