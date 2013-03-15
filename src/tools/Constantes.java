package tools;

import org.newdawn.slick.Color;

public interface Constantes {
	
	//Les constantes pour les entrees clavier/souris
	public final static int KEY_VOID=0;
	public final static int KEY_PRESSED=1;
	public final static int KEY_DOWN=2;

	//Constante de vitesse de scroll
	public final static int SCROLL_SPEED=10;
	
	//Quelques couleurs
	public final static Color COLOR_BLANC= new Color(255,255,255);
	public final static Color COLOR_BLANC_TRANSPARENT= new Color(255,255,255,100);
	public final static Color COLOR_BLANC_TRANSPARENT_2= new Color(255,255,255,150);
	public final static Color COLOR_ORANGE= new Color(255,128,64,255);
	public final static Color COLOR_ROUGE= new Color(255,0,0,255);
	public final static Color COLOR_AMBRE= new Color(173,57,14);
	public final static Color COLOR_JAUNEPISSE= new Color(240,195,0);
	public final static Color COLOR_BLEU_TRANSPARENT = new Color(58, 142, 186, 70);
	
	//Les phases du jeu
	public final static int PHASE_DEPLOIEMENT = 1;
	public final static int PHASE_DEPLOIEMENT_AFFICHE_IHM = 10;
	public final static int PHASE_DEPLOIEMENT_PLACEMENT_UNITE = 11;
	public final static int PHASE_DEPLOIEMENT_TERMINER = 12;
	public final static int PHASE_DEPLOIEMENT_IA_EN_COURS = 13;
	public final static int PHASE_DEPLOIEMENT_IA_TERMINER = 14;
	public final static int PHASE_DEPLOIEMENT_AFFICHER_CARTE = 15;
	public final static int PHASE_DEPLOIEMENT_SUPPRIMER_UNITE = 16;
	
	public final static int PHASE_BATAILLE = 2;
	public final static int PHASE_BATAILLE_DEPLACEMENT = 21;
	

	//Variables de dimenssions
	public final static int IHM_BAS_HAUTEUR = 100;
    
	//vitesse unités
	public final static int DELAY = 200; // 1 second determine la vitesse de deplacement de mise a jour du deplacement
	public final static int SPEED_MOVE = 10; // determine le nombre de pixels entr chaque image
}
