package cases;

import game.Case;

public class Usine extends Case{

	public Usine(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.defense = 50;
		this.attaque = 20;
	}
	
	public Usine(int x, int y, int defense, int attaque)
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
