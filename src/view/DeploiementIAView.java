package view;

import model.DeploiementIAModel;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DeploiementIAView extends PhaseView{

	DeploiementIAModel model;
	
	public DeploiementIAView(DeploiementIAModel model, Graphics g) throws SlickException
	{
		super(g, model.getPartie().getContainer());
		
		this.model = model;
	}
}
