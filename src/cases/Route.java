package cases;

import game.Case;

public class Route extends Case{

	public Route(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.typeCase = "route";
		this.defense = 0;
		this.attaque = 50;
	}
	
	public Route(int x, int y, int defense, int attaque)
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
