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
		
		//Fonctions qui permet d'afficher tous les carrés où le placement des unités est possibles
		int x;
		int y;
		this.g.setColor(Constantes.COLOR_BLEU_TRANSPARENT);
		for(String s : this.model.getAl_casesPossibilitesDeploiement())
		{
			String str[] = s.split(":");
			x = Integer.parseInt(str[0]);
			y = Integer.parseInt(str[1]);
			this.g.fillRect(x * tileWidth + screenX, y * tileHeight + screenY, tileWidth, tileHeight);
		}
		
		int placementX;
		int placementY;
		for (Unite unite : this.model.getPartie().getEquipeEnCours().getAl_unitesEquipe()) {
			placementX = unite.getCaseX();
			placementY = unite.getCaseY();

			if(unite.getName() == "Tank")
			{
				this.g.setColor(Constantes.COLOR_AMBRE);

				this.g.fillRect(placementX * tileWidth + screenX + 15, placementY * tileHeight + screenY +15, tileWidth-30, tileHeight-30);
			}
			else if(unite.getName() == "Sniper")
			{
				this.g.setColor(Constantes.COLOR_JAUNEPISSE);

				this.g.fillRect(placementX * tileWidth + screenX + 15, placementY * tileHeight + screenY +15, tileWidth-30, tileHeight-30);
			}
		}
		
		
	}
	
	public void interactionsCarte()
	{
		int tileWidth = this.model.getPartie().getMap().getTileWidth();
		int tileHeight = this.model.getPartie().getMap().getTileHeight();
		int screenX = this.model.getPartie().getScreenX();
		int screenY = this.model.getPartie().getScreenY();
		int caseY = this.model.getCaseY();
		int caseX = this.model.getCaseX();
		int selectionX = this.model.getSelectionX();
		int selectionY = this.model.getSelectionY();
		
		this.afficherSelectionCase(selectionX, selectionY, tileWidth, tileHeight, screenX, screenY, caseX, caseY);
	}
	
	public void afficherIHMBas()
	{
		this.ihmBas.setViewButtonStart(this.model.isViewButtonStart());
		this.ihmBas.setCaseX(this.model.getCaseX());
		this.ihmBas.setCaseY(this.model.getCaseY());
		this.ihmBas.setViewIHM(this.model.isViewIHMBas());
		this.ihmBas.setCaseSelection(this.model.getCaseSelection());
		this.ihmBas.setUniteSelection(this.model.getUniteSelect());
		this.ihmBas.setViewButtonSupprimerUnite(this.model.isViewButtonSupprimerUnite());
		this.ihmBas.setMsgErrorDeploiement(this.model.getMsgError());
		this.ihmBas.drawIHM();
			
	}
	
	public void afficherIHMDeploiementUnite() throws SlickException
	{
		//drawAllUnits(); //Affichage des unitŽs	
		this.ihmDeploiementUnite.render();
			
	}
}
