package model;

import java.util.ArrayList;

import game.Joueur;
import game.Unite;
import states.Partie;

public class BatailleIAModel extends PhaseModel{

	private ArrayList<Unite> al_unites;
	
	public BatailleIAModel(Partie partie)
	{
		super(partie);
		this.al_unites = new ArrayList<Unite>(); //où seront stockés l'ensembles des unités présentes sur la carte
	}
	
	public void addAllUnites()
	{
		if(this.partie.getEquipeA().getNbJoueurEquipe() == 1)
		{
			for(Unite unite : this.partie.getEquipeA().getJoueur1().getListe_unites())
			{
				this.al_unites.add(unite);
			}
		}
		else
		{
			for(Unite unite : this.partie.getEquipeA().getJoueur1().getListe_unites())
			{
				this.al_unites.add(unite);
			}
			for(Unite unite : this.partie.getEquipeA().getJoueur2().getListe_unites())
			{
				this.al_unites.add(unite);
			}
		}
		
		if(this.partie.getEquipeB().getNbJoueurEquipe() == 1)
		{
			for(Unite unite : this.partie.getEquipeB().getJoueur1().getListe_unites())
			{
				this.al_unites.add(unite);
			}
		}
		else
		{
			for(Unite unite : this.partie.getEquipeB().getJoueur1().getListe_unites())
			{
				this.al_unites.add(unite);
			}
			for(Unite unite : this.partie.getEquipeB().getJoueur2().getListe_unites())
			{
				this.al_unites.add(unite);
			}
		}
	}

	public ArrayList<Unite> getAl_unites() {
		return al_unites;
	}

	public void setAl_unites(ArrayList<Unite> al_unites) {
		this.al_unites = al_unites;
	}

	
}
