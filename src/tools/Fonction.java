package tools;

import java.util.ArrayList;

public abstract class Fonction {

	public final static ArrayList<String> calculRayonDeplacement(int caseX, int caseY, int xMax, int yMax, int rayon)
	{
		ArrayList<String> coordValides = new ArrayList<String>();
		
		for(int x=0; x < xMax; x++)
    	{
    		for(int y=0; y< yMax; y++)
        	{
    			if(x <= caseX+rayon && x >= caseX-rayon && y <= caseY+rayon && y >= caseY-rayon && Math.abs(x-caseX)+Math.abs(y-caseY) <= rayon)
    			{
    				coordValides.add(x+":"+y);
    			}
        	}
    	}		
		return coordValides;		
	}
}
