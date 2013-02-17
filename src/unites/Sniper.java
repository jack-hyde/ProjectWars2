package unites;

import game.Unite;

public class Sniper extends Unite{

	public Sniper(int caseX, int caseY)
	{
		
		this.attaque = 100;
		this.defense = 50;
		this.valeur = 10;
		this.vie = 20;
		this.caseX=caseX;
		this.caseY=caseY;
		this.name="Sniper";
	}
	
	public Sniper(int attaque, int defense, int valeur, int vie, String name, int caseX, int caseY)
	{
		this.attaque = attaque;
		this.defense = defense;
		this.valeur = valeur;
		this.vie = vie;
		this.caseX=caseX;
		this.caseY=caseY;
		this.name=name;
	}

	@Override
	public void deplacement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawImage() {
		// TODO Auto-generated method stub
		
	}

}
