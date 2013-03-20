package model;

import game.Case;
import game.Unite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.newdawn.slick.SlickException;

import states.Partie;
import tools.Constantes;
import tools.Entree;
import tools.Fonction;

public class BatailleModel extends PhaseModel{
	
	public static int phaseDeBataille;
	
	private ArrayList<Unite> unitesDejaDeplaces = new ArrayList<Unite>();
	private boolean deplacementPossible;
	
	private ArrayList<String> casesPosibiliteDeplacement = new ArrayList<String>();
	private ArrayList<String> casesChemin = new ArrayList<String>();

	private ArrayList<Unite> al_unites;
	private Unite uniteSelection;
	private Unite uniteJ2Selection;

	private boolean deplacementEnCours;
	private int deplacement;
	private int tempsPasse;
	
	private String msgError;
	
	public BatailleModel(Partie partie) throws SlickException
	{
		super(partie);
		
		this.al_unites = new ArrayList<Unite>(); //où seront stockés l'ensembles des unités présentes sur la carte
		this.deplacementPossible = false;
		this.msgError = "";
	}
	
	public void addAllUnites()
	{
		for(Unite unite : this.partie.getJ1().getAl_unitesEquipe())
		{
			this.al_unites.add(unite);
		}
		for(Unite unite : this.partie.getIa().getAl_unitesEquipe())
		{
			this.al_unites.add(unite);
		}
	}

	public void selectionUnite()
	{
		//Si on a dŽjˆ une unitŽ de sŽlectionnŽ, on peut la deplacer...
		boolean isSelect = false; //variable qui va permettre de voir si une unité DU j1 est sélectionné
		
		if(this.caseSelection != null)
    	{
    		this.uniteJ2Selection = null; //raz du l'unité j2 selectionné
		
			//Première boucle pour voir si il y a une unité appartenant au j1 sur cette case
			boolean uniteAuj1 = false;
			for(Unite unite : this.partie.getJ1().getAl_unitesEquipe())
        	{
        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
        		{
        			
        			this.uniteSelection = unite;
        			isSelect = true;
        			if(this.unitesDejaDeplaces.contains(unite)) //on regarde si lunité a déjà été déplacé
        			{
        				this.msgError = "Cette unité a déjà été déplacée.";
        				this.deplacementPossible = false;
        			}
        			else
        			{
        				int rayon = unite.getRayonDeplacement();
	        			this.casesPosibiliteDeplacement = Fonction.calculRayonDeplacement(this.caseX, this.caseY, rayon, this.partie.getMap());
	        			
	        			uniteAuj1 = true;
	        			this.deplacementPossible = true;
	        			BatailleModel.phaseDeBataille = Constantes.PHASE_BATAILLE_DEPLACEMENT;
        			}
        		}
        	}
			if(uniteAuj1 == false) //Si l'unité n'appartient pas au j1, on va simplement récupérer les informations de cette unité
			{
				for(Unite unite : this.partie.getIa().getAl_unitesEquipe())
	        	{
	        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
	        		{
	        			this.uniteJ2Selection = unite;
	        			this.deplacementPossible = false;
	        		}
	        	}
			}
    	}
		
		if(isSelect == false)
		{
			if(this.deplacementPossible == false)
				this.casesPosibiliteDeplacement.clear();
			
			this.uniteSelection = null;
		}		
	}
	public void deplacementEnCours()
	{
		
		if(this.deplacementPossible == true) //Le déplacement de l'unité est possible (la case choisie ne contient pas d'unité du J1 ni de l'ia et l'unité n'a pas encore été déplacé durant ce tour)
		{
			if(this.casesPosibiliteDeplacement.contains(this.caseX+":"+this.caseY))
			{
				this.uniteSelection.deplacement(this.caseX, this.caseY); //deplacement
				this.unitesDejaDeplaces.add(this.uniteSelection);
				this.deplacementEnCours = true;
				this.majDesCasesOccupes(); //on met à jours les cases occupés ou non
				this.casesPosibiliteDeplacement.clear(); //on delete les cases de possibilite de deplacement
			}
			else
			{
				BatailleModel.phaseDeBataille = Constantes.PHASE_BATAILLE_SELECTION_UNITE;
			}
		}
		if(this.deplacementEnCours)
		{
			int x = 0;
			int y = 0;
			tempsPasse = tempsPasse + delta;
			System.out.println(tempsPasse);
			if(deplacement <= casesChemin.size()-1)
			{
				if (tempsPasse >= Constantes.DELAY) 
				{
					String str[] = casesChemin.get(deplacement).split(":");
					x = Integer.parseInt(str[0]);
					y = Integer.parseInt(str[1]);
					this.uniteSelection.deplacement(x, y);
					this.unitesDejaDeplaces.add(this.uniteSelection);
					this.majDesCasesOccupes(); //on met à jours les cases occupés ou non
					this.casesPosibiliteDeplacement.clear(); //on delete les cases de possibilite de deplacement
					tempsPasse = 0;	
					deplacement++;
				}
			}
			else
			{
				deplacement = 0;
				deplacementEnCours = false;
			}
		}
	}
	
	
	public void afficherChemin() //affiche le chemin pris ne marche pas totalement
	{
		if(this.uniteSelection != null)
		{
			int rayon = this.uniteSelection.getRayonDeplacement();
			int cheminX = this.uniteSelection.getCaseX();
			int cheminY = this.uniteSelection.getCaseY();
			boolean endFor = false;
			
			int tileWidth = this.partie.getMap().getTileWidth();
			int tileHeight = this.partie.getMap().getTileHeight();
			int screenX = this.partie.getScreenX();
			int screenY = this.partie.getScreenY();
			Entree entree_clavier = this.partie.getEntree_clavier();
			
			if(this.casesChemin.size() != 0)// si un case chemin est en cours on le recupere
			{
				rayon = rayon - this.casesChemin.size();// on recupere aussi le nombre de deplacement restant
				for(String s : this.casesChemin)
				{
					String str[] = s.split(":");
					cheminX = Integer.parseInt(str[0]);
					cheminY = Integer.parseInt(str[1]);
				}
			}
			if(rayon != 0)//si == 0 on ne peut pas aller plus loin
			{
				for(int x = -1; x<2; x++)// teste les cases autour
				{
					for(int y = -1; y<2; y++)
					{
						if(Math.abs(x) != Math.abs(y) && Math.abs(x) + Math.abs(y) != 0)//pour enlever les cases en diagonal et la case central
						{
							int cheminXbis = cheminX + x;
							int cheminYbis = cheminY + y;
							for(String s : this.casesPosibiliteDeplacement)
							{
								String str[] = s.split(":");
								if(cheminXbis == Integer.parseInt(str[0]) && cheminYbis == Integer.parseInt(str[1]))
								{	
									if(entree_clavier.moa(cheminXbis * tileWidth + screenX, cheminYbis * tileHeight + screenY, tileWidth, tileHeight))
									{
										this.casesChemin.add(cheminXbis+":"+cheminYbis);
										//this.casesPosibiliteDeplacement.clear();
										//this.casesPosibiliteDeplacement = Fonction.calculRayonDeplacement(cheminXbis, cheminYbis, rayon - 1, this.map);
										endFor = true;
									}
								}
								if(endFor)break;
							}
						}
						if(endFor)break;
					}
					if(endFor)break;
				}
			}
			if(entree_clavier.moa(this.uniteSelection.getCaseX() * tileWidth + screenX, this.uniteSelection.getCaseY() * tileHeight + screenY, tileWidth, tileHeight))
			{
				this.casesChemin.clear();
			}
			for(String s : this.casesChemin)
			{
				String str[] = s.split(":");
				if(entree_clavier.moa(Integer.parseInt(str[0]) * tileWidth + screenX, Integer.parseInt(str[1]) * tileHeight + screenY, tileWidth, tileHeight))
				{	

					int indexLast = this.casesChemin.size()-1;
					
					for(int index = this.casesChemin.indexOf(s) + 1; index  < this.casesChemin.size(); index++)
					{
						this.casesChemin.remove(index);
					}
				}
			}
		}	
	}

	
	
	public void majDesCasesOccupes()
	{
		for(Entry<String, Case> entry : this.partie.getMap().getAllCases().entrySet()) //parcourt de toutes les cases de la map
		{
			String key = entry.getKey();
			Case laCase = entry.getValue();
			
			String str[] = key.split(":");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			
			laCase.setEstOccupe(false); //on dit que la case n'est pas occupé avant de faire le test (raz)
			
			for(Unite unite : this.al_unites) //on parcourt les unités et s'il y en a une présente sur une case on récupère le nom de l'équipe
			{
				if(unite.getCaseX() == x && unite.getCaseY() == y)
				{
					laCase.setEstOccupe(true);
					laCase.setEquipe(unite.getNomEquipe());
				}

			}
			
		}
	}
	
	public void checkTouchesEtTemps(HashMap<String, Integer> touches, int delta)
	{
		this.delta = delta;
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
	
	public static int getPhaseDeBataille() {
		return phaseDeBataille;
	}

	public static void setPhaseDeBataille(int phaseDeBataille) {
		BatailleModel.phaseDeBataille = phaseDeBataille;
	}


	public ArrayList<String> getCasesPosibiliteDeplacement() {
		return casesPosibiliteDeplacement;
	}

	public void setCasesPosibiliteDeplacement(
			ArrayList<String> casesPosibiliteDeplacement) {
		this.casesPosibiliteDeplacement = casesPosibiliteDeplacement;
	}

	public ArrayList<String> getCasesChemin() {
		return casesChemin;
	}

	public void setCasesChemin(ArrayList<String> casesChemin) {
		this.casesChemin = casesChemin;
	}

	public ArrayList<Unite> getAl_unites() {
		return al_unites;
	}

	public void setAl_unites(ArrayList<Unite> al_unites) {
		this.al_unites = al_unites;
	}

	public Unite getUniteSelection() {
		return uniteSelection;
	}

	public void setUniteSelection(Unite uniteSelection) {
		this.uniteSelection = uniteSelection;
	}

	public Unite getUniteJ2Selection() {
		return uniteJ2Selection;
	}

	public void setUniteJ2Selection(Unite uniteJ2Selection) {
		this.uniteJ2Selection = uniteJ2Selection;
	}

	public ArrayList<Unite> getUnitesDejaDeplaces() {
		return unitesDejaDeplaces;
	}

	public void setUnitesDejaDeplaces(ArrayList<Unite> unitesDejaDeplaces) {
		this.unitesDejaDeplaces = unitesDejaDeplaces;
	}

	public boolean isDeplacementPossible() {
		return deplacementPossible;
	}

	public void setDeplacementPossible(boolean deplacementPossible) {
		this.deplacementPossible = deplacementPossible;
	}

	public boolean isDeplacementEnCours() {
		return deplacementEnCours;
	}

	public void setDeplacementEnCours(boolean deplacementEnCours) {
		this.deplacementEnCours = deplacementEnCours;
	}

	public int getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(int deplacement) {
		this.deplacement = deplacement;
	}

	public int getTempsPasse() {
		return tempsPasse;
	}

	public void setTempsPasse(int tempsPasse) {
		this.tempsPasse = tempsPasse;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	
	
}
