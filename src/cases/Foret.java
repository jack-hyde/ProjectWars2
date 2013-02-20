package cases;

import game.Case;

public class Foret extends Case{

	public Foret(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.typeCase = "foret";
		this.defense = 50;
		this.attaque = 10;
		
	}
	
	public Foret(int x, int y, int defense, int attaque)
	{
		this.x = x;
		this.y = y;
		this.defense = defense;
		this.attaque = attaque;
	}
	
	@Override
	public void methode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void special() {
		// TODO Auto-generated method stub
		
	}

}
