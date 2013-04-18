package game;

import java.util.ArrayList;

public class Equipe {

	private Joueur joueur1;
	private Joueur joueur2;
	private int nbJoueurEquipe;
	private int nbUnite;
	private String nomEquipe;
	private ArrayList<Unite> liste_unites;
	private boolean ia;
	
	public Equipe()
	{
		
	}
	
	public Equipe(Joueur joueur1) {
		this.joueur1 =  joueur1;
		this.nbJoueurEquipe = 1;
	}

	public Equipe(Joueur joueur1, Joueur joueur2) {
		this.joueur1 =  joueur1;
		this.joueur2 =  joueur2;
		this.nbJoueurEquipe = 2;
	}


	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	public int getNbJoueurEquipe(){	
		return this.nbJoueurEquipe;
	}


	public int getNbUnite() {
		return nbUnite;
	}

	public void setNbUnite(int nbUnite) {
		this.nbUnite = nbUnite;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}
}
