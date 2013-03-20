package model;

import game.Unite;

import org.newdawn.slick.SlickException;

import states.Partie;
import tools.Constantes;
import unites.Sniper;
import unites.Tank;

public class DeploiementIAModel extends PhaseModel{

	public DeploiementIAModel(Partie partie) throws SlickException
	{
		super(partie);
	}
	
	public void deploiement()
	{
		Unite sniper1 = new Sniper();
		sniper1.setCaseX(28);
		sniper1.setCaseY(3);
		
		Unite sniper2 = new Sniper();
		sniper2.setCaseX(29);
		sniper2.setCaseY(4);
		
		Unite tank1 = new Tank();
		tank1.setCaseX(26);
		tank1.setCaseY(7);
		
		this.partie.getEquipeEnCours().addUnite(sniper1);
		this.partie.getEquipeEnCours().addUnite(sniper2);
		this.partie.getEquipeEnCours().addUnite(tank1);
	}
	
	
	

}
