import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 ** C'est l'état pricipal du jeu, c'est ici que l'on codera l'action du jeu !
 */
public class GameState extends BasicGameState{
	
	private Image barre1;
	private Image barre2;
	private int selection;
	private Input input;
	
    public static final int ID = 1;
    @Override
    public int getID() {return ID;}

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        barre1 = new Image ("images/menu/10.png");	//chargement de la barre de base
        barre2 = new Image ("images/menu/11.png");	//chargement de la barre de selection
        selection = 1;
        input = container.getInput();
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	switch(selection){
    	case 1 :
    			barre2.draw(150,150);//barre campagne selectioné
    			barre1.draw(150,300);//barre multiplayer
    			barre1.draw(150,450);//barre options
    			barre1.draw(150,600);//barre quitter
    			break;
    	case 2 :
    			barre1.draw(150,150); 
				barre2.draw(150,300);
				barre1.draw(150,450);
    			barre1.draw(150,600);
    			break;
    	case 3 :
	    		barre1.draw(150,150);
				barre1.draw(150,300);
				barre2.draw(150,450);
				barre1.draw(150,600);
				break;
    	case 4 :
	    		barre1.draw(150,150); 
				barre1.draw(150,300);
				barre1.draw(150,450);
				barre2.draw(150,600);
    			break;
    	default :
    			barre2.draw(150,150);
				barre1.draw(150,300);
				barre1.draw(150,450);
    			barre1.draw(150,600);
    			break;
    	}

        g.drawString("Campagne", 240, 170);
        g.drawString("Multijoueur", 240, 320);
        g.drawString("Options", 240, 470);
        g.drawString("Quitter", 240, 620);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    	 if (input.isKeyPressed(Input.KEY_DOWN))
         {
            if(selection == 4){
            	selection=1;
            }
            else{
            	selection++;
            }
         }
    	 if (input.isKeyPressed(Input.KEY_UP))
         {
            if(selection == 1){
            	selection=4;
            }
            else{
            	selection--;
            }
         }
    	 if (input.isKeyPressed(Input.KEY_ENTER))
         {
    		 switch(selection){
    	    	case 1 :
    	    			
    	    			break;
    	    	case 2 :
    	    			
    	    			break;
    	    	case 3 :
    	    			
    					break;
    	    	case 4 :
    	    			System.exit(0);
    	    			break;
    	    	default :
    	    			
    	    			break;
    	    	}
         }
    }

}