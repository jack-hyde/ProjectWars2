import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/*
 A modifier pour reatribuer les touches. 
 liste touches: http://www.slick2d.org/javadoc/org/newdawn/slick/Input.html#getAbsoluteMouseX%28%29
*/

public class Entree {
	
	private Input input;
	private int touches[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};		//tableau ou seront stoké les touches 13
																//si on change la valeur du tableau penser a le faire partout
	public Entree(GameContainer container){
		input = container.getInput();
		clavier();
		clicksouris();
		deplacementsouris();
	}
	
	public void clavier(){										//recupere les touches du clavier
		if (input.isKeyPressed(Input.KEY_A))
        {
			touches[0] = 1;
        }
		if (input.isKeyPressed(Input.KEY_Z))
        {
			touches[1] = 1;
        }
		if (input.isKeyPressed(Input.KEY_E))
        {
			touches[2] = 1;
        }
		if (input.isKeyPressed(Input.KEY_Q))
        {
			touches[3] = 1;
        }
		if (input.isKeyPressed(Input.KEY_S))
        {
			touches[4] = 1;
        }
		if (input.isKeyPressed(Input.KEY_D))
        {
			touches[5] = 1;
        }
		if (input.isKeyPressed(Input.KEY_SPACE))
        {
			touches[6] = 1;
        }
		if (input.isKeyPressed(Input.KEY_ESCAPE))
        {
			touches[7] = 1;
        }
	}
	
	public void clicksouris(){									//recupere les touches de la souris
		if (input.isKeyPressed(Input.MOUSE_LEFT_BUTTON))
        {
			touches[8] = 1;
        }
		if (input.isKeyPressed(Input.MOUSE_RIGHT_BUTTON))
        {
			touches[9] = 1;
        }
		if (input.isKeyPressed(Input.MOUSE_MIDDLE_BUTTON))
        {
			touches[10] = 1;
        }
	}
	
	public void deplacementsouris(){							//...
		touches[11] = input.getAbsoluteMouseX();
		touches[12] = input.getAbsoluteMouseY();
	} 
	
	public int pad(){											//recupere les touches du pad / la manette
		return 1;
	}
	
	public boolean moa(int moaX, int moaY, int moaWidth, int moaHeight){                 //verifie si le pointeur est sur une zone. MOA = mouse over area
		if(touches[11] >= moaX && touches[11] <= moaX + moaWidth && touches[12] >= moaY && touches[12]<= moaY + moaHeight){
    		return true;
    	}
		else{
			return false;	
		}
		
	}
	
	public int[] getTouches() {
		return touches;
	}
}
