package tools;

import game.Map;
import java.util.ArrayList;

public abstract class Fonction {
	//depart X / Y case sur laquelle est l'unité a deplacer, rayon son rayon de deplacement, map pour recuperer le type de la case et si il y a une unité sur cette case
	//il faut aussi passer en parametre l'unité que l'on deplace
	public final static ArrayList<String> calculRayonDeplacement(int departX, int departY, int rayon, Map map)
	{
		ArrayList<String> coordValides = new ArrayList<String>();
		int cheminX = 0;
		int cheminY = 0;
		int mapWidth = map.getWidth();
		int mapHeight = map.getHeight();
		
		for(int x1 = -1; x1 <= 1; x1++)
		{
			for(int y1 = -1; y1 <= 1; y1++)
			{
				if(Math.abs(x1) != Math.abs(y1) && Math.abs(x1) + Math.abs(y1) != 0)
				{
					if(departX + x1 >= 0 && departY + y1 >= 0 && departX + x1 < mapWidth && departY + y1 < mapHeight)//enleve les cases qui sortent de la carte et les case occupé par l'ennemi et les batiments
					{
						if(map.recupUneCase(departX + x1, departY + y1).getTypeCase().equals("montagne") == false)
						{
							cheminX = departX + x1;
							cheminY = departY + y1;
							boolean existe = false;
							if(coordValides.contains(cheminX+":"+cheminY))
							{
								existe = true;
							}
							if(existe == false)
							{
								coordValides.add(cheminX+":"+cheminY);
							}
							coordValides = fctRecursiveCalculRayonDeplacement(1, rayon-1, cheminX, cheminY, coordValides, map);
						}
					}		  
				}  
			}
		}
		return coordValides;
	}
	
	public static ArrayList<String> fctRecursiveCalculRayonDeplacement(int rayon_en_cours, int rayonMax, int cheminX, int cheminY, ArrayList<String> coordValides, Map map)
	{
		int mapWidth = map.getWidth();
		int mapHeight = map.getHeight();
		
		if(rayon_en_cours <= rayonMax)
		{
			rayon_en_cours++;
			for(int x = -1; x <= 1; x++)
			{
				for(int y = -1; y <= 1; y++)
				{
					if(Math.abs(x) != Math.abs(y) && Math.abs(x) + Math.abs(y) != 0)
					{
						if(cheminX + x >= 0 && cheminY + y >= 0 && cheminX + x < mapWidth && cheminY + y < mapHeight) //enleve les cases qui sortent de la carte et les case occupé par l'ennemi et les batiments
						{
							if(map.recupUneCase(cheminX + x, cheminY + y).getTypeCase().equals("montagne") == false)
							{
								int cheminX2 = cheminX + x;
								int cheminY2 = cheminY + y;
								boolean existe = false;
								if(coordValides.contains(cheminX2+":"+cheminY2))
								{
									existe = true;
								}
								if(existe == false)
								{
									coordValides.add(cheminX2+":"+cheminY2);
								}	
								fctRecursiveCalculRayonDeplacement(rayon_en_cours, rayonMax, cheminX2, cheminY2, coordValides, map);
							}	
						}
					}
				}         
			}
		}	
		return coordValides;
	}

}
