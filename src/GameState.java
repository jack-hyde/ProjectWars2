
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 ** C'est l'état pricipal du jeu, c'est ici que l'on codera l'action du jeu !
 */
public class GameState extends BasicGameState{

    public static final int ID = 1;
    @Override
    public int getID() {return ID;}

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        //vide
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Hello World", 100, 50);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        //vide
    }

}