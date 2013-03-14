package model;


import states.Partie;

public abstract class PhaseModel {

	protected Partie partie;
	protected int selectionX;
	protected int selectionY;
	protected int caseX;
	protected int caseY;
	protected boolean viewIHMBas; 
	protected int delta;
	
	public PhaseModel(Partie partie)
	{
		this.partie = partie;
		this.viewIHMBas = true;
	}
	
	public boolean isViewIHMBas() {
		return viewIHMBas;
	}

	public void setViewIHMBas(boolean viewIHMBas) {
		this.viewIHMBas = viewIHMBas;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public int getSelectionX() {
		return selectionX;
	}

	public void setSelectionX(int selectionX) {
		this.selectionX = selectionX;
	}

	public int getSelectionY() {
		return selectionY;
	}

	public void setSelectionY(int selectionY) {
		this.selectionY = selectionY;
	}

	public int getCaseX() {
		return caseX;
	}

	public void setCaseX(int caseX) {
		this.caseX = caseX;
	}

	public int getCaseY() {
		return caseY;
	}

	public void setCaseY(int caseY) {
		this.caseY = caseY;
	}
}
