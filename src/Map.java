import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends TiledMap{
 
	public Map(String ref) throws SlickException {
		super(ref);//envoie à TiledMap l'adresse du fichier .tmx
	
	}
}
