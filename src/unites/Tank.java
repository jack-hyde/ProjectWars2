package unites;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import inc.Constantes;
import game.Unite;

public class Tank extends Unite {

	public Tank(int caseX, int caseY)
	{
		
		this.attaque = 500;
		this.defense = 350;
		this.valeur = 80;
		this.vie = 700;
		this.caseX=caseX;
		this.caseY=caseY;
		this.name="Tank";
	}
	
	public Tank(int attaque, int defense, int valeur, int vie, String name, int caseX, int caseY)
	{
		this.attaque = attaque;
		this.defense = defense;
		this.valeur = valeur;
		this.vie = vie;
		this.caseX=caseX;
		this.caseY=caseY;
		this.name=name;
	}

	
	public void drawImage()
	{

	}
	@Override
	public void deplacement() {
		// TODO Auto-generated method stub
		
	}

}
