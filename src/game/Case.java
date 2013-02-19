package game;

public abstract class Case {

	protected int defense;
	protected int attaque;
	protected boolean estOccupe;
	protected String typeJoueur;

	protected int x;
	protected int y;
	
	public abstract void methode();
	public abstract void special();

	public String getTypeJoueur() {
		return typeJoueur;
	}
	public void setTypeJoueur(String typeJoueur) {
		this.typeJoueur = typeJoueur;
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
	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

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
}
