package unites;

import java.util.ArrayList;

import game.Unite;

public class Tank extends Unite {

	public Tank()
	{		
		this.attaque = 500;
		this.defense = 350;
		this.valeur = 80;
		this.vie = 700;
		this.caseX=-1;
		this.caseY=-1;
		this.name="Tank";
		this.rayonDeplacement= 5;
	}
	
	public Tank(int caseX, int caseY, String nomEquipe)
	{		
		this.attaque = 500;
		this.defense = 350;
		this.valeur = 80;
		this.vie = 700;
		this.caseX=caseX;
		this.caseY=caseY;
		this.name="Tank";
		this.rayonDeplacement= 5;
		this.nomEquipe = nomEquipe;
	}
	
	public Tank(int attaque, int defense, int valeur, int vie, String name, int rayonDeplacement, int caseX, int caseY, String nomEquipe)
	{
		this.attaque = attaque;
		this.defense = defense;
		this.valeur = valeur;
		this.vie = vie;
		this.caseX=caseX;
		this.caseY=caseY;
		this.name=name;
		this.rayonDeplacement = rayonDeplacement;
		this.nomEquipe = nomEquipe;
	}

	
	@Override
	public void drawImage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deplacement(int x, int y) {
		// TODO Auto-generated method stub
		this.caseX = x;
		this.caseY = y;
		
	}
	
	@Override
	public void vuedeplacement() {
		// TODO Auto-generated method stub
		
	}

}
