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
	public final static Color COLOR_BLANC= new Color(255,255,255,100);
	public final static Color COLOR_BLANC_TRANSPARENT= new Color(255,255,255,100);
	public final static Color COLOR_BLANC_TRANSPARENT_2= new Color(255,255,255,150);
	public final static Color COLOR_ORANGE= new Color(255,128,64,255);
	public final static Color COLOR_ROUGE= new Color(255,0,0,255);
}
