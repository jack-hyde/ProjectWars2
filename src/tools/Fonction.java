package tools;

import java.util.ArrayList;

public abstract class Fonction {

	public final static ArrayList<String> calculRayonDeplacement(int departX, int departY, int mapWidth, int mapHeight, int rayon)
	{
		ArrayList<String> coordValides = new ArrayList<String>();

		int cheminX = 0;
		int cheminY = 0;
		int cheminX2 = 0;
		int cheminY2 = 0;
		int cheminX3 = 0;
		int cheminY3 = 0;
		int cheminX4 = 0;
		int cheminY4 = 0;
		int cheminX5 = 0;
		int cheminY5 = 0;
		int cheminX6 = 0;
		int cheminY6 = 0;
		int cheminX7 = 0;
		int cheminY7 = 0;

		for(int x1 = -1; x1 <= 1; x1++)
		{
			for(int y1 = -1; y1 <= 1; y1++)
			{
				if(Math.abs(x1) != Math.abs(y1))
				{
					if(departX + x1 != 7 && departY + y1 != 3 && departX + x1 >= 0 && departY + y1 >= 0 && departX + x1 <= mapWidth && departY + y1 <= mapHeight)//enleve les cases qui sortent de la carte et les case occupé par l'ennemi et les batiments
					{
						cheminX = departX + x1;
						cheminY = departY + y1;
						boolean existe = false;
						for(String s : coordValides)
						{
							if(s.equals(cheminX+":"+cheminY))
							{
								existe = true;
							}   
						}
						if(existe == false)
						{
							coordValides.add(cheminX+":"+cheminY);
						}
					}
					if(rayon > 1)
					{
						for(int x2 = -1; x2 <= 1; x2++)
						{
							cheminX2 = cheminX;
							for(int y2 = -1; y2 <= 1; y2++)
							{
								cheminY2 = cheminY;
								if(Math.abs(x2) != Math.abs(y2))
								{
									if(cheminX2 + x2 != 7 && cheminY2 + y2 != 3 && cheminX2 + x2 >= 0 && cheminY2 + y2 >= 0 && cheminX2 + x2 <= mapWidth && cheminY2 + y2 <= mapHeight) //enleve les cases qui sortent de la carte et les case occupé par l'ennemi et les batiments
									{
										cheminX3 = cheminX2 + x2;
										cheminY3 = cheminY2 + y2;
										boolean existe = false;
										for(String s : coordValides)
										{
											if(s.equals(cheminX3+":"+cheminY3))
											{
												existe = true;
											}   
										}
										if(existe == false)
										{
											coordValides.add(cheminX3+":"+cheminY3);
										}
										if(rayon > 2)
										{
											for(int x3 = -1; x3 <= 1; x3++)
											{
												cheminX4 = cheminX3;
												for(int y3 = -1; y3 <= 1; y3++)
												{
													cheminY4 = cheminY3;
													if(Math.abs(x3) != Math.abs(y3))
													{
														if(cheminX4 + x3 != 7 && cheminY4 + y3 != 3 && cheminX4 + x3 >= 0 && cheminY4 + y3 >= 0 && cheminX4 + x3 <= mapWidth && cheminY4 + y3 <= mapHeight) //enleve les cases qui sortent de la carte et les case occupé par l'ennemi et les batiments
														{
															cheminX5 = cheminX4 + x3;
															cheminY5 = cheminY4 + y3;
															boolean existe2 = false;
															for(String s : coordValides)
															{
																if(s.equals(cheminX5+":"+cheminY5))
																{
																	existe2 = true;
																}   
															}
															if(existe2 == false)
															{
																coordValides.add(cheminX5+":"+cheminY5);
															}
															if(rayon > 3)
															{
																for(int x4 = -1; x4 <= 1; x4++)
																{
																	cheminX6 = cheminX5;
																	for(int y4 = -1; y4 <= 1; y4++)
																	{
																		cheminY6 = cheminY5;
																		if(Math.abs(x4) != Math.abs(y4))
																		{
																			if(cheminX6 + x4 != 7 && cheminY6 + y4 != 3 && cheminX6 + x4 >= 0 && cheminY6 + y4 >= 0 && cheminX6 + x4 <= mapWidth && cheminY6 + y4 <= mapHeight) //enleve les cases qui sortent de la carte et les case occupé par l'ennemi et les batiments
																			{
																				cheminX7 = cheminX6 + x4;
																				cheminY7 = cheminY6 + y4;
																				boolean existe3 = false;
																				for(String s : coordValides)
																				{
																					if(s.equals(cheminX7+":"+cheminY7))
																					{
																						existe3 = true;
																					}   
																				}
																				if(existe3 == false)
																				{
																					coordValides.add(cheminX7+":"+cheminY7);
																				}
																			}
																		}
																	}          
																}
															}
														}
													}
												}          
											}
										}
									}
								}
							}          
						}
					}					
				}   
			}
		} 
		return coordValides; 
	}
}	
/*
for(int x=0; x < xMax; x++)
{
	for(int y=0; y< yMax; y++)
	{
		if(x <= caseX+rayon && x >= caseX-rayon && y <= caseY+rayon && y >= caseY-rayon && Math.abs(x-caseX)+Math.abs(y-caseY) <= rayon)
		{
			coordValides.add(x+":"+y);
		}
	}
}		
return coordValides;*/