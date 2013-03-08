package game;

import java.util.HashMap;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import cases.*;

public class Map extends TiledMap{
 
	private HashMap<String, Case> allCases; //Un tableau o� sera stocker toutes les cases. Comme ceci : tab[x][y] = Case

	public Map(String ref) throws SlickException {
		super(ref);//envoie � TiledMap l'adresse du fichier .tmx
		this.allCases = new HashMap<String, Case>();
		this.recupTiles();
	}

	public void recupTiles()
	{
		Case laCase = null;
		for (int x = 0; x < this.width; x++)
		{
			String coord = "";
			for (int y = 0; y < this.height; y++)
			{
				int id = this.getTileId(x, y, 0); //on r�cup�re l'id, uniquement pour voir s'il existe bien un tile
				//System.out.println("id : "+id+" x : "+x+" y: "+y);
				String strType = "";
				if (id != 0)
				{ 
					strType = this.getTileProperty(id, "type", ""); //on r�cup�re la valeur de la d�fense de la case (en String)				
				}
				coord = x+":"+y;
				if(strType.equals("route"))
				{
					laCase = new Route(x, y); //On cr�er notre objet case
				}
				else if(strType.equals("montagne"))
				{
					laCase = new Montagne(x, y); //On cr�er notre objet case
				}
				else if(strType.equals("plaine"))
				{
					laCase = new Plaine(x, y); //On cr�er notre objet case
				}
				else if(strType.equals("usine"))
				{
					laCase = new Usine(x, y); //On cr�er notre objet case
				}
				else if(strType.equals("batiment"))
				{
					laCase = new Batiment(x, y); //On cr�er notre objet case
				}
				else if(strType.equals("foret"))
				{
					laCase = new Foret(x, y); //On cr�er notre objet case
				}		
				
				if(coord != "" && laCase != null)
				{
					this.allCases.put(coord, laCase); //puis on ajoute le tout dans notre tableau complet
				}
			}	
		}
		
		//Debug.afficheHashMap(this.allCases);
	}

	public Case recupUneCase(int xSelect, int ySelect)
	{
		Case laCaseSelectionnee = null;
		laCaseSelectionnee = this.allCases.get(xSelect+":"+ySelect);
		
		return laCaseSelectionnee;
	}
	
	public void caseOccupe(int x, int y, String nomEquipe)
	{
		this.allCases.get(x+":"+y).setEstOccupe(true);
		this.allCases.get(x+":"+y).setEquipe(nomEquipe);
	}
	
	
	public HashMap<String, Case> getAllCases() {
		return this.allCases;
	}

	public void setAllCases(HashMap<String, Case> allCases) {
		this.allCases = allCases;
	}

}

