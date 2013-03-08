package model;

import game.Unite;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.SlickException;

import states.Partie;
import tools.Constantes;
import tools.Entree;
import unites.Sniper;
import unites.Tank;

public class DeploiementModel extends PhaseModel{
	
	public static int phaseDeDeploiement;
	
	private ArrayList<Unite> al_unitesPossibles;
	private int valeurMax;
	private int valeur;
	
	private Unite uniteSelect;

	public DeploiementModel(Partie partie) throws SlickException
	{
		super(partie);
		
		this.valeur = 0; //valeur en cours
		this.valeurMax = 100; //valeur max d'unité pour la partie
		
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
	
	
	public void placerUnite(HashMap<String, Integer> touches)
	{
		
		int tileWidth = this.partie.getMap().getTileWidth();
		int tileHeight = this.partie.getMap().getTileHeight();
		int screenX = this.partie.getScreenX();
		int screenY = this.partie.getScreenY();
		Entree entree_clavier = this.partie.getEntree_clavier();
		//selectionnne la case ou est le pointeur
    	for(int x=0; x<this.partie.getMap().getWidth(); x++)
    	{
    		for(int y=0; y<this.partie.getMap().getHeight(); y++)
        	{
    			
    			if(entree_clavier.moa(x * tileWidth + screenX, y *  tileHeight + screenY, tileWidth, tileHeight))
            	{
    				
    				this.selectionX = x * this.partie.getMap().getTileWidth() + this.partie.getScreenX();
    				this.selectionY = y * this.partie.getMap().getTileHeight() + this.partie.getScreenY();           	
		
            		if(touches.get("SPACE") == 1 || touches.get("MOUSE_LEFT") == 1) //appuis sur espace ou click gauche
                	{
                    	this.caseX = x;
                    	this.caseY = y;
                    	
                    	this.uniteSelect.setCaseX(x);
                    	this.uniteSelect.setCaseY(y);
                    	this.partie.getEquipeEnCours().addUnite(this.uniteSelect);
                    	DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM);
                    	                 	
                	}
            	}
        	}
    	}
	}

	
	
	public void checkTouches(HashMap<String, Integer> touches)
	{
		if(touches.get("E") == 1)//appuis sur E
		{
			if(this.viewIHMBas)
			{
				this.viewIHMBas = false;
			}
			else
			{
				this.viewIHMBas = true;
			}
		}
	}
	
	public static void setPhaseDeDeploiement(int phase)
	{
		DeploiementModel.phaseDeDeploiement = phase;
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

	public static int getPhaseDeDeploiement() {
		return phaseDeDeploiement;
	}
	
	public Unite getUniteSelect() {
		return uniteSelect;
	}


	public void setUniteSelect(Unite uniteSelect) {
		this.uniteSelect = uniteSelect;
	}


	

}
