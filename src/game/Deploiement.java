package game;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ihm.IHMBas;
import ihm.IHMDeploiementUnite;
import states.Partie;
import tools.Constantes;
import tools.Entree;
import unites.Sniper;
import unites.Tank;

public class Deploiement{

	private Equipe equipe;
	private Partie partie;
	public static int phaseDeDeploiement;
	
	private IHMDeploiementUnite ihmDeploiementUnite;
	
	private IHMBas ihmBas;
	private boolean viewIHMBas;
	
	private GameContainer container;
	
	private ArrayList<Unite> al_unitesPossibles;
	private int valeurMax;
	private int valeur;
	
	private Unite uniteSelect;
	
	private int selectionX;
	private int selectionY;
	private int caseX;
	private int caseY;



	public Deploiement(Equipe equipe, Partie partie) throws SlickException
	{
		this.partie = partie;
		this.container = partie.getContainer();
		this.valeur = 0; //valeur en cours
		this.valeurMax = 100; //valeur max d'unité pour la partie
		this.equipe = equipe;
		
		//On remplit al_unitesPossibles
		
		this.al_unitesPossibles = new ArrayList<Unite>();
		this.razUnitesPossibles();
		
		
		this.ihmDeploiementUnite = new IHMDeploiementUnite(container, this);
		
		this.selectionX = 0;
		this.selectionY = 0;
		this.caseX = -1;
		this.caseY = -1;
		
		this.viewIHMBas = true;
		this.ihmBas = new IHMBas(this.container);
	}
	
	public void razUnitesPossibles()
	{
		this.al_unitesPossibles.clear();
		
		Unite sniper = new Sniper();
		if((sniper.getValeur() + this.valeur) <= this.valeurMax)
		{
			this.al_unitesPossibles.add(sniper);
		}
		
		Unite tank = new Tank();
		if(tank.getValeur() + this.valeur <= this.valeurMax)
		{
			this.al_unitesPossibles.add(tank);
		}
	}
	
	
	public void placerUnite(HashMap<String, Integer> touches)
	{
		
		int tileWidth = this.partie.getMap().getTileWidth();
		int tileHeight = this.partie.getMap().getTileHeight();
		int screenX = this.partie.getScreenX();
		int screenY = this.partie.getScreenY();
		Entree entree_clavier = this.partie.getEntree_clavier();
		//selectionnne la case ou est le pointeur
    	for(int x=0; x<this.partie.getMap().getWidth(); x++)
    	{
    		for(int y=0; y<this.partie.getMap().getHeight(); y++)
        	{
    			
    			if(entree_clavier.moa(x * tileWidth + screenX, y *  tileHeight + screenY, tileWidth, tileHeight))
            	{
    				
    				this.selectionX = x * this.partie.getMap().getTileWidth() + this.partie.getScreenX();
    				this.selectionY = y * this.partie.getMap().getTileHeight() + this.partie.getScreenY();           	
		
            		if(touches.get("SPACE") == 1 || touches.get("MOUSE_LEFT") == 1) //appuis sur espace ou click gauche
                	{
                    	this.caseX = x;
                    	this.caseY = y;
                    	
                    	this.uniteSelect.setCaseX(x);
                    	this.uniteSelect.setCaseY(y);
                    	this.equipe.addUnite(this.uniteSelect);
                    	Deploiement.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM);
                    	                 	
                	}
            	}
        	}
    	}
	}

	//fonction d'affichage des unitŽs (en private car elle ne peut pas etre appelŽ ailleur)
	//Il faudra virer le g.fillRect et utiliser la fonction drawImage (prŽsente dans chaque unitŽ) pour afficher l'image de l'unitŽ
	public void drawAllUnits()
	{
		Graphics g2 = new Graphics();
		Color ambre = new Color(173,57,14);
		Color jaunepisse = new Color(240,195,0);
		for (Unite unite : this.equipe.getAl_unitesEquipe()) {
			int placementX = unite.getCaseX();
			int placementY = unite.getCaseY();

			if(unite.getName() == "Tank")
			{
				g2.setColor(ambre);

				g2.fillRect(placementX * this.partie.getMap().getTileWidth() + this.partie.getScreenX() + 15, placementY * this.partie.getMap().getTileHeight() + this.partie.getScreenY() +15, this.partie.getMap().getTileWidth()-30, this.partie.getMap().getTileHeight()-30);
			}
			else if(unite.getName() == "Sniper")
			{
				g2.setColor(jaunepisse);

				g2.fillRect(placementX * this.partie.getMap().getTileWidth() + this.partie.getScreenX() + 15, placementY * this.partie.getMap().getTileHeight() + this.partie.getScreenY() +15, this.partie.getMap().getTileWidth()-30, this.partie.getMap().getTileHeight()-30);
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
	}
	
	public void afficherIHMBas(Graphics g)
	{
		//drawAllUnits(); //Affichage des unitŽs	
		this.ihmBas.majIHM(g, this.viewIHMBas, this.caseX, this.caseY, null, null, null);
			
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
	
	public static void setPhaseDeDeploiement(int phase)
	{
		Deploiement.phaseDeDeploiement = phase;
	}
	
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public IHMDeploiementUnite getIhmDeploiementUnite() {
		return ihmDeploiementUnite;
	}

	public void setIhmDeploiementUnite(IHMDeploiementUnite ihmDeploiementUnite) {
		this.ihmDeploiementUnite = ihmDeploiementUnite;
	}

	public GameContainer getContainer() {
		return container;
	}

	public void setContainer(GameContainer container) {
		this.container = container;
	}

	public ArrayList<Unite> getAl_unitesPossibles() {
		return al_unitesPossibles;
	}

	public void setAl_unitesPossibles(ArrayList<Unite> al_unitesPossibles) {
		this.al_unitesPossibles = al_unitesPossibles;
	}

	public int getValeurMax() {
		return valeurMax;
	}

	public void setValeurMax(int valeurMax) {
		this.valeurMax = valeurMax;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public static int getPhaseDeDeploiement() {
		return phaseDeDeploiement;
	}
	
	public Unite getUniteSelect() {
		return uniteSelect;
	}


	public void setUniteSelect(Unite uniteSelect) {
		this.uniteSelect = uniteSelect;
	}


	

}
