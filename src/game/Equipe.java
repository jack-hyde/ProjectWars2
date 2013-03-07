package game;

import java.util.ArrayList;

public class Equipe {

	private int nbUnite;
	private String nomEquipe;
	private ArrayList<Unite> al_unitesEquipe;
	private boolean ia;
	
	public Equipe(String nomEquipe, boolean ia)
	{
		this.nomEquipe = nomEquipe;
		this.al_unitesEquipe = new ArrayList<Unite>();
		this.ia = ia;
	}

	public boolean isIa() {
		return ia;
	}

	public void setIa(boolean ia) {
		this.ia = ia;
	}

	public void addUnite(Unite unite)
	{
		this.al_unitesEquipe.add(unite);
	}
	
	public void removeUnite(Unite unite)
	{
		this.al_unitesEquipe.remove(unite);
	}
	
	public ArrayList<Unite> getAl_unitesEquipe() {
		return al_unitesEquipe;
	}

	public void setAl_unitesEquipe(ArrayList<Unite> al_unitesEquipe) {
		this.al_unitesEquipe = al_unitesEquipe;
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
