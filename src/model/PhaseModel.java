package model;


import game.Case;

import java.util.HashMap;

import states.Partie;
import tools.Constantes;
import tools.Entree;

public abstract class PhaseModel {

	protected Partie partie;
	protected int selectionX;
	protected int selectionY;
	protected int caseX;
	protected int caseY;
	protected int caseXPrecedent;
	protected int caseYPrecedent;
	protected boolean viewIHMBas; 
	protected int delta;
	protected Case caseSelection;
	
	public PhaseModel(Partie partie)
	{
		this.partie = partie;
		this.viewIHMBas = true;
		this.caseSelection = null;
		this.selectionX = -1;
		this.selectionY= -1;
		this.caseX = -1;
		this.caseY = -1;
		this.caseXPrecedent = -1;
		this.caseYPrecedent = -1;
	}
	
	
	public boolean selectionCase(HashMap<String, Integer> touches)
	{
		int tileWidth = this.partie.getMap().getTileWidth();
		int tileHeight = this.partie.getMap().getTileHeight();
		int screenX = this.partie.getScreenX();
		int screenY = this.partie.getScreenY();
		Entree entree_clavier = this.partie.getEntree_clavier();
		boolean actionClique = false;
		
		if((touches.get("MOUSE_Y") < this.partie.getContainer().getHeight()-Constantes.IHM_BAS_HAUTEUR) || this.viewIHMBas == false) //Si le curseur ne se trouve pas dans l'ihm du bas
		{
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
		        			this.caseXPrecedent = this.caseX;
		        			this.caseYPrecedent = this.caseY;
		        			
		                	this.caseX = x;
		                	this.caseY = y;
		                	actionClique = true;
		                	this.caseSelection = this.partie.getMap().recupUneCase(x, y);
		            	}
		        	}
		    	}
			}
		}
		else
		{
			this.selectionX = -1;
			this.selectionY = -1;
		}
		return actionClique;
	                	
	}



	public boolean isViewIHMBas() {
		return viewIHMBas;
	}

	public void setViewIHMBas(boolean viewIHMBas) {
		this.viewIHMBas = viewIHMBas;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public int getSelectionX() {
		return selectionX;
	}

	public void setSelectionX(int selectionX) {
		this.selectionX = selectionX;
	}

	public int getSelectionY() {
		return selectionY;
	}

	public void setSelectionY(int selectionY) {
		this.selectionY = selectionY;
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
	

	public Case getCaseSelection() {
		return caseSelection;
	}

	public void setCaseSelection(Case caseSelection) {
		this.caseSelection = caseSelection;
	}


	public int getCaseXPrecedent() {
		return caseXPrecedent;
	}


	public void setCaseXPrecedent(int caseXPrecedent) {
		this.caseXPrecedent = caseXPrecedent;
	}


	public int getCaseYPrecedent() {
		return caseYPrecedent;
	}


	public void setCaseYPrecedent(int caseYPrecedent) {
		this.caseYPrecedent = caseYPrecedent;
	}
	
	
}
