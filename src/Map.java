import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends TiledMap{
 
	private HashMap<Integer, HashMap<Integer, Case>> allCases; //Un tableau où sera stocker toutes les cases. Comme ceci : tab[x][y] = Case

	public Map(String ref) throws SlickException {
		super(ref);//envoie à TiledMap l'adresse du fichier .tmx
		this.allCases = new HashMap<Integer, HashMap<Integer, Case>>();
		this.recupTiles();
	}

	public void recupTiles()
	{
		for (int x = 0; x < this.width; x++)
		{
			for (int y = 0; y < this.height; y++)
			{
				int id = this.getTileId(x, y, 0); //on récupère l'id, uniquement pour voir s'il existe bien un tile
				//System.out.println("id : "+id+" x : "+x+" y: "+y);
				
				int nbDefense = 0;
				if (id != 0)
				{ 
					String strDefense = this.getTileProperty(id, "defense", ""); //on récupère la valeur de la défense de la case (en String)
					nbDefense = Integer.parseInt(strDefense); //on parse la valeur de la défense en Int
				}
				
				HashMap<Integer, Case> tabCase = new HashMap<Integer, Case>(); //on créer un tableau qui aura cette forme : tab[y] = Case
				Case laCase = new Case(nbDefense, x, y); //On créer notre objet case
				tabCase.put(y, laCase); //on ajoute la case dans le tableau tab[y]
				this.allCases.put(x, tabCase); //puis on ajoute le tout dans notre tableau complet
			}
		}
		
		//Debug.afficheHashMap(this.allCases);
	}

	public HashMap<Integer, HashMap<Integer, Case>> getAllCases() {
		return allCases;
	}

	public void setAllCases(HashMap<Integer, HashMap<Integer, Case>> allCases) {
		this.allCases = allCases;
	}

}

