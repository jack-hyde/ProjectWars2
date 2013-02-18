package game;

public class Joueur {

	private int nbUnite;
	private String nomJoueur;
	
	public Joueur(String nomJoueur)
	{
		this.nomJoueur = nomJoueur;
		
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
