package view;

import game.Unite;
import ihm.IHMDeploiementUnite;
import model.DeploiementModel;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import tools.Constantes;

public class DeploiementView extends PhaseView {

	DeploiementModel model;
	private IHMDeploiementUnite ihmDeploiementUnite;
	
	public DeploiementView(DeploiementModel model, Graphics g) throws SlickException
	{
		super(g, model.getPartie().getContainer());
		
		this.model = model;
		
		this.ihmDeploiementUnite = new IHMDeploiementUnite(model.getPartie().getContainer(), model);
	}
	
	//fonction d'affichage des unitŽs (en private car elle ne peut pas etre appelŽ ailleur)
	//Il faudra virer le g.fillRect et utiliser la fonction drawImage (prŽsente dans chaque unitŽ) pour afficher l'image de l'unitŽ
	public void drawAllUnits()
	{
		int tileWidth = this.model.getPartie().getMap().getTileWidth();
		int tileHeight = this.model.getPartie().getMap().getTileHeight();
		int screenX = this.model.getPartie().getScreenX();
		int screenY = this.model.getPartie().getScreenY();
		
		Graphics g2 = new Graphics();
		Color ambre = new Color(173,57,14);
		Color jaunepisse = new Color(240,195,0);
		for (Unite unite : this.model.getPartie().getEquipeEnCours().getAl_unitesEquipe()) {
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
		
		//affiche le carré de selection
		g.drawRect(selectionX, selectionY,  tileWidth, tileHeight);
		
		//case selectionné blanc transparent
		g2.fillRect(caseX * tileWidth + screenX, caseY * tileHeight + screenY, tileWidth, tileHeight);
	}
	
	public void afficherIHMBas()
	{
		int caseY = this.model.getCaseY();
		int caseX = this.model.getCaseX();
		//drawAllUnits(); //Affichage des unitŽs	
		this.ihmBas.majIHM(g, this.model.isViewIHMBas(), caseX, caseY, null, null, null);
			
	}
	
	public void afficherIHMDeploiementUnite() throws SlickException
	{
		//drawAllUnits(); //Affichage des unitŽs	
		this.ihmDeploiementUnite.render();
			
	}
}
