package phases;

import java.util.ArrayList;

import tools.Entree;

import game.Case;
import game.Equipe;
import game.Map;
import game.Unite;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Deploiment {
	
	private UpdateDeploiment updateDeploiment;
	private RenderDeploiment renderDeploiment;
	
	private String phase;//determine la phase dans laquelle on est
	private String session;//determine la session dans laquelle on est
	private boolean pret;//= true si le joueur a appuiyer sur pret
	
	private boolean hud;//si true l'hud est affiché
	
	private Unite uniteSelection;//l'unité selectionné
	private Case caseSelection;//la case selectionné
	private int selectionX;//coordonées du carré de selection
	private int selectionY;
	
	private Equipe equipeA;
	private Equipe equipeB;
	
	private ArrayList<String> casesDeploiementA1;//les zones de deploiment de chaque joueur ils sont tous affichés dans chaque session
	private ArrayList<String> casesDeploiementA2;
	private ArrayList<String> casesDeploiementB1;
	private ArrayList<String> casesDeploiementB2;
	
	
	public void init(Map map, Equipe equipeA, Equipe equipeB){	
		this.phase = "deploiment";
		this.session = "joueurA1";
		this.pret = false;
		
		this.hud = true;
		
		this.uniteSelection = null;
		this.caseSelection = null;
		this.selectionX = 0;
		this.selectionY = 0;
		
		//on recupere les equipes
		this.equipeA = equipeA;
		this.equipeB = equipeB;
		
		//definie la zone de deploiment
		this.updateDeploiment.initCasesDeploiement(map, this.equipeA, this.equipeB);
		this.casesDeploiementA1 = this.updateDeploiment.getCasesDeploiementA1();
		this.casesDeploiementA2 = this.updateDeploiment.getCasesDeploiementA2();
		this.casesDeploiementB1 = this.updateDeploiment.getCasesDeploiementB1();
		this.casesDeploiementB2 = this.updateDeploiment.getCasesDeploiementB2();
		
		//positionne les unités de chaque joueur sur la carte
		this.equipeA.getJoueur1().setUnites(this.updateDeploiment.initUniteDeploiment(this.equipeA.getJoueur1(), this.casesDeploiementA1));
		this.equipeA.getJoueur2().setUnites(this.updateDeploiment.initUniteDeploiment(this.equipeA.getJoueur2(), this.casesDeploiementA2));
		this.equipeB.getJoueur1().setUnites(this.updateDeploiment.initUniteDeploiment(this.equipeB.getJoueur1(), this.casesDeploiementB1));
		this.equipeB.getJoueur2().setUnites(this.updateDeploiment.initUniteDeploiment(this.equipeB.getJoueur2(), this.casesDeploiementB2));	
	}

	public void render(Graphics g, int screenX, int screenY) {
		switch(session)
		{
			case "joueurA1" : 
				if(equipeA.getJoueur1().getIa() == false)
				{
					//carré de deploiment
					this.renderDeploiment.carreDeploiment(g, screenX, screenY, this.casesDeploiementA1, this.casesDeploiementA2, this.casesDeploiementB1, this.casesDeploiementB2);
					
					//unités
					this.renderDeploiment.drawUnites(g, screenX, screenY, this.uniteSelection, this.equipeA.getJoueur1());
					
					// desinne les carré de selection sur la carte
					this.renderDeploiment.drawSelection(g, screenX, screenY, this.caseSelection, this.selectionX, this.selectionY);
					
					//HUD
					this.renderDeploiment.drawHud(g, screenX, screenY, this.caseSelection, this.uniteSelection, this.equipeA.getJoueur1());	
				}
				else
				{
					
				}
				break;
			case "joueurA2" : 
				break;
			case "joueurB1" : 
				break;
			case "joueurB2" : 
				break;
		}
	}

	public void update(GameContainer container, Map map, int screenX, int screenY, Entree entree, int delta) {
		
		int index = 0;
		//session a qui le tour ?
		switch(session)
		{
			case "joueurA1" : 
				if(equipeA.getJoueur1().getIa() == false)//si ce n'est pas une ia
				{
					if(uniteSelection != null)//si on a une unité on peut la replacer
					{
						index = this.equipeA.getJoueur1().getListe_unites().indexOf(this.uniteSelection);//on a besoin de l'index pour (voir un peu au dessous)
						this.uniteSelection = this.updateDeploiment.setUniteCase(map, screenX, screenY, this.uniteSelection, this.casesDeploiementA1, entree);
						this.equipeA.getJoueur1().setUnite(index, this.uniteSelection);//on met a jour la position de l'unite dans la liste des unites (ICI !!)
					}
					
					//selection de l'unité dans l'hud
					this.uniteSelection = this.updateDeploiment.getUniteHud(container, this.equipeA.getJoueur1(), entree);
					
					//selection d'une unité sur la carte ou d'une case
					this.uniteSelection = this.updateDeploiment.getUniteCarte(container, map, this.equipeA.getJoueur1(), entree, screenX, screenY);
					
					//on recupere ce qui servira dans render
					this.caseSelection = this.updateDeploiment.getCaseSelection();
					this.selectionX = this.updateDeploiment.getSelectionX();
					this.selectionY = this.updateDeploiment.getSelectionY();			
				}
				else // IA
				{
					
				}
	
				//on met a jour les cases qui sont occupé en leur associant une unité
				this.updateDeploiment.majCases();
				//puis on recupere les cases
				
				//interaction avec l'hud du joueur ex : changer l'ordre des unites avec un drag & drop
				this.updateDeploiment.hudJoueur();
				
				break;
				
			case "joueurA2" : 
				break;
			case "joueurB1" : 
				break;
			case "joueurB2" : 
				break;
		}
		//interaction avec l'interface ex : Echap, bouton pret
		
		this.updateDeploiment.menu();//s'occupe de  toutes les interaction avec le menu dont le bouton suivant qui change de session ou de phase
		this.pret = this.updateDeploiment.getPret();
		
		//si le joueur appuis sur commencer on change de session si c'etait le dernier joueur on passe en mode bataille
		if(this.pret == true)
		{
			this.updateDeploiment.sessionPhase();
			this.session = this.updateDeploiment.getSession();
			this.phase = this.updateDeploiment.getPhase();
			this.pret = false;
		}	
	}

	public String getPhase() {
		return phase;
	}

	public Equipe getEquipeA() {
		return equipeA;
	}

	public Equipe getEquipeB() {
		return equipeB;
	}
}
