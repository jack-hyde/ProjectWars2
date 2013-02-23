package cases;

import game.Case;

public class Batiment extends Case{

	public Batiment(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.typeCase = "batiment";
		this.defense = 50;
		this.attaque = 20;
		
	}
	
	public Batiment(int x, int y, int defense, int attaque)
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
