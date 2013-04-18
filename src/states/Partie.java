package states;

import java.util.HashMap;

import game.Equipe;
import game.Joueur;

import model.BatailleIAModel;
import model.BatailleModel;
import model.DeploiementIAModel;
import model.DeploiementModel;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import game.Map;
import tools.Constantes;
import tools.Entree;
import view.BatailleIAView;
import view.BatailleView;
import view.DeploiementIAView;
import view.DeploiementView;



public class Partie extends BasicGameState {

	private int stateID;

	private Equipe equipeA;
	private Equipe equipeB;
	private Joueur joueurA1;
	private Joueur joueurA2;
	private Joueur joueurB1;
	private Joueur joueurB2;
	private Map map;
	private GameContainer container;
	private int mapWidth;
	private int mapHeight;
	private int screenX;
	private int screenY;

	private Entree entree_clavier;
	
	private static int phaseDeJeu;
	private Joueur joueurEnCours;
	
	private DeploiementModel modelDeploiementJ1;
	private DeploiementIAModel modelDeploiementIA;
	private BatailleModel modelBatailleJ1;
	private BatailleIAModel modelBatailleIA;
	
	private BatailleView viewBatailleJ1;
	private BatailleIAView viewBatailleIA;
	private DeploiementView viewDeploiementJ1;
	private DeploiementIAView viewDeploiementIA;
	
	public Partie(int id) throws SlickException
	{
		this.stateID = id;
		
		this.screenX = 0;
		this.screenY = 0;
		
		
	}

	//Fonction qui permet d'initialiser la carte au moment où on entre dans ce gamestate
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	{
		this.joueurA1 = new Joueur(false, "hyde", "capacite", "capacite", "capacite");
		this.joueurB1 = new Joueur(false, "paycheur", "capacite", "capacite", "capacite");
		
		this.equipeA = new Equipe(joueurA1);
		this.equipeB = new Equipe(joueurB1);
		
		this.joueurA1.setEquipe(equipeA);
		this.joueurB1.setEquipe(equipeB);
		
//		this.equipeA.getJoueur1() = new Equipe("Jacky", false);
//  this.ia = new Equipe("la tarlouze", true);
		this.map  = new Map("images/map/map3.tmx");

		this.mapWidth = map.getWidth() * map.getTileWidth();
	    this.mapHeight = map.getHeight() * map.getTileHeight();
	    
	    this.entree_clavier =  new Entree(container);
	    
	    
	    
	    this.modelDeploiementJ1 = new DeploiementModel(this);
	    this.modelDeploiementIA = new DeploiementIAModel(this);
	    this.modelBatailleIA = new BatailleIAModel(this);
	    this.modelBatailleJ1 = new BatailleModel(this);
	    
	    this.viewDeploiementJ1 = new DeploiementView(this.modelDeploiementJ1, container.getGraphics());
	    this.viewDeploiementIA = new DeploiementIAView(this.modelDeploiementIA, container.getGraphics());
	    this.viewBatailleIA = new BatailleIAView(this.modelBatailleIA, container.getGraphics());
	    this.viewBatailleJ1 = new BatailleView(this.modelBatailleJ1, container.getGraphics());
	   

	    this.joueurEnCours = this.equipeA.getJoueur1(); //le J1 commence, peut etre que cette variable sera inutile pour la suite
	    
	    Partie.phaseDeJeu = Constantes.PHASE_DEPLOIEMENT;
	    DeploiementModel.phaseDeDeploiement = Constantes.PHASE_DEPLOIEMENT_AFFICHER_CARTE;
	    
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
		
		
		
		if(this.joueurEnCours.isIa()) //on gère le cas pour voir si c'est au tour de l'ordi ou pas car il n'y aura pas les meme fonctions appelés
		{
			
			
	    	
	    	switch(Partie.phaseDeJeu)
	    	{
	    		case Constantes.PHASE_DEPLOIEMENT :
	    			
	    			switch(DeploiementModel.phaseDeDeploiement)
	    			{
	    				case Constantes.PHASE_DEPLOIEMENT_IA_EN_COURS: 
	    					//fonctions possibles si on veut afficher quelque chose pendant la phase de deploiement de l'ia...
	    					//Je pense que ca ne servira pas. A voir par la suite
	    					break;
	    					
	    				default : break;
	    			}
	    		
	    		case Constantes.PHASE_BATAILLE : 
	    			
	    			break;
	    		default : break;
	    	}
		}
		else
		{
			
			
	    	
	    	switch(Partie.phaseDeJeu)
	    	{
	    		case Constantes.PHASE_DEPLOIEMENT :
	    			
	    			
	    			switch(DeploiementModel.phaseDeDeploiement)
	    			{
	    				case Constantes.PHASE_DEPLOIEMENT_AFFICHER_CARTE :
	    					this.viewDeploiementJ1.interactionsCarte();
	    					this.viewDeploiementJ1.drawAllUnits();
	    					break;
	    					
	    				case Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM : 
	    					this.viewDeploiementJ1.drawAllUnits();
	    					this.viewDeploiementJ1.afficherIHMDeploiementUnite(); 
	    					
	    					break;
	    					
	    				case Constantes.PHASE_DEPLOIEMENT_PLACEMENT_UNITE : 
	    					this.viewDeploiementJ1.interactionsCarte();
	    					this.viewDeploiementJ1.drawAllUnits();
	    					break;
	    					
	    				default : break;
	    			}
	    			
	    			this.viewDeploiementJ1.afficherIHMBas();
	    			
	    		case Constantes.PHASE_BATAILLE :
	    				switch(BatailleModel.phaseDeBataille)
	    				{
	    					case Constantes.PHASE_BATAILLE_SELECTION_UNITE : 
	    						this.viewBatailleJ1.render();
	    						break;
	    					case Constantes.PHASE_BATAILLE_DEPLACEMENT : 
	    						this.viewBatailleJ1.render();
	    						break;
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
		boolean clique = false; //variable pour détecter un clique (avec la fonction selectionCase)
		//this.scroll(touches);
		//this.afficherCasePointer(touches);
    	
		if(this.joueurEnCours.isIa())
		{
			
			
	    	switch(Partie.phaseDeJeu)
	    	{
	    		case Constantes.PHASE_DEPLOIEMENT :
	    			
	    			switch(DeploiementModel.phaseDeDeploiement)
	    			{
	    				case Constantes.PHASE_DEPLOIEMENT_IA_EN_COURS: 
	    					this.modelDeploiementIA.deploiement();				
	    				
	    					break;
	    				
	    				
	    				case Constantes.PHASE_BATAILLE : 
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
	    			
	    			this.modelDeploiementJ1.checkTouches(touches);
	    			switch(DeploiementModel.phaseDeDeploiement)
	    			{
	    				case Constantes.PHASE_DEPLOIEMENT_AFFICHER_CARTE :
	    					scroll(touches);
	    					clique = this.modelDeploiementJ1.selectionCase(touches);
	    					if(clique)
	    					{
	    						this.modelDeploiementJ1.deplacementUnite();
	    					}
	    					break;
	    					
	    				case Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM : 
	    					scroll(touches);
	    					break;
	    					
	    				case Constantes.PHASE_DEPLOIEMENT_PLACEMENT_UNITE :
	    					scroll(touches);
	    					clique = this.modelDeploiementJ1.selectionCase(touches);
	    					if(clique)
	    					{
	    						this.modelDeploiementJ1.placerUnite();
	    					}
	    					 
	    					
	    					break;
	    				
	    				case Constantes.PHASE_DEPLOIEMENT_SUPPRIMER_UNITE :
	    					this.modelDeploiementJ1.deleteUnite();
	    					DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_AFFICHER_CARTE);
	    					break;
	    					
	    				case Constantes.PHASE_DEPLOIEMENT_TERMINER : //la phase de déploiement pour le joueur est terminé, on va maintenant lancé le deploiement de l'IA automatiquement
	    					
	    					boolean dernierJoueur = this.changementJoueur();
	    					if(dernierJoueur == true)
	    					{
	    						Partie.setPhaseDeJeu(Constantes.PHASE_BATAILLE);
		    					BatailleModel.setPhaseDeBataille(Constantes.PHASE_BATAILLE_ADD_UNITES);
	    					}
	    					else if(this.joueurEnCours.isIa())
	    						DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_IA_EN_COURS);
	    					else
	    					{
	    						this.modelDeploiementJ1.raz();
	    						DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_AFFICHER_CARTE);
	    					}
	    						
	    					break;
	    					
	    				default : break;
	    			}
	    			
	    		case Constantes.PHASE_BATAILLE : 
	    			
	    			
	    			switch(BatailleModel.phaseDeBataille)
	    			{
	    				case Constantes.PHASE_BATAILLE_ADD_UNITES :
	    					
	    					this.modelBatailleIA.addAllUnites(); 
	    					this.modelBatailleJ1.addAllUnites();
	    					BatailleModel.setPhaseDeBataille(Constantes.PHASE_BATAILLE_SELECTION_UNITE);
	    					break;
	    				case Constantes.PHASE_BATAILLE_SELECTION_UNITE : 

	    					scroll(touches);
	    					clique = this.modelBatailleJ1.selectionCase(touches);
	    					if(clique)
	    					{
	    						this.modelBatailleJ1.selectionUnite();
	    					}
	    					this.modelBatailleJ1.checkTouchesEtTemps(touches, delta);
	    					break;
	    					
	    				case Constantes.PHASE_BATAILLE_DEPLACEMENT : 
	    					scroll(touches);
	    					this.modelBatailleJ1.afficherChemin();
	    					clique = this.modelBatailleJ1.selectionCase(touches);
	    					if(clique)
	    					{
	    						this.modelBatailleJ1.deplacementEnCours();
	    					}
	    					this.modelBatailleJ1.checkTouchesEtTemps(touches, delta);
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

	public boolean changementJoueur() {
		
		boolean dernierJoueur = false;
		
		Equipe equipeEnCours = this.joueurEnCours.getEquipe();
		
		int nbJoueurEquipeEnCours = equipeEnCours.getNbJoueurEquipe();
		
		if(equipeEnCours == this.equipeA)
		{
			if(nbJoueurEquipeEnCours == 1)
			{
				this.joueurEnCours = this.joueurB1;
			}
			else
			{
				if(this.joueurEnCours == equipeEnCours.getJoueur1())
				{
					this.joueurEnCours = this.joueurA2;
				}
				else
				{
					this.joueurEnCours = this.joueurB1;
				}
			}
		}
		else
		{
			if(nbJoueurEquipeEnCours == 1)
			{
				this.joueurEnCours = this.joueurA1;
				dernierJoueur = true;
			}
			else
			{
				if(this.joueurEnCours == equipeEnCours.getJoueur1())
				{
					this.joueurEnCours = this.joueurB2;
				}
				else
				{
					this.joueurEnCours = this.joueurA1;
					dernierJoueur = true;
				}
			}
		}
		
		return dernierJoueur;
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

	public Joueur getJoueurEnCours() {
		return joueurEnCours;
	}

	public void setJoueurEnCours(Joueur joueurEnCours) {
		this.joueurEnCours = joueurEnCours;
	}

	public static int getPhaseDeJeu() {
		return phaseDeJeu;
	}
	
	public Equipe getEquipeA() {
		return equipeA;
	}

	public void setEquipeA(Equipe equipeA) {
		this.equipeA = equipeA;
	}

	public Equipe getEquipeB() {
		return equipeB;
	}

	public void setEquipeB(Equipe equipeB) {
		this.equipeB = equipeB;
	}

	public Joueur getJoueurA1() {
		return joueurA1;
	}

	public void setJoueurA1(Joueur joueurA1) {
		this.joueurA1 = joueurA1;
	}

	public Joueur getJoueurB1() {
		return joueurB1;
	}

	public void setJoueurB1(Joueur joueurB1) {
		this.joueurB1 = joueurB1;
	}
}
