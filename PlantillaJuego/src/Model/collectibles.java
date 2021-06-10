package Model;

import processing.core.PApplet;
import processing.core.PImage;

public class collectibles implements Runnable{

	private PApplet app;

	private PImage yarn;

	private int posXYarn;
	private int posYYarn;
	private int moveX;
	private boolean moveXYarn;

	public collectibles(PApplet app , int posXYarn, int posYYarn) {

		this.app=app;

		this.posXYarn=posXYarn;
		this.posYYarn=posYYarn;
		
		moveXYarn= false;

		yarn=app.loadImage("../data/yarn.png");
	} //CONSTRUCTOR

	public void draw () {

		app.image(yarn, posXYarn, posYYarn);	

	}
	
	public void moveYarn() {
		if (moveXYarn == true) {
			posXYarn = posXYarn - 15;
		}	
	}
	
	

	public int getposXYarn () {
		return posXYarn;
	}
	public void setPosXYarn () {
		this.posXYarn = posXYarn;
	}
	public int getposYYarn () {
		return posYYarn;
	}
	
	public void setPosYYarn () {
		this.posYYarn = posYYarn;
	}

	public boolean isMoveXStar() {
		return moveXYarn;
	}

	public void setMoveXYarn(boolean moveXYarn) {
		this.moveXYarn = moveXYarn;
	}

	@Override
	public void run() {
		moveYarn();
	}
	
	

} 
