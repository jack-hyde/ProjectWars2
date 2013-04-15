package states;

import tools.Constantes;
import tools.Entree;
import unites.Sniper;
import unites.Tank;

import game.Joueur;
import game.Map;
import game.Unite;
import menu.ConfigPartie;
import game.Equipe;

import phases.Bataille;
import phases.Deploiment;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Partie extends BasicGameState //avant de rentrer dans gameplay state on doit envoyer le nombre de joueurs equipe A et B, les differents joueurs, leurs capacité et leurs unités
{
	public int id;

	private GameContainer container;
	private Map map;
	private Entree entree;
	private Deploiment deploiment;
	private Bataille bataille;
	private int screenX;
	private int screenY;
	private Equipe equipeA;
	private Equipe equipeB;
	private int nbjoueurEquipeA;
	private int nbjoueurEquipeB;
	private String phase;

	public Partie(int id) 
	{
		this.id = id;	
	}

	public void enter(GameContainer container, StateBasedGame game) throws SlickException //c'est ici que l'on traite tout ce que l'on recois de configPartie
	{	
		this.container = container;
		this.map = new Map("images/map/map3.tmx");
		this.entree =  new Entree(container);
		this.deploiment = new Deploiment();//humm a enlever peut etre
		this.screenX = 0;
		this.screenY = 0;
		this.phase = "deploiment";
		
		/*a rajouter des que configPartie est fini
		this.map  = ConfigPartie.getMap;
		
		//suivant le nombre de joueur dans chaque equipe on crée diferentes equipes
		this.nbjoueurEquipeA = ConfigPartie.getNbjoueurEquipeA();
		this.nbjoueurEquipeB = ConfigPartie.getNbjoueurEquipeB();
	
		if(nbjoueurEquipeA == 2)
		{
			this.equipeA = new Equipe(ConfigPartie.getJoueurA1(), ConfigPartie.getJoueurA2());
		}
		else
		{
			this.equipeA = new Equipe(ConfigPartie.getJoueurA1());
		}
		
		if(nbjoueurEquipeB == 2)
		{
			this.equipeB = new Equipe(ConfigPartie.getJoueurB1(), ConfigPartie.getJoueurB2());
		}
		else
		{
			this.equipeB = new Equipe(ConfigPartie.getJoueurB1());
		}
		*/
		
	}

	public void init(GameContainer container, StateBasedGame game)throws SlickException 
	{
		//pour tester
		//a enlever des que config partie est fini
		
		ArrayList<Unite> liste_unites1 = new ArrayList<Unite>();
		ArrayList<Unite> liste_unites2 = new ArrayList<Unite>();
		
		//suivants les capacités et si les unites sont des veterans ou pas les unites sont crée avec diferentes methodes
		//aucune capacité pas de veterans
		Unite sniper1 = new Sniper();
		Unite tank1 = new Tank();
		Unite sniper2 = new Sniper();
		Unite tank2 = new Tank();
		
		liste_unites1.add(sniper1);
		liste_unites1.add(tank1);
		liste_unites2.add(sniper2);
		liste_unites2.add(tank2);
		
		Joueur joueur1 = new Joueur(false, "hyde", liste_unites1, "capacite", "capacite", "capacite");
		Joueur joueur2 = new Joueur(false, "paycheur", liste_unites2, "capacite", "capacite", "capacite");
		
		this.equipeA = new Equipe(joueur1);
		this.equipeB = new Equipe(joueur2);
		
		this.deploiment.init(this.map, this.equipeA, this.equipeB);//on envoie l'equipe au deploiment si faudra la recuperer pour bataille
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)throws SlickException 
	{
		//map
		this.map.render(this.screenX,this.screenY); //render(int x, int y, int sx, int sy, int width, int height)  a faire pour eviter d'afficher toute la carte meme les parties que l'on ne voit pas a faire aussi pour tout ce qu'il y a dans render
		
		//phases
		if(this.phase == "deploiment")
		{
			this.deploiment.render(g, this.screenX, this.screenY);
		}
		else if(this.phase == "bataille")
		{
			this.bataille.render(g, this.screenX, this.screenY, this.equipeA, this.equipeB);
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException 
	{
		//touches
		HashMap<String, Integer> touches = this.entree.getTouches();
		
		//map
		scroll(touches);
		
		//phases
		if(this.phase == "deploiment")
		{
			this.deploiment.update(this.container, this.map, this.screenX, this.screenY, this.entree, delta);
			this.phase = this.deploiment.getPhase();
		}
		else if(this.phase == "bataille")
		{
			this.equipeA = this.deploiment.getEquipeA();//on recupere les equipes pour la bataille
			this.equipeB = this.deploiment.getEquipeB();
			this.bataille.update(this.container, this.map, this.screenX, this.screenY, this.equipeA, this.equipeB, this.entree, delta);
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
			if((-this.screenY + Constantes.SCROLL_SPEED + container.getHeight()) > this.map.getHeight())
				this.screenY = -this.map.getHeight() + container.getHeight();
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
			if((-this.screenX + Constantes.SCROLL_SPEED + container.getWidth()) > this.map.getWidth())
				this.screenX = -this.map.getWidth() + container.getWidth();
			else
				this.screenX = this.screenX-Constantes.SCROLL_SPEED;
		}
		
	}

	public int getID() {
		return id;
	}
}
