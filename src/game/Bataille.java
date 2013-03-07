package game;

import ihm.IHMBas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import states.Partie;
import tools.Constantes;
import tools.Entree;
import tools.Fonction;

public class Bataille {

	private Equipe j1;
	private Equipe ia;
	private Partie partie;
	private GameContainer container;
	public static int phaseDeBataille;
	
	private int selectionX;
	private int selectionY;
	private int caseX;
	private int caseY;
	
	private Case caseSelection;
	private ArrayList<String> casesPosibiliteDeplacement = new ArrayList<String>();
	private ArrayList<String> casesChemin = new ArrayList<String>();

	private ArrayList<Unite> al_unites;
	private Unite uniteSelection;
	private Unite uniteJ2Selection;

	private IHMBas ihmBas;
	private boolean viewIHMBas;
	
	public Bataille(Equipe j1, Equipe ia, Partie partie) throws SlickException
	{
		this.partie = partie;
		this.container = partie.getContainer();
		this.j1 = j1;
		this.ia = ia;
		this.selectionX = 0;
		this.selectionY = 0;
		this.caseX = -1;
		this.caseY = -1;
		
		this.viewIHMBas = true;
		this.ihmBas = new IHMBas(this.container);
		
		this.al_unites = new ArrayList<Unite>(); //où seront stockés l'ensembles des unités présentes sur la carte
		
		for(Unite unite : this.j1.getAl_unitesEquipe())
		{
			this.al_unites.add(unite);
		}
		for(Unite unite : this.ia.getAl_unitesEquipe())
		{
			this.al_unites.add(unite);
		}
		
	}

	public void afficherCasePointer(HashMap<String, Integer> touches)
	{
		//selectionnne la case ou est le pointeur	
		int tileWidth = this.partie.getMap().getTileWidth();
		int tileHeight = this.partie.getMap().getTileHeight();
		int screenX = this.partie.getScreenX();
		int screenY = this.partie.getScreenY();
		Entree entree_clavier = this.partie.getEntree_clavier();
		
    	for(int x=0; x<this.partie.getMap().getWidth(); x++)
    	{
    		for(int y=0; y<this.partie.getMap().getHeight(); y++)
        	{
    			if(entree_clavier.moa(x * tileWidth + screenX, y * tileHeight + screenY, tileWidth, tileHeight))
            	{
    				this.selectionX = x * tileWidth + screenX;
                	this.selectionY = y * tileHeight + screenY;               	
		
            		if(touches.get("SPACE") == 1 || touches.get("MOUSE_LEFT") == 1) //appuis sur espace ou click gauche
                	{
            			this.caseX = x;
                    	this.caseY = y;
                    	
                    	this.caseSelection = this.partie.getMap().recupUneCase(x, y);
                    	if(this.caseSelection != null)
                    	{
                    		this.uniteJ2Selection = null; //raz du l'unité j2 selectionné
                    		boolean isSelect = checkUniteEtDeplacement();
                    		//Si aucune unitŽ n'est sŽlectionnŽ, on vide les possibilitŽs de dŽplacement
                    		if(isSelect == false)
                    		{
                    			this.casesPosibiliteDeplacement.clear();
                    			this.uniteSelection = null;
                    		}	
                    	}                   	
                	}
            	}
        	}
    	}
	}
	
	private boolean checkUniteEtDeplacement()
	{
		//Si on a dŽjˆ une unitŽ de sŽlectionnŽ, on peut la deplacer...
		
		boolean isSelect = false; //variable qui va permettre de voir si une unité DU j1 est sélectionné
		if(this.uniteSelection != null)
		{
			boolean uniteSurLaCase = false; //on va regarder s'il n'y a pas deja une unite sur la case ou l'ont veut aller
			for(Unite unite : this.j1.getAl_unitesEquipe())
        	{
        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
        		{
        			uniteSurLaCase = true; //Il y a deja une unitŽ sur cette case
        			this.uniteSelection = unite; //On rŽcup�re donc l'unite qu'il y a sur cette case
        			int rayon = unite.getRayonDeplacement();
        			//On calcul toutes les coordonnŽes qui sont possible pour le dŽplacement de cette unite
        			this.casesPosibiliteDeplacement = Fonction.calculRayonDeplacement(this.caseX, this.caseY, rayon, this.partie.getMap());
        			isSelect = true;
        		}
        	}
			if(uniteSurLaCase == false) //Si aucune unité du j1 n'a été trouvé, on va chcker pour voir s'il n'y a pas une unité adverse sur cette case
			{
				for(Unite unite : this.ia.getAl_unitesEquipe())
	        	{
	        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
	        		{
	        			this.uniteJ2Selection = unite;
	        			uniteSurLaCase = true;
	        		}
	        	}
			}
			
			if(uniteSurLaCase == false) //Si il n'y a pas d'unite du j1 ni de l'j2 sur la case, on deplace l'unite
			{
				if(this.casesPosibiliteDeplacement.contains(this.caseX+":"+this.caseY))
				{
						this.uniteSelection.deplacement(this.caseX, this.caseY); //deplacement
						this.majDesCasesOccupes(); //on met à jours les cases occupés ou non
						this.casesPosibiliteDeplacement.clear(); //on delete les cases de possibilite de deplacement
						isSelect = true;
				}
			}
		}
		else //S'il n'y a pas d'unitŽ de selectionnŽ, on va regarder si la case selectionne contient une unite
		{
			//Première boucle pour voir si il y a une unité appartenant au j1 sur cette case
			boolean uniteAuj1 = false;
			for(Unite unite : this.j1.getAl_unitesEquipe())
        	{
        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
        		{
        			this.uniteSelection = unite;
        			int rayon = unite.getRayonDeplacement();
        			this.casesPosibiliteDeplacement = Fonction.calculRayonDeplacement(this.caseX, this.caseY, rayon, this.partie.getMap());
        			isSelect = true;
        			uniteAuj1 = true;
        		}
        	}
			if(uniteAuj1 == false) //Si l'unité n'appartient pas au j1, on va simplement récupérer les informations de cette unité
			{
				for(Unite unite : this.ia.getAl_unitesEquipe())
	        	{
	        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
	        		{
	        			this.uniteJ2Selection = unite;
	        		}
	        	}
			}
		}		
		return isSelect;	
	}
	public void afficherChemin() //affiche le chemin pris ne marche pas totalement
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
					System.out.println("La case "+laCase.getX()+":"+laCase.getY()+" est occupée par "+unite.getNomEquipe());
				}

			}
			
		}
	}
	
	public void render(Graphics g)
	{
		int tileWidth = this.partie.getMap().getTileWidth();
		int tileHeight = this.partie.getMap().getTileHeight();
		int screenX = this.partie.getScreenX();
		int screenY = this.partie.getScreenY();
		
		Graphics g3 = new Graphics();
		Graphics g2 = new Graphics();
		g2.setColor(Constantes.COLOR_BLANC_TRANSPARENT);
		g3.setColor(Constantes.COLOR_ROUGE);
		
		//affiche le carré de selection
		g.drawRect(this.selectionX, this.selectionY,  tileWidth, tileHeight);
		
		//case selectionné blanc transparent
		g2.fillRect(this.caseX * tileWidth + screenX, this.caseY * tileHeight + screenY, tileWidth, tileHeight);
				
		//valeurs
		g.drawString("screenX "+screenX, 10, 60);
		g.drawString("screenY "+screenY, 10, 80);
			
		
		if(this.uniteSelection != null)
		{
			//Debug.afficheHashMap(this.casesPosibiliteDeplacement);
			for(String s : this.casesPosibiliteDeplacement)
			{
				String str[] = s.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				g2.setColor(Constantes.COLOR_BLANC_TRANSPARENT_2);
				g2.fillRect(x * tileWidth + screenX, y * tileHeight + screenY, tileWidth, tileHeight);
			}
			for(String s : this.casesChemin)
			{
				String str[] = s.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				g3.fillRect(x * tileWidth + screenX, y * tileHeight + screenY, tileWidth/2, tileHeight/2);
			}
			
		}		
		
		//drawAllUnits(); //Affichage des unitŽs	
		this.ihmBas.majIHM(g, this.viewIHMBas, this.caseX, this.caseY, this.caseSelection, this.uniteSelection, this.uniteJ2Selection);
	
		this.drawAllUnits();
	
	}
	
	private void drawAllUnits()
	{
		int tileWidth = this.partie.getMap().getTileWidth();
		int tileHeight = this.partie.getMap().getTileHeight();
		int screenX = this.partie.getScreenX();
		int screenY = this.partie.getScreenY();
		
		Graphics g2 = new Graphics();
		Color ambre = new Color(173,57,14);
		Color jaunepisse = new Color(240,195,0);
		for (Unite unite : this.al_unites) {
			int placementX = unite.getCaseX();
			int placementY = unite.getCaseY();

			if(unite.getName() == "Tank")
			{
				g2.setColor(ambre);

				g2.fillRect(placementX * tileWidth + screenX + 15, placementY * tileHeight + screenY +15, tileWidth-30, tileHeight-30);
			}
			else if(unite.getName() == "Sniper")
			{
				g2.setColor(jaunepisse);

				g2.fillRect(placementX * tileWidth + screenX + 15, placementY * tileHeight + screenY +15, tileWidth-30, tileHeight-30);
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
	
	public Equipe getJ1() {
		return j1;
	}

	public void setJ1(Equipe equipe) {
		this.j1 = equipe;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public static int getPhaseDeBataille() {
		return phaseDeBataille;
	}

	public static void setPhaseDeBataille(int phaseDeBataille) {
		Bataille.phaseDeBataille = phaseDeBataille;
	}
	
	
}
