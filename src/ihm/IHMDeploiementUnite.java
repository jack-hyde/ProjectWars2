package ihm;

import java.util.HashMap;
import java.util.Map.Entry;

import game.Unite;

import model.DeploiementModel;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import tools.Constantes;


public class IHMDeploiementUnite implements ComponentListener {  
	 
	private Graphics g;
	
	private DeploiementModel deploiement;
	private GameContainer container;
	
	private HashMap<MouseOverArea, Unite> allMouseOverArea;
	private boolean flagRAZ;
	
	private MouseOverArea button_Start;
	
	public IHMDeploiementUnite(GameContainer c, DeploiementModel deploiement) throws SlickException {

		this.container = c;
		this.deploiement = deploiement;
		this.flagRAZ = false;
		
		this.g=	this.container.getGraphics();
		
		this.allMouseOverArea = new HashMap<MouseOverArea, Unite>();
		this.razMouseOverArea();
		
		
		// TODO Auto-generated constructor stub
	}
  
	public void razMouseOverArea() throws SlickException
	{
		this.allMouseOverArea.clear();
		int x=0;
		for(Unite unite : this.deploiement.getAl_unitesPossibles())
		{
			MouseOverArea moa = new MouseOverArea(this.container, new Image("images/select_unite.png"), 700, 210+x, this);   
			this.allMouseOverArea.put(moa, unite);
			x=x+40;
		}
		if(this.deploiement.getValeur() > 50)
		{
			this.button_Start = new MouseOverArea(this.container, new Image("images/start_battle.png"), 700, 240+x, this);
		}
		else
		{
			this.button_Start = null;
		}
	}
	
	public void render() throws SlickException {
		
		if(this.flagRAZ == true)
		{
			this.deploiement.razUnitesPossibles(); //RAZ des unités possibles, ce qui va permettre entre autre de créer de nouveau objet
			this.razMouseOverArea(); //raz des mouseOverArea qui dépendent des unités possibles
			this.flagRAZ = false;
		}
		
		this.g.setColor(Constantes.COLOR_ORANGE);
		this.g.fillRect(400, 200 , 500, 400);
		this.g.setColor(Constantes.COLOR_BLANC);
		
		int x = 0;
		int y = 0;
		
		for(Unite unite : this.deploiement.getAl_unitesPossibles())
		{
			this.g.drawString("* "+unite.getName()+" [AT:"+unite.getAttaque()+"|DEF:"+unite.getDefense()+"]", 410, 210+x);
			
			x=x+40;
		}
		
		this.g.drawString("VALEUR : "+this.deploiement.getValeur()+"/"+this.deploiement.getValeurMax(), 410, 210+x);
		
		if(this.button_Start != null)
		{
			this.button_Start.render(this.container, this.g);
		}
		for(Entry<MouseOverArea, Unite> entry : this.allMouseOverArea.entrySet())
		{
			entry.getKey().render(this.container, this.g);
		}
		
	}
	
	@Override
	public void componentActivated(AbstractComponent source) {
		
		if(source.equals(this.button_Start))  
		{
			DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_TERMINER);

		}
		else
		{
			Unite unite_select = this.allMouseOverArea.get(source);
			
			this.deploiement.setUniteSelect(unite_select);
			this.deploiement.setValeur(this.deploiement.getValeur()+unite_select.getValeur());
			DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_PLACEMENT_UNITE);
			
			this.flagRAZ = true; //on leve le flag qui va permettre de remettre à zero les unités possibles (créer de nouveaux objets) et également les mouseOverArea
	
		}
	}
	
	



	

} 