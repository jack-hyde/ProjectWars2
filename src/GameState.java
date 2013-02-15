import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 ** C'est l'état pricipal du jeu, c'est ici que l'on codera l'action du jeu !
 */
public class GameState extends BasicGameState{
	
	private Image barre1;
	private Image barre2;
	private Image fond;
	private Image selec;
	private int selection;
	private boolean solo;
	private boolean multi;
	private boolean editeur;
	private boolean options;
	private boolean aide;
	private int touches[] = {0,0,0,0,0,0,0,0,0,0,0,0,0}; //13
	
    public int stateID;
    
    @Override
    public int getID() {return stateID;}

    public GameState(int id)
    {
    	this.stateID = id;
    }
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        barre1 = new Image ("images/menu/10.png");	//chargement de la barre de base
        barre2 = new Image ("images/menu/13.png");	//chargement de la barre de selection
       // fond = new Image ("images/menu/fond.png");
        selec = new Image ("images/menu/14.png");
        selection = 1;
        solo = false;
        multi = false;
        editeur = false;
        options = false;
        aide = false;
        
    }

    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	//fond.draw(0,0);
    	//barre1.draw(200,130);//barre campagne selectioné
		//barre1.draw(200,268);//barre multiplayer
		//barre1.draw(200,406);//barre options
		//barre1.draw(200,544);//barre quitter
		//barre1.draw(200,682);
    	switch(selection){
    	case 1 :
    		selec.draw(152,190);
    		break;
    	case 2 :
    		selec.draw(152,328);
    		break;
    	case 3 :
    		selec.draw(152,466);
			break;
    	case 4 :
    		selec.draw(152,604);
    		break;
    	case 5 :
    		selec.draw(152,742);
    		break;
    	case 6 :
    		selec.draw(100,742);
    		break;
    	default :
    		break;
    	}

        g.drawString("Campagne", 270, 150);
        g.drawString("Multijoueur", 270, 300);
        g.drawString("Editeur", 270, 450);
        g.drawString("Options", 270, 600);
        g.drawString("Quitter", 270, 750);
        g.drawString("?", 100, 750);
        
        //touches appuyés et position de la souris en haut a gauche de l'ecran  a supprimer
        int u = 0;
        for(int i=0; i<touches.length; i++)
        {  	
        	g.drawString("i : "+touches[i], 10+u, 60);
        	u = u+70;
        }
    }

    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    	Entree entree = new Entree(container);
    	touches = entree.getTouches();		//recupere les touches
    	
    	if(touches[4] == 1)//appuis sur S
    	{
    		if(selection == 5){
    			selection=1;
    		}
    		else{
    			selection++;
    		}
    	}
    	if(touches[1] == 1)//appuis sur Z
    	{
    		if(selection == 1){
    			selection=5;
    		}
    		else{
    			selection--;
    		}
    	}
    	if(touches[3] == 1)//appuis sur Q
    	{
    		selection=6;
    	}
    	if(touches[5] == 1)//appuis sur D
    	{
    		selection=5;
    	}
    	if(touches[6] == 1)//appuis sur espace
    	{
    		switch(selection){
    		case 1 :
    			solo = true;
    			System.out.println("test");//on passe dans le menu solo
    			game.enterState(ProjectWars.GAMEPLAYSTATE);
    			break;
    		case 2 :
    			multi = true;//on passe dans le menu multi
    			break;
    		case 3 :
				editeur = true;//on passe dans l'editeur
    			break;
    		case 4 :
    			options = true;//on passe dans le menu option
    			break;
    		case 5 :
    			System.exit(0);
    			break;
    		case 6 :
    			aide = true;//on passe dans le menu aide
    			break;
    		default :

    			break;
    		}
    	}
    	if(entree.moa(150, 150, 250, 58))//moa verifie si le pointeur est sur un rectangle
    	{
    		selection=1;
    	}
    	if(entree.moa(150, 300, 250, 58))
    	{
    		selection=2;
    	}
    	if(entree.moa(150, 450, 250, 58))
    	{
    		selection=3;
    	}
    	if(entree.moa(150, 600, 250, 58))
    	{
    		selection=4;
    	}
    	if(entree.moa(150, 750, 250, 58))
    	{
    		selection=5;
    		
    		if(touches[8]==1)
    		{
    			System.exit(0);
    		}
    	}
    	if(entree.moa(100, 750, 10, 10))
    	{
    		selection=6;
    	}
    }
}