package ihm;

import game.Case;
import game.Deploiement;
import game.Unite;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import states.Partie;
import tools.Constantes;


public class IHMBas implements ComponentListener {  
	  
	private int caseX;
	private int caseY;
	private boolean viewIHM;
	
	private Case caseSelection;
	private Unite uniteSelection;
	private Unite uniteAdversaireSelection;
	
	private Graphics g;
	private Graphics g2;
	private GameContainer container;
	
	private MouseOverArea deploiement; 
	
	public IHMBas(GameContainer c) throws SlickException {
		
		this.viewIHM =false;
		this.caseX=0;
		this.caseY=0;
		this.caseSelection = null;
		this.uniteSelection = null;
		this.uniteAdversaireSelection = null;
		
		
		this.container = c;
		
		//Création d'objet graphique dans le constructeur (pour éviter de le recreer à chaque fois)
		this.g = new Graphics();
		this.g2 = new Graphics();
		
		this.deploiement = new MouseOverArea(c, new Image("images/deploiement.png"), 1090, 700, this);   
		this.deploiement.setNormalColor(new Color(1,1,1,0.8f)); 
		this.deploiement.setMouseOverColor(new Color(1,1,1,0.9f));
		
		
		// TODO Auto-generated constructor stub
	}
  
	public void drawIHM() {
		
		if(this.viewIHM)
		{
			
			this.g2.setColor(Constantes.COLOR_BLANC_TRANSPARENT_2);
			
			this.g2.setColor(Constantes.COLOR_ORANGE);
	    	this.g2.fillRect(0, this.container.getHeight() - 100 , 1300, 100);
	    	
	    	this.g.drawString("case X "+this.caseX, 10, 710);
			this.g.drawString("case Y "+this.caseY, 10, 725);
			if(this.caseSelection != null)
			{
				this.g.drawString("Type de la case :"+this.caseSelection.getTypeCase(), 10, 740);
				this.g.drawString("Defense de la case :"+this.caseSelection.getDefense(), 10, 755);
				this.g.drawString("Attaque de la case :"+this.caseSelection.getAttaque(), 10, 770);
				
			}
			if(this.uniteSelection != null)
			{
				this.g.drawString("MON UNITE : "+this.uniteSelection.getName()+" [AT:"+this.uniteSelection.getAttaque()+"|DEF:"+this.uniteSelection.getDefense()+"]", 300, 710);
				
			}
			if(this.uniteAdversaireSelection != null)
			{
				this.g.drawString("UNITE ADVERSAIRE : "+this.uniteAdversaireSelection.getName()+" [AT:"+this.uniteAdversaireSelection.getAttaque()+"|DEF:"+this.uniteAdversaireSelection.getDefense()+"]", 300, 710);
			}
			
			this.deploiement.render(this.container, this.g);	
		}
	}

	public void majIHM(Graphics g, boolean viewIHM, int caseX, int caseY, Case caseSelection, Unite uniteSelection, Unite uniteAdversaireSelection)
	{
		this.viewIHM = viewIHM;
		this.caseX= caseX;
		this.caseY= caseY;
		this.caseSelection = caseSelection;
		this.uniteSelection = uniteSelection;
		this.uniteAdversaireSelection = uniteAdversaireSelection;
		this.g = g;
		
		this.drawIHM();
	}
	
	@Override
	public void componentActivated(AbstractComponent source) {
		// TODO Auto-generated method stub
		if(source.equals(this.deploiement))  
		{
			
			Partie.setPhaseDeJeu(Constantes.PHASE_DEPLOIEMENT); 
			Deploiement.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM);
		}
	}
	
	public int getCaseX() {
		return caseX;
	}

	public void setCaseX(int caseX) {
		this.caseX = caseX;
	}

	public int getCaseY() {
		return caseY;
	}

	public void setCaseY(int caseY) {
		this.caseY = caseY;
	}

	public boolean isViewIHM() {
		return viewIHM;
	}

	public void setViewIHM(boolean viewIHM) {
		this.viewIHM = viewIHM;
	}

	public Case getCaseSelection() {
		return caseSelection;
	}

	public void setCaseSelection(Case caseSelection) {
		this.caseSelection = caseSelection;
	}

	public Unite getUniteSelection() {
		return uniteSelection;
	}

	public void setUniteSelection(Unite uniteSelection) {
		this.uniteSelection = uniteSelection;
	}

	public Unite getUniteAdversaireSelection() {
		return uniteAdversaireSelection;
	}

	public void setUniteAdversaireSelection(Unite uniteAdversaireSelection) {
		this.uniteAdversaireSelection = uniteAdversaireSelection;
	}

	



	

} 