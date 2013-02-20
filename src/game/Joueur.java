package game;

import java.util.ArrayList;

public class Joueur {

	private int nbUnite;
	private String nomJoueur;
	private ArrayList<Unite> al_unitesDuJoueur;
	
	public Joueur(String nomJoueur)
	{
		this.nomJoueur = nomJoueur;
		this.al_unitesDuJoueur = new ArrayList<Unite>();
	}

	public void addUnite(Unite unite)
	{
		this.al_unitesDuJoueur.add(unite);
	}
	
	public void removeUnite(Unite unite)
	{
		this.al_unitesDuJoueur.remove(unite);
	}
	
	public ArrayList<Unite> getAl_unitesDuJoueur() {
		return al_unitesDuJoueur;
	}

	public void setAl_unitesDuJoueur(ArrayList<Unite> al_unitesDuJoueur) {
		this.al_unitesDuJoueur = al_unitesDuJoueur;
	}

	public int getNbUnite() {
		return nbUnite;
	}

	public void setNbUnite(int nbUnite) {
		this.nbUnite = nbUnite;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}
}
