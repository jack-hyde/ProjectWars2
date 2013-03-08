package game;

import java.util.ArrayList;

public abstract class Unite {

	protected int caseX;
	protected int caseY;
	protected String name;
	protected int vie;
	protected int defense;
	protected int attaque;
	protected int valeur;
	protected int rayonDeplacement;
	protected String nomEquipe;
	protected int tempsPasse = 0;
	
	private static final int DELAY = 1000; // 1 second determine la vitesse de deplacement
	
	public boolean deplacement(int x, int y, ArrayList<String> casesChemin, int delta)  //m�thode de d�placement abstraite
	{
		 // The time that has passed, reset to 0 after +-1 sec
		
		tempsPasse = tempsPasse + delta;
		System.out.println(tempsPasse);
		if (tempsPasse >= DELAY) 
		{
			for(int i = 0; i < casesChemin.size(); i++)
			{
				tempsPasse = 0;
				String s = casesChemin.get(i);
				String str[] = s.split(":");
				x = Integer.parseInt(str[0]);
				y = Integer.parseInt(str[1]);
				this.caseX = x;
				this.caseY = y;
				return true; //retourne vrai si le deplacement est fini
			}
		}
		return false;
	}

	
	public abstract void vuedeplacement(); //m�thode de la vue du d�placement abstraite
	{
		
	}
	
	public abstract void drawImage();
	
	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}
	
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
