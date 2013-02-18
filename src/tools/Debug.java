package tools;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


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
}
