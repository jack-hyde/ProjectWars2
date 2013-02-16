import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends TiledMap{
 
	public Map(String ref) throws SlickException {
		super(ref);//envoie à TiledMap l'adresse du fichier .tmx
		
		this.recupTiles();
	}
	 
	public void recupTiles()
	{
		for (int x = 0; x < this.width; x++)
		{
		      for (int y = 0; y < this.height; y++)
		      {
		                int id = this.getTileId(x, y, 0);
		                //System.out.println(id);
		                if (id != 0)
		               { 
		                	//System.out.println(this.getTileProperty(id, "defense", ""));
		               }
		       }
		}
	}
	
}

