package states;


import java.util.ArrayList;
import java.util.HashMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import game.Case;
import game.Joueur;
import game.Map;
import game.Unite;
import tools.Constantes;
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
	private boolean afficherMenu;
	private Case caseSelection;
	private ArrayList<String> casesPosibiliteDeplacement = new ArrayList<String>();
	private ArrayList<String> casesChemin = new ArrayList<String>();
	
	private Entree entree_clavier;
	
	private ArrayList<Unite> al_unites;
	private Unite uniteSelection;
	private Unite uniteAdversaireSelection;
    
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
		this.joueur = new Joueur("Jacky");
		this.adversaire = new Joueur("Tarlouze");
		this.map  = new Map("images/map/map3.tmx");

		this.mapWidth = map.getWidth() * map.getTileWidth();
	    this.mapHeight = map.getHeight() * map.getTileHeight();
	    
	    this.entree_clavier =  new Entree(container);
	    
	    //Initialisation des unités du joueur
	    Unite tank1 = new Tank(15, 10);
	    Unite tank2 = new Tank(1, 2);
	    Unite sniper1 = new Sniper(10,3);
	    Unite sniper2 = new Sniper(10, 4);
	    //Insertion des unités du joueur dans son tableau d'unite
	    this.joueur.addUnite(tank1);
	    this.joueur.addUnite(tank2);
	    this.joueur.addUnite(sniper1);
	    this.joueur.addUnite(sniper2);
	    
	    //Initialisation des unités de l'adversaire
	    Unite tankAdversaire = new Tank(15, 3);
	    Unite sniperAdversaire = new Sniper(15, 4);
	    
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
		
		Graphics g2 = new Graphics();
		Graphics g3 = new Graphics();
		Color blanct = new Color(255,255,255,100);
		Color blanct2 = new Color(84,152,235,150);
		Color orange = new Color(255,128,64,255);
		Color red = new Color(255,0,0,255);
		g2.setColor(blanct);
		g3.setColor(red);
		
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
				g2.setColor(blanct2);
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
		
		if(afficherMenu) //un appuis sur E affiche le menu
	    {
	    	g2.setColor(orange);
	    	g2.fillRect(0, container.getHeight() - 100 , 1300, 100);
	    	g.drawString("case X "+this.caseX, 10, 710);
			g.drawString("case Y "+this.caseY, 10, 725);
			if(this.caseSelection != null)
			{
				g.drawString("Type de la case :"+this.caseSelection.getTypeCase(), 10, 740);
				g.drawString("Defense de la case :"+this.caseSelection.getDefense(), 10, 755);
				g.drawString("Attaque de la case :"+this.caseSelection.getAttaque(), 10, 770);
				
			}
			if(this.uniteSelection != null)
			{
				g.drawString("MON UNITE : "+this.uniteSelection.getName()+" [AT:"+this.uniteSelection.getAttaque()+"|DEF:"+this.uniteSelection.getDefense()+"]", 300, 710);
				g.drawString(""+uniteSelection.getCaseX(),100,100);
			}
			if(this.uniteAdversaireSelection != null)
			{
				g.drawString("UNITE ADVERSAIRE : "+this.uniteAdversaireSelection.getName()+" [AT:"+this.uniteAdversaireSelection.getAttaque()+"|DEF:"+this.uniteAdversaireSelection.getDefense()+"]", 300, 710);
			}
			
	    }
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {
		this.entree_clavier.check();
		HashMap<String, Integer> touches = this.entree_clavier.getTouches();	
		
		this.scroll(touches);
    	this.afficherCasePointer(touches);
    	/*if(this.uniteSelection != null)
		{
    		afficherChemin();
		}
    	else
    	{
    		casesChemin.clear();
    	}*/
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
			for(Unite unite : this.joueur.getAl_unitesDuJoueur())
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
				for(Unite unite : this.adversaire.getAl_unitesDuJoueur())
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
			//Première boucle pour voir si il y a une unité appartenant au joueur sur cette case
			boolean uniteAuJoueur = false;
			for(Unite unite : this.joueur.getAl_unitesDuJoueur())
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
				for(Unite unite : this.adversaire.getAl_unitesDuJoueur())
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
		int xOld = this.uniteSelection.getCaseX();
		int yOld = this.uniteSelection.getCaseY();

		for(String s : this.casesPosibiliteDeplacement)
		{
			String str[] = s.split(":");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);

			if(entree_clavier.moa(x * this.map.getTileWidth() + this.screenX, y * this.map.getTileHeight() + this.screenY, this.map.getTileWidth(), this.map.getTileHeight()))
			{	
				if(rayon == this.uniteSelection.getRayonDeplacement())
				{
					if(Math.abs(this.uniteSelection.getCaseX()) - Math.abs(x) == 0 || Math.abs(this.uniteSelection.getCaseY()) - Math.abs(y) == 0)
					{
						casesChemin.add(x+":"+y);
						xOld = this.uniteSelection.getCaseX();
						yOld = this.uniteSelection.getCaseY();
					}	
					rayon--;
				}
				else
				{
					if(rayon > 0)
					{
						if(Math.abs(xOld) - Math.abs(x) == 0 || Math.abs(yOld) - Math.abs(y) == 0)
						{
							casesChemin.add(x+":"+y);
							xOld = x;
							yOld = y;
						}	
					}
					rayon--;
				}		
			}
			if(entree_clavier.moa(this.uniteSelection.getCaseX() * this.map.getTileWidth() + this.screenX, this.uniteSelection.getCaseY() * this.map.getTileHeight() + this.screenY, this.map.getTileWidth(), this.map.getTileHeight()))
			{
				casesChemin.clear();
			}
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
			if(afficherMenu)
			{
				afficherMenu = false;
			}
			else
			{
				afficherMenu = true;
			}
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
