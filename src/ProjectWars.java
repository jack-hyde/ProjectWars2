import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class ProjectWars extends StateBasedGame{
    private GameState jeu;
    private AppGameContainer container;

   public ProjectWars() {
		super("Mon premier jeu");
	}

    @Override
   public void initStatesList(GameContainer container) throws SlickException {
		if (container instanceof AppGameContainer) {

			this.container = (AppGameContainer) container;// on stocke le conteneur du jeu !
		}

		jeu = new GameState();//le jeu en lui même !!
                container.setShowFPS(true);//on ne veut pas voir le FPS ?? mettre alors "false" !
		addState(jeu);	//on ajoute le GameState au conteneur !
	}


    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new ProjectWars());
             container.setDisplayMode(1280, 768, false);// fenêtre de 1280*768 fullscreen =false !!
             container.setTargetFrameRate(60);
             container.start();
        }                       //on démarre le container
        catch (SlickException e) {e.printStackTrace();}  // l'exception de base de slick !!
    }

}  // fin class