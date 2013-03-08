package view;

import model.BatailleIAModel;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class BatailleIAView extends PhaseView{

	BatailleIAModel model;
	
	public BatailleIAView(BatailleIAModel model, Graphics g) throws SlickException
	{
		super(g, model.getPartie().getContainer());
		
		this.model = model;
	}
}
