package game;


public abstract class Unite {

	protected int defense;
	protected int attaque;
	protected int valeur;
	protected int vie;	
	protected int caseX;
	protected int caseY;
	
	protected String name;
	
	protected int rayonDeplacement;
	
	public int getRayonDeplacement() {
		return rayonDeplacement;
	}

	public void setRayonDeplacement(int rayonDeplacement) {
		this.rayonDeplacement = rayonDeplacement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void deplacement(int x, int y); //méthode de déplacement abstraite
	
	public abstract void drawImage();
	
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
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public int getVie() {
		return vie;
	}
	public void setVie(int vie) {
		this.vie = vie;
	}
	
	public int getCaseX() {
		return caseX;
	}
	public void setCaseX(int caseX) {
		this.caseX = caseX;
	}
	public int getCaseY() {
		return caseY;
	}
	public void setCaseY(int caseY) {
		this.caseY = caseY;
	}
}
