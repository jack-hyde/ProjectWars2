package model;

import game.Unite;

import ihm.IHMDeploiementUnite;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.SlickException;

import states.Partie;
import tools.Constantes;
import tools.Entree;
import tools.Fonction;
import unites.Sniper;
import unites.Tank;

public class DeploiementModel extends PhaseModel{
	
	public static int phaseDeDeploiement;
	
	private ArrayList<Unite> al_unitesPossibles;
	private ArrayList<String> al_casesPossibilitesDeploiement;
	private int valeurMax;
	private int valeur;
	
	private Unite uniteSelect;
	
	private boolean viewButtonStart; //est-ce que le bouton start sera afficher dans la vue ?
	private boolean viewButtonSupprimerUnite; //est-ce que le bouton supprimer unité sera afficher dans la vue ?
	private String msgError; //y a t'il un message d'érreur a afficher dans la vue ?

	public DeploiementModel(Partie partie) throws SlickException
	{
		super(partie);
		
		this.valeur = 0; //valeur en cours
		this.valeurMax = 100; //valeur max d'unité pour la partie
		
		//On remplit al_unitesPossibles
		
		this.viewButtonStart = false;
		this.viewButtonSupprimerUnite = false;
		this.msgError = "";
		
		this.al_unitesPossibles = new ArrayList<Unite>();
		this.updateUnitesPossibles();
		
		this.al_casesPossibilitesDeploiement = new ArrayList<String>();
		this.initCasesPossibiliteDeploiement();
	}
	
	//Permet d'initialiser les unités qu'il y aura dans l'ihm de deploiement. Cette fonction est rappelé à chaque fois
	//qu'une unité est placé, pour mettre à jour l'ihm de deploiement.
	public void updateUnitesPossibles()
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
		
		this.checkValeur();
		
	}
	
	public void checkValeur() //On vérifie si le nombre d'unité correspond bien au minimum à une certaine valeur
	{
		System.out.println("test valeur : "+this.getValeur());
		if(this.getValeur() > 50)
		{
			this.viewButtonStart = true;
		}
		else
		{
			this.viewButtonStart = false;
		}
	}
	
	//Lorsqu'on a selectionné une unité via l'ihm de deploiement, et qu'on clique sur une case, on place l'unite dans cette case
	public void placerUnite() 
	{   
		if(this.al_casesPossibilitesDeploiement.contains(this.caseX+":"+this.caseY))
		{
			boolean testPlacement = true; //permet de voir si la case est déjà occupé par une unité ou pas
			for(Unite unite : this.partie.getJoueurEnCours().getListe_unites())
			{
				if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
				{
					testPlacement = false;
				}
			}
			if(testPlacement == true)
			{
				this.uniteSelect.setCaseX(this.caseX);
		    	this.uniteSelect.setCaseY(this.caseY);
		    	this.partie.getJoueurEnCours().addUnite(this.uniteSelect);
		    	DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM);
		    	this.valeur += this.uniteSelect.getValeur();
		    	this.updateUnitesPossibles();
		    	this.deselectionCase();
			}
			else
			{
				this.msgError = "Cette case est déjà occupé par une unité.";
			}
		}
		else
		{
			this.msgError = "Vous ne pouvez pas placer votre unité sur cette case.";
		}
		
    	
	}
	
	//Lorsque l'ihm de deploiement n'est pas ouvert et qu'on clique sur une case, on regarde si on a une unité de selectionné. 
	//Si c'est le cas on la déplace sur cett case, sinon on selectionne l'unité qu'il y a sur la case s'il y en a une.
	public void deplacementUnite() 
	{
		
		if(this.uniteSelect != null)
		{
			if(this.al_casesPossibilitesDeploiement.contains(this.caseX+":"+this.caseY))
			{
				boolean uniteSurLaCase = false; //on va regarder s'il n'y a pas deja une unite sur la case ou l'ont veut aller
				for(Unite unite : this.partie.getJoueurEnCours().getListe_unites())
	        	{
	        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
	        		{
	        			uniteSurLaCase = true; //Il y a deja une unitŽ sur cette case
	        			this.uniteSelect = unite; //On rŽcup�re donc l'unite qu'il y a sur cette case
	        			
	        		}
	        	}
				if(uniteSurLaCase == false) //Si il n'y a pas d'unite du j1 ni de l'j2 sur la case, on deplace l'unite
				{
				
					this.uniteSelect.setCaseX(this.caseX);
					this.uniteSelect.setCaseY(this.caseY);//deplacement
					this.deselectionCase();
				}
			}
			else
			{
				this.msgError = "Vous ne pouvez pas placer votre unité sur cette case.";
				
				//On réaffiche le carré sur le bonne case
				this.caseX = this.caseXPrecedent;
				this.caseY = this.caseYPrecedent;
			}
		}
		else
		{
			for(Unite unite : this.partie.getJoueurEnCours().getListe_unites())
	    	{
	    		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
	    		{
	    			this.uniteSelect = unite;
	    			this.viewButtonSupprimerUnite = true;
	    		}
	    	}
		}
	}
	
	//Permet de supprimer une unité selectionnée
	public void deleteUnite()
	{
		if(this.uniteSelect != null)
		{
			this.partie.getJoueurEnCours().removeUnite(this.uniteSelect);
			this.valeur = this.valeur - this.uniteSelect.getValeur();
			this.deselectionCase();
			this.updateUnitesPossibles();
			IHMDeploiementUnite.flagMoaUpdate = true;
		}
	}

	//Permet de déselectionné complement une case
	public void deselectionCase()
	{
		this.caseSelection = null;
		this.uniteSelect = null;
		this.caseX = -1;
    	this.caseY = -1;
    	this.viewButtonSupprimerUnite = false;
    	this.msgError = "";
	}
	
	public void initCasesPossibiliteDeploiement()
	{
		//on va faire un rectangle, pour placer les unités à 5 cases maximum en X
		int xMax = 4;
		for(int y=0; y<this.partie.getMap().getHeight(); y++)
		{
			for(int x=0; x<xMax; x++)
			{
				this.al_casesPossibilitesDeploiement.add(x+":"+y);
			}
		}
	}
	
	//Cette fonction gère les touches
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
	

	public void raz()
	{
		this.valeur = 0; //valeur en cours
		this.valeurMax = 100; //valeur max d'unité pour la partie
		
		//On remplit al_unitesPossibles
		
		this.viewButtonStart = false;
		this.viewButtonSupprimerUnite = false;
		this.msgError = "";
		
		this.al_unitesPossibles = new ArrayList<Unite>();
		this.updateUnitesPossibles();
		
		this.al_casesPossibilitesDeploiement = new ArrayList<String>();
		this.initCasesPossibiliteDeploiement();
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

	public boolean isViewButtonStart() {
		return viewButtonStart;
	}

	public void setViewButtonStart(boolean viewButtonStart) {
		this.viewButtonStart = viewButtonStart;
	}

	public boolean isViewButtonSupprimerUnite() {
		return viewButtonSupprimerUnite;
	}

	public void setViewButtonSupprimerUnite(boolean viewButtonSupprimerUnite) {
		this.viewButtonSupprimerUnite = viewButtonSupprimerUnite;
	}

	public ArrayList<String> getAl_casesPossibilitesDeploiement() {
		return al_casesPossibilitesDeploiement;
	}

	public void setAl_casesPossibilitesDeploiement(
			ArrayList<String> al_casesPossibilitesDeploiement) {
		this.al_casesPossibilitesDeploiement = al_casesPossibilitesDeploiement;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}


	

}
