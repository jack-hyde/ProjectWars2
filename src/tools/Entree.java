package tools;

import java.util.HashMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Entree {
	
	private Input input;
	
	HashMap<String, Integer> touches = new HashMap<String, Integer>();
	
	public Entree(GameContainer container){
		input = container.getInput();
	}
	
	public void clavier(){										//recupere les touches du clavier
		if (this.input.isKeyPressed(Input.KEY_A))
			touches.put("A", Constantes.KEY_PRESSED);
		else if(this.input.isKeyDown(Input.KEY_A))
			touches.put("A", Constantes.KEY_DOWN);
		else
			touches.put("A", Constantes.KEY_VOID);
		
		if (input.isKeyPressed(Input.KEY_Z))
			touches.put("Z", Constantes.KEY_PRESSED);
		else if(this.input.isKeyDown(Input.KEY_Z))
			touches.put("Z", Constantes.KEY_DOWN);
		else
			touches.put("Z", Constantes.KEY_VOID);
		
		if (input.isKeyPressed(Input.KEY_E))
			touches.put("E", Constantes.KEY_PRESSED);
		else if(this.input.isKeyDown(Input.KEY_E))
			touches.put("E", Constantes.KEY_DOWN);
		else
			touches.put("E", Constantes.KEY_VOID);
		
		if (input.isKeyPressed(Input.KEY_Q))
			touches.put("Q", Constantes.KEY_PRESSED);
		else if(this.input.isKeyDown(Input.KEY_Q))
			touches.put("Q", Constantes.KEY_DOWN);
		else
			touches.put("Q", Constantes.KEY_VOID);
		
		if (input.isKeyPressed(Input.KEY_S))
			touches.put("S", Constantes.KEY_PRESSED);
		else if(this.input.isKeyDown(Input.KEY_S))
			touches.put("S", Constantes.KEY_DOWN);
		else
			touches.put("S", Constantes.KEY_VOID);
		
		
		if (input.isKeyPressed(Input.KEY_D))
			touches.put("D", Constantes.KEY_PRESSED);
		else if(this.input.isKeyDown(Input.KEY_D))
			touches.put("D", Constantes.KEY_DOWN);
		else
			touches.put("D", Constantes.KEY_VOID);
		
		if (input.isKeyPressed(Input.KEY_SPACE))
			touches.put("SPACE", Constantes.KEY_PRESSED);
		else if(this.input.isKeyDown(Input.KEY_SPACE))
			touches.put("SPACE", Constantes.KEY_DOWN);
		else
			touches.put("SPACE", Constantes.KEY_VOID);
		
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			touches.put("ESCAPE", Constantes.KEY_PRESSED);
		else if(this.input.isKeyDown(Input.KEY_ESCAPE))
			touches.put("ESCAPE", Constantes.KEY_DOWN);
		else
			touches.put("ESCAPE", Constantes.KEY_VOID);
	}
	
	public void clicksouris(){									//recupere les touches de la souris
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			touches.put("MOUSE_LEFT", Constantes.KEY_PRESSED);
		else if(this.input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			touches.put("MOUSE_LEFT", Constantes.KEY_DOWN);
		else
			touches.put("MOUSE_LEFT", Constantes.KEY_VOID);
		
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
			touches.put("MOUSE_RIGHT", Constantes.KEY_PRESSED);
		else if(this.input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON))
			touches.put("MOUSE_RIGHT", Constantes.KEY_DOWN);
		else
			touches.put("MOUSE_RIGHT", Constantes.KEY_VOID);
		
		if (input.isMousePressed(Input.MOUSE_MIDDLE_BUTTON))
			touches.put("MOUSE_MIDDLE", Constantes.KEY_PRESSED);
		else if(this.input.isMouseButtonDown(Input.MOUSE_MIDDLE_BUTTON))
			touches.put("MOUSE_MIDDLE", Constantes.KEY_DOWN);
		else
			touches.put("MOUSE_MIDDLE", Constantes.KEY_VOID);
	}
	
	public void rollsouris(){									//recupere la roulette de la souris
		
	}
	
	public void deplacementsouris(){	
		touches.put("MOUSE_X", input.getAbsoluteMouseX());
		touches.put("MOUSE_Y", input.getAbsoluteMouseY());
	} 
	
	public void pad(){											//recupere les touches du pad / la manette
		
	}
	
	public boolean moa(int moaX, int moaY, int moaWidth, int moaHeight){                 //verifie si le pointeur est sur une zone. MOA = mouse over area
		if(touches.get("MOUSE_X") >= moaX && touches.get("MOUSE_X") <= moaX + moaWidth && touches.get("MOUSE_Y") >= moaY && touches.get("MOUSE_Y")<= moaY + moaHeight){
    		return true;
    	}
		else{
			return false;	
		}
		
	}
	
	public HashMap<String, Integer> getTouches() {
		clavier();
		clicksouris();
		deplacementsouris();
		return touches;
	}
}
