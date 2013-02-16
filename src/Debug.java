import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.newdawn.slick.Graphics;


public class Debug {
	
	public Debug()
	{
		
	}
	
	public static void afficheHashMap(HashMap map)
	{
		// Afficher le contenu du MAP
		Set listKeys=map.keySet();  // Obtenir la liste des clés
		Iterator iterateur=listKeys.iterator();
		// Parcourir les clés et afficher les entrées de chaque clé;
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			System.out.println (key+"=>"+map.get(key));
		}
	}
	
	public static void centerTexte(String string, int boiteX, int boiteY, int boiteWidth, int boiteHeight, Graphics g) //centre le texte dans une boite
	{
		int taillePoliceWidth = 5;	//valeurs a changer suivant la taille de la poloce
		int taillePoliceHeight = 8;	
		
		int tailleString = string.length();
		int centreWidth = boiteWidth / 2 ;
		int centreHeight = boiteHeight / 2 ;
		
		int texteX = boiteX + centreWidth - ((tailleString * taillePoliceWidth)/2);
		int texteY = boiteY + centreHeight - ((tailleString * taillePoliceHeight)/2);
		
		g.drawString(string, texteX, texteY);
		
	}

}
