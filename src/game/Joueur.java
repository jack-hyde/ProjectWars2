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
	private Equipe equipe;
	
	public Joueur(boolean ia, String nom,String capacite1, String capacite2, String capacite3)
	{
		this.ia = ia;
		this.nom = nom;
		this.capacite1 = capacite1;
		this.capacite2 = capacite2;
		this.capacite3 = capacite3;
		this.liste_unites = new ArrayList<Unite>();
		this.equipe = new Equipe();
	}	

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
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

	public boolean isIa() {
		return ia;
	}

	public void setIa(boolean ia) {
		this.ia = ia;
	}
	
	public void addUnite(Unite unite)
	{
		this.liste_unites.add(unite);
	}
	
	public void removeUnite(Unite unite)
	{
		this.liste_unites.remove(unite);
	}
}
