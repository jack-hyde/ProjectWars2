package states;

import game.Equipe;

import ihm.IHMBas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import game.Case;
import game.Map;
import game.Unite;
import tools.Constantes;
import tools.Entree;
import tools.Fonction;
import unites.*;



public class Partie extends BasicGameState {

	private int stateID;

	private Equipe joueur;
	private Equipe adversaire;
	private Map map;
	private GameContainer container;
	private int mapWidth;
	private int mapHeight;
	private int screenX;
	private int screenY;
	private int selectionX;
	private int selectionY;
	private int caseX;
	private int caseY;
	private boolean afficherMenu;
	private Case caseSelection;
	private ArrayList<String> casesPosibiliteDeplacement = new ArrayList<String>();
	private ArrayList<String> casesChemin = new ArrayList<String>();
	
	private Entree entree_clavier;
	
	private ArrayList<Unite> al_unites;
	private Unite uniteSelection;
	private Unite uniteAdversaireSelection;
	
	private IHMBas ihmBas;
    
	public Partie(int id) throws SlickException
	{
		this.stateID = id;
		
		this.screenX = 0;
		this.screenY = 0;
		this.selectionX = 0;
		this.selectionY = 0;
		this.caseX = -1;
		this.caseY = -1;
		this.afficherMenu = true;
	}

	//Fonction qui permet d'initialiser la carte au moment où on entre dans ce gamestate
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	{
		this.joueur = new Equipe("Jacky");
		this.adversaire = new Equipe("la tarlouze");
		this.map  = new Map("images/map/map3.tmx");

		this.mapWidth = map.getWidth() * map.getTileWidth();
	    this.mapHeight = map.getHeight() * map.getTileHeight();
	    
	    this.entree_clavier =  new Entree(container);
	    
	    this.ihmBas = new IHMBas(container);
	    
	    //Initialisation des unités du joueur
	    Unite tank1 = new Tank(2, 3, this.joueur.getNomEquipe());
	    Unite tank2 = new Tank(1, 2, this.joueur.getNomEquipe());
	    Unite sniper1 = new Sniper(10,3, this.joueur.getNomEquipe());
	    Unite sniper2 = new Sniper(10, 4, this.joueur.getNomEquipe());
	    //Insertion des unités du joueur dans son tableau d'unite
	    this.joueur.addUnite(tank1);
	    this.joueur.addUnite(tank2);
	    this.joueur.addUnite(sniper1);
	    this.joueur.addUnite(sniper2);
	    
	    //Initialisation des unités de l'adversaire
	    Unite tankAdversaire = new Tank(15, 3, this.adversaire.getNomEquipe());
	    Unite sniperAdversaire = new Sniper(15, 4, this.adversaire.getNomEquipe());
	    
	    //Insertion des unités de l'adversaire dans son tableau d'unite
	    this.adversaire.addUnite(tankAdversaire);
	    this.adversaire.addUnite(sniperAdversaire);
	    
	    //Ajout de toutes les unités dans un tableau global pour que la partie puisse connaitre toutes les unites
	    this.al_unites = new ArrayList<Unite>();
	    this.al_unites.add(tank1);
	    this.al_unites.add(tank2);
	    this.al_unites.add(sniper1);
	    this.al_unites.add(sniper2);
	    this.al_unites.add(tankAdversaire);
	    this.al_unites.add(sniperAdversaire);
	    this.majDesCasesOccupes();
	    
	}	

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.container = container;
		
	    //Debug.afficheHashMap(entree_clavier.getTouches());
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		this.map.render(this.screenX,this.screenY); //render(int x, int y, int sx, int sy, int width, int height)  a faire pour eviter de charger toute la carte meme les parties que l'on ne voit pas a faire aussi pour tout ce qu'il y a dans render
		
		
		Graphics g3 = new Graphics();
		Graphics g2 = new Graphics();
		g2.setColor(Constantes.COLOR_BLANC_TRANSPARENT);
		g3.setColor(Constantes.COLOR_ROUGE);
		
		//affiche le carré de selection
		g.drawRect(this.selectionX, this.selectionY,  this.map.getTileWidth(), this.map.getTileHeight());
		
		//case selectionné blanc transparent
		g2.fillRect(this.caseX * this.map.getTileWidth() + this.screenX, this.caseY * this.map.getTileHeight() + this.screenY, this.map.getTileWidth(), this.map.getTileHeight());
				
		//valeurs
		g.drawString("screenX "+this.screenX, 10, 60);
		g.drawString("screenY "+this.screenY, 10, 80);
			
		
		if(this.uniteSelection != null)
		{
			//Debug.afficheHashMap(this.casesPosibiliteDeplacement);
			for(String s : this.casesPosibiliteDeplacement)
			{
				String str[] = s.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				g2.setColor(Constantes.COLOR_BLANC_TRANSPARENT_2);
				g2.fillRect(x * this.map.getTileWidth() + this.screenX, y * this.map.getTileHeight() + this.screenY, this.map.getTileWidth(), this.map.getTileHeight());
			}
			for(String s : this.casesChemin)
			{
				String str[] = s.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				g3.fillRect(x * this.map.getTileWidth() + this.screenX, y * this.map.getTileHeight() + this.screenY, this.map.getTileWidth()/2, this.map.getTileHeight()/2);
			}
			
		}		
		
		drawAllUnits(); //Affichage des unitŽs	
		this.ihmBas.majIHM(g, this.afficherMenu, this.caseX, this.caseY, this.caseSelection, this.uniteSelection, this.uniteAdversaireSelection);
	}
	
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {
		this.entree_clavier.check();
		HashMap<String, Integer> touches = this.entree_clavier.getTouches();	
		
		this.scroll(touches);
    	this.afficherCasePointer(touches);
    	if(this.uniteSelection != null)
		{
    		afficherChemin();
		}
    	else
    	{
    		casesChemin.clear();
    	}
	}	
	
	//fonction d'affichage des unitŽs (en private car elle ne peut pas etre appelŽ ailleur)
	//Il faudra virer le g.fillRect et utiliser la fonction drawImage (prŽsente dans chaque unitŽ) pour afficher l'image de l'unitŽ
	private void drawAllUnits()
	{
		Graphics g2 = new Graphics();
		Color ambre = new Color(173,57,14);
		Color jaunepisse = new Color(240,195,0);
		for (Unite unite : this.al_unites) {
			int placementX = unite.getCaseX();
			int placementY = unite.getCaseY();

			if(unite.getName() == "Tank")
			{
				g2.setColor(ambre);

				g2.fillRect(placementX * this.map.getTileWidth() + this.screenX + 15, placementY * this.map.getTileHeight() + this.screenY +15, this.map.getTileWidth()-30, this.map.getTileHeight()-30);
			}
			else if(unite.getName() == "Sniper")
			{
				g2.setColor(jaunepisse);

				g2.fillRect(placementX * this.map.getTileWidth() + this.screenX + 15, placementY * this.map.getTileHeight() + this.screenY +15, this.map.getTileWidth()-30, this.map.getTileHeight()-30);
			}
		}
	}		
		
	public void afficherCasePointer(HashMap<String, Integer> touches)
	{
		//selectionnne la case ou est le pointeur	
    	for(int x=0; x<this.map.getWidth(); x++)
    	{
    		for(int y=0; y<this.map.getHeight(); y++)
        	{
    			if(entree_clavier.moa(x * this.map.getTileWidth() + this.screenX, y * this.map.getTileHeight() + this.screenY, this.map.getTileWidth(), this.map.getTileHeight()))
            	{
    				this.selectionX = x * this.map.getTileWidth() + this.screenX;
                	this.selectionY = y * this.map.getTileHeight() + this.screenY;               	
		
            		if(touches.get("SPACE") == 1 || touches.get("MOUSE_LEFT") == 1) //appuis sur espace ou click gauche
                	{
            			this.caseX = x;
                    	this.caseY = y;
                    	
                    	this.caseSelection = this.map.recupUneCase(x, y);
                    	if(this.caseSelection != null)
                    	{
                    		this.uniteAdversaireSelection = null; //raz du l'unité adversaire selectionné
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
		
		boolean isSelect = false; //variable qui va permettre de voir si une unité DU JOUEUR est sélectionné
		if(this.uniteSelection != null)
		{
			boolean uniteSurLaCase = false; //on va regarder s'il n'y a pas deja une unite sur la case ou l'ont veut aller
			for(Unite unite : this.joueur.getAl_unitesEquipe())
        	{
        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
        		{
        			uniteSurLaCase = true; //Il y a deja une unitŽ sur cette case
        			this.uniteSelection = unite; //On rŽcup�re donc l'unite qu'il y a sur cette case
        			int rayon = unite.getRayonDeplacement();
        			//On calcul toutes les coordonnŽes qui sont possible pour le dŽplacement de cette unite
        			this.casesPosibiliteDeplacement = Fonction.calculRayonDeplacement(this.caseX, this.caseY, rayon, this.map);
        			isSelect = true;
        		}
        	}
			if(uniteSurLaCase == false) //Si aucune unité du joueur n'a été trouvé, on va chcker pour voir s'il n'y a pas une unité adverse sur cette case
			{
				for(Unite unite : this.adversaire.getAl_unitesEquipe())
	        	{
	        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
	        		{
	        			this.uniteAdversaireSelection = unite;
	        			uniteSurLaCase = true;
	        		}
	        	}
			}
			
			if(uniteSurLaCase == false) //Si il n'y a pas d'unite du joueur ni de l'adversaire sur la case, on deplace l'unite
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
			//Première boucle pour voir si il y a une unité appartenant au joueur sur cette case
			boolean uniteAuJoueur = false;
			for(Unite unite : this.joueur.getAl_unitesEquipe())
        	{
        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
        		{
        			this.uniteSelection = unite;
        			int rayon = unite.getRayonDeplacement();
        			this.casesPosibiliteDeplacement = Fonction.calculRayonDeplacement(this.caseX, this.caseY, rayon, this.map);
        			isSelect = true;
        			uniteAuJoueur = true;
        		}
        	}
			if(uniteAuJoueur == false) //Si l'unité n'appartient pas au joueur, on va simplement récupérer les informations de cette unité
			{
				for(Unite unite : this.adversaire.getAl_unitesEquipe())
	        	{
	        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
	        		{
	        			this.uniteAdversaireSelection = unite;
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
								if(entree_clavier.moa(cheminXbis * this.map.getTileWidth() + this.screenX, cheminYbis * this.map.getTileHeight() + this.screenY, this.map.getTileWidth(), this.map.getTileHeight()))
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
		if(entree_clavier.moa(this.uniteSelection.getCaseX() * this.map.getTileWidth() + this.screenX, this.uniteSelection.getCaseY() * this.map.getTileHeight() + this.screenY, this.map.getTileWidth(), this.map.getTileHeight()))
		{
			this.casesChemin.clear();
		}
	}
	
	public void scroll(HashMap<String, Integer> touches)
	{
		if(touches.get("Z") >= 1)//appuis sur Z
		{
			if((this.screenY + Constantes.SCROLL_SPEED) >= 0) //pour que le scroll ne depasse pas la carte (pareil en dessous) screenX et Y on des valeur negative
				this.screenY = 0;
			else
				this.screenY = this.screenY+Constantes.SCROLL_SPEED;
		}
		if(touches.get("S") >= 1)//appuis sur S
		{
			if((-this.screenY + Constantes.SCROLL_SPEED + container.getHeight()) > this.mapHeight)
				this.screenY = -this.mapHeight + container.getHeight();
			else
				this.screenY = this.screenY-Constantes.SCROLL_SPEED;
		}
		if(touches.get("Q")>= 1)//appuis sur Q
		{
			if((this.screenX + Constantes.SCROLL_SPEED) > 0)
				this.screenX = 0;
			else
				this.screenX = this.screenX+Constantes.SCROLL_SPEED;
		}
		if(touches.get("D")>= 1)//appuis sur D
		{
			if((-this.screenX + Constantes.SCROLL_SPEED + container.getWidth()) > this.mapWidth)
				this.screenX = -this.mapWidth + container.getWidth();
			else
				this.screenX = this.screenX-Constantes.SCROLL_SPEED;
		}
		if(touches.get("E") == 1)//appuis sur E
		{
			if(this.afficherMenu)
			{
				this.afficherMenu = false;
			}
			else
			{
				this.afficherMenu = true;
			}
		}
	}

	public void majDesCasesOccupes()
	{
		for(Entry<String, Case> entry : this.map.getAllCases().entrySet()) //parcourt de toutes les cases de la map
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
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}
}
