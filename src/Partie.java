import java.util.ArrayList;
import java.util.HashMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tests.xml.Entity;


public class Partie extends BasicGameState {

	private Joueur joueur;
	private Joueur adversaire;
	private Map map;
	private ArrayList <Entity>entities;
	private GameContainer container;
	private int mapWidth;
	private int mapHeight;
	private int screenX;
	private int screenY;
	private int selectionX;
	private int selectionY;
	
	private int stateID;

	private Entree entree_clavier;
    
	public Partie(int id) throws SlickException
	{
		this.stateID = id;
		this.joueur = new Joueur("Jacky");
		this.adversaire = new Joueur("Tarlouze");
		this.map  = new Map("images/map/map3.tmx");
		this.screenX = 0;
		this.screenY = 0;
		this.selectionX = 0;
		this.selectionY = 0;		
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
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.container = container;
		
		
		this.mapWidth = map.getWidth() * map.getTileWidth();
	    this.mapHeight = map.getHeight() * map.getTileHeight();

	    entree_clavier =  new Entree(container);
	    //szDebug.afficheHashMap(entree_clavier.getTouches());
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		this.map.render(this.screenX,this.screenY); //render(int x, int y, int sx, int sy, int width, int height)  a faire pour eviter de charger toute la carte meme les parties que l'on ne voit pas
		
		//affiche le carré de selection
		g.drawRect(this.selectionX, this.selectionY,  this.map.getTileWidth(), this.map.getTileHeight()); 
		g.drawString("screenX"+this.screenX, 10, 60);
		g.drawString("screenY"+this.screenY, 10, 80);
	}


	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {
		entree_clavier.check();
		HashMap<String, Integer> touches = entree_clavier.getTouches();	
		
		this.scroll(touches);
    	this.afficherCasePointer(touches);
    	
	}
	
	public void afficherCasePointer(HashMap<String, Integer> touches)
	{
		//selectionnne la case ou est le pointeur	
    	for(int i=0; i<this.map.getWidth(); i++)
    	{
    		for(int u=0; u<this.map.getHeight(); u++)
        	{
    			if(entree_clavier.moa(i * this.map.getTileWidth() + (-this.screenX % this.map.getTileWidth()), u * this.map.getTileHeight() + (-this.screenY % this.map.getTileHeight()), this.map.getTileWidth(), this.map.getTileHeight()))
            	{
    				this.selectionX = i * this.map.getTileWidth();// + (-this.screenX % this.map.getTileWidth());
                	this.selectionY = u * this.map.getTileHeight();// + (-this.screenY % this.map.getTileHeight());
		
            		if(touches.get("SPACE") == 1 || touches.get("MOUSE_LEFT") == 1) //appuis sur espace ou click gauche
                	{
            			
                	}
            	}
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
    	
    	
	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}
}
