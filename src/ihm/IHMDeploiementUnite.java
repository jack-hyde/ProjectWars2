package ihm;

import java.util.HashMap;
import java.util.Map.Entry;

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

import tools.Constantes;


public class IHMDeploiementUnite implements ComponentListener {  
	 
	private Graphics g;
	
	private DeploiementModel deploiement;
	private GameContainer container;
	
	private HashMap<MouseOverArea, Unite> allMouseOverArea;
	
	 //Plus tard je me demanderai probablement pourquoi j'ai fait ce système de flagMoaUpdate et des fct raz qui vont avec...
	//mais il ne faut pas toucher, je pense que c'est la meilleure solution. Cette variable est en static pour qu'on puisse y acceder depuis le model de deploiement.
	public static boolean flagMoaUpdate = false;
	
	
	
	private MouseOverArea button_Close;
	
	public IHMDeploiementUnite(GameContainer c, DeploiementModel deploiement) throws SlickException {

		this.container = c;
		this.deploiement = deploiement;
		
		this.g=	this.container.getGraphics();
		
		this.button_Close = new MouseOverArea(this.container, new Image("images/close_ihm.png"), 870, 210, this);
		this.button_Close.setNormalColor(new Color(1,1,1,0.8f)); 
		this.button_Close.setMouseOverColor(new Color(1,1,1,0.9f));
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
	}
	
	public void render() throws SlickException {
		
		if(IHMDeploiementUnite.flagMoaUpdate == true)
		{
			this.razMouseOverArea(); //raz des mouseOverArea qui dépendent des unités possibles
			IHMDeploiementUnite.flagMoaUpdate = false;
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
		
		
		for(Entry<MouseOverArea, Unite> entry : this.allMouseOverArea.entrySet())
		{
			entry.getKey().render(this.container, this.g);
		}
		
		this.button_Close.render(this.container, this.g);
		
	}
	
	@Override
	public void componentActivated(AbstractComponent source) {
		
		
		if(source.equals(this.button_Close))  
		{
			DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_AFFICHER_CARTE);

		}
		else
		{
			Unite unite_select = this.allMouseOverArea.get(source);
			
			this.deploiement.setUniteSelect(unite_select);
			DeploiementModel.setPhaseDeDeploiement(Constantes.PHASE_DEPLOIEMENT_PLACEMENT_UNITE);
			
			IHMDeploiementUnite.flagMoaUpdate = true; //on leve le flag qui va permettre de remettre à zero les unités possibles (créer de nouveaux objets) et également les mouseOverArea
	
		}
	}
	
	



	

} 