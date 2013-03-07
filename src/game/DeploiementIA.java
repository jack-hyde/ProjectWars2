package game;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import states.Partie;
import tools.Constantes;
import tools.Entree;
import unites.Sniper;
import unites.Tank;

public class DeploiementIA{

	private Equipe equipe;
	private Partie partie;
	
	private GameContainer container;
	
	private ArrayList<Unite> al_unitesPossibles;
	private int valeurMax;
	private int valeur;
	
	private Unite uniteSelect;
	




	public DeploiementIA(Equipe equipe, Partie partie) throws SlickException
	{
		this.partie = partie;
		this.container = partie.getContainer();
		this.valeur = 0; //valeur en cours
		this.valeurMax = 100; //valeur max d'unité pour la partie
		this.equipe = equipe;
		
		//On remplit al_unitesPossibles
		
		this.al_unitesPossibles = new ArrayList<Unite>();
		this.razUnitesPossibles();
		
		
	    
	}
	
	public void razUnitesPossibles()
	{
		this.al_unitesPossibles.clear();
		
		Unite sniper = new Sniper();
		if((sniper.getValeur() + this.valeur) <= this.valeurMax)
		{
			this.al_unitesPossibles.add(sniper);
		}
		
		Unite tank = new Tank();
		if(tank.getValeur() + this.valeur <= this.valeurMax)
		{
			this.al_unitesPossibles.add(tank);
		}
	}
	

	
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}


	public GameContainer getContainer() {
		return container;
	}

	public void setContainer(GameContainer container) {
		this.container = container;
	}

	public ArrayList<Unite> getAl_unitesPossibles() {
		return al_unitesPossibles;
	}

	public void setAl_unitesPossibles(ArrayList<Unite> al_unitesPossibles) {
		this.al_unitesPossibles = al_unitesPossibles;
	}

	public int getValeurMax() {
		return valeurMax;
	}

	public void setValeurMax(int valeurMax) {
		this.valeurMax = valeurMax;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	public Unite getUniteSelect() {
		return uniteSelect;
	}


	public void setUniteSelect(Unite uniteSelect) {
		this.uniteSelect = uniteSelect;
	}


	

}
