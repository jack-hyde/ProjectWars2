package states;

import game.Case;
import game.Joueur;
import game.Map;
import game.Unite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tools.Constantes;
import tools.Debug;
import tools.Entree;
import tools.Fonction;
import unites.*;



public class Partie extends BasicGameState {

	private int stateID;

	private Joueur joueur;
	private Joueur adversaire;
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
	private int i = 0;
	private Case caseSelection;
	private ArrayList<String> casesPosibiliteDeplacement = new ArrayList<String>();
	
	private Entree entree_clavier;
	
	private ArrayList<Unite> al_unites;
	private Unite uniteSelection;
    
	public Partie(int id) throws SlickException
	{
		this.stateID = id;
		
		this.screenX = 0;
		this.screenY = 0;
		this.selectionX = 0;
		this.selectionY = 0;
		this.caseX = -1;
		this.caseY = -1;
	}

	//Fonction qui permet d'initialiser la carte au moment où on entre dans ce gamestate
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	{
		this.joueur = new Joueur("Jacky");
		this.adversaire = new Joueur("Tarlouze");
		this.map  = new Map("images/map/map3.tmx");

		this.mapWidth = map.getWidth() * map.getTileWidth();
	    this.mapHeight = map.getHeight() * map.getTileHeight();
	    
	    this.entree_clavier =  new Entree(container);
	    
	    //CrŽation des 2 tanks
	    Unite tank1 = new Tank(2, 3);
	    Unite tank2 = new Tank(1, 2);
	    Unite sniper1 = new Sniper(10,3);
	    Unite sniper2 = new Sniper(10, 4);

	    //Ajout des 2 tanks dans un arraylist pour les "stocker" dans l'objet Partie
	    this.al_unites = new ArrayList<Unite>();
	    this.al_unites.add(tank1);
	    this.al_unites.add(tank2);
	    this.al_unites.add(sniper1);
	    this.al_unites.add(sniper2);
	    
	    //Affichage de l'attaque du tank1
	    System.out.println("Attaque tank : "+tank1.getAttaque());
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
		
		this.map.render(this.screenX,this.screenY); //render(int x, int y, int sx, int sy, int width, int height)  a faire pour eviter de charger toute la carte meme les parties que l'on ne voit pas
		
		Graphics g2 = new Graphics();
		Color blanct = new Color(255,255,255,100);
		Color blanct2 = new Color(255,255,255,50);
		g2.setColor(blanct);
		
		//affiche le carré de selection
		g.drawRect(this.selectionX, this.selectionY,  this.map.getTileWidth(), this.map.getTileHeight());
		
		//case selectionné blanc transparent
		g2.fillRect(this.caseX * this.map.getTileWidth() + this.screenX, this.caseY * this.map.getTileHeight() + this.screenY, this.map.getTileWidth(), this.map.getTileHeight());
				
		//valeurs
		g.drawString("screenX "+this.screenX, 10, 60);
		g.drawString("screenY "+this.screenY, 10, 80);
		
		//case selectionné
		g.drawString("case X "+this.caseX, 10, 100);
		g.drawString("case Y "+this.caseY, 10, 120);
		
		if(this.uniteSelection != null)
		{
			//Debug.afficheHashMap(this.casesPosibiliteDeplacement);
			g.drawString("Nom unité : "+this.uniteSelection.getName(), 10, 140);
			
			for(String s : this.casesPosibiliteDeplacement)
			{
				String str[] = s.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				g2.setColor(blanct2);
				g2.fillRect(x * this.map.getTileWidth() + this.screenX, y * this.map.getTileHeight() + this.screenY, this.map.getTileWidth(), this.map.getTileHeight());
			}
		}		
		if(this.caseSelection != null)
		{
			g.drawString("Defense de la case :"+this.caseSelection.getDefense(), 10, 160);
		}	
		drawAllUnits(); //Affichage des unitŽs	
		g.drawString("temps"+this.i, 10, 200);
	}

	
	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {
		i++;
		this.entree_clavier.check();
		HashMap<String, Integer> touches = this.entree_clavier.getTouches();	
		
		this.scroll(touches);
    	this.afficherCasePointer(touches);	
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
                    		boolean isSelect = checkUniteEtDeplacement();
                    		//Si aucune unitŽ n'est sŽlectionnŽ, on vide les possibilitŽs de dŽplacement
                    		if(isSelect == false)
                    		{
                    			this.casesPosibiliteDeplacement.clear();
                    			this.uniteSelection = null;
                    		}
                    		
                    		System.out.println("La case selectionnée ["+this.caseSelection.getX()+","+this.caseSelection.getY()+"] => DEFENSE : "+this.caseSelection.getDefense());
                    			
                    	}                   	
                	}
            	}
        	}
    	}
	}

	
	private boolean checkUniteEtDeplacement()
	{
		//Si on a dŽjˆ une unitŽ de sŽlectionnŽ, on peut la deplacer...
		
		boolean isSelect = false;
		if(this.uniteSelection != null)
		{
			boolean uniteSurLaCase = false; //on va regarder s'il n'y a pas deja une unite sur la case ou l'ont veut aller
			for(Unite unite : this.al_unites) //On boucle sur toutes les unites de notre partie
        	{
        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
        		{
        			uniteSurLaCase = true; //Il y a deja une unitŽ sur cette case
        			this.uniteSelection = unite; //On rŽcup�re donc l'unite qu'il y a sur cette case
        			int rayon = unite.getRayonDeplacement();
        			//On calcul toutes les coordonnŽes qui sont possible pour le dŽplacement de cette unite
        			this.casesPosibiliteDeplacement = Fonction.calculRayonDeplacement(this.caseX, this.caseY, this.map.getWidth(), this.map.getHeight(), rayon);
        			isSelect = true;
        		}
        	}
			if(uniteSurLaCase == false) //Si il n'y a pas d'unite sur la case, on deplace l'unite
			{
				for(String s : this.casesPosibiliteDeplacement) //on parcourt toutes les possibilites de deplacement
				{
					String str[] = s.split(":"); //on split car l'enregistrement des possibilite est comme ca : X:Y
					int x = Integer.parseInt(str[0]); //on rŽcup�re la valeur x et y
					int y = Integer.parseInt(str[1]);
					if(this.caseX == x && this.caseY == y) //Si la case sur laquelle on a cliquer correspond a l'une des valeur, on peut deplacer
					{
						this.uniteSelection.deplacement(this.caseX, this.caseY); //deplacement
						this.casesPosibiliteDeplacement.clear(); //on delete les cases de possibilite de deplacement
						isSelect = true;
						//this.uniteSelection = null;
						
						break; //on sort de la boucle
					}
				}
			}
		}
		else //S'il n'y a pas d'unitŽ de selectionnŽ, on va regarder si la case selectionne contient une unite
		{
			for(Unite unite : this.al_unites)
        	{
        		if(unite.getCaseX() == this.caseX && unite.getCaseY() == this.caseY)
        		{
        			this.uniteSelection = unite;
        			int rayon = unite.getRayonDeplacement();
        			this.casesPosibiliteDeplacement = Fonction.calculRayonDeplacement(this.caseX, this.caseY, this.map.getWidth(), this.map.getHeight(), rayon);
        			isSelect = true;
        		}
        	}
		}		
		return isSelect;	
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
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Joueur getAdversaire() {
		return adversaire;
	}

	public void setAdversaire(Joueur adversaire) {
		this.adversaire = adversaire;
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}
}
