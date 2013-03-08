package view;

import ihm.IHMBas;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class PhaseView {

	protected Graphics g;
	protected GameContainer c;
	protected IHMBas ihmBas;
	
	public PhaseView(Graphics g, GameContainer c) throws SlickException
	{
		this.g=g;
		this.c=c;
		this.ihmBas = new IHMBas(c);
	}
}
