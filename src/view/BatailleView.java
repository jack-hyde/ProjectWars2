package view;

import game.Unite;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import tools.Constantes;
import model.BatailleModel;

public class BatailleView extends PhaseView{

	BatailleModel model;
	
	public BatailleView(BatailleModel model, Graphics g) throws SlickException
	{
		super(g, model.getPartie().getContainer());
		
		this.model = model;
	}

	
	public void render()
	{
		int tileWidth = this.model.getPartie().getMap().getTileWidth();
		int tileHeight = this.model.getPartie().getMap().getTileHeight();
		int screenX = this.model.getPartie().getScreenX();
		int screenY = this.model.getPartie().getScreenY();
		int caseY = this.model.getCaseY();
		int caseX = this.model.getCaseX();
		int selectionX = this.model.getSelectionX();
		int selectionY = this.model.getSelectionY();
		
		Graphics g3 = new Graphics();
		Graphics g2 = new Graphics();
		g2.setColor(Constantes.COLOR_BLANC_TRANSPARENT);
		g3.setColor(Constantes.COLOR_ROUGE);
		
		this.afficherSelectionCase(selectionX, selectionY, tileWidth, tileHeight, screenX, screenY, caseX, caseY);
				
		//valeurs
		this.g.drawString("screenX "+screenX, 10, 60);
		this.g.drawString("screenY "+screenY, 10, 80);
			
		
		if(this.model.getUniteSelection() != null)
		{
			//Debug.afficheHashMap(this.casesPosibiliteDeplacement);
			for(String s : this.model.getCasesPosibiliteDeplacement())
			{
				String str[] = s.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				g2.setColor(Constantes.COLOR_BLANC_TRANSPARENT_2);
				g2.fillRect(x * tileWidth + screenX, y * tileHeight + screenY, tileWidth, tileHeight);
			}
			for(String s : this.model.getCasesChemin())
			{
				String str[] = s.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				g3.fillRect(x * tileWidth + screenX, y * tileHeight + screenY, tileWidth/2, tileHeight/2);
			}
			
		}		
		
		//drawAllUnits(); //Affichage des unitŽs	
	
	
		this.drawAllUnits();
		this.afficherIHMBas();
	}
	
	public void afficherIHMBas()
	{
		//drawAllUnits(); //Affichage des unitŽs	
		this.ihmBas.setCaseX(this.model.getCaseX());
		this.ihmBas.setCaseY(this.model.getCaseY());
		this.ihmBas.setViewIHM(this.model.isViewIHMBas());
		this.ihmBas.setCaseSelection(this.model.getCaseSelection());
		this.ihmBas.setUniteSelection(this.model.getUniteSelection());
		this.ihmBas.setUniteAdversaireSelection(this.model.getUniteJ2Selection());
		
		this.ihmBas.drawIHM();
			
	}
	
	private void drawAllUnits()
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
	}

}
