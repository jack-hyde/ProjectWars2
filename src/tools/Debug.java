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
		Set listKeys=map.keySet();  // Obtenir la liste des cl�s
		Iterator iterateur=listKeys.iterator();
		// Parcourir les cl�s et afficher les entr�es de chaque cl�;
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			System.out.println (key+"=>"+map.get(key));
		}
	}
}
