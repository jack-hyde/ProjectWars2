package cases;

import game.Case;

public class Plaine extends Case{

	public Plaine(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.typeCase = "plaine";
		this.defense = 20;
		this.attaque = 50;
	}
	
	public Plaine(int x, int y, int defense, int attaque)
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
