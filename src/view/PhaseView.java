package view;

import ihm.IHMBas;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import tools.Constantes;

public abstract class PhaseView {

	protected Graphics g;
	protected GameContainer c;
	protected IHMBas ihmBas;
	
	public PhaseView(Graphics g, GameContainer c) throws SlickException
	{
		this.g=g;
		this.c=c;
		this.ihmBas = new IHMBas(c, g);
	}
	
	public void afficherSelectionCase(int selectionX, int selectionY, int tileWidth, int tileHeight, int screenX, int screenY, int caseX, int caseY)
	{
		Graphics g2 = new Graphics();
		g2.setColor(Constantes.COLOR_BLANC_TRANSPARENT);
		this.g.setColor(Constantes.COLOR_BLANC);
		if(selectionX > -1 && selectionY > -1)
		{
			//affiche le carré de selection
			this.g.drawRect(selectionX, selectionY,  tileWidth, tileHeight);
		}
		if(caseX > -1 && caseY > -1)
		{
			//case selectionné blanc transparent
			g2.fillRect(caseX * tileWidth + screenX, caseY * tileHeight + screenY, tileWidth, tileHeight);
		}
		
		
	}
}
