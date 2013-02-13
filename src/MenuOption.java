import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;


public class MenuOption {
	
	private int menu;
	private int selection;
	private int fleche;
	private Image barre1;
	private Image barre2;
	private Image fGauche;
	private Image fGaucheRed;
	private Image fDroite;
	private Image fDroiteRed;
	private int touches[] = {0,0,0,0,0,0,0,0,0,0,0,0,0}; //13
	
	void initMenuOption(GameContainer container, StateBasedGame game, XMLPackedSheet menuImage)throws SlickException
	{
		this.menu = 4;
		this.selection = 1;
		this.barre1 = menuImage.getSprite("10.png");		//chargement de la barre de base
		this.barre2 = menuImage.getSprite("11.png"); 		//chargement de la barre de selection
		this.fGauche = menuImage.getSprite("12.png");		//chargement de la fleche gauche
		this.fGaucheRed = menuImage.getSprite("14.png");	//chargement de la fleche gauche rouge
		this.fDroite = menuImage.getSprite("13.png");		//chargement de la fleche droite
		this.fDroiteRed = menuImage.getSprite("15.png");	//chargement de la fleche droite rouge
	}

	void renderMenuOption(GameContainer container, StateBasedGame game, Graphics g)
	{
		this.barre1.draw(200,130);//barre resolution
		this.barre1.draw(200,230);//barre plein ecran
		this.barre1.draw(200,330);//barre Volume general
		this.barre1.draw(200,430);//barre Volume effet
		this.barre1.draw(200,530);//barre Volume musique
		this.barre1.draw(200,630);//barre valider
		this.barre1.draw(500,630);//barre annuler
		
		this.fGauche.draw(500,130);//fleche gauche
		this.fDroite.draw(750,130);//fleche droite barre resolution
		
		this.fGauche.draw(500,230);
		this.fDroite.draw(750,230);
		
		this.fGauche.draw(500,330);
		this.fDroite.draw(750,330);
		
		this.fGauche.draw(500,430);
		this.fDroite.draw(750,430);
		
		this.fGauche.draw(500,530);
		this.fDroite.draw(750,530);
		
		
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
    		this.barre2.draw(200,630);
    		break;
    	case 7 :
    		this.barre2.draw(500,630);
    		break;
    	default :
    		break;
    	}
    	
    	
    	//affiche la fleche selectionné
		switch(this.fleche){
    	case 1 :
    		this.fGaucheRed.draw(500,130);
    		break;
    	case 2 :
    		this.fDroiteRed.draw(750,130);
    		break;
    	case 3 :
    		this.fGaucheRed.draw(500,230);
			break;
    	case 4 :
    		this.fDroiteRed.draw(750,230);
    		break;
    	case 5 :
    		this.fGaucheRed.draw(500,330);
    		break;
    	case 6 :
    		this.fDroiteRed.draw(750,330);
    		break;
    	case 7 :
    		this.fGaucheRed.draw(500,430);
    		break;
    	case 8 :
    		this.fDroiteRed.draw(750,430);
    		break;
    	case 9 :
    		this.fGaucheRed.draw(500,530);
    		break;
    	case 10 :
    		this.fDroiteRed.draw(750,530);
    		break;
    	default :
    		break;
    	}
    	
		//affiche le texte
        g.drawString("Resolution", 280, 145);
        g.drawString("plein ecran", 280, 245);
        g.drawString("Volume general", 280, 345);
        g.drawString("Volume effet", 280, 445);
        g.drawString("Volume musique", 280, 545);
        g.drawString("Valider", 280, 645);
        g.drawString("Annuler", 580, 645);
        
        
        
        //touches appuyés et position de la souris en haut a gauche de l'ecran  a supprimer
        int u = 0;
        for(int i=0; i<touches.length; i++)
        {  	
        	g.drawString("i : "+touches[i], 10+u, 60);
        	u = u+70;
        }
	}

	void updateMenuOption(GameContainer container, StateBasedGame game, int delta)
	{
		//recupere les touches
		Entree entree = new Entree(container);
    	touches = entree.getTouches();		
    	
    	//test sur les touches
    	if(this.touches[4] == 1)//appuis sur S
    	{
    		if(this.selection == 7){
    			this.selection=1;
    			this.fleche=0;
    		}
    		else{
    			this.selection++;
    			this.fleche=0;
    		}
    	}
    	if(this.touches[1] == 1)//appuis sur Z
    	{
    		if(this.selection == 1){
    			this.selection=7;
    			this.fleche=0;
    		}
    		else{
    			this.selection--;
    			this.fleche=0;
    		}
    	}
    	if(this.touches[3] == 1)//appuis sur Q
    	{
    		this.fleche--;
    	}
    	if(this.touches[5] == 1)//appuis sur D
    	{
    		this.fleche++;
    	}
    	if(this.touches[6] == 1)//appuis sur espace
    	{
    		switch(this.selection){
    		case 1 :
    			break;
    		case 2 :
    			break;
    		case 3 :
    			break;
    		case 4 :   			
    			break;
    		case 5 :
    			break;
    		case 6 :
    			//on sauvegarde et on retourne dans le menu principal
    			this.menu = 0;
    			break;
    		case 7 :
    			//on ne sauvegarde pas et retourne dans le menu principal
    			this.menu = 0;
    			break;
    		default :

    			break;
    		}
    	}
    	
    	//moa verifie si le pointeur est sur un rectangle
    	//les barres
    	if(entree.moa(200, 130, 250, 50))//barre resolution 
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
    	if(entree.moa(200, 630, 250, 50))
    	{
    		this.selection=6;
    	}
    	if(entree.moa(500, 630, 250, 50))
    	{
    		this.selection=7;
    	}
    	//les fleches
    	if(entree.moa(500, 130, 50, 50))
    	{
    		this.fleche=1;
    		this.selection=1;
    	}
    	if(entree.moa(750, 130, 50, 50))
    	{
    		this.fleche=2;
    		this.selection=1;
    	}
    	if(entree.moa(500, 230, 50, 50))
    	{
    		this.fleche=3;
    		this.selection=2;
    	}
    	if(entree.moa(750, 230, 50, 50))
    	{
    		this.fleche=4;
    		this.selection=2;
    	}
    	if(entree.moa(500, 330, 50, 50))
    	{
    		this.fleche=5;
    		this.selection=3;
    	}
    	if(entree.moa(750, 330, 50, 50))
    	{
    		this.fleche=6;
    		this.selection=3;
    	}
    	if(entree.moa(500, 430, 50, 50))
    	{
    		this.fleche=7;
    		this.selection=4;
    	}
    	if(entree.moa(750, 430, 50, 50))
    	{
    		this.fleche=8;
    		this.selection=4;
    	}
    	if(entree.moa(500, 530, 50, 50))
    	{
    		this.fleche=9;
    		this.selection=5;
    	}
    	if(entree.moa(750, 530, 50, 50))
    	{
    		this.fleche=10;
    		this.selection=5;
    	}
	}
	
	int getMenu()
	{
		return this.menu;
	}
		
	
}
