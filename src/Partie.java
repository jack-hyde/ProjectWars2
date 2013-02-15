import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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
	
	private int stateID;

    
	public Partie(int id) throws SlickException
	{
		this.stateID = id;
		this.joueur = new Joueur("Jacky");
		this.adversaire = new Joueur("Tarlouze");
		this.map  = new Map("images/map/map3.tmx");
		
		this.screenX = 0;
		this.screenY = 0;
		
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
		
	    System.out.println(this.mapWidth);
	    System.out.println(this.mapHeight);
	    
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		this.map.render(this.screenX,this.screenY);
		
	}


	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {
		
		Entree entree_clavier = new Entree(container);
		boolean scrollMap = false;
		int[] touches = entree_clavier.getTouches();	
		System.out.println(touches);
		if(touches[4] == 1)//appuis sur S
    	{
			if((this.screenY - 10) < -this.mapHeight)
				this.screenY = -this.mapHeight;
			else
				this.screenY = this.screenY-10;
			
			System.out.println("tessssst");
			scrollMap = true;
    		
    	}
    	if(touches[1] == 1)//appuis sur Z
    	{
    		if((this.screenY + 10) >= 0)
				this.screenY = 0;
			else
				this.screenY = this.screenY+10;
    		
    		scrollMap = true;
    	}
    	if(touches[3] == 1)//appuis sur Q
    	{
    		scrollMap = true;
    	}
    	if(touches[5] == 1)//appuis sur D
    	{
    		scrollMap = true;
    	}
		
    	if(scrollMap)
    		this.map.render(this.screenX,this.screenY);
	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}
}
