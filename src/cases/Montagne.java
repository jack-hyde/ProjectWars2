package cases;

import game.Case;

public class Montagne extends Case{

	public Montagne(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.typeCase = "montagne";
		this.defense = 70;
		this.attaque = 40;
	}
	
	public Montagne(int x, int y, int defense, int attaque)
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
