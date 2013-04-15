package phases;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;

import tools.Constantes;
import tools.Entree;
import game.Case;
import game.Equipe;
import game.Joueur;
import game.Map;
import game.Unite;

public class UpdateDeploiment {

	private int hudUniteSelection;//coordonées de l'unité que l'on survole dans l'hud
	private Case caseSelection;
	private int selectionX;//coordonées du carré de selection
	private int selectionY;
	private ArrayList<String> casesDeploiementA1;
	private ArrayList<String> casesDeploiementA2;
	private ArrayList<String> casesDeploiementB1;
	private ArrayList<String> casesDeploiementB2;
	
	private boolean pret;
	private String phase;
	private String session;
	

	public void initCasesDeploiement(Map map, Equipe equipeA, Equipe equipeB)//on va faire un rectangle, pour placer les unités à 5 cases maximum en X && en faire un autre pour des unites specials qui peuvent etre deploiyer plus loin
	{		
		this.casesDeploiementA1 = new ArrayList<String>();
		this.casesDeploiementA2 = new ArrayList<String>();
		this.casesDeploiementB1 = new ArrayList<String>();
		this.casesDeploiementB2 = new ArrayList<String>();	
		
		if(equipeA.getNbJoueurEquipe() == 1)//suvant le nb de joueur par equipe la taille des zones change
		{
			for(int y = 1; y < map.getHeight()-1; y++)//genere la zone
			{
				for(int x = 1; x < 5; x++)
				{
					this.casesDeploiementA1.add(x+":"+y);
				}
			}
		}
		else
		{
			for(int y = 1; y < map.getHeight()/2 -1; y++)//genere la zone
			{
				for(int x = 1; x < 5; x++)
				{
					this.casesDeploiementA1.add(x+":"+y);
				}
			}
			for(int y = map.getHeight()/2 + 1; y < map.getHeight()-1; y++)//genere la zone
			{
				for(int x = 1; x < 5; x++)
				{
					this.casesDeploiementA2.add(x+":"+y);
				}
			}
		}
		
		if(equipeB.getNbJoueurEquipe() == 1)//suvant le nb de joueur par equipe la taille des zones change
		{
			for(int y = 1; y < map.getHeight()-1; y++)//genere la zone
			{
				for(int x = map.getWidth() - 5; x < map.getWidth() - 1; x++)
				{
					this.casesDeploiementB1.add(x+":"+y);
				}
			}	
		}
		else
		{
			for(int y = 1; y < map.getHeight()/2 -1; y++)//genere la zone
			{
				for(int x = map.getWidth() - 5; x < map.getWidth() - 1; x++)
				{
					this.casesDeploiementB1.add(x+":"+y);
				}
			}
			for(int y = map.getHeight()/2 + 1; y < map.getHeight()-1; y++)//genere la zone
			{
				for(int x = map.getWidth() - 5; x < map.getWidth() - 1; x++)
				{
					this.casesDeploiementB2.add(x+":"+y);
				}
			}
		}
	}
	
	public ArrayList<Unite> initUniteDeploiment(Joueur joueur, ArrayList<String> casesDeploiement)// on place toute les unités sur la carte && A REFAIRE pour centrer les unités dans la zone de deploiment
	{
		int x = 0;
		int y = 0;
		ArrayList<Unite> liste_unites = new ArrayList<Unite>(); //c'est celle que l'on renvera
		ArrayList<Unite> liste_unitesbis = new ArrayList<Unite>();
		
		liste_unitesbis = joueur.getListe_unites();
		
		for(Unite unite : liste_unitesbis)
		{
			if(casesDeploiement.contains(x+":"+y))
			{
				unite.setCaseX(x);
				unite.setCaseY(y);
				liste_unites.add(unite);
			}
			else//on change de colonne des que la premiere est pleine ou si ce n'est pas la bonne colonne
			{
				x++;
				y=0;
			}
			y++;
		}
		return liste_unites;
	}
	
	public Unite getUniteHud(GameContainer container, Joueur joueur, Entree entree)//selectioner une unité dans l'hud
	{
		Unite unite = null;
		int nbUnites;
		HashMap<String, Integer> touches = entree.getTouches();
		
		nbUnites = joueur.getNbUnites();
		
		if(touches.get("MOUSE_Y") < container.getHeight() - Constantes.HUD_LISTE_UNITES_DECALAGE_Y && touches.get("MOUSE_Y") > container.getHeight() - Constantes.HUD_LISTE_UNITES_DECALAGE_Y - Constantes.HUD_LISTE_UNITES_HEIGHT) 
		{
			if(touches.get("MOUSE_X") < Constantes.HUD_LISTE_UNITES_DECALAGE_X + Constantes.HUD_LISTE_UNITES_WIDTH && touches.get("MOUSE_X") > Constantes.HUD_LISTE_UNITES_DECALAGE_X) 
			{
				for(int i = 0; i < nbUnites; i++)
				{	
					if(entree.moa((i) * Constantes.HUD_LISTE_UNITE_WIDTH + Constantes.HUD_LISTE_UNITES_DECALAGE_X, container.getHeight() - Constantes.HUD_LISTE_UNITES_DECALAGE_Y - Constantes.HUD_LISTE_UNITES_HEIGHT, Constantes.HUD_LISTE_UNITE_WIDTH, Constantes.HUD_LISTE_UNITE_HEIGHT))
					{
						if(touches.get("SPACE") == 1 || touches.get("MOUSE_LEFT") == 1) 
						{
							unite = joueur.getUnite(i);	
						}
					}
				}
				
			}
		}
		return unite;
	}

	public Unite getUniteCarte(GameContainer container, Map map, Joueur joueur, Entree entree, int screenX, int screenY)//selectioner une unité sur la carte
	{
		Unite unite = null;
		HashMap<String, Integer> touches = entree.getTouches();
		
		if(touches.get("MOUSE_Y") < container.getHeight() - Constantes.IHM_BAS_HAUTEUR)
		{
			for(int x=0; x< map.getWidth(); x++)
			{
				for(int y=0; y< map.getHeight(); y++)
		    	{
					if(entree.moa(x * map.getTileWidth() + screenX, y *  map.getTileHeight() + screenY, map.getTileWidth(), map.getTileHeight()))
		        	{		
						this.selectionX = x * map.getTileWidth() + screenX;
						this.selectionY = y * map.getTileHeight() + screenY;           	
						
		        		if(touches.get("SPACE") == 1 || touches.get("MOUSE_LEFT") == 1) 
		            	{
		                	this.caseSelection = map.recupUneCase(x, y);
		                	
		                	//si une unité est presente sur cette case
							if(this.caseSelection != null)
							{
								if(this.caseSelection.getUnite() != null)
								{	
									unite = this.caseSelection.getUnite();
									//si c'est notre unité on peut la deplacer
								}
							}
		            	}
		        	}
		    	}
			}
		}
		return unite;
	}
	
	public Unite setUniteCase(Map map, int screenX, int screenY, Unite uniteSelection, ArrayList<String> casesDeploiement, Entree entree) {//on positionne une unité sur la carte
		Unite unite = null;
		unite = uniteSelection;
		HashMap<String, Integer> touches = entree.getTouches();
		
		if(touches.get("MOUSE_RIGHT") == 1) 
    	{
			for(int x=0; x< map.getWidth(); x++)
			{
				for(int y=0; y< map.getHeight(); y++)
		    	{
					if(entree.moa(x * map.getTileWidth() + screenX, y *  map.getTileHeight() + screenY, map.getTileWidth(), map.getTileHeight()))
		        	{
						if(casesDeploiement.contains(x+":"+y))
						{
							uniteSelection.setCaseX(x);
							uniteSelection.setCaseY(y);
							unite = uniteSelection;	
						}
		        	}	
		    	}
			}	
    	}
		return unite;	
	}
	
	public void majCases() {
		
	}
	
	public void hudJoueur() {
		
	}
	
	public void menu() {
		
	}
	
	public void sessionPhase() {
		
	}
	
	public Case getCaseSelection() {
		return this.caseSelection;
	}

	public int getSelectionX() {
		return this.selectionX;
	}

	public int getSelectionY() {
		return this.selectionY;
	}

	public int getHudUniteSelection() {
		return this.hudUniteSelection;
	}

	public ArrayList<String> getCasesDeploiementA1() {
		return this.casesDeploiementA1;
	}

	public ArrayList<String> getCasesDeploiementA2() {
		return this.casesDeploiementA2;
	}

	public ArrayList<String> getCasesDeploiementB1() {
		return this.casesDeploiementB1;
	}

	public ArrayList<String> getCasesDeploiementB2() {
		return this.casesDeploiementB2;
	}

	public boolean getPret() {
		return this.pret;
	}

	public String getPhase() {
		return this.phase;
	}

	public String getSession() {
		return this.session;
	}
}
