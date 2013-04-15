package game;

import java.util.ArrayList;

public class Joueur {

	public boolean ia;
	public String nom;
	private int nbUnites;
	private ArrayList<Unite> liste_unites; // la ou seront stokées les unités
	private String capacite1;
	private String capacite2;
	private String capacite3;
	
	public Joueur(boolean ia, String nom, ArrayList<Unite> liste_unites, String capacite1, String capacite2, String capacite3)
	{
		this.ia = ia;
		this.nom = nom;
		this.liste_unites = liste_unites;
		this.capacite1 = capacite1;
		this.capacite2 = capacite2;
		this.capacite3 = capacite3;
		this.nbUnites = this.liste_unites.size();
	}	

	public Unite getUnite(int i) //retourne une unité qui a ete appelé depuis le hud
	{ 
		Unite unite = this.liste_unites.get(i);
		return unite;
	}
	
	public void setUnite(int index, Unite unite)//change une unité
	{
		this.liste_unites.set(index, unite);
	}
	
	public ArrayList<Unite> getListe_unites() {
		return liste_unites;
	}

	public void setUnites(ArrayList<Unite> unites)//change toute les unités pas sur que sa marche il faut peut etre utiliser .clone() ou .addAll
	{
		liste_unites = unites;
	}
	
	public int getNbUnites() {	
		return nbUnites;
	}

	public boolean getIa() {
		return ia;
	}

	public void setIa(boolean ia) {
		ia = ia;
	}
}
