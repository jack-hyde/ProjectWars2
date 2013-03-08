package unites;

import java.util.ArrayList;

import game.Unite;

public class Sniper extends Unite{

	public Sniper()
	{	
		this.caseX=-1;
		this.caseY=-1;
		this.name="Sniper";
		this.vie = 20;
		this.attaque = 100;
		this.defense = 50;
		this.valeur = 10;
		this.rayonDeplacement = 2;
	}
	
	public Sniper(int caseX, int caseY, String nomEquipe)
	{	
		this.caseX=caseX;
		this.caseY=caseY;
		this.name="Sniper";
		this.vie = 20;
		this.attaque = 100;
		this.defense = 50;
		this.valeur = 10;
		this.rayonDeplacement = 2;
		this.nomEquipe = nomEquipe;
	}
	
	public Sniper(int attaque, int defense, int valeur, int vie, String name, int rayonDeplacement, int caseX, int caseY, String nomEquipe)
	{
		this.caseX=caseX;
		this.caseY=caseY;
		this.name=name;
		this.vie = vie;
		this.attaque = attaque;
		this.defense = defense;
		this.valeur = valeur;
		this.rayonDeplacement = rayonDeplacement;
		this.nomEquipe = nomEquipe;
	}


	@Override
	public void drawImage() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void vuedeplacement() {
		// TODO Auto-generated method stub
		
	}

}
