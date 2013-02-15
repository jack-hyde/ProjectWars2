import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;


public class MenuBase {
	
	private int menu;
	private boolean quitter;
	private Image barre1;
	private Image barre2;
	private Image boite;
	private int selection;
	private int touches[] = {0,0,0,0,0,0,0,0,0,0,0,0,0}; //13
	
	void initMenuBase(GameContainer container, StateBasedGame game, XMLPackedSheet menuImage)throws SlickException
	{
		this.menu = 0;
		this.quitter = false;
		this.selection = 1;
		this.barre1 = menuImage.getSprite("10.png");	//chargement de la barre de base
		this.barre2 = menuImage.getSprite("11.png");	//chargement de la barre de selection
		this.boite = menuImage.getSprite("16.png");		//chargement de la boite de dialogue
	}

	void renderMenuBase(GameContainer container, StateBasedGame game, Graphics g)//il faut afficher les objets dans le bon ordre fond > boites > texte ou sinon le texte est couvert par les images ect..
	{
		this.barre1.draw(200,130);//barre campagne 
		this.barre1.draw(200,230);//barre multiplayer
		this.barre1.draw(200,330);//barre editeur
		this.barre1.draw(200,430);//barre options
		this.barre1.draw(200,530);//barre quitter
		

		//affiche la barre selectionné
    	switch(this.selection){
    	case 1 :
    		this.barre2.draw(200,130);
    		break;
    	case 2 :
    		this.barre2.draw(200,230);
    		break;
    	case 3 :
    		this.barre2.draw(200,330);
			break;
    	case 4 :
    		this.barre2.draw(200,430);
    		break;
    	case 5 :
    		this.barre2.draw(200,530);
    		break;
    	case 6 :
    		this.barre2.draw(100,630);
    		break;
    	default :
    		break;
    	}
        
    	//affiche le texte
        g.drawString("Campagne", 280, 145);
        g.drawString("Multijoueur", 280, 245);
        g.drawString("Editeur", 280, 345);
        g.drawString("Options", 280, 445);
        g.drawString("Quitter", 280, 545);
        g.drawString("?", 100, 750);
        
      
    	//affiche la boite pour quitter
        if(quitter)
        {	
        	this.boite.draw(500,350);//barre oui
        	this.barre1.draw(520,400);//barre oui
    		this.barre1.draw(800,400);//barre non
        	switch(selection)
        	{
        	case 7 :
        		this.barre2.draw(520,400);
        		break;
        	case 8 :
        		this.barre2.draw(800,400);
        		break;
        	default :
        		break;
        	}
    		g.drawString("Voulez vous quitter ?", 660, 360);
    		g.drawString("OUI", 620, 415);
            g.drawString("NON", 900, 415);
        }
        
        //touches appuyés et position de la souris en haut a gauche de l'ecran  a supprimer
        int u = 0;
        for(int i=0; i<touches.length; i++)
        {  	
        	g.drawString("i : "+touches[i], 10+u, 60);
        	u = u+70;
        }
	}
	
	void updateMenuBase(GameContainer container, StateBasedGame game, int delta)
	{
		//recupere les touches
		Entree entree = new Entree(container);
    	touches = entree.getTouches();		
    	
    	//test sur les touches
    	if(this.touches[4] == 1)//appuis sur S
    	{
    		if(this.selection == 5){
    			this.selection=1;
    		}
    		else{
    			this.selection++;
    		}
    	}
    	if(this.touches[1] == 1)//appuis sur Z
    	{
    		if(this.selection == 1){
    			this.selection=5;
    		}
    		else{
    			this.selection--;
    		}
    	}
    	if(this.touches[3] == 1)//appuis sur Q
    	{
    		this.selection=6;
    	}
    	if(touches[5] == 1)//appuis sur D
    	{
    		this.selection=5;
    	}
    	if(this.touches[6] == 1 || this.touches[8] == 1)//appuis sur espace ou click gauche
    	{
    		switch(this.selection){
    		case 1 :
    			//on passe dans le menu solo
    			break;
    		case 2 :
    			//on passe dans le menu multi
    			break;
    		case 3 :
				//on passe dans l'editeur
    			break;
    		case 4 :
    			//on passe dans le menu option
    			this.menu = 4;
    			break;
    		case 5 :
    			this.quitter = true;
    			break;
    		case 6 :
    			//on passe dans le menu aide
    			break;
    		default :
    			break;
    		}
    	}
    	
    	//moa verifie si le pointeur est sur un rectangle
    	if(entree.moa(200, 130, 250, 50))
    	{
    		this.selection=1;
    	}
    	if(entree.moa(200, 230, 250, 50))
    	{
    		this.selection=2;
    	}
    	if(entree.moa(200, 330, 250, 50))
    	{
    		this.selection=3;
    	}
    	if(entree.moa(200, 430, 250, 50))
    	{
    		this.selection=4;
    	}
    	if(entree.moa(200, 530, 250, 50))
    	{
    		this.selection=5;
    	}
    	if(entree.moa(100, 750, 10, 10))
    	{
    		this.selection=6;
    	}
    	
    	//moa pour boite quitter
    	if (quitter)
    	{
    		if(entree.moa(520, 400, 250, 50))
        	{
        		this.selection = 7;
        		if(this.touches[6] == 1 || this.touches[8] == 1)//appuis sur espace ou click gauche
            	{
        			System.exit(0);
            	}
        	}
        	if(entree.moa(800, 400, 250, 50))
        	{
        		this.selection = 8;
        		if(this.touches[6] == 1 || this.touches[8] == 1)//appuis sur espace ou click gauche
            	{
        			this.quitter = false;
            	}
        	}
    	}
	}
	
	int getMenu()
	{
		return this.menu;
	}
	
	void setMenu(int menubis)
	{
		this.menu = menubis;
	}
}
