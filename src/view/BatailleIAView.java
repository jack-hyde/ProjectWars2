package view;

import game.Unite;
import model.BatailleIAModel;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class BatailleIAView extends PhaseView{

	BatailleIAModel model;
	
	public BatailleIAView(BatailleIAModel model, Graphics g) throws SlickException
	{
		super(g, model.getPartie().getContainer());
		
		this.model = model;
	}
	
	//LIRE CE QUI SUIT :
	//Je pense qu'on aura pas besoin de cette fonction? Peut etre meme pas besoin des IAView du tout... a voir
	
	/*private void drawAllUnits()
	{
		int tileWidth = this.model.getPartie().getMap().getTileWidth();
		int tileHeight = this.model.getPartie().getMap().getTileHeight();
		int screenX = this.model.getPartie().getScreenX();
		int screenY = this.model.getPartie().getScreenY();
		
		Graphics g2 = new Graphics();
		Color ambre = new Color(173,57,14);
		Color jaunepisse = new Color(240,195,0);
		
		for (Unite unite : this.model.getAl_unites()) {
			int placementX = unite.getCaseX();
			int placementY = unite.getCaseY();

			if(unite.getName() == "Tank")
			{
				g2.setColor(ambre);

				g2.fillRect(placementX * tileWidth + screenX + 15, placementY * tileHeight + screenY +15, tileWidth-30, tileHeight-30);
			}
			else if(unite.getName() == "Sniper")
			{
				g2.setColor(jaunepisse);

				g2.fillRect(placementX * tileWidth + screenX + 15, placementY * tileHeight + screenY +15, tileWidth-30, tileHeight-30);
			}
		}
	}*/
}
