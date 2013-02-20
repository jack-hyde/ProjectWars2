package unites;

import game.Unite;

public class Sniper extends Unite{

	public Sniper(int caseX, int caseY)
	{	
		this.caseX=caseX;
		this.caseY=caseY;
		this.name="Sniper";
		this.vie = 20;
		this.attaque = 100;
		this.defense = 50;
		this.valeur = 10;
		this.rayonDeplacement = 2;
	}
	
	public Sniper(int attaque, int defense, int valeur, int vie, String name, int rayonDeplacement, int caseX, int caseY)
	{
		this.caseX=caseX;
		this.caseY=caseY;
		this.name=name;
		this.vie = vie;
		this.attaque = attaque;
		this.defense = defense;
		this.valeur = valeur;
		this.rayonDeplacement = rayonDeplacement;
	}

	@Override
	public void deplacement(int x, int y) {
		// TODO Auto-generated method stub
		this.caseX = x;
		this.caseY = y;
	}

	@Override
	public void drawImage() {
		// TODO Auto-generated method stub
		
	}

}
