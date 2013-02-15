import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class ProjectWars extends StateBasedGame{
    private GameState jeu;
    private AppGameContainer container;

    public static final int MAINMENUSTATE          = 0;
    public static final int GAMEPLAYSTATE          = 1;
    
   public ProjectWars() {
		super("Mon premier jeu");
	}

    @Override
   public void initStatesList(GameContainer container) throws SlickException {
		if (container instanceof AppGameContainer) {

			this.container = (AppGameContainer) container;// on stocke le conteneur du jeu !
		}

        container.setShowFPS(true);//on ne veut pas voir le FPS ?? mettre alors "false" !
        
		this.addState(new GameState(MAINMENUSTATE)); //RENOMMER LA CLASS EN MENU
        this.addState(new Partie(GAMEPLAYSTATE));
	}


    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new ProjectWars());
             container.setDisplayMode(1300, 800, false);// fenêtre de 1280*768 fullscreen = true !!
             container.setVSync(true);  
             container.setMultiSample(64);  
             container.setTargetFrameRate(60);  
             container.setVerbose(true);  
             container.start();
        }                       //on démarre le container
        catch (SlickException e) {e.printStackTrace();}  // l'exception de base de slick !!
    }

}  // fin class