import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 ** C'est l'état pricipal du jeu, c'est ici que l'on codera l'action du jeu !
 */
public class GameState extends BasicGameState{
	
	private int menu;				//indique dans quel menu on est
	private int menubis;
	MenuBase menuBase = new MenuBase();
	MenuOption menuOption = new MenuOption();
	
    public static final int ID = 1;
    
    @Override
    public int getID() {return ID;}
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException 
    {
    	this.menu = 0;
    	this.menubis = 0;
    	XMLPackedSheet menuImage = new XMLPackedSheet("images/menu/menu.png", "images/menu/menu.xml");		//charge le tileSet menu
    	menuBase.initMenuBase(container, game, menuImage);
    	menuOption.initMenuOption(container, game, menuImage);
    	
    }

    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException // choisi quel menu a afficher
    {
    	switch(this.menu){
    	case 4: this.menuOption.renderMenuOption(container, game, g);
    		break;
    	case 3:
    		break;
        case 2:
     	   break; 
        case 1:
     	   break;
        case 0: this.menuBase.renderMenuBase(container, game, g);
     	   break;
        default :
     	   break;
        }
    	g.drawString("menu : "+menu, 100, 40);
    }

    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException //choisi quel test faire
    {
    	switch(this.menu){
    	case 4:  this.menuOption.updateMenuOption(container, game, delta);
    		this.menubis = menuOption.getMenu();
    		break;
    	case 3:
    		break;
        case 2:
     	   break;
        case 1:
     	   break;
        case 0: this.menuBase.updateMenuBase(container, game, delta);
        	this.menubis = menuBase.getMenu();
     	   break;
        default : 
     	   break;
        }
    	this.menu = this.menubis;
    }
}