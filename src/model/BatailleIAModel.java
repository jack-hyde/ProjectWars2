package model;

import java.util.ArrayList;

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
		for(Unite unite : this.partie.getJ1().getAl_unitesEquipe())
		{
			this.al_unites.add(unite);
		}
		for(Unite unite : this.partie.getIa().getAl_unitesEquipe())
		{
			this.al_unites.add(unite);
		}
	}

	public ArrayList<Unite> getAl_unites() {
		return al_unites;
	}

	public void setAl_unites(ArrayList<Unite> al_unites) {
		this.al_unites = al_unites;
	}

	
}
