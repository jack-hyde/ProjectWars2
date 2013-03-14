package ihm;

import game.Case;
import game.Unite;

import model.DeploiementModel;

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
	  
	private boolean viewIHM;
	
	private int caseX; //global
	private int caseY; //global 
	private Case caseSelection; //global
	private Unite uniteSelection; //global
	private Unite uniteAdversaireSelection; //bataille
	
	private Graphics g; //global
	private Graphics g2; //global
	private GameContainer container; //global
	
	private MouseOverArea button_Deploiement; //deploiement
	private MouseOverArea button_Start; //deploiement
	private MouseOverArea button_SupprimerUnite; //deploiement
	
	private boolean viewButtonStart; //deploiement
	private boolean viewButtonSupprimerUnite; //deploiement
	
	private String msgErrorDeploiement;
	
	public IHMBas(GameContainer c, Graphics g) throws SlickException {
		
		this.viewIHM =false;
		this.caseX=0;
		this.caseY=0;
		this.caseSelection = null;
		this.uniteSelection = null;
		this.uniteAdversaireSelection = null;
		
		//Les variables qui définissent si les boutons sont visibles ou non
		this.viewButtonStart = false;
		this.viewButtonSupprimerUnite = false;
		
		//Les messages
		this.msgErrorDeploiement = "";
		
		this.container = c;
		
		//Création d'objet graphique dans le constructeur (pour éviter de le recreer à chaque fois)
		this.g = g;
		this.g2 = new Graphics();
		
		this.button_Deploiement = new MouseOverArea(c, new Image("images/deploiement.png"), 1090, 700, this);   
		this.button_Deploiement.setNormalColor(new Color(1,1,1,0.8f)); 
		this.button_Deploiement.setMouseOverColor(new Color(1,1,1,0.9f));
		
		this.button_Start = new MouseOverArea(this.container, new Image("images/start_battle.png"), 1090, 750, this);
		this.button_Deploiement.setNormalColor(new Color(1,1,1,0.8f)); 
		this.button_Deploiement.setMouseOverColor(new Color(1,1,1,0.9f));
		
		this.button_SupprimerUnite = new MouseOverArea(this.container, new Image("images/delete_unite.png"), 850, 700, this);
		this.button_SupprimerUnite.setNormalColor(new Color(1,1,1,0.8f)); 
		this.button_SupprimerUnite.setMouseOverColor(new Color(1,1,1,0.9f));
		// TODO Auto-generated constructor stub
	}
  
	public void drawIHM() {
		
		if(this.viewIHM)
		{
			switch(Partie.getPhaseDeJeu())
			{
				case Constantes.PHASE_BATAILLE :
					this.phaseBataille();
					break;
				
				case Constantes.PHASE_DEPLOIEMENT : 
					this.phaseDeploiement();
					break;
				
				default : break;
			}
		}
	}
		
	public void phaseBataille()
	{
		this.g2.setColor(Constantes.COLOR_BLANC_TRANSPARENT_2);
		
		this.g2.setColor(Constantes.COLOR_ORANGE);
    	this.g2.fillRect(0, this.container.getHeight() - 100 , 1300, Constantes.IHM_BAS_HAUTEUR);
    	
    	this.g.setColor(Constantes.COLOR_BLANC);
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
	}
	
	public void phaseDeploiement()
	{
		this.g2.setColor(Constantes.COLOR_BLANC_TRANSPARENT_2);
		
		this.g2.setColor(Constantes.COLOR_ORANGE);
    	this.g2.fillRect(0, this.container.getHeight() - 100 , 1300, Constantes.IHM_BAS_HAUTEUR);
    	
    	this.g.setColor(Constantes.COLOR_BLANC);
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

		//Si on est dans la phase de déploiement et que l'ihm de selection des unités n'est pas ouverte, on affiche le bouton de deploiement
		if(Partie.getPhaseDeJeu() == Constantes.PHASE_DEPLOIEMENT && DeploiementModel.getPhaseDeDeploiement() != Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM)
		{
			this.button_Deploiement.render(this.container, this.g);
		}
		
		if(this.viewButtonStart == true)
		{
			this.button_Start.render(this.container, this.g);
		}
		
		if(this.viewButtonSupprimerUnite == true)
		{
			this.button_SupprimerUnite.render(this.container, this.g);
		}
		
		
		if(this.msgErrorDeploiement != "")
		{
			this.g.setColor(Constantes.COLOR_ROUGE);
			this.g.drawString(this.msgErrorDeploiement, 400, 750);
		}
	}
	
	@Override
	public void componentActivated(AbstractComponent source) {
		// TODO Auto-generated method stub
		if(source.equals(this.button_Deploiement))  
		{
			
			Partie.setPhaseDeJeu(Constantes.PHASE_DEPLOIEMENT); 
			DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_AFFICHE_IHM);
		}
		else if(source.equals(this.button_Start))  
		{
			DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_TERMINER);

		}
		else if(source.equals(this.button_SupprimerUnite))  
		{
			DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_SUPPRIMER_UNITE);
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

	public Graphics getG() {
		return g;
	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public Graphics getG2() {
		return g2;
	}

	public void setG2(Graphics g2) {
		this.g2 = g2;
	}

	public GameContainer getContainer() {
		return container;
	}

	public void setContainer(GameContainer container) {
		this.container = container;
	}

	public MouseOverArea getButton_Deploiement() {
		return button_Deploiement;
	}

	public void setButton_Deploiement(MouseOverArea button_Deploiement) {
		this.button_Deploiement = button_Deploiement;
	}

	public MouseOverArea getButton_Start() {
		return button_Start;
	}

	public void setButton_Start(MouseOverArea button_Start) {
		this.button_Start = button_Start;
	}

	public boolean isViewButtonStart() {
		return viewButtonStart;
	}

	public void setViewButtonStart(boolean viewButtonStart) {
		this.viewButtonStart = viewButtonStart;
	}

	public MouseOverArea getButton_SupprimerUnite() {
		return button_SupprimerUnite;
	}

	public void setButton_SupprimerUnite(MouseOverArea button_SupprimerUnite) {
		this.button_SupprimerUnite = button_SupprimerUnite;
	}

	public boolean isViewButtonSupprimerUnite() {
		return viewButtonSupprimerUnite;
	}

	public void setViewButtonSupprimerUnite(boolean viewButtonSupprimerUnite) {
		this.viewButtonSupprimerUnite = viewButtonSupprimerUnite;
	}

	public String getMsgErrorDeploiement() {
		return msgErrorDeploiement;
	}

	public void setMsgErrorDeploiement(String msgErrorDeploiement) {
		this.msgErrorDeploiement = msgErrorDeploiement;
	}

	



	

} 