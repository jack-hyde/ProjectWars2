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

import game.Bataille;
import game.BatailleIA;
import game.Case;
import game.Deploiement;
import game.DeploiementIA;
import game.Map;
import game.Unite;
import tools.Constantes;
import tools.Entree;
import tools.Fonction;
import unites.*;



public class Partie extends BasicGameState {

	private int stateID;

	private Equipe j1;
	private Equipe ia;
	private Map map;
	private GameContainer container;
	private int mapWidth;
	private int mapHeight;
	private int screenX;
	private int screenY;

	
	private Entree entree_clavier;

	
	
	private static int phaseDeJeu;
	private Equipe equipeEnCours;
	
	private Deploiement deploiementJ1;
	private DeploiementIA deploiementIA;
	
	private Bataille batailleJ1;
	private BatailleIA batailleIA;
	
	public Partie(int id) throws SlickException
	{
		this.stateID = id;
		
		this.screenX = 0;
		this.screenY = 0;
		
		
	}

	//Fonction qui permet d'initialiser la carte au moment où on entre dans ce gamestate
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	{
		this.j1 = new Equipe("Jacky", false);
		this.ia = new Equipe("la tarlouze", true);
		this.map  = new Map("images/map/map3.tmx");

		this.mapWidth = map.getWidth() * map.getTileWidth();
	    this.mapHeight = map.getHeight() * map.getTileHeight();
	    
	    this.entree_clavier =  new Entree(container);
	    
	    
	    
	    this.deploiementJ1 = new Deploiement(this.j1, this);
	    //Créer un déploiement IA. Si on veut jouer à 2 joueurs on créera un autre fichier "Partie". 
	    //On va ensuite mettre un if dans render() et update() en fonction de si c'est au tour de l'ia ou pas. Peut etre ajouter le "equipe en cours" en variable de classe
	    this.deploiementIA = new DeploiementIA(this.ia, this);

	    this.equipeEnCours = this.j1; //le J1 commence
	    
	    Partie.phaseDeJeu = Constantes.PHASE_DEPLOIEMENT;
	    Deploiement.phaseDeDeploiement = Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM;
	    
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
		
		
		
		if(this.equipeEnCours.isIa()) //on gère le cas pour voir si c'est au tour de l'ordi ou pas car il n'y aura pas les meme fonctions appelés
		{
			
			
	    	
	    	switch(Partie.phaseDeJeu)
	    	{
	    		case Constantes.PHASE_DEPLOIEMENT :
	    			
	    			DeploiementIA deploiement = this.deploiementIA;
	    			
	    			switch(Deploiement.phaseDeDeploiement)
	    			{
	    				case Constantes.PHASE_DEPLOIEMENT_IA_EN_COURS: 
	    					//fonctions possibles si on veut afficher quelque chose pendant la phase de deploiement de l'ia...
	    					//Je pense que ca ne servira pas. A voir par la suite
	    					break;
	    					
	    				default : break;
	    			}
	    		
	    		case Constantes.PHASE_BATAILLE : 
	    			BatailleIA bataille = this.batailleIA;
	    			
	    			break;
	    		default : break;
	    	}
		}
		else
		{
			
			
	    	
	    	switch(Partie.phaseDeJeu)
	    	{
	    		case Constantes.PHASE_DEPLOIEMENT :
	    			Deploiement deploiement = this.deploiementJ1;
	    			deploiement.afficherIHMBas(g);
	    			
	    			switch(Deploiement.phaseDeDeploiement)
	    			{
	    				case Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM : 
	    					deploiement.drawAllUnits();
	    					deploiement.getIhmDeploiementUnite().render(); 
	    					
	    					break;
	    					
	    				case Constantes.PHASE_DEPLOIEMENT_PLACEMENT_UNITE : 
	    					deploiement.render(g);
	    					deploiement.drawAllUnits();
	    					break;
	    					
	    				default : break;
	    			}
	    		
	    		case Constantes.PHASE_BATAILLE :
	    			Bataille bataille = this.batailleJ1;
	    				switch(Bataille.phaseDeBataille)
	    				{
	    					case Constantes.PHASE_BATAILLE_DEPLACEMENT : 
	    						bataille.render(g);
	    				}
	    			break;
	    		default : break;
	    	}
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		this.entree_clavier.check();
		HashMap<String, Integer> touches = this.entree_clavier.getTouches();	
		
		//this.scroll(touches);
		//this.afficherCasePointer(touches);
    	
		if(this.equipeEnCours.isIa())
		{
			
			
			
	    	switch(Partie.phaseDeJeu)
	    	{
	    		case Constantes.PHASE_DEPLOIEMENT :
	    			DeploiementIA deploiement = this.deploiementIA;
	    			
	    			switch(Deploiement.phaseDeDeploiement)
	    			{
	    				case Constantes.PHASE_DEPLOIEMENT_IA_EN_COURS: 
	    					//ici les fonctions qui serviront à faire le deploiement de l'ia
	    					
	    					//On ajoute les unités du joueur et de l'ia dans notre tableau qui regroupe toutes les unités
	    					
	    					//Création des objets pour lancer la bataille
	    					this.batailleIA = new BatailleIA(this.j1, this.ia, this);
	    				    this.batailleJ1 = new Bataille(this.j1, this.ia, this);
	    				    
	    					Partie.setPhaseDeJeu(Constantes.PHASE_BATAILLE);
	    					Bataille.setPhaseDeBataille(Constantes.PHASE_BATAILLE_DEPLACEMENT);
	    					this.setEquipeEnCours(this.j1);
	    					break;
	    				
	    				
	    				case Constantes.PHASE_BATAILLE : 
	    					BatailleIA bataille = this.batailleIA;
	    					break;
	    				default : break;
	    			}
	    		default : break;
	    	}
		}
		else
		{
			
			
			
			switch(Partie.phaseDeJeu)
			{
				case Constantes.PHASE_DEPLOIEMENT :
	
					Deploiement deploiement = this.deploiementJ1;
					deploiement.checkTouches(touches);
					switch(Deploiement.phaseDeDeploiement)
					{
					case Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM : 
						scroll(touches);
						break;
	
					case Constantes.PHASE_DEPLOIEMENT_PLACEMENT_UNITE :
						deploiement.placerUnite(touches); 
	
						scroll(touches);
						break;
	
					case Constantes.PHASE_DEPLOIEMENT_TERMINER : //la phase de déploiement pour le joueur est terminé, on va maintenant lancé le deploiement de l'IA automatiquement
						this.equipeEnCours = this.ia;
						Deploiement.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_IA_EN_COURS);
						break;
	
					default : break;
					}
	
				case Constantes.PHASE_BATAILLE : 
	
					Bataille bataille = this.batailleJ1;
	
					switch(Bataille.phaseDeBataille)
					{
					case Constantes.PHASE_BATAILLE_DEPLACEMENT : 
						scroll(touches);
						bataille.checkTouchesEtTemps(touches, delta);
						bataille.afficherCasePointer(touches);
						bataille.afficherChemin();
						break;
	
					default : break;
					}
				default : break;
			}
		}
	}	
	
	//fonction d'affichage des unitŽs (en private car elle ne peut pas etre appelŽ ailleur)
	//Il faudra virer le g.fillRect et utiliser la fonction drawImage (prŽsente dans chaque unitŽ) pour afficher l'image de l'unitŽ
	
		
	
	
	
	
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

	
	
	public static void setPhaseDeJeu(int phase)
	{
		Partie.phaseDeJeu = phase;
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}

	public int getStateID() {
		return stateID;
	}

	public void setStateID(int stateID) {
		this.stateID = stateID;
	}

	public Equipe getJ1() {
		return j1;
	}

	public void setJ1(Equipe j1) {
		this.j1 = j1;
	}

	public Equipe getIa() {
		return ia;
	}

	public void setIa(Equipe j2) {
		this.ia = j2;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public GameContainer getContainer() {
		return container;
	}

	public void setContainer(GameContainer container) {
		this.container = container;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public int getScreenX() {
		return screenX;
	}

	public void setScreenX(int screenX) {
		this.screenX = screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	public void setScreenY(int screenY) {
		this.screenY = screenY;
	}

	
	public Entree getEntree_clavier() {
		return entree_clavier;
	}

	public void setEntree_clavier(Entree entree_clavier) {
		this.entree_clavier = entree_clavier;
	}

	

	public Deploiement getDeploiementJ1() {
		return deploiementJ1;
	}

	public void setDeploiementJ1(Deploiement deploiementJ1) {
		this.deploiementJ1 = deploiementJ1;
	}


	public Equipe getEquipeEnCours() {
		return equipeEnCours;
	}

	public void setEquipeEnCours(Equipe equipeEnCours) {
		this.equipeEnCours = equipeEnCours;
	}

	public DeploiementIA getDeploiementIA() {
		return deploiementIA;
	}

	public void setDeploiementIA(DeploiementIA deploiementJ2) {
		this.deploiementIA = deploiementJ2;
	}

	public static int getPhaseDeJeu() {
		return phaseDeJeu;
	}
}
